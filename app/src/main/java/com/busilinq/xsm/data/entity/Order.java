package com.busilinq.xsm.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingyi on 2017/2/22.
 */

public class Order implements Serializable {

    private static final long serialVersionUID = -6828249014191798853L;
    /**
     * crtTime : 12:12:53
     * orgCode : 11450101
     * crtDate : 20160113
     * whseCode : GX0000000002789
     * slamanCode : 11450101
     * saleDeptCode : 1145010124
     * status : 60
     * ordAmtSum : null
     * saleCenterCode : 1145010100
     * seq : 10
     * coNum : GN25036937
     * orderDate : 20160115
     * pmtStatus : 1
     * reqQtySum : 5
     * ordQtySum : 5
     * vfyQtySum :
     * note : mobile
     * type : 33
     */

    private String crtTime;
    private String orgCode;
    private String crtDate;
    private String whseCode;
    private String slamanCode;
    private String saleDeptCode;
    private String status;
    private String ordAmtSum;
    private String saleCenterCode;
    private String seq;
    private String coNum;
    private String orderDate;
    private String pmtStatus;
    private String reqQtySum;
    private String ordQtySum;
    private String vfyQtySum;
    private String note;
    private String type;
    private List<OrderDetail> details;

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public void setWhseCode(String whseCode) {
        this.whseCode = whseCode;
    }

    public void setSlamanCode(String slamanCode) {
        this.slamanCode = slamanCode;
    }

    public void setSaleDeptCode(String saleDeptCode) {
        this.saleDeptCode = saleDeptCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrdAmtSum(String ordAmtSum) {
        this.ordAmtSum = ordAmtSum;
    }

    public void setSaleCenterCode(String saleCenterCode) {
        this.saleCenterCode = saleCenterCode;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public void setCoNum(String coNum) {
        this.coNum = coNum;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setPmtStatus(String pmtStatus) {
        this.pmtStatus = pmtStatus;
    }

    public void setReqQtySum(String reqQtySum) {
        this.reqQtySum = reqQtySum;
    }

    public void setOrdQtySum(String ordQtySum) {
        this.ordQtySum = ordQtySum;
    }

    public void setVfyQtySum(String vfyQtySum) {
        this.vfyQtySum = vfyQtySum;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public String getWhseCode() {
        return whseCode;
    }

    public String getSlamanCode() {
        return slamanCode;
    }

    public String getSaleDeptCode() {
        return saleDeptCode;
    }

    public String getStatus() {
        return status;
    }

    public String getOrdAmtSum() {
        return ordAmtSum;
    }

    public String getSaleCenterCode() {
        return saleCenterCode;
    }

    public String getSeq() {
        return seq;
    }

    public String getCoNum() {
        return coNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getPmtStatus() {
        return pmtStatus;
    }

    public String getReqQtySum() {
        return reqQtySum;
    }

    public String getOrdQtySum() {
        return ordQtySum;
    }

    public String getVfyQtySum() {
        return vfyQtySum;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> datasetLine) {
        this.details = datasetLine;
    }
}
