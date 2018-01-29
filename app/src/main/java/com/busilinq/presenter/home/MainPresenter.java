package com.busilinq.presenter.home;

import com.busilinq.contract.home.IMainView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.GoodEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

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
     */
    public void getBannerList() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", "1");
        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(RetrofitApiFactory.getHomeApi().banner(body), new SubscriberCallBack<PageEntity<BannerEntity>>() {
            @Override
            protected void onSuccess(PageEntity<BannerEntity> bannerList) {
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
     */
    public void getGoodsList(int p) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.get().getUserId());
        param.put("p", p);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(RetrofitApiFactory.getHomeApi().recommend(body), new SubscriberCallBack<PageEntity<GoodEntity>>() {
            @Override
            protected void onSuccess(PageEntity<GoodEntity> bannerList) {
                MvpView.GoodsList(bannerList);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
