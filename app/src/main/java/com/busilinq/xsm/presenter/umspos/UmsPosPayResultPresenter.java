package com.busilinq.xsm.presenter.umspos;

import android.app.Activity;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.ParseRespEntity;
import com.busilinq.xsm.presenter.XsmBasePresenter;
import com.busilinq.xsm.ui.umspos.UmsPosPayResultActivity;
import com.busilinq.xsm.viewinterface.IUmsPosPayResultView;


/**
 * Created by yu on 2017/9/5.
 */

public class UmsPosPayResultPresenter extends XsmBasePresenter<IUmsPosPayResultView> {
    private ParseRespEntity tradeResult;

    @Override
    public void attachView(IUmsPosPayResultView view) {
        super.attachView(view);
        tradeResult = (ParseRespEntity) ((Activity) mContext).getIntent().getSerializableExtra(UmsPosPayResultActivity.class.getName());
    }


    public void btnClicked() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void start() {
        showView();
    }

    private void showView() {
        if (tradeResult != null) {
            if (tradeResult.getRspCode() != null && tradeResult.getRspCode().equals("0")) {
                mBaseView.showData(R.mipmap.ic_pay_success, "支付成功", "提示：" + tradeResult.getRspMsg(), "确认");
            } else
                mBaseView.showData(R.mipmap.ic_pay_fail, "支付失败", "提示：" + tradeResult.getRspMsg(), "确认");
        }

    }
}

