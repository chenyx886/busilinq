package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by LWX on 2017/11/17.
 */

public class UnMsgEvent implements Serializable {

    private String userName;
    private String Pwd;
    private String orgCode;//公司编码
    private String token;//随机数
    private int type;//消息状态0：表示扫码成功，1：表示确认登录

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
