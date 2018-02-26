package com.busilinq.contract.classify;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.HomeGoodsEntity;

/**
 * Company：华科建邺
 * Class Describe：商品搜索 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IGoodsSearchView extends IBaseMvpView {


    /**
     * 商品搜索
     *
     * @param list
     */
    void ShowGoodsList(PageEntity<HomeGoodsEntity> list);
}
