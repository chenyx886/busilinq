package com.busilinq.data.entity;

import java.io.Serializable;


/**
 * 订单关联商品
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderDetailsEntity extends BaseEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
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

    /**
     * 设置：
     */
    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    /**
     * 获取：
     */
    public int getDetailsId() {
        return detailsId;
    }

    /**
     * 设置：
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：
     */
    public int getOrderId() {
        return orderId;
    }


    /**
     * 设置：关联商户ID
     */
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取：关联商户ID
     */
    public int getMerchantId() {
        return merchantId;
    }


    /**
     * 设置：冗余商户名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取：冗余商户名称
     */
    public String getMerchantName() {
        return merchantName;
    }


    /**
     * 设置：关联商品ID
     */
    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：关联商品ID
     */
    public int getGoodsId() {
        return goodsId;
    }


    /**
     * 设置：冗余商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：冗余商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }


    /**
     * 设置：冗余商品单价
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：冗余商品单价
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }


    /**
     * 设置：冗余商品主图
     */
    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    /**
     * 获取：冗余商品主图
     */
    public String getGoodsImage() {
        return goodsImage;
    }


    /**
     * 设置：购买数量
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 获取：购买数量
     */
    public int getNumber() {
        return number;
    }


}
