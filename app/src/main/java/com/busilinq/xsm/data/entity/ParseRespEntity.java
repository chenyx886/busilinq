package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Company：华科建邺
 * Class Describe：刷卡支付 实体
 * Create Person：Chenyx
 * Create Time：2017/11/11 下午3:53
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class ParseRespEntity implements Serializable{
    /**
     * 对应应答码说明
     * 0	交易成功
     * -3	发送失败
     * -5	打包失败
     * -11	交易金额不符
     * -12	流水号不一致
     * -13	终端号不一致
     * -14	商户号不一致
     * -15	无交易
     * -17	此交易已撤销
     * -18	此交易不可撤销
     * -19	打开通讯口错误
     * -20	平台拒绝
     * -21	交易终止
     * -22	终端未签到
     * -23	交易笔数超限，立即结算
     * -24	交易笔数超限，稍后结算
     * -26	终端不支持该交易
     * -27	卡号不一致
     * -28	密码错误
     * -29	参数错误
     */

    private String[] rspCodeType = new String[]{"0", "-3", "-5", "-11", "-12", "-13", "-14", "-15", "-17", "-18", "-19", "-20", "-21", "-22", "-23", "-24", "-26", "-27", "-28", "-29"};
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 应答码
     */
    private String rspCode;
    /**
     * 应答信息
     */
    private String rspMsg;
    /**
     * 商户名称
     */
    private String merchName;
    /**
     * 商户编号
     */
    private String merchId;
    /**
     * 终端编号
     */
    private String termId;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 凭证号
     */
    private String voucherNo;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 发卡行号
     */
    private String isserCode;
    /**
     * 收单行号
     */
    private String acqCode;
    /**
     * 授权码
     */
    private String authNo;
    /**
     * 参考号
     */
    private String refNo;
    /**
     * 交易时间
     */
    private String transTime;
    /**
     * 交易日期
     */
    private String transDate;
    /**
     * 交易金额
     */
    private String transAmount;
    /**
     * 原授权码
     */
    private String origAuthNo;
    /**
     * 原凭证号
     */
    private String origVoucherNo;
    /**
     * 原参考号
     */
    private String origRefNo;

    public String[] getRspCodeType() {
        return rspCodeType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getIsserCode() {
        return isserCode;
    }

    public void setIsserCode(String isserCode) {
        this.isserCode = isserCode;
    }

    public String getAcqCode() {
        return acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getOrigAuthNo() {
        return origAuthNo;
    }

    public void setOrigAuthNo(String origAuthNo) {
        this.origAuthNo = origAuthNo;
    }

    public String getOrigVoucherNo() {
        return origVoucherNo;
    }

    public void setOrigVoucherNo(String origVoucherNo) {
        this.origVoucherNo = origVoucherNo;
    }

    public String getOrigRefNo() {
        return origRefNo;
    }

    public void setOrigRefNo(String origRefNo) {
        this.origRefNo = origRefNo;
    }
}
