package com.ning;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class ExamNingSystemExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingSystemExamApplication.class, args);
        System.out.println("exam-ning-springcloud-system-exam start success !!!");
    }

}
