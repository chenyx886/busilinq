package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：区域表
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:25
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class RegionEntity extends BaseEntity {

    //
    private int regionId;
    //省
    private String province;
    //城市
    private String city;
    //区
    private String district;
    //详细地址
    private String graphicDescription;
    //定位信息
    private String location;
    //备注信息
    private String remark;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
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

    public String getGraphicDescription() {
        return graphicDescription;
    }

    public void setGraphicDescription(String graphicDescription) {
        this.graphicDescription = graphicDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

