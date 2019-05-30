package com.lung.application.service.impl;

import com.lung.application.service.TestBean;
import org.springframework.stereotype.Service;

/**
 * @Title: TestBeanImpl
 * @Author: lung
 * @Date: 19-1-25 下午3:49
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Service
public class TestBeanImpl implements TestBean {

    @Override
    public void sendMsg() {
        System.out.println("============Msg============");
    }
}
