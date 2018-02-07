package com.busilinq.xsm.data.entity;


import java.io.Serializable;

/**
 * @author AntikDing
 * @version 1.0
 * @created 03-二月-2016 13:57:10
 */
public class CgtCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2001744764028166222L;
	/**
	 * 卷烟编号，C15
	 */
	private String CgtCode;
	/**
	 * 卷烟名称，C30
	 */
	private String CgtName;
	/**
	 * 卷烟订购量，N18.1
	 */
	private String OrdQty;
	/**
	 * 卷烟需求量，N18.1
	 */
	private String ReqQty;
	/**
	 * 创建日期，C8
	 * 
	 */
	private String CrtDate;
	/**
	 * 创建时间，C8
	 */
	private String CrtTime;
	/**
	 * 备注，C32
	 */
	private String Note;

	public CgtCart(){

	}

	public String getCgtCode(){
		return CgtCode;
	}

	public String getCgtName(){
		return CgtName;
	}

	public String getOrdQty(){
		return OrdQty;
	}

	public String getReqQty(){
		return ReqQty;
	}
	
	public String getCrtDate(){
		return CrtDate;
	}

	public String getCrtTime(){
		return CrtTime;
	}

	public String getNote(){
		return Note;
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
	 * @param qty
	 */
	public void setOrdQty(String qty){
		this.OrdQty = qty;
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
	 * @param note
	 */
	public void setNote(String note){
		this.Note = note;
	}


	@Override
	public String toString() {
		String str = null;
		str = "mCgtCode :"+this.CgtCode + " mCgtName: "+this.CgtName+" mOrdQty: "+this.OrdQty;
		str = str + " mReqQty: "+this.ReqQty;
		str = str + " mCrtDate: "+this.CrtDate+" mCrtTime: "+this.CrtTime+" mNote: "+this.Note;
		return str;
	}
	
	

}