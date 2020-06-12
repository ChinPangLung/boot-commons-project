package com.lung.application.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lung.application.model.entity.Account;
import com.lung.application.model.entity.User;
import com.lung.application.service.IAccountService;
import com.lung.application.service.IUserService;
import com.lung.application.utils.redis.RedisUtils;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Reference
    private IAccountService accountService;

    @RequestMapping(value = "create")
    @GlobalTransactional(timeoutMills = 300000)
    public Map<String, Object> create() {
        LOGGER.info("user purchase begin ... xid: " + RootContext.getXID());
        Map<String, Object> result = new HashMap<>(16);
        //写入一个user
        User user = new User();
        user.setAge(new Random().nextInt(100));
        user.setCreateBy("system");
        user.setCreateDate(new Date());
        user.setDelFlag("1");
        user.setName("test" + user.getAge());
        user.setRemarks("备注");
        user.setSex(1);
        userService.insert(user);
        List<User> users = userService.findByUserName(user.getName());
        log.info("调用order-service的account接口=======");
        Account account = new Account();
        account.setBalance(new BigDecimal(1000));
        account.setFreezeAmount(new BigDecimal(1000));
        account.setUserId("100");
        account.setCreateTime(new Date());
        accountService.createAccount(account);
        List<Account> accounts = accountService.selectList(new EntityWrapper<>());
        result.put("user", users);
        result.put("accounts", accounts);
        return result;
    }

    @RequestMapping(value = "redislock")
    public String testRedisLock() throws InterruptedException {
        String name = Thread.currentThread().getName();
        String key = UUID.randomUUID().toString();
        String requestId = UUID.randomUUID().toString();
        boolean lock = redisUtils.tryGetDistributedLock(key, requestId, 3000);
        log.info(name + "尝试加锁============" + lock);
        boolean lock2 = redisUtils.tryGetDistributedLock(key, requestId, 3000);
        log.info(name + "未释放锁再次尝试加锁============" + lock2);
        boolean ulock = redisUtils.releaseDistributedLock(key, requestId);
        log.info(name + "主动释放分布式锁======" + ulock);
        boolean lock3 = redisUtils.tryGetDistributedLock(key, requestId, 3000);
        log.info(name + "主动释放锁后，重新获取锁，主线程等待3000ms========" + lock3);
        Thread.sleep(3000);
        boolean lock4 = redisUtils.tryGetDistributedLock(key, requestId, 3000);
        log.info(name + "主线程等待3000ms，尝试重新获取锁========" + lock4);
        return "redis test success";
    }

}

