package com.busilinq.data.entity;

/**
 * 订单支付和配送方式
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderShippingEntity extends BaseEntity {

    //
    private int shippingId;
    //关联订单ID
    private int orderId;
    //配送方式
    private String shippingType;
    //配送状态
    private String shippingStatus;
    //支付方式 支付方式:0(默认,代表未支付),1(支付宝扫码),11(支付宝反扫),2(微信扫码),22(微信反扫),3(苹果支付),4(银联),其他暂略
    private String payType;
    //支付状态
    private String payStatus;
    //支付通道0线上支付
    private String payChannel;

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
}
