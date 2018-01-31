package com.busilinq.data.entity;

import android.icu.math.BigDecimal;

/**
 * Company：华科建邺
 * Class Describe：商户商品价格
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MerchantGoodsPriceEntity extends BaseEntity {
    //
    private int priceId;
    //删除状态：1.启用 -1.未启用 （后台未启用页面不显示，前台显示已下架）
    //创建时间
    //更新时间
    //商品id
    private Integer goodsId;
    //批发价
    private BigDecimal salesPrice;
    //
    private BigDecimal wholesalePrice;
    //进货价
    private BigDecimal purchasePrice;

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
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
