package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/6 下午4:24
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyCollectionEntity extends BaseEntity{
    private UserFavoriteEntity favorite;
    private HomeGoodsEntity goods;

    public UserFavoriteEntity getFavorite() {
        return favorite;
    }

    public void setFavorite(UserFavoriteEntity favorite) {
        this.favorite = favorite;
    }

    public HomeGoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(HomeGoodsEntity goods) {
        this.goods = goods;
    }
}
