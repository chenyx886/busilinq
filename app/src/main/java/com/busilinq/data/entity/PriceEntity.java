package com.busilinq.data.entity;

import java.math.BigDecimal;

/**
 * Company：华科建邺
 * Class Describe：商品价格实体
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午12:31
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class PriceEntity extends BaseEntity {
    //
    private int priceId;
    //
    private int goodsId;
    //
    private BigDecimal salesPrice;
    //
    private BigDecimal wholesalePrice;
    //
    private BigDecimal purchasePrice;

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

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
