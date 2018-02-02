package com.busilinq.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Company：华科建邺
 * Class Describe：用户收货地址
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午4:08
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserShopAddrEntity extends BaseEntity implements Parcelable {

    //id
    private Integer addrId;
    //用户ID
    private Integer userId;
    //默认标志 0普通地址  1默认地址
    private String isDefault;
    //收货人电话
    private String cell;
    //收货人姓名
    private String name;
    //收货人国家
    private String country;
    //省
    private String province;
    //城市
    private String city;
    //区
    private String district;
    //具体详细地址
    private String specificAddr;
    //邮政编码
    private String zipCode;
    //收货单位
    private String company;

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSpecificAddr() {
        return specificAddr;
    }

    public void setSpecificAddr(String specificAddr) {
        this.specificAddr = specificAddr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.addrId);
        dest.writeValue(this.userId);
        dest.writeString(this.isDefault);
        dest.writeString(this.cell);
        dest.writeString(this.name);
        dest.writeString(this.country);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.specificAddr);
        dest.writeString(this.zipCode);
        dest.writeString(this.company);
    }

    public UserShopAddrEntity() {
    }

    protected UserShopAddrEntity(Parcel in) {
        this.addrId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isDefault = in.readString();
        this.cell = in.readString();
        this.name = in.readString();
        this.country = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.specificAddr = in.readString();
        this.zipCode = in.readString();
        this.company = in.readString();
    }

    public static final Creator<UserShopAddrEntity> CREATOR = new Creator<UserShopAddrEntity>() {
        @Override
        public UserShopAddrEntity createFromParcel(Parcel source) {
            return new UserShopAddrEntity(source);
        }

        @Override
        public UserShopAddrEntity[] newArray(int size) {
            return new UserShopAddrEntity[size];
        }
    };
}
