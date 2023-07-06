package com.werun.utils;

import com.werun.entity.Account;
import com.werun.service.impl.AccountServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//shiro的一个特殊层
public class MyRealm extends AuthorizingRealm {

    @Autowired
    AccountServiceImpl accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //这里可以设置role和perms,本项目应该不需要
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.selectByAccount(token.getUsername());
        if(account != null){
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }
        return null;
    }
}
