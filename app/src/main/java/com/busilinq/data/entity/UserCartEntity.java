package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：用户购物车
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午3:54
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserCartEntity extends BaseEntity {

    //
    private int cartId;
    //用户ID
    private int userId;
    //商品ID
    private int goodsId;
    //商品数量
    private int number;
    //购物车内商品总金额
    private int totalMoney;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}

