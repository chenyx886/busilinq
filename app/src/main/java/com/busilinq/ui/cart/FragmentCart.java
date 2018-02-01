package com.busilinq.ui.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.IBaseMvpView;
import com.busilinq.presenter.mine.MinePresenter;
import com.busilinq.ui.MainActivity;
import com.busilinq.ui.mine.FeedbackActivity;
import com.busilinq.ui.mine.ForgetPwdActivity;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 购物车
 */
public class FragmentCart extends BaseMvpFragment<MinePresenter> implements IBaseMvpView {

    public static String TAG = FragmentCart.class.getName();
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 返回（此处须隐藏）
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 编辑
     */
    @BindView(R.id.tv_confirm)
    TextView mEdit;
    /**
     * 购物车为空显示的布局
     */
    @BindView(R.id.line_cart_empty_layout)
    LinearLayout cart_empty_layout;
    /**
     * 购物车有商品显示的布局
     */
    @BindView(R.id.rela_cart_full_layout)
    RelativeLayout cart_full_layout;
    /**
     * 选中全选
     */
    @BindView(R.id.check_select)
    CheckBox mSelect;
    /**
     * 合计（总价）
     */
    @BindView(R.id.tv_total_money)
    TextView mTotalMoney;
    /**
     * 结算
     */
    @BindView(R.id.btn_settlement)
    Button mSettlement;


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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }


    @Override
    protected void initUI() {
        mTitle.setText("购物车");
        mEdit.setText("编辑");
        mEdit.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.GONE);

        mSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    @OnClick({R.id.btn_settlement, R.id.check_select,R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 去结算
             */
            case R.id.btn_settlement:
                JumpUtil.overlay(getActivity(), SubmitOrderActivity.class);
                break;
            /**
             * 全选
             */
            case R.id.check_select:
                if (mSelect.isChecked()) {
                    ToastUtils.showShort("选中");
                } else {
                    ToastUtils.showShort("取消");
                }
                break;
            /**
             * 编辑
             */
            case R.id.tv_confirm:
                ToastUtils.showShort("编辑");
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