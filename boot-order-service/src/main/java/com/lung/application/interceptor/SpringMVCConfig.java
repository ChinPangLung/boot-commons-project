package com.lung.application.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title: SpringMVCConfig
 * @Author: lung
 * @Date: 19-1-25 下午3:05
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
//@SpringBootConfiguration
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UserConfig userConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userConfig).addPathPatterns("/**");
    }
}
