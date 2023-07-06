package com.werun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.dao.AccountDao;
import com.werun.entity.Account;
import com.werun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public Account selectByAccount(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return accountDao.selectOne(wrapper);
    }
    @Override
    public boolean saveByAccount(Account account) {
        return accountDao.insert(account) == 1;
    }

    @Override
    public Boolean updatePassword(String newPassword,Account account) {
        UpdateWrapper<Account> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",account.getUsername()).set("password",newPassword);
        return accountDao.update(null,updateWrapper)>0;
    }
}
