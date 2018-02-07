package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by yu on 2017/8/1.
 * 角色表
 */

public class Role implements Serializable {
    private String id;//角色编码
    private String code;//角色简码
    private String name;//角色名称

    public Role() {
    }

    public Role(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
