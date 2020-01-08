package com.lung.application;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * spingboot整合dubbo三种方式
 * 1、 通过properties（yml）配置文件，使用注解的方式来 配置启动dubbo 注解的相关配置不能精确到方法级 @EnableDubbo
 *
 * 2、 保留dubbo.xml配置文件的方式 在resources中新建xml配置文件 @ImportResource(locations = "classpath:xxxx.xml")
 *     主类启动方法使用@ImportResource(locations=“classpath：XXXX.xml”) 相关的属性配置能够精确到方法级别
 *
 * 3、 配置API的方式（其实就是使用java注解替代xml配置文件） @DubboComponentScan(basePackages = "com.lung.application")
 *     见config/DubboConfig
 *
 */
@EnableDubbo
//@ImportResource(locations = "classpath:xxxx.xml")
//@DubboComponentScan(basePackages = "com.lung.application")
@SpringBootApplication
@EnableScheduling //定时任务注解
public class BootUserServiceApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BootUserServiceApplication.class, args);
    }

//    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        taskScheduler.setPoolSize(10);
        //线程名称的前缀
        taskScheduler.setThreadNamePrefix("SpringBoot-Task");
        return taskScheduler;
    }


}

