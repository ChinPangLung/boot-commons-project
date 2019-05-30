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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private IUserService userService;

    @Reference
    private IAccountService accountService;

    @RequestMapping(value = "list")
    public Map<String, Object> findAllUser() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.selectList(new EntityWrapper<>());
        log.info("调用order-service的account接口=======");
        List<Account> accounts = accountService.selectList(new EntityWrapper<>());
        result.put("user", users);
        result.put("account", accounts);
        return result;
    }

}

