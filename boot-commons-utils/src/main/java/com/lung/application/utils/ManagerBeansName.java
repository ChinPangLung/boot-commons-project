package com.lung.application.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统启动时输出容器的bean对象
 */
//@Component
public class ManagerBeansName implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount" + beanDefinitionCount);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str :
                beanDefinitionNames) {
            System.out.println(str);
        }
    }
}
