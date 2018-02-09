package com.busilinq.data.entity;

import java.io.Serializable;

/**
 * @author aleyds
 * @Copyright 华科建邺
 * @类的作用：
 * @创建时间: 2018/2/8 13:31
 * @版本号: 1.0
 * @包名: com.busilinq.orders.facade.po
 */
public class OrderGoodsPO implements Serializable{
    private int goodsId;//商品ID
    private int merchantId;//商户ID 暂时为空
    private int count;//商品数量

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
