package com.werun.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//日期类
public class MyCalendar {
    public String getTime() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date time = calendar.getTime();
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return data.format(time);
    }
}
