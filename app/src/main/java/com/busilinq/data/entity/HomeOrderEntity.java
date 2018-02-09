package com.busilinq.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author aleyds
 * @Copyright 华科建邺
 * @类的作用：
 * @创建时间: 2018/2/7 8:56
 * @版本号: 1.0
 * @包名: com.busilinq.orders.facade.po
 */
public class HomeOrderEntity implements Serializable{
    private OrderEntity order;
    private OrderAddressEntity addr;
    private OrderShippingEntity shipping;
    private List<OrderDetailsEntity> details;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public OrderAddressEntity getAddr() {
        return addr;
    }

    public void setAddr(OrderAddressEntity addr) {
        this.addr = addr;
    }

    public OrderShippingEntity getShipping() {
        return shipping;
    }

    public void setShipping(OrderShippingEntity shipping) {
        this.shipping = shipping;
    }

    public List<OrderDetailsEntity> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailsEntity> details) {
        this.details = details;
    }
}
