package com.werun.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//自动填充笔记创建日期
@Component
public class TimeMetObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String time = new MyCalendar().getTime();
        this.setFieldValByName("notebookCreatedTime",time,metaObject);
        this.setFieldValByName("notebookEditedTime",time,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("notebookEditedTime",new MyCalendar().getTime(),metaObject);
    }
}
