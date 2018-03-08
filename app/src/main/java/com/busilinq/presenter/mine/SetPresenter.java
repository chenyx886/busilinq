package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.ISetView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.TUpgradeEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：yuzhenxing
 * Create Time：2018/2/1 15:31
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SetPresenter extends BasePresenter<ISetView> {
    public SetPresenter(ISetView MvpView) {
        super(MvpView);
    }

    /**
     * 检测当前版本
     *
     * @param version
     */
    public void upgrade(int version) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId ", UserCache.GetUserId());
        param.put("type ", "0");
        param.put("version ", version);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("检测中...");
        addSubscription(RetrofitApiFactory.getMineApi().upgrade(body), new SubscriberCallBack<TUpgradeEntity>() {
            @Override
            protected void onSuccess(TUpgradeEntity data) {
                MvpView.showUpgrade(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }


}
