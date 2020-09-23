package com.ning;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class ExamNingAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingAuthApplication.class, args);
        System.out.println("exam-ning-springcloud-auth start success !!!");
    }

}
