package com.ning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ExamNingSystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingSystemUserApplication.class, args);
        log.info("exam-ning-springcloud-system-user start success !!!");
    }

    /**
     * 跨域请求过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    /**
     * 跨域请求配置
     *
     * @return
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // * 表示对所有的地址都可以访问
        corsConfiguration.addAllowedOrigin("*");
        // 跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        // 跨域的请求方法
        corsConfiguration.addAllowedMethod("*");
        // 大致意思是可以携带验证信息 cookie、token等等
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

}
