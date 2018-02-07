package com.busilinq.xsm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.presenter.XsmOrderDetailPresenter;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;
import com.busilinq.xsm.ulits.StringParse;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.IXsmOrderDetailView;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2017/6/19.
 */

public class XsmOrderDetailActivity extends XsmBaseActivity implements IXsmOrderDetailView {
    @BindView(R.id.myxsm_order_detail_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_order_detail_error_layout)
    EmptyLayout mErrorLayout;
    @BindView(R.id.myxsm_order_detail_container)
    LinearLayout mContainer;
    @BindView(R.id.myxsm_order_detail_lv)
    ListView mDetailLv;
    @BindView(R.id.xsm_order_detail_code_tv)
    TextView mCodeTv;
    @BindView(R.id.xsm_order_detail_time_tv)
    TextView mTimeTv;
    @BindView(R.id.xsm_order_detail_type_tv)
    TextView mTypeTv;
    @BindView(R.id.myxsm_order_detail_total_number_tv)
    TextView mToTalNumberTv;
    @BindView(R.id.myxsm_order_detail_total_tv)
    TextView mToTalTv;
    @BindView(R.id.myxsm_order_detail_edit_btn)
    Button mEditBtn;

    private XsmOrderDetailPresenter mPresenter;

    @Override
    public int initContentView() {
        return R.layout.myxsm_order_detail_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmOrderDetailPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setMidMsg(mContext.getResources().getString(R.string.myxsm_order_detail));
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        mPresenter.start();
    }

    @Override
    public void updateOrderInfo(Order order) {
    }

    @Override
    public void updateListView(XsmOrderDetailAdapter adapter) {
        mDetailLv.setAdapter(adapter);
    }

    @Override
    public void updateView(Order order, boolean isCurrentOrder) {
        if (isCurrentOrder)
            mEditBtn.setVisibility(View.GONE);
        else
            mEditBtn.setVisibility(View.VISIBLE);
        mCodeTv.setText(order.getCoNum());
        mTimeTv.setText(StringParse.getDate(mContext, order.getCrtDate(), "-") + " " + order.getCrtTime());
        if (order.getPmtStatus().equals("1"))
            mTypeTv.setTextColor(mContext.getResources().getColor(R.color.color_009999));
        else
            mTypeTv.setTextColor(mContext.getResources().getColor(R.color.color_f20c0c));
        mTypeTv.setText(StringParse.getOrderPayStatus(mContext, order.getPmtStatus()));
        mToTalNumberTv.setText(getResources().getString(R.string.gong) + order.getOrdQtySum() + getResources().getString(R.string.tiao));
        mToTalTv.setText(getResources().getString(R.string.myxsm_order_total) + getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(order.getOrdAmtSum()));
    }

    @OnClick({R.id.myxsm_order_detail_edit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myxsm_order_detail_edit_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable(Order.class.getSimpleName(), mPresenter.getmOrder());
                showActivity(this, XsmOrderingActivity.class, bundle);
                break;
        }
    }

    @Override
    public void showLoading(int visibility) {
        if (visibility == View.VISIBLE) {
            mContainer.setVisibility(View.GONE);
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        } else {
            mContainer.setVisibility(View.VISIBLE);
            mErrorLayout.dismiss();
        }
    }


    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            XsmOrderDetailActivity.this.onBackPressed();
        }
    };

}
