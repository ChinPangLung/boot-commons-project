package com.lung.application.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Title: WebSocketConfig
 * @Author: chinpanglung
 * @Date: 19-5-27 下午4:43
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
