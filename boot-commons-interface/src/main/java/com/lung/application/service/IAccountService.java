package com.lung.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.lung.application.model.entity.Account;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ChinPangLung
 * @since 2019-05-30
 */
public interface IAccountService extends IService<Account> {

    /**
     * 创建账号信息
     *
     * @param account
     * @return
     */
    Account createAccount(Account account);


}
