package com.busilinq.base;

import android.os.Bundle;
import android.view.View;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.presenter.BasePresenter;
import com.busilinq.widget.headeractionbar.HeaderFragment;


/**
 * Company：华科建邺
 * Class Describe：MvpFragment基类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public abstract class BaseHomeFragment<P extends BasePresenter> extends HeaderFragment implements IBaseMvpView {

    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        if (mPresenter == null) mPresenter = createPresenter();
        super.onViewCreated(view, bundle);
    }

    @Override
    protected void lazyLoad() {
        if (mPresenter == null) mPresenter = createPresenter();
        super.lazyLoad();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
