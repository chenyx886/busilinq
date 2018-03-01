package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.MyCollectionView;
import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.MyCollectionEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

/**
 * Company：华科建邺
 * Class Describe：我的收藏
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

    /**
     * 收藏列表
     *
     * @param userId
     * @param page
     */
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

    /**
     * 删除收藏
     *
     * @param userId
     * @param favoriteId
     */
    public void deleteMyCollection(int userId, final int pos, String favoriteId) {
        addSubscription(RetrofitApiFactory.getMineApi().deleteMyCollection(userId, favoriteId, ""), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData response) {
                MvpView.deleteSuccess(pos);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
