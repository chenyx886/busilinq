package com.busilinq.contract.cart;

import com.busilinq.contract.IBaseMvpView;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:37
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface INewlyAddedAddress extends IBaseMvpView{

    /**
     * 成功添加收货地址
     */
    void addAddressSuccess();

    void addAddressInfo();
}
