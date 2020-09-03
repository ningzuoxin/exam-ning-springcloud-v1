package com.ning;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ExamNingSystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamNingSystemUserApplication.class, args);
        System.out.println("exam-ning-springcloud-system-user start success !!!");
    }

}
