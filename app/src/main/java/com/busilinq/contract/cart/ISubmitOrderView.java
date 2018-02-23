package com.busilinq.contract.cart;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.OrderEntity;
import com.busilinq.data.entity.UserShopAddrEntity;

/**
 * Created by LWX on 2018/1/31.
 */

public interface ISubmitOrderView extends IBaseMvpView {
    /**
     * 获取默认收货地址
     *
     * @param entity
     */
    void showDefaultAddress(UserShopAddrEntity entity);

    /**
     * 订单提交成功
     *
     * @param
     */
    void submitSuccess(OrderEntity orderEntity);

    /**
     * 批量删除结果
     *
     * @param
     */
    void deleteResult();
}
