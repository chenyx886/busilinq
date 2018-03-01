package com.busilinq.data.entity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：商品类别
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsCategoryEntity extends BaseEntity {
    //商品种类id
    private int id;
    //删除状态：1.启用 -1.未启用 （后台未启用页面不显示，前台显示已下架）
    //创建时间
    //更新时间
    //父种类id 为零表示顶层种类
    private int parentId;
    //商品种类名称
    private String name;
    //商品种类识别图-图标
    private String imageUrl;
    //简要描述
    private String describe;
    //子分类
    private List<GoodsCategoryEntity> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<GoodsCategoryEntity> getList() {
        return list;
    }

    public void setList(List<GoodsCategoryEntity> list) {
        this.list = list;
    }
}
