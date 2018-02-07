package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by dingyi on 2016/11/22.
 */

public class XsmAccount implements Serializable {
    private String mOrgCode;
    private String mOrgName;
    private String mAccount;
    private String mName;
    private String mAddress;
    private String mPsw;

    public void setAccount(String account){
        this.mAccount = account;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setAddress(String address){
        this.mAddress = address;
    }

    public void setPsw(String psw) {
        this.mPsw = psw;
    }

    public String getAccount() {
        return mAccount;
    }

    public String getName(){
        return mName;
    }

    public String getAddress(){
        return mAddress;
    }

    public String getPsw() {
        return mPsw;
    }

    public String getOrgCode() {
        return mOrgCode;
    }

    public void setOrgCode(String mOrgCode) {
        this.mOrgCode = mOrgCode;
    }

    public String getOrgName() {
        return mOrgName;
    }

    public void setOrgName(String orgName) {
        this.mOrgName = orgName;
    }
}
