package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * 新商盟客户信息
 * @author AntikDing
 * @version 1.0
 * @created 28-一月-2016 9:14:49
 */
public class CustInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 282986307985489575L;
	/**
	 * 开户帐号，C32
	 * 
	 */
	private String Account;
	/**
	 * 送达日期，C8
	 * 
	 */
	private String ArrDate;
	/**
	 * 订货开始时间，C..8
	 * 
	 */
	private String BeginDate;
	/**
	 * 开户行名称，C..30
	 * 
	 */
	private String BankName;
	/**
	 * 经营地址，C..128
	 */
	private String BusiAddr;
	/**
	 * 客户编码,C..12
	 */
	private String CustCode;
	/**
	 * 市场类型，C..20
	 * 
	 */
	private String CustGeoType;
	/**
	 * 客户分类，C..30
	 * 
	 */
	private String CustLmtType;
	/**
	 * 客户名称，C..100
	 */
	private String CustName;
	/**
	 * 客户档位，C..30
	 * 
	 */
	private String CustSeg;
	/**
	 * 经营规模，C..30
	 * 
	 */
	private String CustSize;
	/**
	 * 客户电话,C..16
	 */
	private String CustTel;
	/**
	 * 自定义订货开始时间，C8
	 * 
	 */
	private String DefineOrderBeginTime;
	/**
	 * 自定义订货结束时间，C8
	 * 
	 */
	private String DefineOrderEndTime;
	/**
	 * 订货结束时间，C8
	 * 
	 */
	private String EndDate;
	/**
	 * 是否为网上支付用户，C1
	 * 
	 */
	private String IsOnLinePay;
	/**
	 * 许可证号，C..12
	 */
	private String LicenseCode;
	/**
	 * 许可证日期，C8
	 */
	private String LicenseDate;
	/**
	 * 负责人,C..100
	 */
	private String Manager;
	/**
	 * 预计下次订货日期，C8
	 * 
	 */
	private String NextOrderDate;
	/**
	 * 备注，C..200
	 * 
	 */
	private String Note;
	/**
	 * 本月订货次数,N4
	 */
	private String OrderTimersOfMonth;
	/**
	 * 订货日期，C8
	 */
	private String OrderDate;
	/**
	 * 订货周期，C8
	 */
	private String OrderPeriod;
	/**
	 * 订货电话,C..16
	 */
	private String OrderTel;
	/**
	 * 订货方式，C..30,10：电话，20：配货，31：网上，32：电视，33：手机
	 * 
	 */
	private String OrderWay;
	/**
	 * 公司编码,C..12
	 */
	private String OrgCode;
	/**
	 * 支付类型，C2
	 * 
	 */
	private String PayType;
	/**
	 * 周期类型，C..200
	 */
	private String PeriodType;
	/**
	 * 周期订货开始时间，C8
	 */
	private String PrdStartDate;
	/**
	 * 零售业态，C..20
	 * 
	 */
	private String RtlCustType;
	/**
	 * 营销中心编码，C..14
	 */
	private String SaleCenterCode;
	/**
	 * 营销中心名称，C..100
	 */
	private String SaleCenterName;
	/**
	 * 营销部编码，C..14
	 */
	private String SaleDeptCode;
	/**
	 * 营销部编码，C..100
	 */
	private String SaleDeptName;
	/**
	 * 客户经理编码，C..16
	 */
	private String SaleManCode;
	/**
	 * 客户经理手机号，C..16
	 */
	private String SaleManMobile;
	/**
	 * 客户经理名称，C..100
	 */
	private String SaleManName;
	/**
	 * 客户经理电话，C..16
	 */
	private String SaleManTel;
	/**
	 * 客户简码，C..15
	 */
	private String ShortCode;
	/**
	 * 客户简称，C..100
	 */
	private String ShortName;
	/**
	 * 客户状态 01:新增，02：有效，03：暂停，04:无效
	 */
	private String Status;
	/**
	 * 仓库编码,C..30
	 */
	private String WhseCode;

	public CustInfo(){

	}

	public String getAccount(){
		return Account;
	}

	public String getArrDate(){
		return ArrDate;
	}

	public String getBankName(){
		return BankName;
	}
	
	public String getBeginDate(){
		return BeginDate;
	}

	public String getBusiAddr(){
		return BusiAddr;
	}

	public String getCustCode(){
		return CustCode;
	}

	public String getCustGeoType(){
		return CustGeoType;
	}

	public String getCustLmtType(){
		return CustLmtType;
	}

	public String getCustName(){
		return CustName;
	}
	
	public String getCustSeg(){
		return CustSeg;
	}

	public String getCustSize(){
		return CustSize;
	}

	public String getCustTel(){
		return CustTel;
	}

	public String getDefineOrderBeginTime(){
		return DefineOrderBeginTime;
	}

	public String getDefineOrderEndTime(){
		return DefineOrderEndTime;
	}
	
	public String getEndDate(){
		return EndDate;
	}

	public String getIsOnLinePay(){
		return IsOnLinePay;
	}

	public String getLicenseCode(){
		return LicenseCode;
	}

	public String getLicenseDate(){
		return LicenseDate;
	}

	public String getManager(){
		return Manager;
	}

	public String getNextOrderDate(){
		return NextOrderDate;
	}

	public String getNote(){
		return Note;
	}
	
	public String getOrderPeriod(){
		return OrderPeriod;
	}

	public String getOrderTimersOfMonth(){
		return OrderTimersOfMonth;
	}

	public String getOrderDate(){
		return OrderDate;
	}

	public String getOrderTel(){
		return OrderTel;
	}

	public String getOrderWay(){
		return OrderWay;
	}
	
	public String getOrgCode(){
		return OrgCode;
	}


	public String getPayType(){
		return PayType;
	}

	public String getPeriodType(){
		return PeriodType;
	}

	public String getPrdStartDate(){
		return PrdStartDate;
	}

	public String getRtlCustType(){
		return RtlCustType;
	}

	public String getSaleCenterCode(){
		return SaleCenterCode;
	}

	public String getSaleCenterName(){
		return SaleCenterName;
	}

	public String getSaleDeptCode(){
		return SaleDeptCode;
	}

	public String getSaleDeptName(){
		return SaleDeptName;
	}

	public String getSaleManCode(){
		return SaleManCode;
	}
	
	public String getSaleManMobile(){
		return SaleManMobile;
	}
	
	public String getSalemanName(){
		return SaleManName;
	}

	public String getSaleManTel(){
		return SaleManTel;
	}

	public String getShortCode(){
		return ShortCode;
	}
	
	public String getShortName(){
		return ShortName;
	}

	public String getStatus(){
		return Status;
	}

	public String getWhseCode(){
		return WhseCode;
	}

	/**
	 * 
	 * @param account
	 */
	public void setAccount(String account){
		this.Account = account;
	}

	/**
	 * 
	 * @param date
	 */
	public void setArrDate(String date){
		this.ArrDate = date;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void setBeginDate(String date){
		this.BeginDate = date;
	}

	/**
	 * 
	 * @param name
	 */
	public void setBankName(String name){
		this.BankName = name;
	}

	/**
	 * 
	 * @param address
	 */
	public void setBusiAddr(String address){
		this.BusiAddr = address;
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
	 * @param type
	 */
	public void setCustGeoType(String type){
		this.CustGeoType = type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setCustLmtType(String type){
		this.CustLmtType = type;
	}

	/**
	 * 
	 * @param name
	 */
	public void setCustName(String name){
		this.CustName = name;
	}
	
	/**
	 * 
	 * @param seg
	 */
	public void setCustSeg(String seg){
		this.CustSeg = seg;
	}

	/**
	 * 
	 * @param size
	 */
	public void setCustSize(String size){
		this.CustSize = size;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setCustTel(String tel){
		this.CustTel = tel;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDefineOderBeginTime(String time){
		this.DefineOrderBeginTime = time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setDefineOrderEndTime(String time){
		this.DefineOrderEndTime = time;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void setEndDate(String date){
		this.EndDate = date;
	}

	/**
	 * 
	 * @param isOnLinePay
	 */
	public void setIsOnLinePay(String isOnLinePay){
		this.IsOnLinePay = isOnLinePay;
	}

	/**
	 * 
	 * @param code
	 */
	public void setLicenseCode(String code){
		this.LicenseCode = code;
	}

	/**
	 * 
	 * @param date
	 */
	public void setLicenseDate(String date){
		this.LicenseDate = date;
	}

	/**
	 * 
	 * @param manager
	 */
	public void setManager(String manager){
		this.Manager = manager;
	}

	/**
	 * 
	 * @param date
	 */
	public void setNextOderDate(String date){
		this.NextOrderDate = date;
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
	 * @param times
	 */
	public void setOderTimersOfMonth(String times){
		this.OrderTimersOfMonth = times;
	}

	/**
	 * 
	 * @param way
	 */
	public void setOderWay(String way){
		this.OrderWay = way;
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
	 * @param period
	 */
	public void setOrderPeriod(String period){
		this.OrderPeriod = period;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setOrderTel(String tel){
		this.OrderTel = tel;
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
	 * @param type
	 */
	public void setPayType(String type){
		this.PayType = type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setPeriodType(String type){
		this.PeriodType = type;
	}

	/**
	 * 
	 * @param date
	 */
	public void setPrdStartDate(String date){
		this.PrdStartDate = date;
	}

	/**
	 * 
	 * @param type
	 */
	public void setRtlCustType(String type){
		this.RtlCustType = type;
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
	 * @param name
	 */
	public void setSaleCenterName(String name){
		this.SaleCenterName = name;
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
	 * @param name
	 */
	public void setSaleDeptName(String name){
		this.SaleDeptName = name;
	}

	/**
	 * 
	 * @param code
	 */
	public void setSaleManCode(String code){
		this.SaleManCode = code;
	}
	
	/**
	 * 
	 * @param mobile
	 */
	public void setSaleManMobile(String mobile){
		this.SaleManMobile = mobile;
	}
	
	/**
	 * 
	 * @param mobile
	 */
	public void setSaleManName(String name){
		this.SaleManName = name;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setSaleManTel(String tel){
		this.SaleManTel = tel;
		
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
	 * @param code
	 */
	public void setShortName(String name){
		this.ShortName = name;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status){
		this.Status = status;
	}

	/**
	 * 
	 * @param code
	 */
	public void setWhseCode(String code){
		this.WhseCode = code;
	}

	@Override
	public String toString() {
		String str;
		str = "mAccount: " + this.Account +" mArrDate: " + this.ArrDate +" mBankName :" + this.BankName;
		str = str + " mBeginDate: "+ this.BeginDate+" mBusiAddr: "+this.BusiAddr+ " mCustCode: "+this.CustCode;
		str = str + " mCustGeoType: "+ this.CustGeoType+" mCustLmtType: "+this.CustLmtType+ " mCustName: "+this.CustName;
		str = str + " mCustSeg: "+ this.CustSeg+" mCustSize: "+this.CustSize+ " mCustTel: "+this.CustTel;
		str = str + " mDefineOrderBeginTime: "+ this.DefineOrderBeginTime+" mDefineOrderEndTime: "+this.DefineOrderEndTime+ " mEndDate: "+this.EndDate;
		str = str + " mLicenseCode: "+ this.LicenseCode+" mLicenseDate: "+this.LicenseDate+ " mIsOnLinePay: "+this.IsOnLinePay;
		str = str + " mManager: "+ this.Manager+" mNextOrderDate: "+this.NextOrderDate+ " mNote: "+this.Note;
		str = str + " mOrderDate: "+ this.OrderDate+" mOrderPeriod: "+this.OrderPeriod+ " mOrderTel: "+this.OrderTel;
		str = str + " mOrderTimersOfMonth: "+ this.OrderTimersOfMonth+" mOrderWay: "+this.OrderWay+ " mPayType: "+this.PayType;
		str = str + " mPeriodType: "+ this.PeriodType+" mPrdStartDate: "+this.PrdStartDate+ " mRtlCustType: "+this.RtlCustType;
		str = str + " mSaleCenterCode: "+ this.SaleCenterCode+" mSaleCenterName: "+this.SaleCenterName+ " mSaleDeptCode: "+this.SaleDeptCode;
		str = str + " mSaleDeptCode: "+ this.SaleDeptCode+" mSaleDeptName: "+this.SaleDeptName+ " mSaleManCode: "+this.SaleManCode;
		str = str + " mSaleManMobile: "+ this.SaleManMobile+" mSaleManName: "+this.SaleManName+ " mSaleManTel: "+this.SaleManTel;
		str = str + " mShortCode: "+ this.ShortCode+" mShortName: "+this.ShortName+ " mSaleManCode: "+this.SaleManCode;
		str = str + " mStatus: "+ this.Status+" mWhseCode: "+this.WhseCode;
		return str;
	}
	
	

}