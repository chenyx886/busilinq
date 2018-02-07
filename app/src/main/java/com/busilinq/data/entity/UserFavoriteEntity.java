package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：收藏表实体
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午4:06
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserFavoriteEntity extends BaseEntity {

    //收藏id
    private String favoriteId;
    //用户ID
    private int userId;
    //商品ID
    private int goodsId;

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
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
}
