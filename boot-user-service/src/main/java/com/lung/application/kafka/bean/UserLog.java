package com.lung.application.kafka.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 定义用户发送的日志数据
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-08 15:09
 */
@Data
@Accessors(chain = true)
public class UserLog {
    private String userName;
    private String userId;
    private String state;
}