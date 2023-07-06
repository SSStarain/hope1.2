package com.werun.controller;

import com.werun.entity.Account;
import com.werun.utils.R;
import com.werun.service.INotebookService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyInfoController {
    @Autowired
    private INotebookService iNotebookService;
    //“我”页面
    @GetMapping("/myInfo")
    public R showMyInfo(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        Integer sumNoteName = iNotebookService.sumNoteName(account.getUsername());
        Integer sumWords = iNotebookService.sumWords(account.getUsername());
        String username = account.getUsername();

        List<Integer> twoSums = new ArrayList<>();
        twoSums.add(sumWords);
        twoSums.add(sumNoteName);

        return new R(true,twoSums,username);
    }
}
