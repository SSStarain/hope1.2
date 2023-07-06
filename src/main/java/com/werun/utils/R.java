package com.werun.utils;

import lombok.Data;
//表现层统一回复模型类
@Data
public class R {
    private Boolean flag;
    private Object data;
    private String msg;
    public R(){

    }

    public R(Boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    public R(Boolean flag){
        this.flag=flag;
    }
    public R(Boolean flag,Object data){
        this.flag=flag;
        this.data=data;
    }
    public R(Boolean flag,String msg){
        this.flag=flag;
        this.msg=msg;
    }
}
