package com.ning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 考试服务启动入口
 *
 * @author zuoxin.ning
 * @since 2024-12-02 09:30
 */
@Slf4j
@EnableFeignClients
@SpringCloudApplication
public class ExamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamServiceApplication.class, args);
        log.info("exam service run success !!!");
    }

}
