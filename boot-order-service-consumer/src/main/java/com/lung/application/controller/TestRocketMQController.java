package com.lung.application.controller;

import com.lung.application.rocketmq.RocketMQConsumer;
import com.lung.application.rocketmq.RocketMQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRocketMQController {
    @Autowired
    RocketMQProvider rocketMQProvider;

    @Autowired
    RocketMQConsumer rocketMQConsumer;

    @RequestMapping("/testMQ")
    public String testMq() {

        try {
            rocketMQProvider.defaultMQProducer();
//            rocketMQConsumer.defaultMQPushConsumer();
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
