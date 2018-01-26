package com.busilinq.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.IBaseMvpView;
import com.busilinq.presenter.mine.MinePresenter;
import com.busilinq.widget.MLoadingDialog;

/**
 * Company：华科建邺
 * Class Describe：我的
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentHome extends BaseMvpFragment<MinePresenter> implements IBaseMvpView {

    public static String TAG = FragmentHome.class.getName();


    @Override
    protected MinePresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new MinePresenter(this);
        }
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


    @Override
    protected void initUI() {
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(getActivity(), message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }


}