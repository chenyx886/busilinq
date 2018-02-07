package com.busilinq.xsm.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.presenter.XsmHistoryOrdersPresenter;
import com.busilinq.xsm.ui.adapter.XsmHistoryOrderAdapter;
import com.busilinq.xsm.viewinterface.IXsmHistoryOrdersView;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;

/**
 * Created by yu on 2017/6/16.
 */

public class XsmHistoryOrdersActivity extends XsmBaseActivity implements IXsmHistoryOrdersView {

    @BindView(R.id.myxsm_ordering_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_ordering_error_layout)
    EmptyLayout mErrorLayout;
    @BindView(R.id.xsm_history_orders_lv)
    ListView mContainerlv;
    private XsmHistoryOrdersPresenter mPresenter;


    @Override
    public int initContentView() {
        return R.layout.myxsm_history_order_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmHistoryOrdersPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        mHeadbar.setMidMsg(mPresenter.getMerchant().getCustName());
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        mPresenter.start();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        mPresenter.cancel();
        super.onDestroy();
    }

    public void showLoading(int visibility) {
        if (visibility == View.VISIBLE) {
            mContainerlv.setVisibility(View.GONE);
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        } else {
            mContainerlv.setVisibility(View.VISIBLE);
            mErrorLayout.dismiss();
        }
    }

    @Override
    public void showLoadingError(int errorType) {
        mErrorLayout.setErrorType(errorType);
    }


    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            XsmHistoryOrdersActivity.this.onBackPressed();
        }

        @Override
        public void onMid() {
            super.onMid();
        }
    };


    @Override
    public void updateList(XsmHistoryOrderAdapter adapter) {
        mContainerlv.setAdapter(adapter);
        mContainerlv.setOnItemClickListener(onItemClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mPresenter.setOnclickItem(position);
        }
    };
}
