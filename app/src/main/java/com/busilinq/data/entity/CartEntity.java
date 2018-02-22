package com.busilinq.data.entity;


/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/5 11:44
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CartEntity extends BaseEntity {
    private String userId;
    private int cartId;//购物车列表Id
    private int goodsId;//商品Id
    private int number;//商品购买数量
    private double totalMoney;//单独列表总的商品价格
    private int isChecked;//是否被选中 0：取消  1：选中


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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


    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
