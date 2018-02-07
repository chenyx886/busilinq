package com.busilinq.xsm.data.entity.pospay;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/9/5.
 */

public class PosPayOrder implements Serializable {
    /**
     * custId : 522426102019
     * custName : 黄菊
     * coNum : HKT90905083033
     * amount : 70.0
     * transNo : POS20170905084842056879
     * validTime : 600
     * acquirer : {"merchantId":"898520158140765","merchantName":"毕节邮储","posId":"01183778","sn":"P820119383"}
     */

    private String custId;
    private String custName;
    private String coNum;
    private String amount;
    private String transNo;
    private String validTime;
    private PosAcquirer acquirer;

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCoNum(String coNum) {
        this.coNum = coNum;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public void setAcquirer(PosAcquirer acquirer) {
        this.acquirer = acquirer;
    }

    public String getCustId() {
        return custId;
    }

    public String getCustName() {
        return custName;
    }

    public String getCoNum() {
        return coNum;
    }

    public String getAmount() {
        return amount;
    }

    public String getTransNo() {
        return transNo;
    }

    public String getValidTime() {
        return validTime;
    }

    public PosAcquirer getAcquirer() {
        return acquirer;
    }

}
