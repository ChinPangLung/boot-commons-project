/*
package com.lung.application.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lung.application.model.entity.User;
import com.lung.application.service.IUserService;
import com.lung.application.service.IndexerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

*/
/**
 * @Title: IndexerController
 * @Author: lung
 * @Date: 19-3-14 下午2:40
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **//*

@RestController
public class IndexerController {

    @Reference
    private IndexerService indexerService;

    @Reference
    private IUserService userService;

    @RequestMapping(value = "bulkIndex")
    public long bulkIndex() {
        long l = indexerService.bulkIndex();
        return l;
    }

    @RequestMapping(value = "findlist")
    public String findUserList(String name) {
        if (StringUtils.isBlank(name)) {
            return "name 不能为空!";
        }
        List<User> list = userService.findByUserName(name);
        return JSON.toJSONString(list);
    }
}
*/
