package com.busilinq.contract.classify;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.GoodsDetailEntity;
import com.busilinq.data.entity.UserFavoriteEntity;

/**
 * Company：华科建邺
 * Class Describe：商品详情 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IGoodsDetailView extends IBaseMvpView {


    /**
     * 显示商品详情
     *
     * @param data
     */
    void GoodsDetail(GoodsDetailEntity data);

    /**
     * 增加购物车成功
     *
     * @param cartEntity
     */
    void Success(CartEntity cartEntity);

    /**
     * 收藏或取消成功
     *
     */
    void ShowFavorite(UserFavoriteEntity data);
}
