package com.busilinq.presenter.classify;

import com.busilinq.contract.classify.IClassifyView;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.GoodsCategoryEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：分类界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class ClassifyPresenter extends BasePresenter<IClassifyView> {

    public ClassifyPresenter(IClassifyView MvpView) {
        super(MvpView);
    }

    /**
     * 获取商品分类列表
     *
     * @param userId
     */
    public void getClassifyList(int userId) {
        addSubscription(RetrofitApiFactory.getClassifyApi().getClassifyList(userId), new SubscriberCallBack<List<GoodsCategoryEntity>>() {
            @Override
            protected void onSuccess(List<GoodsCategoryEntity> categoryList) {
                MvpView.CategoryList(categoryList);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
