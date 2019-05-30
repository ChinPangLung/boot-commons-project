package com.lung.application.config.beanconfig;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ProducerConfig
 * @Author: chinpanglung
 * @Date: 19-5-29 下午3:50
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Data
@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@ToString
public class ProducerConfig {

    private String groupName;

    private String namesrvAddr;

    
}
