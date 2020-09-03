package com.ning;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ExamNingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingGatewayApplication.class, args);
        System.out.println("exam-ning-gateway start success !!!");
    }

}
