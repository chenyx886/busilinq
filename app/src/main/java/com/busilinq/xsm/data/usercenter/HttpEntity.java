package com.busilinq.xsm.data.usercenter;

import java.io.Serializable;

/**
 * Created by dingyi on 2016/11/30.
 */

public class HttpEntity<T> implements Serializable{
    private String msg;
    private String code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return code.equals("0000")?true:false;
    }
}
