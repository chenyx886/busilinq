package com.busilinq.contract.classify;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.SpecialGoodsEntity;

/**
 * Company：华科建邺
 * Class Describe：特价商品列表 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface ISpecialGoodsListView extends IBaseMvpView {


    /**
     * 显示特价商品列表
     *
     * @param list
     */
    void ShowGoodsList(PageEntity<SpecialGoodsEntity> list);
}
