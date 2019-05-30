package com.lung.application.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lung.application.model.entity.User;
import com.lung.application.model.mapper.UserMapper;
import com.lung.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
@Service
@Component
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findByUserName(String name) {

        EntityWrapper entityWrapper = new EntityWrapper<User>();
        entityWrapper.eq("name", name);

        List list = userMapper.selectList(entityWrapper);

        return list;
    }
}
