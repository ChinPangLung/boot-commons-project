package com.lung.application.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.lung.application.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * 定义消息的发送者
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-08 15:21
 */
@Component
@Slf4j
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String userId) {
        UserLog userLog = new UserLog();
        userLog.setUserName("lzp").setUserId(userId).setState("0");
        log.info("发送用户日志数据：" + userLog.toString());
        try {
            ListenableFuture userLog1 = kafkaTemplate.send("userLog", JSON.toJSONString(userLog));
            log.info("<<<<<<<<<<<<<<" + userLog1.toString());
            Object o = userLog1.get();
            log.info("===============>" + o.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}