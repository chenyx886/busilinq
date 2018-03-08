package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.FeedbackView;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/26 下午1:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FeedbackPresenter extends BasePresenter<FeedbackView> {

    public FeedbackPresenter(FeedbackView mvpView) {
        super(mvpView);
    }

    /**
     * 提交意见反馈
     *
     * @param userId
     * @param content
     */
    public void submitContent(int userId, String content) {
        MvpView.showProgress("提交中...");
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("content", content);
        param.put("title", "");
        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(RetrofitApiFactory.getMineApi().submitFeedback(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData response) {
                MvpView.submitSuccess("");
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }
}
