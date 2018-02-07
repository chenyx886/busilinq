package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.ui.XsmHistoryOrdersActivity;
import com.busilinq.xsm.ui.XsmOrderDetailActivity;
import com.busilinq.xsm.ui.adapter.XsmHistoryOrderAdapter;
import com.busilinq.xsm.viewinterface.IXsmHistoryOrdersView;
import com.busilinq.xsm.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 2017/6/16.
 */

public class XsmHistoryOrdersPresenter extends XsmBasePresenter<IXsmHistoryOrdersView> {
    private Merchant mMerchant;
    private List<Order> mHistoryOrders = new ArrayList<>();
    private XsmHistoryOrderAdapter mAdapter;
    private boolean isCurrentOrder = false;//当期订单是否存在标识

    @Override
    public void attachView(IXsmHistoryOrdersView view) {
        super.attachView(view);
        this.mMerchant = mXsmDbApi.getMerchant();
        isCurrentOrder = ((Activity) mContext).getIntent().getBooleanExtra(XsmHistoryOrdersActivity.class.getName(), false);
        mHistoryOrders = (List<Order>) ((Activity) mContext).getIntent().getSerializableExtra(Order.class.getName());
    }

    @Override
    public void start() {
        if (mHistoryOrders != null) {
            initListView();
            mBaseView.showLoading(View.GONE);
        } else
            mBaseView.showLoadingError(EmptyLayout.NODATA);
    }

    public void setOnclickItem(int position) {
        Order order = mHistoryOrders.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(XsmHistoryOrdersActivity.class.getName(),
                isCurrentOrder);
        bundle.putSerializable(Order.class.getSimpleName(), order);
        mBaseView.showActivity((Activity) mContext, XsmOrderDetailActivity.class, bundle);
    }

    private void initListView() {
        if (null == mAdapter) {
            mAdapter = new XsmHistoryOrderAdapter(mContext, mHistoryOrders);
            mBaseView.updateList(mAdapter);
        } else {
            mAdapter.setDetails(mHistoryOrders);
        }
    }

    public Merchant getMerchant() {
        return mXsmDbApi.getMerchant();
    }
}
