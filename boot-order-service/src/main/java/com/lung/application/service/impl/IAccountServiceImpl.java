package com.lung.application.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lung.application.model.entity.Account;
import com.lung.application.model.mapper.AccountMapper;
import com.lung.application.service.IAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
@Log4j2
public class IAccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public Account createAccount(Account account) {
        LOGGER.info("order purchase begin ... xid: " + RootContext.getXID());
        boolean bool = insert(account);
        if (bool) {
            log.info("创建account账号信息成功:" + account.toString());
        }
        int i = 10 / 0;
        return account;
    }
}
