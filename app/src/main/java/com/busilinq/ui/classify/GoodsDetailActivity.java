package com.busilinq.ui.classify;

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
 * Class Describe：商品详情
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午5:20
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsDetailActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 商品id
     */
    private int goodsId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_goods_detail);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.goods_detail);
        goodsId = getIntent().getIntExtra("goodsId", 0);
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
