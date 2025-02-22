package com.ning.infrastructure.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class RequestLoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingGlobalFilter.class);
    private static final String LOG_FORMAT = "%-15s: %s";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();

        // 打印 URL 和方法
        logInfo("URL", uri.toString());
        logInfo("Method", request.getMethod().name());

        // 打印 Query Params
        Map<String, String> queryParams = request.getQueryParams().toSingleValueMap();
        logInfo("Query Params", queryParams.toString());

        // 打印 Headers
        HttpHeaders headers = request.getHeaders();
        logInfo("Headers", headers.toSingleValueMap().toString());

        // 检查是否为文件上传请求
        if (isMultipartRequest(headers)) {
            logInfo("Request Type", "File Upload");
            // 如果是文件上传请求，只记录文件名称和大小
            return logFileUploadRequest(request, exchange, chain);
        } else {
            // 普通请求，记录请求体
            return logRequestBody(request, exchange, chain);
        }
    }

    @Override
    public int getOrder() {
        return -1; // 确保过滤器优先级最高
    }

    // 判断是否为文件上传请求
    private boolean isMultipartRequest(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        return contentType != null && contentType.includes(MediaType.MULTIPART_FORM_DATA);
    }

    // 记录文件上传请求
    private Mono<Void> logFileUploadRequest(ServerHttpRequest request, ServerWebExchange exchange, GatewayFilterChain chain) {
        return request.getBody()
                .collectList()
                .flatMap(dataBuffers -> {
                    long contentLength = request.getHeaders().getContentLength();
                    logInfo("File Size", contentLength + " bytes");

                    // 重新包装请求，确保后续处理器可以读取请求体
                    ServerHttpRequestDecorator wrappedRequest = new ServerHttpRequestDecorator(request) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return Flux.fromIterable(dataBuffers);
                        }
                    };

                    ServerWebExchange mutatedExchange = exchange.mutate().request(wrappedRequest).build();
                    return chain.filter(mutatedExchange);
                });
    }

    // 记录普通请求的请求体
    private Mono<Void> logRequestBody(ServerHttpRequest request, ServerWebExchange exchange, GatewayFilterChain chain) {
        return DataBufferUtils.join(request.getBody())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer); // 释放缓冲区
                    String requestBody = new String(bytes, StandardCharsets.UTF_8);
                    logInfo("Request Body", requestBody);

                    // 重新包装请求，确保后续处理器可以读取请求体
                    ServerHttpRequestDecorator wrappedRequest = new ServerHttpRequestDecorator(request) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return Flux.just(exchange.getResponse().bufferFactory().wrap(bytes));
                        }
                    };

                    ServerWebExchange mutatedExchange = exchange.mutate().request(wrappedRequest).build();
                    return chain.filter(mutatedExchange);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // 如果请求体为空，直接继续过滤器链
                    return chain.filter(exchange);
                }));
    }

    // 格式化日志输出
    private void logInfo(String key, String value) {
        logger.info(String.format(LOG_FORMAT, key, value));
    }
}
