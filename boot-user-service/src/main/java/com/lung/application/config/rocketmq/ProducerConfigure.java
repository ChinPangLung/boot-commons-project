package com.lung.application.config.rocketmq;

import com.lung.application.config.beanconfig.ProducerConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ProducerConfigure
 * @Author: chinpanglung
 * @Date: 19-5-29 下午3:53
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Log4j2
@Configuration
public class ProducerConfigure {

    @Autowired
    private ProducerConfig producerConfig;

    /**
     * 创建普通消息发送者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "default", havingValue = "true")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        log.info(producerConfig.toString());
        log.info("DefaultMQProducer 正在创建=======================================");
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server 开启成功================================");
        return producer;
    }

}
