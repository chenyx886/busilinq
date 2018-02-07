package com.busilinq.xsm.data.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * @author AntikDing
 * @version 1.0
 * @created 15-二月-2016 14:11:31
 */
public class CgtDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867224101675792731L;
	/**
	 * 商品编码，C15
	 */
	@Expose
	@SerializedName("CGT_CODE")
	private String CgtCode;
	/**
	 * 商品名称，C32
	 */
	@Expose
	@SerializedName("CGT_NAME")
	private String CgtName;
	/**
	 * 是否投放，C30
	 */
	@Expose
	@SerializedName("IS_SUPPLY")
	private String IsSupply;
	/**
	 * 订购金额，N18.6
	 */
	@Expose
	@SerializedName("AMT")
	private String OrdAmt;
	/**
	 * 订购量，N18.1
	 */
	@Expose
	@SerializedName("ORD_QTY")
	private String OrdQty;
	/**
	 * 批发价，N18.6
	 */
	@Expose
	@SerializedName("PRICE")
	private String Price;
	/**
	 * 可用量，N18.1
	 */
	@Expose
	@SerializedName("QTY_LMT")
	private String QtyLmt;
	/**
	 * 需求量，N18.1
	 */
	@Expose
	@SerializedName("REQ_QTY")
	private String ReqQty;
	/**
	 * 零售价，N18.6
	 */
	@Expose
	@SerializedName("RTL_PRICE")
	private String RtlPrice;
	/**
	 * 商品简码，C8
	 */
	@Expose
	@SerializedName("SHORT_CODE")
	private String ShortCode;
	/**
	 * 单位，C4
	 */
	@Expose
	@SerializedName("UM_SALE_NAME")
	private String UmSaleName;
	/**
	 * 确认量，N18.1
	 */
	@Expose
	@SerializedName("VFY_QTY")
	private String VfyQty;

	public CgtDetail(){

	}
	
	public String getCgtCode(){
		return CgtCode;
	}

	public String getCgtName(){
		return CgtName;
	}
	
	public String getIsSupply(){
		return IsSupply;
	}

	public String getOrdAmt(){
		return OrdAmt;
	}

	public String getOrdQty(){
		return OrdQty;
	}
	
	public String getQtyLmt(){
		return QtyLmt;
	}

	public String getPrice(){
		return Price;
	}

	public String getReqQty(){
		return ReqQty;
	}

	public String getRtlPrice(){
		return RtlPrice;
	}

	public String getShortCode(){
		return ShortCode;
	}

	public String getUmSaleName(){
		return UmSaleName;
	}

	public String getVfyQty(){
		return VfyQty;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCgtCode(String code){
		this.CgtCode = code;
	}

	/**
	 * 
	 * @param name
	 */
	public void setCgtName(String name){
		this.CgtName = name;
	}
	
	/**
	 * 
	 * @param isSupply
	 */
	public void setIsSupply(String isSupply){
		this.IsSupply = isSupply;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setOrdAmt(String amount){
		this.OrdAmt = amount;
	}

	/**
	 * 
	 * @param qty
	 */
	public void setOrdQty(String qty){
		this.OrdQty = qty;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(String price){
		this.Price = price;
	}
	
	/**
	 * 
	 * @param limit
	 */
	public void setQtyLmt(String limit){
		this.QtyLmt = limit;
	}

	/**
	 * 
	 * @param qty
	 */
	public void setReqQty(String qty){
		this.ReqQty = qty;
	}

	/**
	 * 
	 * @param price
	 */
	public void setRtlPrice(String price){
		this.RtlPrice = price;
	}

	/**
	 * 
	 * @param code
	 */
	public void setShortCode(String code){
		this.ShortCode = code;
	}

	/**
	 * 
	 * @param name
	 */
	public void setUmSaleName(String name){
		this.UmSaleName = name;
	}

	/**
	 * 
	 * @param qty
	 */
	public void setVfyQty(String qty){
		this.VfyQty = qty;
	}


	@Override
	public String toString() {
		String str;
		str = "mCgtCode: "+this.CgtCode+" mCgtName: "+this.CgtName+" mOrdAmt: "+this.OrdAmt;
		str = str+" mOrdQty: "+this.OrdQty+" mPrice: "+this.Price+" mReqQty: "+this.ReqQty;
		str = str+" mShortCode: "+this.ShortCode+" mUmSaleName: "+this.UmSaleName+" mVfyQty: "+this.VfyQty;
		str = str +" mIsSupply: "+this.IsSupply+" mQtyLmt: "+this.QtyLmt;
		return str;
	}

	
}