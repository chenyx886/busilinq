package com.busilinq.contract.cart;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.MainCartEntity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/1 11:44
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface ICartView extends IBaseMvpView {

    /**
     * 购物车列表
     *
     * @param cartList
     */
    void CartList(PageEntity<MainCartEntity> cartList);

    void Success(int position, CartEntity cartEntity);

    /**
     * 购物车无数据时显示的布局
     */
    void showEmptyView();
}
