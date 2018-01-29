package com.busilinq.ui.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.IForgetPwdView;
import com.busilinq.data.event.TimerEvent;
import com.busilinq.presenter.mine.ForgetPwdPresenter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ResourceUtils;
import com.chenyx.libs.utils.Toasts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：忘记密码
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class ForgetPwdActivity extends BaseMvpActivity<ForgetPwdPresenter> implements IForgetPwdView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 手机号
     */
    @BindView(R.id.tv_phone)
    EditText mPhone;
    /**
     * 获取验证码
     */
    @BindView(R.id.getCode)
    TextView mGetCode;
    /**
     * 验证码
     */
    @BindView(R.id.vcode)
    EditText mVCode;


    /**
     * 计时器
     */
    private Timer timer = null;
    private TimerTask task = null;
    /**
     * 短信下发默认计时时间秒数
     */
    private static final int DEFAULT_SECONDS = 60;
    /**
     * 短信计时秒数
     */
    private int seconds = DEFAULT_SECONDS;

    @Override
    protected ForgetPwdPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new ForgetPwdPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forget_pwd);
    }

    @Override
    protected void initUI() {
        EventBus.getDefault().register(this);
        mTitle.setText("忘记密码");
    }

    @Override
    public void Success(int type, String msg) {
        Toasts.showShort(this, msg);
        if (type == 2)
            finish();
        else {
            mGetCode.setEnabled(false);
            mGetCode.setBackgroundResource(R.drawable.textview_send_vode_style);
            startTimer();
        }
    }

    @OnClick({R.id.tv_back, R.id.getCode, R.id.btn_next_step})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getCode:
                if (TextUtils.isEmpty(mPhone.getText().toString().trim()) || mPhone.getText().toString().trim().length() != 11) {
                    Toasts.showShort(ForgetPwdActivity.this, "手机号不能为空");
                    mPhone.setFocusable(true);
                    mPhone.requestFocus();
                    return;
                }
                mPresenter.getCode(mPhone.getText().toString().trim());
                break;
            case R.id.btn_next_step:
                String phone = mPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toasts.showShort(ForgetPwdActivity.this, "手机号不能为空");
                    mPhone.setFocusable(true);
                    mPhone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mVCode.getText().toString())) {
                    Toasts.showShort(ForgetPwdActivity.this, "请输入验证码");
                    mVCode.setFocusable(true);
                    mVCode.requestFocus();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                JumpUtil.overlay(mContext, SetNewPwdActivity.class, bundle);
                break;
            case R.id.tv_back:
                finish();
                break;

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showTimer(TimerEvent timerEvent) {
        mGetCode.setEnabled(false);
        startTimer();
    }

    /**
     * 启动定时器
     */
    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (task == null)
            task = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
        timer.schedule(task, 1000, 1000);
    }

    /**
     * 关闭定时器
     */
    private void closeTimer() {
        if (task != null) {
            task.cancel();
            task = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            seconds--;
            mGetCode.setText("获取验证码(" + seconds + "s)");
            if (seconds <= 0) {
                resetValidityData();
            }
        }
    };


    /**
     * 重置计数数据
     */
    private void resetValidityData() {
        closeTimer();
        seconds = DEFAULT_SECONDS;
        mGetCode.setText(ResourceUtils.getString(MApplication.getAppContext(), R.string.get_code));
        mGetCode.setEnabled(true);
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
    protected void onDestroy() {
        super.onDestroy();
        closeTimer();
        EventBus.getDefault().unregister(this);
    }

}