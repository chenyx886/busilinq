package com.busilinq.presenter.classify;

import com.busilinq.contract.classify.ISpecialGoodsListView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.SpecialGoodsEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

/**
 * Company：华科建邺
 * Class Describe：特价商品列表 界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SpecialGoodsListPresenter extends BasePresenter<ISpecialGoodsListView> {

    public SpecialGoodsListPresenter(ISpecialGoodsListView MvpView) {
        super(MvpView);
    }

    /**
     * 获取特价商品列表
     *
     * @param userId
     */
    public void getSpecialGoodsList(int userId, int classifyId, int page, String sort, String field) {

        addSubscription(RetrofitApiFactory.getClassifyApi().getSpecialGoodsList(userId, classifyId, page, SysConfig.limit, sort, field), new SubscriberCallBack<PageEntity<SpecialGoodsEntity>>() {
            @Override
            protected void onSuccess(PageEntity<SpecialGoodsEntity> list) {
                MvpView.ShowGoodsList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
