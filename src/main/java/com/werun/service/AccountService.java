package com.werun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.entity.Account;


public interface AccountService extends IService<Account> {
    Account selectByAccount(String username);
    boolean saveByAccount(Account account);
    Boolean updatePassword(String newPassword,Account account);

}
