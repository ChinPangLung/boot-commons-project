package com.lung.application.controller;

import com.lung.application.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "redis")
    public Map<String, Object> testRedis() {

        Map<String, Object> result = new HashMap<>();

        // 1、设置值
        boolean set = redisUtil.set("name", "longzhangpeng");
        System.out.println(set);

        //2、获取值
        Object name = redisUtil.get("name");
        System.out.println(name.toString());

        result.put("code", 200);
        result.put("message", "success");
        return result;
    }
}
