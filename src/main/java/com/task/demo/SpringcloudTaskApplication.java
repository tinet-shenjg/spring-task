package com.task.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringcloudTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTaskApplication.class, args);
    }

}
