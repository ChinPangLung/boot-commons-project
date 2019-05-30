package com.lung.application.config.rocketmq;

import com.lung.application.config.beanconfig.ConsumerConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Title: DefaultConsumerConfigure
 * @Author: chinpanglung
 * @Date: 19-5-29 下午4:05
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Configuration
@Log4j2
public abstract class DefaultConsumerConfigure {

    @Autowired
    private ConsumerConfig consumerConfig;

    public void listener(String topic, String tag) throws MQClientException {
        log.info("开启" + topic + " : " + tag + "消费者=================================");
        log.info(consumerConfig.toString());
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
        consumer.subscribe(topic, tag);

        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> DefaultConsumerConfigure.this.dealBody(list));

        consumer.start();
        log.info("rocketmq启动成功===========================================");

    }

    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);
}
