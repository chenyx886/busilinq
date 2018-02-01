package com.busilinq.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.widget.MLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：待开发
 * Create Person：Chenyx
 * Create Time：2018/2/1 下午4:20
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class ToDevelopedActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_to_developed);
    }

    @Override
    protected void initUI() {
        mTitle.setText("待开发");
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

}