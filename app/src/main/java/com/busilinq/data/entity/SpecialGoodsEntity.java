package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：特价商品列表 实体
 * Create Person：Chenyx
 * Create Time：2018/2/24 下午5:16
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SpecialGoodsEntity extends BaseEntity {

    //价格实体
    private SpecialEntity special;
    //商品基本参数
    private HomeGoodsEntity goods;

    public SpecialEntity getSpecial() {
        return special;
    }

    public void setSpecial(SpecialEntity special) {
        this.special = special;
    }

    public HomeGoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(HomeGoodsEntity goods) {
        this.goods = goods;
    }
}
