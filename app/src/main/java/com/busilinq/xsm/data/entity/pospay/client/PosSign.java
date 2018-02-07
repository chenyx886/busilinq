package com.busilinq.xsm.data.entity.pospay.client;

/**
 * Created by dingyi on 2017/9/6.
 */

public class PosSign {
    /**
     * resCode : 00
     * uniCode : 89852015814061601100011
     * merchantNo : 898520158140616
     * resDesc : 签到成功
     * terminalNo : 01100011
     */

    private String resCode;
    private String uniCode;
    private String merchantNo;
    private String resDesc;
    private String terminalNo;

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setUniCode(String uniCode) {
        this.uniCode = uniCode;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getResCode() {
        return resCode;
    }

    public String getUniCode() {
        return uniCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public String getResDesc() {
        return resDesc;
    }

    public String getTerminalNo() {
        return terminalNo;
    }
}
