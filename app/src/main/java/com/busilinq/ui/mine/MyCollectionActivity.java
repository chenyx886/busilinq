package com.busilinq.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：我的收藏
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午4:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyCollectionActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.my_collection);
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