package com.busilinq.contract.home;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.GoodEntity;

/**
 * Company：华科建邺
 * Class Describe：首页 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IMainView extends IBaseMvpView {

    /**
     * 获取轮播广告
     *
     * @param bannerList
     */
    void BannerList(PageEntity<BannerEntity> bannerList);

    void GoodsList(PageEntity<GoodEntity> bannerList);
}
