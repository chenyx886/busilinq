package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/2/17.
 */

public class Cart implements Serializable {

    private static final long serialVersionUID = 6369953797313091429L;
    /**
     * cgtCode : 6901028011587
     * cgtName : 甲天下（山水）
     * crtDate : 20170217
     * crtTime : 14:34:08
     * reqQty : 1
     * ordQty : 1
     * note :
     * price : null
     */

    private String cgtCode;
    private String cgtName;
    private String crtDate;
    private String crtTime;
    private String reqQty;
    private String ordQty;
    private String note;
    private String price;

    public void setCgtCode(String cgtCode) {
        this.cgtCode = cgtCode;
    }

    public void setCgtName(String cgtName) {
        this.cgtName = cgtName;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public void setReqQty(String reqQty) {
        this.reqQty = reqQty;
    }

    public void setOrdQty(String ordQty) {
        this.ordQty = ordQty;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCgtCode() {
        return cgtCode;
    }

    public String getCgtName() {
        return cgtName;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public String getReqQty() {
        return reqQty;
    }

    public String getOrdQty() {
        return ordQty;
    }

    public String getNote() {
        return note;
    }

    public String getPrice() {
        return price;
    }
}
