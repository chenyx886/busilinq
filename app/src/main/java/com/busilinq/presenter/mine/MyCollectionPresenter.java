package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.MyCollectionView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.MyCollectionEntity;
import com.busilinq.presenter.BasePresenter;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/6 下午1:54
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyCollectionPresenter extends BasePresenter<MyCollectionView> {
    private int limit=5;

    public MyCollectionPresenter(MyCollectionView mvpView) {
        super(mvpView);
    }

    public void getMyCollectionList(String userId,int page) {
        addSubscription(RetrofitApiFactory.getMineApi().getMyCollectionList(userId,page,limit),new SubscriberCallBack<PageEntity<MyCollectionEntity>>() {
            @Override
            protected void onSuccess(PageEntity<MyCollectionEntity> list) {
                MvpView.getMyCollectionList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
