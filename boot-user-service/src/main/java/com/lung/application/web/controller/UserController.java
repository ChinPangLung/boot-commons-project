package com.lung.application.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lung.application.model.entity.User;
import com.lung.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "list")
    public List<User> findAllUser() {
        List<User> users = userService.selectList(new EntityWrapper<>());
        return users;
    }

}

