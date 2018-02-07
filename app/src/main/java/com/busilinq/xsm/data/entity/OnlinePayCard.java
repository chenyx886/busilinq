package com.busilinq.xsm.data.entity;

/**
 * 在线支付银行卡
 * @author dingyi
 * @version 1.0
 * @created 25-11月-2016 17:06:50
 */
public class OnlinePayCard {

	/**
	 * 签约号
	 */
	private String bindId;
	/**
	 * 银行预留手机号
	 */
	private String cellPhone;
	/**
	 * 银行卡编码
	 */
	private String code;
	/**
	 * 银行卡前四位
	 */
	private String firstDigst;
	/**
	 * 银行卡后四位
	 */
	private String lastDigist;
	/**
	 * 银行卡名称
	 */
	private String name;
	/**
	 * 银行卡卡号
	 */
    private String pan;
	/**
	 * 卡类型
	 */
	private String type;
	/**
	 * 开序列号
	 */
    private String cvn2;
	/**
	 * 卡有效日期
	 */
    private String expDate;

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFirstDigst() {
		return firstDigst;
	}

	public void setFirstDigst(String firstDigst) {
		this.firstDigst = firstDigst;
	}

	public String getLastDigist() {
		return lastDigist;
	}

	public void setLastDigist(String lastDigist) {
		this.lastDigist = lastDigist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getCvn2() {
		return cvn2;
	}

	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
}