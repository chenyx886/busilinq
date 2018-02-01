package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.UserShopAddrEntity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/1 下午2:37
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface AddressListView extends IBaseMvpView {

    /**
     * 获取收货地址列表
     * @param addrEntityList
     */
    void getAddressList(List<UserShopAddrEntity> addrEntityList);
}
