package com.busilinq.presenter.home;

import com.busilinq.contract.home.IMainView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.GoodsEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(IMainView MvpView) {
        super(MvpView);
    }

    /**
     * 获取轮播广告
     *
     * @param type
     */
    public void getBannerList(String type) {
        addSubscription(RetrofitApiFactory.getHomeApi().banner(type), new SubscriberCallBack<List<BannerEntity>>() {
            @Override
            protected void onSuccess(List<BannerEntity> bannerList) {
                MvpView.BannerList(bannerList);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }

    /**
     * 获取推荐商品列表
     *
     * @param page
     */
    public void getGoodsList(int page) {
        addSubscription(RetrofitApiFactory.getHomeApi().recommend(page, SysConfig.limit), new SubscriberCallBack<PageEntity<GoodsEntity>>() {
            @Override
            protected void onSuccess(PageEntity<GoodsEntity> bannerList) {
                MvpView.GoodsList(bannerList);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
