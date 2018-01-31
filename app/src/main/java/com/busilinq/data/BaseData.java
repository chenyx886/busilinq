package com.busilinq.data;

/**
 * Company：华科建邺
 * Class Describe：Http 请求基础类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class BaseData<T> extends BaseResp {

    private String code;

    private String msg;

    private T data;


    public BaseData() {
        super();
    }

    public BaseData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
