package com.busilinq.data.entity;


/**
 * Company：华科建邺
 * Class Describe： 公共商品
 * Create Person：Chenyx
 * Create Time：2017/11/13 下午2:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsEntity extends BaseEntity {
    //商品id
    private int goodsId;
    //删除状态：1.启用 -1.未启用 （后台未启用页面不显示，前台显示已下架）
    //创建时间
    //更新时间
    //所属类别id
    private int categoryId;
    //商品名称
    private String name;
    //别名
    private String nickname;
    //条码
    private String barcode;
    //商品主图
    private String image;
    //简要描述
    private String simpleDescribe;
    //
    private String detailDescribe;
    //商品规格
    private String standard;
    //基本单位
    private String unit;
    //建议价格
    private double price;


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSimpleDescribe() {
        return simpleDescribe;
    }

    public void setSimpleDescribe(String simpleDescribe) {
        this.simpleDescribe = simpleDescribe;
    }

    public String getDetailDescribe() {
        return detailDescribe;
    }

    public void setDetailDescribe(String detailDescribe) {
        this.detailDescribe = detailDescribe;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
