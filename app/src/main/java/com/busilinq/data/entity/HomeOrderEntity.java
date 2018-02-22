package com.busilinq.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class HomeOrderEntity implements Serializable {
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
