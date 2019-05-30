package com.lung.application.service.impl;

import java.util.Arrays;
import java.util.List;

import com.lung.application.bean.UserAddress;
import com.lung.application.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;


@Service//暴露服务
@Component
@Log4j2
public class UserServiceImpl implements UserService {

	@Autowired
	private DefaultMQProducer defaultMQProducer;

	@Override
	public List<UserAddress> getUserAddressList(String userId)  {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl..3.....");
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		try {
			test("hello");
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.asList(address1,address2);
	}


	/**
	 * 在这里测试rocketMQ modify 20190529
	 *
	 */
	public void test(String info) throws RemotingException, MQClientException, InterruptedException {
		Message message = new Message("TopicTest", "Tag1", "12345", "rocketMQ测试数据".getBytes());
		defaultMQProducer.send(message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				log.info("传输成功");
				log.info(sendResult);
			}

			@Override
			public void onException(Throwable throwable) {
				log.error("传输失败",throwable);
			}
		});
	}
}
