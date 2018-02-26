package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/2/24 下午5:17
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SpecialEntity extends BaseEntity {

    private int specialId;
    private double discount;
    private double price;

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
