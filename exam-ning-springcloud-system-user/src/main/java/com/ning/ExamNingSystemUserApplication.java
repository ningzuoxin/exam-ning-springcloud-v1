package com.ning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ExamNingSystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingSystemUserApplication.class, args);
        log.info("exam-ning-springcloud-system-user start success !!!");
    }

}
