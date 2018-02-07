package com.busilinq.xsm.presenter;

import android.content.Context;
import android.content.res.Resources;


import com.busilinq.R;
import com.busilinq.xsm.ulits.Logger;
import com.busilinq.xsm.viewinterface.IBaseView;
import com.busilinq.xsm.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/10 下午4:42
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public abstract class ABasePresenter<T extends IBaseView> implements XsmIPresenter<T> {

    protected Context mContext;
    protected Resources mRes;
    protected UserCenterHelper mHelper;
    protected List<Subscription> mSubscriptions;
    protected T mBaseView;

    private String mFunCode;

    @Override
    public void attachView(T view) {
        this.mBaseView = view;
        this.mContext = view.getContext();
        this.mRes = mContext.getResources();
        this.mHelper = UserCenterHelper.getInstance(mContext);
        this.mSubscriptions = new ArrayList<>();
    }

    @Override
    public void detachView() {
        if(null != mBaseView)
            mBaseView = null;
    }

    @Override
    public void cancel() {
        if(mSubscriptions.size() > 0){
            for (Subscription subscription:mSubscriptions){
                if(!subscription.isUnsubscribed())
                    subscription.unsubscribe();
            }
            mSubscriptions.clear();
        }
    }

    public boolean isAttached(){
        return mBaseView != null ? true : false;
    }

    /**
     * Presenter开始工作
     */
    public abstract void start();

    private Action1 mThrowableAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Logger.e(throwable.getMessage());
            mBaseView.showLoadingError(EmptyLayout.NETWORK_ERROR);
            if(null == mFunCode  || mFunCode.equals("")){
                mBaseView.toast(mRes.getString(R.string.prompt_net_failure));
            }else{
                mBaseView.toast("("+mFunCode+") "+mRes.getString(R.string.prompt_net_failure));
            }
            mBaseView.closeProgressDialog();
            mBaseView.onRefresh(false);
        }
    };

    protected Action1 getThrowableAction(String funCode){
        this.mFunCode = funCode;
        return mThrowableAction;
    }
}
