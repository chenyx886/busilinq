package com.busilinq.data.entity;

import java.io.Serializable;

/**
 * Company：华科建邺
 * Class Describe：商品价格实体
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午12:31
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class PriceEntity extends BaseEntity implements Serializable {
    //
    private int priceId;
    //
    private int goodsId;
    //
    private double salesPrice;
    //
    private double wholesalePrice;
    //
    private double purchasePrice;

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
