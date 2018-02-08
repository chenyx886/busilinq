package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.MyCollectionView;
import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.MyCollectionEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;
import com.chenyx.libs.utils.ToastUtils;

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

    public MyCollectionPresenter(MyCollectionView mvpView) {
        super(mvpView);
    }

    public void getMyCollectionList(int userId, int page) {
        addSubscription(RetrofitApiFactory.getMineApi().getMyCollectionList(userId, page, SysConfig.limit), new SubscriberCallBack<PageEntity<MyCollectionEntity>>() {
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

    public void deleteMyCollection(final int userId, final int page, String favoriteId) {
        addSubscription(RetrofitApiFactory.getMineApi().deleteMyCollection(userId, favoriteId, ""), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData response) {
                ToastUtils.showShort("删除成功");
                getMyCollectionList(userId, page);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
