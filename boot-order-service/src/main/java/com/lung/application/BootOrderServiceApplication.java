package com.lung.application;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@EnableDubbo
@SpringBootApplication
public class BootOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootOrderServiceApplication.class, args);
    }

}

