package com.busilinq.data.entity;


/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/5 14:34
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class MainCartEntity extends BaseEntity{
    //购物车实体
    private CartEntity cart;
    //商品实体
    private HomeGoodsEntity goods;

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public HomeGoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(HomeGoodsEntity goods) {
        this.goods = goods;
    }
}
