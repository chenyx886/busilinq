package com.busilinq.data.entity;


import java.util.List;

/**
 * @author aleyds
 * @Copyright 华科建邺
 * @类的作用：
 * @创建时间: 2018/2/8 11:32
 * @版本号: 1.0
 * @包名: com.busilinq.webs.controller.ask
 */
public class OrderCreateASK extends BaseEntity {
    private int userId;//下单用户ID
    private int addressId;//收货地址ID
    private int shippingId;//收货方式ID 暂时为空
    private int activityId;//活动ID 暂时为空
    private List<OrderGoodsPO> goodsList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public List<OrderGoodsPO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsPO> goodsList) {
        this.goodsList = goodsList;
    }
}
