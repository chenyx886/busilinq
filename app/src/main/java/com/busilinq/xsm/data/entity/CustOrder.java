package com.busilinq.xsm.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author AntikDing
 * @version 1.0
 * @created 15-二月-2016 14:11:24
 */
public class CustOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7140017758260262531L;
	/**
	 * 坐席员，C30
	 */
	private String CallMan;
	/**
	 * 订单号，C15
	 */
	private String CoNum;
	/**
	 * 创建日期，C8
	 */
	private String CrtDate;
	/**
	 * 创建时间，C8
	 */
	private String CrtTime;
	/**
	 * 客户编码，C30
	 */
	private String CustCode;
	/**
	 * 订单烟品详细信息
	 */
	private List<CgtDetail> Details;
	/**
	 * 是否要发票，C1
	 */
	private String InvType;
	/**
	 * 备注，N32
	 */
	private String Note;
	/**
	 * 订单金额，N18.1
	 */
	private String OrdAmtSum;
	/**
	 * 订货日期，C8
	 */
	private String OrderDate;
	/**
	 * 订单状态，10：新建；20：提交；30：审核；40确认；50：配送；60：完成；90：停止；C2
	 */
	private String OrderStatus;
	/**
	 * 订单类型：10：电话；20：网配；31：网上；34：终端；33：手机；32：电视；C2
	 */
	private String OrderType;
	/**
	 * 订购总量，N18.1
	 */
	private String OrdQtySum;
	/**
	 * 公司编码，C15
	 */
	private String OrgCode;
	/**
	 * 财月，C6
	 */
	private String Per;
	/**
	 * 付款状态，0：未付款；1：已付款；2：付款中；C1
	 */
	private String PmtStatus;
	/**
	 * 需求总量，N18.1
	 */
	private String ReqQtySum;
	/**
	 * 序号，C30
	 */
	private String Seq;
	/**
	 * 确认总量，N18.1
	 * 
	 */
	private String VfyQtySum;

	public CustOrder(){

	}

	public String getCallMan(){
		return CallMan;
	}

	public String getCoNum(){
		return CoNum;
	}

	public String getCrtDate(){
		return CrtDate;
	}

	public String getCrtTime(){
		return CrtTime;
	}

	public String getCustCode(){
		return CustCode;
	}

	public List<CgtDetail> getDetails(){
		return Details;
	}

	public String getInvType(){
		return InvType;
	}

	public String getNote(){
		return Note;
	}

	public String getOrdAmtSum(){
		return OrdAmtSum;
	}

	public String getOrderDate(){
		return OrderDate;
	}

	public String getOrderStatus(){
		return OrderStatus;
	}

	public String getOrderType(){
		return OrderType;
	}

	public String getOrdQtySum(){
		return OrdQtySum;
	}

	public String getOrgCode(){
		return OrgCode;
	}

	public String getPer(){
		return Per;
	}

	public String getPmtStatus(){
		return PmtStatus;
	}

	public String getReqQtySum(){
		return ReqQtySum;
	}

	public String getSeq(){
		return Seq;
	}

	public String getVfyQtySum(){
		return VfyQtySum;
	}

	/**
	 * 
	 * @param man
	 */
	public void setCallMan(String man){
		this.CallMan = man;
	}

	/**
	 * 
	 * @param number
	 */
	public void setCoNum(String number){
		this.CoNum = number;
	}

	/**
	 * 
	 * @param date
	 */
	public void setCrtDate(String date){
		this.CrtDate = date;
	}

	/**
	 * 
	 * @param time
	 */
	public void setCrtTime(String time){
		this.CrtTime = time;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCustCode(String code){
		this.CustCode = code;
	}

	/**
	 * 
	 * @param details
	 */
	public void setDetails(List<CgtDetail> details){
		this.Details = details;
	}

	/**
	 * 
	 * @param type
	 */
	public void setInvType(String type){
		this.InvType = type;
	}

	/**
	 * 
	 * @param note
	 */
	public void setNote(String note){
		this.Note = note;
	}

	/**
	 * 
	 * @param amtSum
	 */
	public void setOrdAmtSum(String amtSum){
		this.OrdAmtSum = amtSum;
	}

	/**
	 * 
	 * @param date
	 */
	public void setOrderDate(String date){
		this.OrderDate = date;
	}

	/**
	 * 
	 * @param status
	 */
	public void setOrderStatus(String status){
		this.OrderStatus = status;
	}

	/**
	 * 
	 * @param type
	 */
	public void setOrderType(String type){
		this.OrderType = type;
	}

	/**
	 * 
	 * @param qytSum
	 */
	public void setOrdQtySum(String qytSum){
		this.OrdQtySum = qytSum;
	}

	/**
	 * 
	 * @param code
	 */
	public void setOrgCode(String code){
		this.OrgCode = code;
	}

	/**
	 * 
	 * @param per
	 */
	public void setPer(String per){
		this.Per = per;
	}

	/**
	 * 
	 * @param status
	 */
	public void setPmtStatus(String status){
		this.PmtStatus = status;
	}

	/**
	 * 
	 * @param qtySum
	 */
	public void setReqQtySum(String qtySum){
		this.ReqQtySum = qtySum;
	}

	/**
	 * 
	 * @param sequence
	 */
	public void setSeq(String sequence){
		this.Seq = sequence;
	}

	/**
	 * 
	 * @param qtySum
	 */
	public void setVfyQtySum(String qtySum){
		this.VfyQtySum = qtySum;
	}

	@Override
	public String toString() {
		String str;
		str = "mCallMan： "+this.CallMan+" mCoNum: "+this.CoNum+" mCrtDate:"+this.CrtDate;
		str = str+" mCrtTime: "+this.CrtTime+" mCustCode: "+this.CustCode+" mInvType"+this.InvType;
		str = str+" mNote: "+this.Note+" mOrdAmtSum: "+this.OrdAmtSum+" mOrderDate: "+this.OrderDate;
		str = str+" mOrderStatus: "+this.OrderStatus+" mOrderStatus: "+this.OrderStatus+" mOrderType: "+this.OrderType;
		str = str+" mOrdQtySum: "+this.OrdQtySum+" mOrgCode: "+this.OrgCode+" mPer: "+this.Per;
		str = str+" mPmtStatus: "+this.PmtStatus +" mReqQtySum: "+this.ReqQtySum+" mSeq: "+this.Seq;
		str = str+" mVfyQtySum: "+this.VfyQtySum;
		if(null != Details){
			for (CgtDetail detail : Details) {
				str = str + detail.toString();
			}	
		}
		return str;
	}
	
}