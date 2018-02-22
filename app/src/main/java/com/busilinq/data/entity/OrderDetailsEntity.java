package com.busilinq.data.entity;

/**
 * 订单关联商品
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderDetailsEntity extends BaseEntity {
    //
    private int detailsId;
    //
    private int orderId;
    //关联商户ID
    private int merchantId;
    //冗余商户名称
    private String merchantName;
    //关联商品ID
    private int goodsId;
    //冗余商品名称
    private String goodsName;
    //冗余商品单价
    private Double goodsPrice;
    //冗余商品主图
    private String goodsImage;
    //购买数量
    private int number;

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
