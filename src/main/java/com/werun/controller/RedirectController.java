package com.werun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    //未登录重定向接口
    //如果未登录，访问任何页面（在config/shiroConfig中设置拦截页面）会重定向至登录页面 login.html
    @GetMapping("/toLogin")
    public String toLogin(){
        //前端完善一下这个登录页面哈
        return "login.html";
    }
}
