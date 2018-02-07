package com.busilinq.xsm.data.usercenter;

import java.io.Serializable;

/**
 * * serviceId : 1
 * service : null
 * serviceCode : code
 * <p>
 * <p>
 * Created by dingyi on 2016/11/30.
 */

public class ServiceEntity implements Serializable {

    private String serviceId;
    private String service;
    private String category;
    private String code;
    /**
     * 0：启用 1：不启用
     */
    private int permission = 1;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}