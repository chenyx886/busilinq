package com.busilinq.xsm.data.entity;

import java.io.Serializable;


/**
 * 限量
 * @author AntikDing
 * @version 1.0
 * @created 15-二月-2016 8:30:43
 */
public class CustLimit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8306421341944871889L;
	/**
	 * 订单单次限量，C30
	 */
	private String mCoQtyLmt;
	/**
	 * 客户月限量，C30
	 */
	private String mMonQtyLmt;
	/**
	 * 客户月已订购量，C30
	 */
	private String mMonQtyOrd;


	public CustLimit(){

	}

	public String getCoQtyLmt(){
		return mCoQtyLmt;
	}

	public String getMonQtyLmt(){
		return mMonQtyLmt;
	}

	public String getMonQtyOrd(){
		return mMonQtyOrd;
	}


	/**
	 * 
	 * @param limit
	 */
	public void setCoQtyLmt(String limit){
		this.mCoQtyLmt = limit;
	}

	/**
	 * 
	 * @param limit
	 */
	public void setMonQtyLmt(String limit){
		this.mMonQtyLmt = limit;
	}

	/**
	 * 
	 * @param qty
	 */
	public void setMonQtyOrd(String qty){
		this.mMonQtyOrd = qty;
	}

	/**
	 * 
	 * @param limit
	 */


	@Override
	public String toString() {
		String str = null;
		str = "mCoQtyLmt: "+this.mCoQtyLmt +" mMonQtyLmt: "+ this.mMonQtyLmt+" mMonQtyOrd: "+ this.mMonQtyOrd ;
		return str;
	}
	
	

}