package com.ning.factory.user;

import com.ning.api.user.RemoteUserIndexService;
import com.ning.model.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户服务降级处理
 */
@Slf4j
@Component
public class RemoteUserIndexFallbackFactory implements FallbackFactory<RemoteUserIndexService> {

    @Override
    public RemoteUserIndexService create(Throwable throwable) {

        log.info(" ====== RemoteUserIndexFallbackFactory # create ====== " + throwable.getMessage());

        return new RemoteUserIndexService() {
            @Override
            public Result<String> index() {
                return Result.fail("");
            }

            @Override
            public Result<Map<String, Object>> map() {
                return Result.fail("");
            }
        };
    }
}
