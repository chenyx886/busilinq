package com.busilinq.xsm.data.entity;

/**
 * Created by dingyi on 2017/2/17.
 */

public class Organize {

    /**
     * id : 1
     * orgCode : 100001
     * orgName : 四川烟草专卖
     */

    private int id;
    private String orgCode;
    private String orgName;

    public void setId(int id) {
        this.id = id;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getId() {
        return id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public String getOrgName() {
        return orgName;
    }
}
