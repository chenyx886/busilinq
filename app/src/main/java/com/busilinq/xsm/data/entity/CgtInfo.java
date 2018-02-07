package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * 卷烟信息
 * 
 * @author AntikDing
 * @version 1.0
 * @created 28-一月-2016 9:14:49
 */
public class CgtInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9021869899675238921L;
	/**
	 * 经营类型1:新品,2:紧俏,3:顺销,9:其他，C1
	 */
	private String BrdType;
	/**
	 * 替代品类型，C32
	 */
	private String CateGory;
	/**
	 * 品牌名称，C16
	 */
	private String CgtBrandName;
	/**
	 * 盒码，C16
	 */
	private String CgtCartonCode;
	/**
	 * 卷烟编码，C30
	 */
	private String CgtCode;
	/**
	 * 卷烟名称，C64
	 */
	private String CgtName;
	/**
	 * 条码，C16
	 */
	private String CgtPacketCode;
	/**
	 * 卷烟类型，C8
	 */
	private String CgtTypeName;
	/**
	 * 一氧化碳(mg)，N18.1
	 */
	private String CoCont;
	/**
	 * 烟气烟碱(mg)，N18.1
	 */
	private String GasNicotine;
	/**
	 * 是否推荐，C1
	 */
	private String IsAdvise;
	/**
	 * 是否纳入到订单量限量限制，C1
	 */
	private String IsCoLmt;
	/**
	 * 是否纳入到订单订购量倍数的限制，C1
	 */
	private String IsCoMulti;
	/**
	 * 是否收藏，C1
	 */
	private String IsFav;
	/**
	 * 产地：/0:省内,1:省外,2:其它,3:国外，C1
	 */
	private String IsImported;
	/**
	 * 是否使用单品种卷烟订购量的倍数限制，C1
	 */
	private String IsMulti;
	/**
	 * 是否促销，C1
	 */
	private String IsPromote;
	/**
	 * 是否短缺，C1
	 */
	private String IsShort;
	/**
	 * 是否有活动，C1
	 */
	private String IsTask;
	/**
	 * 品牌拥有者编码，C15
	 */
	private String MfrId;
	/**
	 * 品牌拥有者名称，C64
	 */
	private String MfrIdName;
	/**
	 * 批发价，N18.6
	 */
	private double Price;
	/**
	 * 促销内容，C256
	 */
	private String Promote;
	/**
	 * 可用量，N18.1
	 */
	private String QtyLmt;
	/**
	 * 需求量最大值，C30
	 */
	private String ReqQtyMax;
	/**
	 * 零售价，N18.6
	 */
	private String RtlPrice;
	/**
	 * 卷烟简码，C8
	 */
	private String ShortCode;
	/**
	 * 焦油含量，N18.2
	 */
	private String Tarval;
	/**
	 * 单位：条，C4
	 */
	private String UmSaleName;

	public CgtInfo() {

	}

	public String getBrdType() {
		return BrdType;
	}

	public String getCateGory() {
		return CateGory;
	}

	public String getCgtBrandName() {
		return CgtBrandName;
	}

	public String getCgtCartonCode() {
		return CgtCartonCode;
	}

	public String getCgtCode() {
		return CgtCode;
	}

	public String getCgtName() {
		return CgtName;
	}

	public String getCgtPacketCode() {
		return CgtPacketCode;
	}

	public String getCgtTypeName() {
		return CgtTypeName;
	}

	public String getCoCont() {
		return CoCont;
	}

	public String getGasNicotine() {
		return GasNicotine;
	}

	public String getIsAdvise() {
		return IsAdvise;
	}

	public String getIsCoLmt() {
		return IsCoLmt;
	}

	public String getIsCoMulti() {
		return IsCoMulti;
	}

	public String getIsFav() {
		return IsFav;
	}

	public String getIsImported() {
		return IsImported;
	}

	public String getIsMulti() {
		return IsMulti;
	}

	public String getIsPromote() {
		return IsPromote;
	}

	public String getIsShort() {
		return IsShort;
	}

	public String getIsTask() {
		return IsTask;
	}

	public String getMfrId() {
		return MfrId;
	}

	public String getMfrIdName() {
		return MfrIdName;
	}

	public double getPrice() {
		return Price;
	}

	public String getPromote() {
		return Promote;
	}

	public String getQtyLmt() {
		return QtyLmt;
	}

	public String getReqQtyMax() {
		return ReqQtyMax;
	}

	public String getRtlPrice() {
		return RtlPrice;
	}

	public String getShortCode() {
		return ShortCode;
	}

	public String getTarval() {
		return Tarval;
	}

	public String getUmSaleName() {
		return UmSaleName;
	}

	/**
	 * 
	 * @param brdType
	 */
	public void setBrdType(String brdType) {
		this.BrdType = brdType;
	}

	/**
	 * 
	 * @param cateGory
	 */
	public void setCateGory(String cateGory) {
		this.CateGory = cateGory;
	}

	/**
	 * 
	 * @param name
	 */
	public void setCgtBrandName(String name) {
		this.CgtBrandName = name;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCgtCartonCode(String code) {
		this.CgtCartonCode = code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCgtCode(String code) {
		this.CgtCode = code;
	}

	/**
	 * 
	 * @param name
	 */
	public void setCgtName(String name) {
		this.CgtName = name;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCgtPacketCode(String code) {
		this.CgtPacketCode = code;
	}

	/**
	 * 
	 * @param name
	 */
	public void setCgtTypeName(String name) {
		this.CgtTypeName = name;
	}

	/**
	 * 
	 * @param coCont
	 */
	public void setCoCont(String coCont) {
		this.CoCont = coCont;
	}

	/**
	 * 
	 * @param gasNicotine
	 */
	public void setGasNicotine(String gasNicotine) {
		this.GasNicotine = gasNicotine;
	}

	/**
	 * 
	 * @param isAdvise
	 */
	public void setIsAdvise(String isAdvise) {
		this.IsAdvise = isAdvise;
	}

	/**
	 * 
	 * @param isCoLmt
	 */
	public void setIsCoLmt(String isCoLmt) {
		this.IsCoLmt = isCoLmt;
	}

	/**
	 * 
	 * @param isCoMulti
	 */
	public void setIsCoMulti(String isCoMulti) {
		this.IsCoMulti = isCoMulti;
	}

	/**
	 * 
	 * @param isFav
	 */
	public void setIsFav(String isFav) {
		this.IsFav = isFav;
	}

	/**
	 * 
	 * @param isImported
	 */
	public void setIsImported(String isImported) {
		this.IsImported = isImported;
	}

	/**
	 * 
	 * @param isMulti
	 */
	public void setIsMulti(String isMulti) {
		this.IsMulti = isMulti;
	}

	/**
	 * 
	 * @param isPromote
	 */
	public void setIsPromote(String isPromote) {
		this.IsPromote = isPromote;
	}

	/**
	 * 
	 * @param isShort
	 */
	public void setIsShort(String isShort) {
		this.IsShort = isShort;
	}

	/**
	 * 
	 * @param isTask
	 */
	public void setIsTask(String isTask) {
		this.IsTask = isTask;
	}

	/**
	 * 
	 * @param mfrId
	 */
	public void setMfrId(String mfrId) {
		this.MfrId = mfrId;
	}

	/**
	 * 
	 * @param name
	 */
	public void setMfrIdName(String name) {
		this.MfrIdName = name;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.Price = price;
	}

	/**
	 * 
	 * @param promote
	 */
	public void setPromote(String promote) {
		this.Promote = promote;
	}

	/**
	 * 
	 * @param qtyLmt
	 */
	public void setQtyLmt(String qtyLmt) {
		this.QtyLmt = qtyLmt;
	}

	/**
	 * 
	 * @param max
	 */
	public void setReqQtyMax(String max) {
		this.ReqQtyMax = max;
	}

	/**
	 * 
	 * @param price
	 */
	public void setRtlPrice(String price) {
		this.RtlPrice = price;
	}

	/**
	 * 
	 * @param code
	 */
	public void setShortCode(String code) {
		this.ShortCode = code;
	}

	/**
	 * 
	 * @param tarval
	 */
	public void setTarval(String tarval) {
		this.Tarval = tarval;
	}

	/**
	 * 
	 * @param name
	 */
	public void setUmSaleName(String name) {
		this.UmSaleName = name;
	}

	@Override
	public String toString() {
		String str = null;
		str = "mBrdType: " + this.BrdType + " mCateGory: " + this.CateGory + " mCgtBrandName: " + this.CgtBrandName;
		str = str + " mCgtCartonCode: " + this.CgtCartonCode + " mCgtCode: " + this.CgtCode + " mCgtName: "
				+ this.CgtName;
		str = str + " mCgtPacketCode: " + this.CgtPacketCode + " mCgtTypeName: " + this.CgtTypeName + " mCoCont: "
				+ this.CoCont;
		str = str + " mGasNicotine: " + this.GasNicotine + " mIsAdvise: " + this.IsAdvise + " mIsCoLmt: "
				+ this.IsCoLmt;
		str = str + " mIsCoMulti: " + this.IsCoMulti + " mIsFav: " + this.IsFav + " mIsImported: " + this.IsImported;
		str = str + " mIsShort: " + this.IsShort + " mIsTask: " + this.IsTask + " mMfrId: " + this.MfrId;
		str = str + " mMfrName: " + this.MfrIdName + " mPrice: " + this.Price + " mPromote: " + this.Promote;
		str = str + " mQtyLmt: " + this.QtyLmt + " mReqQtyMax: " + this.ReqQtyMax + " mRtlPrice: " + this.RtlPrice;
		str = str + " mShortCode: " + this.ShortCode + " mTarval: " + this.Tarval + " mUmSaleName: " + this.UmSaleName;
		return str;
	}

}