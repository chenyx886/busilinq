package com.busilinq.contract.classify;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.GoodsCategoryEntity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：分类 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IClassifyView extends IBaseMvpView {


    /**
     * 获取商品分类列表
     *
     * @param categoryList
     */
    void CategoryList(List<GoodsCategoryEntity> categoryList);
}
