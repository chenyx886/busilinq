package com.busilinq.xsm.data.entity.pospay.client;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/9/5.
 */

public class PosTradeResult implements Serializable {

    /**
     * model : A920
     * extBillNo :
     * CVM : 000000
     * ARQC : B5D233D7CD9BD013
     * expDate : 24/09
     * date : 2017/09/04
     * serviceNo :
     * version : 3.2.007
     * AIP : 7C00
     * extOrderNo : 20170904095749545171
     * cardNo : 621779*********4593
     * cardInputType : C
     * time : 09:59:05
     * batchNo : 000001
     * isReprint : false
     * TermCap : E0E1C8
     * merchantName : 贵阳云岩众筹批萨屋
     * CSN : 000
     * ATC : 0017
     * memInfo : 商户流水号:20170904095749545171<br/>
     * vendor : PAX
     * terminalNo : 01183778
     * authNo : 000000
     * TVR : 0000000000
     * APPLAB : PBOC DEBIT
     * IAD : 07010103A00000010A0100000000008F25DBC0
     * orderStatus : 1
     * cardType : 借
     * operNo : 001
     * TSI : 0000
     * amt : 0.01
     * UnprNo : 758FD7D4
     * resCode : 00
     * refNo : 000709107736
     * AID : A000000333010101
     * cardIssuerCode : 1436
     * traceNo : 000091
     * merchantNo : 898520158140765
     * transEngName : SALE
     * APPNAME : PBOC DEBIT
     * resDesc : 交易成功
     * transChnName : 消费
     * cardAcquirerCode : 银联商务
     * cardOrg : CUP
     */

    private String model;
    private String extBillNo;
    private String CVM;
    private String ARQC;
    private String expDate;
    private String date;
    private String serviceNo;
    private String version;
    private String AIP;
    private String extOrderNo;
    private String cardNo;
    private String cardInputType;
    private String time;
    private String batchNo;
    private boolean isReprint;
    private String TermCap;
    private String merchantName;
    private String CSN;
    private String ATC;
    private String memInfo;
    private String vendor;
    private String terminalNo;
    private String authNo;
    private String TVR;
    private String APPLAB;
    private String IAD;
    private String orderStatus;
    private String cardType;
    private String operNo;
    private String TSI;
    private String amt;
    private String UnprNo;
    private String resCode;
    private String refNo;
    private String AID;
    private String cardIssuerCode;
    private String traceNo;
    private String merchantNo;
    private String transEngName;
    private String APPNAME;
    private String resDesc;
    private String transChnName;
    private String cardAcquirerCode;
    private String cardOrg;

    public void setModel(String model) {
        this.model = model;
    }

    public void setExtBillNo(String extBillNo) {
        this.extBillNo = extBillNo;
    }

    public void setCVM(String CVM) {
        this.CVM = CVM;
    }

    public void setARQC(String ARQC) {
        this.ARQC = ARQC;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAIP(String AIP) {
        this.AIP = AIP;
    }

    public void setExtOrderNo(String extOrderNo) {
        this.extOrderNo = extOrderNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardInputType(String cardInputType) {
        this.cardInputType = cardInputType;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public void setIsReprint(boolean isReprint) {
        this.isReprint = isReprint;
    }

    public void setTermCap(String TermCap) {
        this.TermCap = TermCap;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setCSN(String CSN) {
        this.CSN = CSN;
    }

    public void setATC(String ATC) {
        this.ATC = ATC;
    }

    public void setMemInfo(String memInfo) {
        this.memInfo = memInfo;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public void setTVR(String TVR) {
        this.TVR = TVR;
    }

    public void setAPPLAB(String APPLAB) {
        this.APPLAB = APPLAB;
    }

    public void setIAD(String IAD) {
        this.IAD = IAD;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
    }

    public void setTSI(String TSI) {
        this.TSI = TSI;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public void setUnprNo(String UnprNo) {
        this.UnprNo = UnprNo;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public void setCardIssuerCode(String cardIssuerCode) {
        this.cardIssuerCode = cardIssuerCode;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setTransEngName(String transEngName) {
        this.transEngName = transEngName;
    }

    public void setAPPNAME(String APPNAME) {
        this.APPNAME = APPNAME;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public void setTransChnName(String transChnName) {
        this.transChnName = transChnName;
    }

    public void setCardAcquirerCode(String cardAcquirerCode) {
        this.cardAcquirerCode = cardAcquirerCode;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getModel() {
        return model;
    }

    public String getExtBillNo() {
        return extBillNo;
    }

    public String getCVM() {
        return CVM;
    }

    public String getARQC() {
        return ARQC;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getDate() {
        return date;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public String getVersion() {
        return version;
    }

    public String getAIP() {
        return AIP;
    }

    public String getExtOrderNo() {
        return extOrderNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardInputType() {
        return cardInputType;
    }

    public String getTime() {
        return time;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public boolean getIsReprint() {
        return isReprint;
    }

    public String getTermCap() {
        return TermCap;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getCSN() {
        return CSN;
    }

    public String getATC() {
        return ATC;
    }

    public String getMemInfo() {
        return memInfo;
    }

    public String getVendor() {
        return vendor;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public String getAuthNo() {
        return authNo;
    }

    public String getTVR() {
        return TVR;
    }

    public String getAPPLAB() {
        return APPLAB;
    }

    public String getIAD() {
        return IAD;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getCardType() {
        return cardType;
    }

    public String getOperNo() {
        return operNo;
    }

    public String getTSI() {
        return TSI;
    }

    public String getAmt() {
        return amt;
    }

    public String getUnprNo() {
        return UnprNo;
    }

    public String getResCode() {
        return resCode;
    }

    public String getRefNo() {
        return refNo;
    }

    public String getAID() {
        return AID;
    }

    public String getCardIssuerCode() {
        return cardIssuerCode;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public String getTransEngName() {
        return transEngName;
    }

    public String getAPPNAME() {
        return APPNAME;
    }

    public String getResDesc() {
        return resDesc;
    }

    public String getTransChnName() {
        return transChnName;
    }

    public String getCardAcquirerCode() {
        return cardAcquirerCode;
    }

    public String getCardOrg() {
        return cardOrg;
    }
}
