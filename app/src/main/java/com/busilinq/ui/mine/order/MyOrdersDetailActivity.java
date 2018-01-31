package com.busilinq.ui.mine.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.order.IMyOrdersDetailView;
import com.busilinq.contract.mine.order.IMyOrdersView;
import com.busilinq.presenter.mine.order.MyOrderPresenter;
import com.busilinq.ui.mine.order.adapter.MyOrdersAdapter;
import com.busilinq.widget.MLoadingDialog;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2018/1/31.
 */

public class MyOrdersDetailActivity extends BaseMvpActivity<MyOrderPresenter> implements IMyOrdersDetailView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_orders_detail);
    }

    @Override
    protected MyOrderPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.my_orders_detail_title);
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @OnClick({R.id.tv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;

        }
    }
}
