package com.busilinq.presenter.home;

import com.busilinq.contract.classify.IGoodsSearchView;
import com.busilinq.contract.home.IInfoNoticeView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.InfoNoticeEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

/**
 * Company：华科建邺
 * Class Describe：信息公告
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class InfoNoticePresenter extends BasePresenter<IInfoNoticeView> {

    public InfoNoticePresenter(IInfoNoticeView MvpView) {
        super(MvpView);
    }

    /**
     * 获取消息公告列表
     * @param userId
     */
    public void getInfoNoticeList(int userId,int page) {
        addSubscription(RetrofitApiFactory.getHomeApi().getInfoNoticeList(userId, page,SysConfig.limit), new SubscriberCallBack<PageEntity<InfoNoticeEntity>>() {
            @Override
            protected void onSuccess(PageEntity<InfoNoticeEntity> list) {
                MvpView.showNoticeList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
