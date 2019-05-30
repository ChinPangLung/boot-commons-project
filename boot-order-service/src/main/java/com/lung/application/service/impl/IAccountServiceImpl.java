package com.lung.application.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lung.application.model.entity.Account;
import com.lung.application.model.mapper.AccountMapper;
import com.lung.application.service.IAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
@Service
@Component
public class IAccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
