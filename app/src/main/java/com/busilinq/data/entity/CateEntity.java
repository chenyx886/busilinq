package com.busilinq.data.entity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe： 销售分类实体
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CateEntity extends BaseEntity {
    /**
     * 分类id
     */
    private int id;
    /**
     * 分类标题
     */
    private String title;
    /**
     * 商品列表
     */
    private List<GoodEntity> goodslist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GoodEntity> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodEntity> goodslist) {
        this.goodslist = goodslist;
    }
}
