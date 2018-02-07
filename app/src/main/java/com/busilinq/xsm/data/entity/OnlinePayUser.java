package com.busilinq.xsm.data.entity;

import java.util.List;

/**
 * @author dingyi
 * @version 1.0
 * @created 25-11月-2016 17:07:01
 */
public class OnlinePayUser {

	/**
	 * 客户绑定的银行卡
	 */
	private List<OnlinePayCard> cards;

	/**
	 * 商户ID
	 */
	private String merchantId;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 新商盟客户编码
	 */
	private String xsmCode;
	/**
	 * 新商盟客户名称
	 */
	private String xsmName;
	/**
	 * 客户联系电话
	 */
	private String cellPhone;

	public List<OnlinePayCard> getCards() {
		return cards;
	}

	public void setCards(List<OnlinePayCard> cards) {
		this.cards = cards;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getXsmCode() {
		return xsmCode;
	}

	public void setXsmCode(String xsmCode) {
		this.xsmCode = xsmCode;
	}

	public String getXsmName() {
		return xsmName;
	}

	public void setXsmName(String xsmName) {
		this.xsmName = xsmName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
}