package com.lung.application.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lung.application.model.entity.Account;
import com.lung.application.model.entity.User;
import com.lung.application.service.IAccountService;
import com.lung.application.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
@RestController
@RequestMapping("/account")
@Log4j2
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Reference
    private IUserService userService;

    /**
     * findAccountList
     *
     * @param name
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author longzhanpeng chinpang9527@gmail.com
     * @since 19-7-18
     */
    @RequestMapping(value = "findaccount")
    public Map<String, Object> findAccountList(String name) {

        Map<String, Object> result = new HashMap<>(0b10000);
        log.info("首先测试account接口本地调用==========");
        List<Account> accounts = accountService.selectList(new EntityWrapper<>());
        result.put("account", accounts);
        log.info(accounts.toString());

        log.info("测试user-service的rpc接口");
        List<User> users = userService.findByUserName(name);
        result.put("user", users);
        return result;
    }


}

