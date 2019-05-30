package com.lung.application.interceptor;

import com.lung.application.service.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: UserConfig
 * @Author: lung
 * @Date: 19-1-25 下午3:04
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Component
public class UserConfig implements HandlerInterceptor {

    @Autowired
    private TestBean testBean;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("自动以拦截器");
        testBean.sendMsg();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
