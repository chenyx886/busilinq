package com.busilinq.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.cart.INewlyAddedAddress;
import com.busilinq.presenter.cart.NewlyAddedAddressPresenter;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：新增收货地址
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class NewlyAddedAddressActivity extends BaseMvpActivity<NewlyAddedAddressPresenter> implements INewlyAddedAddress {
    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected NewlyAddedAddressPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new NewlyAddedAddressPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_newly_added_address);
    }

    @Override
    protected void initUI() {
        mTitle.setText("收货地址");

    }

    @OnClick({R.id.tv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }


    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }
}
