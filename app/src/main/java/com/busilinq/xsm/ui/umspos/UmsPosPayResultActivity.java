package com.busilinq.xsm.ui.umspos;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.presenter.umspos.UmsPosPayResultPresenter;
import com.busilinq.xsm.ulits.StringUtils;
import com.busilinq.xsm.viewinterface.IUmsPosPayResultView;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by dingyi on 2017/9/5.
 */

public class UmsPosPayResultActivity extends XsmBaseActivity implements IUmsPosPayResultView {
    @BindView(R.id.ums_pos_pay_result_head)
    HeadBar head;
    @BindView(R.id.result_tv)
    TextView mResultTv;
    @BindView(R.id.result_msg_tv)
    TextView mMsgTv;
    @BindView(R.id.result_btn)
    Button mResultBtn;
    @BindView(R.id.result_igv)
    ImageView mResultIgv;

    private UmsPosPayResultPresenter presenter;

    @Override
    public int initContentView() {
        return R.layout.ums_pos_pay_result;
    }

    @Override
    public void initData() {
        presenter = new UmsPosPayResultPresenter();
        presenter.attachView(this);
        presenter.start();
    }

    @Override
    public void initUi() {
        head.setLeftMsg(mContext.getResources().getString(R.string.back));
        head.setMidMsg(mContext.getResources().getString(R.string.ums_pay_result_title));
        head.setOnHeadBarListener(mOnHeadBarListener);
    }


    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            UmsPosPayResultActivity.this.onBackPressed();
        }
    };

    @Override
    protected void onDestroy() {
        presenter.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.result_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.result_btn:
                    presenter.btnClicked();
                break;
        }
    }
    @Override
    public void showData(int imageId, String codeType, String dece, String btnName) {
        mResultIgv.setImageDrawable(mContext.getResources().getDrawable(imageId));
        mResultTv.setText(codeType);
        mMsgTv.setText(dece);
        if (!StringUtils.isEmpty(btnName))
            mResultBtn.setText(btnName);
    }
}
