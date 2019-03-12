package com.lung.application.service.impl;

import java.util.Arrays;
import java.util.List;

import com.lung.application.bean.UserAddress;
import com.lung.application.service.UserService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;


@Service//暴露服务 
@Component
public class UserServiceImpl implements UserService {

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl..3.....");
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");

		return Arrays.asList(address1,address2);
	}

}
