package com.busilinq.presenter.classify;

import com.busilinq.contract.classify.IGoodsListView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.GoodsEntity;
import com.busilinq.presenter.BasePresenter;

/**
 * Company：华科建邺
 * Class Describe：商品列表 界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsListPresenter extends BasePresenter<IGoodsListView> {

    public GoodsListPresenter(IGoodsListView MvpView) {
        super(MvpView);
    }

    /**
     * 获取商品分类列表
     *
     * @param userId
     */
    public void getGoodsList(String userId) {
        addSubscription(RetrofitApiFactory.getClassifyApi().getGoodsList(userId), new SubscriberCallBack<PageEntity<GoodsEntity>>() {
            @Override
            protected void onSuccess(PageEntity<GoodsEntity> list) {
                MvpView.GoodsList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
