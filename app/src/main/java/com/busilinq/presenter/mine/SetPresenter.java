package com.busilinq.presenter.mine;

import android.app.Activity;
import android.content.Context;

import com.busilinq.contract.mine.ISetView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.TUpgradeEntity;
import com.busilinq.presenter.BasePresenter;
import com.busilinq.ulits.AppUtils;
import com.busilinq.ulits.DownApplUtil;

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
    private Context mContext;


    public SetPresenter(ISetView MvpView) {
        super(MvpView);
        mContext = (Context) MvpView;
    }

    public void upgrade() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId ", UserCache.GetUserId());
        param.put("type ", "0");
        param.put("version ", AppUtils.getVersionCode(mContext));
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("检测中...");
        addSubscription(RetrofitApiFactory.getMineApi().upgrade(body), new SubscriberCallBack<TUpgradeEntity>() {
            @Override
            protected void onSuccess(TUpgradeEntity data) {
                DownApplUtil util = new DownApplUtil((Activity) mContext);
                util.getVersionNo(data);
            }
            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }


}
