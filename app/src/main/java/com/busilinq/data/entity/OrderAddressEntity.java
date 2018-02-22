package com.busilinq.data.entity;

/**
 * 订单收货地址
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderAddressEntity extends BaseEntity {

    //地址id
    private int addrId;
    //关联订单ID
    private int orderId;
    //收货人姓名
    private String name;
    //收货人电话
    private String cell;
    //收货人地址
    private String address;

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
