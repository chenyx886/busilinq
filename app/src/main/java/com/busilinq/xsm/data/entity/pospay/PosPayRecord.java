package com.busilinq.xsm.data.entity.pospay;

/**
 * Created by dingyi on 2017/9/20.
 */

public class PosPayRecord {
    private String custId;
    private String custName;
    private String coNum;
    private String amount;
    private String transNo;
    private String transTime;
    private String transVoucherNo;
    private String transRefNo;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCoNum() {
        return coNum;
    }

    public void setCoNum(String coNum) {
        this.coNum = coNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransVoucherNo() {
        return transVoucherNo;
    }

    public void setTransVoucherNo(String transVoucherNo) {
        this.transVoucherNo = transVoucherNo;
    }

    public String getTransRefNo() {
        return transRefNo;
    }

    public void setTransRefNo(String transRefNo) {
        this.transRefNo = transRefNo;
    }
}
