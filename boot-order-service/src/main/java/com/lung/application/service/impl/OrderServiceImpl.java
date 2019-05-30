package com.lung.application.service.impl;

import java.util.Arrays;
import java.util.List;

import com.lung.application.bean.UserAddress;
import com.lung.application.service.OrderService;
import com.lung.application.service.UserTestService;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 2）、配置服务提供者
 * <p>
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 *
 * @author lfy
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * @Autowired
     */
//    @Reference(url = "127.0.0.1:20882")//dubbo直连
    @Reference //引用dubbo服务
    UserTestService userTestService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        // TODO Auto-generated method stub
        System.out.println("用户id：" + userId);
        //1、查询用户的收货地址
        List<UserAddress> addressList = userTestService.getUserAddressList(userId);
        return addressList;
    }


    public List<UserAddress> hello(String userId) {
        // TODO Auto-generated method stub

        return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
    }


}
