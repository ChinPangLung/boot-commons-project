package com.lung.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.lung.application.model.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
public interface IUserService extends IService<User> {

    List<User> findByUserName(String name);

}
