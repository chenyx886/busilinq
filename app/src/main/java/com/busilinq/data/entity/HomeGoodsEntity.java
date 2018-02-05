package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午12:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class HomeGoodsEntity extends BaseEntity {

    //商品实体
    private GoodsEntity goods;
    //价格实体
    private PriceEntity price;
    //商品详细说明
    private GoodsDetailEntity detail;
    //商品类别
    private CategoryEntity category;


    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public GoodsDetailEntity getDetail() {
        return detail;
    }

    public void setDetail(GoodsDetailEntity detail) {
        this.detail = detail;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
