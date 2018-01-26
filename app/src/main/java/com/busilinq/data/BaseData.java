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

    private String status;

    private String msg;

    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseData() {
        super();
    }

    public BaseData(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
