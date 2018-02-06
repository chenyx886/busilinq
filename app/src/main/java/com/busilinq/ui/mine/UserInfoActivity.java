package com.busilinq.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.UserInfoView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.mine.UserInfoPresenter;
import com.busilinq.widget.MLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：用户资料
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午4:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoView{
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 名称
     */
    @BindView(R.id.et_user_name)
    EditText et_user_name;

    /**
     * 帐号
     */
    @BindView(R.id.et_user_account)
    EditText et_user_account;

    /**
     * 邮箱
     */
    @BindView(R.id.et_user_email)
    EditText et_user_email;

    /**
     * 电话
     */
    @BindView(R.id.et_user_tell)
    EditText et_user_tell;

    /**
     * 传真
     */
    @BindView(R.id.et_user_fax)
    EditText et_user_fax;

    /**
     * 快捷支付账号
     */
    @BindView(R.id.et_user_payment)
    EditText et_user_payment;

    UserEntity userEntity = null;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.user_data);
        mPresenter.getUserInfo(UserCache.get().getUserId(),UserCache.get().getName());
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
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void getUserInfoSuccess(UserEntity entity) {
        userEntity = entity;
        if (userEntity.getName() != null)
            et_user_name.setText(userEntity.getName());
        if (userEntity.getCell() != null) {
            et_user_account.setText(userEntity.getCell());
            et_user_tell.setText(userEntity.getCell());
            et_user_fax.setText(userEntity.getCell());
        }
        if (userEntity.getEmail() != null)
            et_user_email.setText(userEntity.getEmail());
//        if (userEntity.)
    }
}