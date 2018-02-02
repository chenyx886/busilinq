package com.busilinq.contract.cart;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.UserShopAddrEntity;

import java.util.List;

/**
 * Created by LWX on 2018/1/31.
 */

public interface ISubmitOrderView extends IBaseMvpView{
    /**
     * 获取默认收货地址
     * @param addrDefaultEntity
     */
    void getDefaultAddress(UserShopAddrEntity addrDefaultEntity);
}
