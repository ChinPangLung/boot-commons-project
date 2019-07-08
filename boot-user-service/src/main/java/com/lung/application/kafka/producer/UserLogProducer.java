package com.lung.application.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.lung.application.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
        kafkaTemplate.send("userLog", JSON.toJSONString(userLog));
    }
}