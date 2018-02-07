package com.busilinq.xsm.viewinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dingyi on 2016/10/9.
 */

public interface IBaseView {
    void toast(String msg);
    void showPrompt(String msg);
    void closePrompt();
    void showProgressDialog(String msg);
    void closeProgressDialog();
    void showLoading(int visibility);
    void showLoadingError(int errorType);
    Context getContext();

    void onRefresh(boolean bRefresh);

    void showActivity(Activity activity, Class<?> cls, Bundle bundle);
    void showActivity(Activity activity, Class<?> cls);
    void showActivity(Activity activity, Intent intent);
    void skipActivity(Activity activity, Class<?> cls, Bundle bundle);
    void skipActivity(Activity activity, Class<?> cls);
    void skipActivity(Activity activity, Intent intent);
}
