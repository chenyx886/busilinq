package com.busilinq.xsm.data.entity;

/**
 * @author AntikDing
 * @version 1.0
 * @created 15-二月-2016 15:51:56
 */
public class CustOrderDetail extends CgtDetail  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8910869886836121488L;
	/**
	 * 订单号，C15
	 */
	private String CoNum;
	/**
	 * 客户编码，C15
	 */
	private String CustCode;
	/**
	 * 订货日期，C8
	 */
	private String OrderDate;
	/**
	 * 公司编码，C15
	 */
	private String OrgCode;
	/**
	 * 营销中心编码，C15
	 */
	private String SaleCenterCode;
	/**
	 * 营销部门编码，C15
	 */
	private String SaleDeptCode;
	/**
	 * 客户经理编码，C30
	 */
	private String SaleManCode;

	public CustOrderDetail(){

	}

	public String getCoNum(){
		return CoNum;
	}
	
	public String getCustCode(){
		return CustCode;
	}

	public String getOrderDate(){
		return OrderDate;
	}

	public String getOrgCode(){
		return OrgCode;
	}

	public String getSaleCenterCode(){
		return SaleCenterCode;
	}

	public String getSaleDeptCode(){
		return SaleDeptCode;
	}

	public String getSaleManCode(){
		return SaleManCode;
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
	 * @param code
	 */
	public void setCustCode(String code){
		this.CustCode = code;
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
	 * @param code
	 */
	public void setOrgCode(String code){
		this.OrgCode = code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setSaleCenterCode(String code){
		this.SaleCenterCode = code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setSaleDeptCode(String code){
		this.SaleDeptCode = code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setSaleManCode(String code){
		this.SaleManCode = code;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str = str+" mCoNum: "+this.CoNum+" mOrderDate: "+this.OrderDate+" mOrgCode: "+this.OrgCode+" mCustCode: "+this.CustCode;
		str = str+" mSaleCenterCode: "+this.SaleCenterCode+" mSaleDeptCode: "+this.SaleDeptCode+" mSaleManCode: "+this.SaleManCode;
		return str;
	}
	
	

}