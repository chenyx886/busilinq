package com.busilinq.data;


import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Company：华科建邺
 * Class Describe：客户端与服务器的数据传输的接收ValueObject
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class BaseResp implements Serializable {
    /**
     * 重写toString方法以实现JSON返回
     *
     * @return JSON字符串
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
