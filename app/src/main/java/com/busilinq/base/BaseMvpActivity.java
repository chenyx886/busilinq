package com.busilinq.base;

import android.os.Bundle;

import com.busilinq.presenter.BasePresenter;

/**
 * Company：华科建邺
 * Class Describe：MvpActivity 基类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle bundle) {
        mPresenter = createPresenter();
        super.onCreate(bundle);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();//释放资源
        this.mPresenter = null;
    }
}