package com.werun.config;

import com.werun.utils.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        Map<String,String> map = new HashMap<>();

        //这里应添加所有需拦截页面
        map.put("/notebooks/**","authc");
        map.put("/logout","authc");
        map.put("/newPassword","authc");
        map.put("/myInfo","authc");

        //放行页面
        map.put("/register","anon");
        map.put("/login", "anon");
        map.put("/toLogin","anon");
        factoryBean.setFilterChainDefinitionMap(map);

        //设置未登录的重定向url
        factoryBean.setLoginUrl("/toLogin");

        //设置登陆后无权限访问后重定向的页面，这个项目用不到
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }


    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
}
