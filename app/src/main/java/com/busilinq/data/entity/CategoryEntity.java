package com.busilinq.data.entity;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/5 11:44
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CategoryEntity extends BaseEntity implements Serializable {


    private String name;
    private int categoryId;//类别Id
    private int parentId;
    private String image;
    private String describe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
