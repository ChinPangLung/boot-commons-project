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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
        Instant startTime = Instant.now();
        log.info("user purchase begin ... xid: " + RootContext.getXID());
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
        log.info("user 模块总处理时间：" + Duration.between(startTime, Instant.now()));
        List<User> users = userService.findByUserName(user.getName());
        Account account = new Account();
        account.setBalance(new BigDecimal(1000));
        account.setFreezeAmount(new BigDecimal(1000));
        account.setUserId("100");
        account.setCreateTime(new Date());
        accountService.createAccount(account);
        Instant orderStart = Instant.now();
        List<Account> accounts = accountService.selectList(new EntityWrapper<>());
        log.info("order 模块总处理时间：" + Duration.between(orderStart, Instant.now()));
        result.put("user", users);
        result.put("accounts", accounts);
        Instant endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        log.info("总处理时间：" + timeElapsed);
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

