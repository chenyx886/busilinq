package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/1/10.
 * 员工表
 */

public class Employee implements Serializable {
    private static final long serialVersionUID = -5629654562822517775L;

    private String name;//姓名
    private String status;//审核状态 0-申请中 1-可用
    private String memberId;//用户Id
    private String qrCode;//店铺编码
    private String shopName;//店铺名称
    private String signTime;//签到时间  yyyy-MM-dd HH:mm:ss
    private String pmtStatus;//收款状态 1可收 0不可收
    private String empUrl;//头像地址
    private String cell;//联系手机号
    private Role role;

    public Employee() {
    }

    public Employee(String cell, String empUrl, String name, String status, String memberId, String qrCode, String shopName, String signTime, String pmtStatus, Role role) {
        this.cell = cell;
        this.name = name;
        this.status = status;
        this.memberId = memberId;
        this.qrCode = qrCode;
        this.shopName = shopName;
        this.signTime = signTime;
        this.pmtStatus = pmtStatus;
        this.role = role;
        this.empUrl = empUrl;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmpUrl() {
        return empUrl;
    }

    public void setEmpUrl(String empUrl) {
        this.empUrl = empUrl;
    }

    public String getPmtStatus() {
        return pmtStatus;
    }

    public void setPmtStatus(String pmtStatus) {
        this.pmtStatus = pmtStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * realName : 丁毅one
     * status : 2
     * memberId : ea1ab8acefeab919ac5d127406f77fe615828627630
     * roleCode : 1
     * roleName : 店长
     * shopName : 红旗连锁
     * lastSignInTime : null
     */

}
