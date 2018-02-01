package com.busilinq.contract.classify;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.GoodsEntity;
import com.busilinq.data.entity.HomeGoodsEntity;

/**
 * Company：华科建邺
 * Class Describe：商品列表 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IGoodsListView extends IBaseMvpView {


    /**
     * 获取商品列表
     *
     * @param list
     */
    void GoodsList(PageEntity<HomeGoodsEntity> list);
}
