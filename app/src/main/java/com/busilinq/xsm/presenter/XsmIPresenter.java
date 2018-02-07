package com.busilinq.xsm.presenter;


import com.busilinq.xsm.viewinterface.IBaseView;

/**
 * Created by dingyi on 2016/11/15.
 */

public interface XsmIPresenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();
    void cancel();
}
