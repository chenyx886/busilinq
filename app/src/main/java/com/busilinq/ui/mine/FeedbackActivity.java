package com.busilinq.ui.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.Toasts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：意见反馈
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FeedbackActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 意见
     */
    @BindView(R.id.tv_content)
    EditText mContent;
    /**
     * 意见框提示
     */
    @BindView(R.id.tv_content_hint)
    TextView mContentHint;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.feedback);

        mContent.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                    mContentHint.setVisibility(View.GONE);
                } else {
                    // 失去焦点
                    mContentHint.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @OnClick({R.id.tv_back, R.id.btn_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_submit:
                if (TextUtils.isEmpty(mContent.getText().toString().trim())) {
                    Toasts.showShort(FeedbackActivity.this, "说说点什么吧");
                    mContent.setFocusable(true);
                    mContent.requestFocus();
                    return;
                }
                Toasts.showShort(FeedbackActivity.this, "您的反馈已提交，我们会尽量为什么解答。");
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

}
