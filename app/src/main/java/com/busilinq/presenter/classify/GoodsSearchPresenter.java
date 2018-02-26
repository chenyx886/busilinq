package com.busilinq.presenter.classify;

import com.busilinq.contract.classify.IGoodsSearchView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

/**
 * Company：华科建邺
 * Class Describe：商品搜索 界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsSearchPresenter extends BasePresenter<IGoodsSearchView> {

    public GoodsSearchPresenter(IGoodsSearchView MvpView) {
        super(MvpView);
    }

    /**
     * 获取商品分类列表
     *
     * @param userId
     */
    public void getGoodsSearchList(int userId, String classifyId, int page, String name) {
        addSubscription(RetrofitApiFactory.getClassifyApi().getGoodsSearchList(userId, classifyId, page, SysConfig.limit, name), new SubscriberCallBack<PageEntity<HomeGoodsEntity>>() {
            @Override
            protected void onSuccess(PageEntity<HomeGoodsEntity> list) {
                MvpView.ShowGoodsList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
