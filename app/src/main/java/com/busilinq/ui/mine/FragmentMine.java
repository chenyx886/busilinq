package com.busilinq.ui.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.IBaseMvpView;
import com.busilinq.presenter.mine.MinePresenter;
import com.busilinq.ui.mine.adapter.MyOrderAdapter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：我的
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentMine extends BaseMvpFragment<MinePresenter> implements IBaseMvpView {

    public static String TAG = FragmentMine.class.getName();

    /**
     * 订单管理
     */
    @BindView(R.id.rv_order_item)
    RecyclerView mOrderItem;

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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }


    @Override
    protected void initUI() {
        mOrderItem.setAdapter(new MyOrderAdapter(getContext()));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mOrderItem.setLayoutManager(staggeredGridLayoutManager);
    }


    @OnClick({R.id.it_update_pwd, R.id.it_address, R.id.it_collection, R.id.it_user_info, R.id.it_feedback, R.id.btn_quit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.it_update_pwd:
                JumpUtil.overlay(getContext(), UpdatePwdActivity.class);
                break;
            case R.id.it_address:
                JumpUtil.overlay(getContext(), AddressActivity.class);
                break;
            case R.id.it_collection:
                JumpUtil.overlay(getContext(), MyCollectionActivity.class);
                break;
            case R.id.it_user_info:
                JumpUtil.overlay(getContext(), UserInfoActivity.class);
                break;
            case R.id.it_feedback:
                JumpUtil.overlay(getContext(), FeedbackActivity.class);
                break;
            case R.id.btn_quit:
                JumpUtil.overlay(getContext(), LoginActivity.class);
                break;

        }
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