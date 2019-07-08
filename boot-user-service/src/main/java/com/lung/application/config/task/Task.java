package com.lung.application.config.task;

import com.lung.application.kafka.producer.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//https://blog.csdn.net/fu_huo_1993/article/details/88224931

@Component
public class Task {

    @Autowired
    private UserLogProducer kafkaSender;

    /**
     * 每天12 18点执行一次
     */
    @Scheduled(cron = "0 0 12,18 * * ?")
    public void task01() {
        System.out.println(Thread.currentThread().getName() + "=============task01");
    }

    /**
     * 每天12 23点执行一次
     */
    @Scheduled(cron = "0 0 12,23 * * ?")
    public void task02() {
        System.out.println(Thread.currentThread().getName() + "=============task02");
    }

    /**
     * 每一分钟执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void task03() {
        System.out.println(Thread.currentThread().getName() + "=============task03");
        for (int i = 0; i < 10; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.sendLog(String.valueOf(i));
        }
    }
}
