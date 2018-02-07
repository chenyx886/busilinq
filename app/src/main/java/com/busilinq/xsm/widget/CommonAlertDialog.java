package com.busilinq.xsm.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.widget.interf.OnAlertDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CommonAlertDialog extends Dialog {

    @BindView(R.id.common_alert_dialog_title_tv)
    TextView mTitleTv;
    @BindView(R.id.common_alert_dialog_msg_tv)
    TextView mMsgTv;
    @BindView(R.id.common_alert_dialog_sure_tv)
    TextView mSureTv;
    @BindView(R.id.common_alert_dialog_cancle_tv)
    TextView mCancleTv;
    @BindView(R.id.common_alert_dialog_qty_edt)
    EditText mQtyEdt;
    @BindView(R.id.common_alert_dialog_content_edt)
    EditText mContentEdt;

    private OnAlertDialogListener mOnListener;
    private String mQtyString;
    private int mIndex;

    public CommonAlertDialog(Context context) {
        super(context, R.style.common_dialog);
        setParams();
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public void setTitle(String msg) {
        mTitleTv.setText(msg);
    }

    public void setMsg(String msg) {
        mMsgTv.setVisibility(View.VISIBLE);
        mQtyEdt.setVisibility(View.GONE);
        mContentEdt.setVisibility(View.GONE);
        mMsgTv.setText(msg);
    }

    public void setSureMsg(String msg) {
        mSureTv.setText(msg);
    }

    public void setCancleMsg(String msg) {
        mCancleTv.setText(msg);
    }

    public void setQty(String text) {
        mMsgTv.setVisibility(View.GONE);
        mContentEdt.setVisibility(View.GONE);
        mQtyEdt.setVisibility(View.VISIBLE);
        mQtyString = text;
        mQtyEdt.setText(text);
        mQtyEdt.setSelection(text.length());
    }

    public void setContent(String content) {
        mMsgTv.setVisibility(View.GONE);
        mQtyEdt.setVisibility(View.GONE);
        mContentEdt.setVisibility(View.VISIBLE);
        mContentEdt.setText(content);
        mContentEdt.setSelection(content.length());
    }


    public void setDialogListener(OnAlertDialogListener listener) {
        this.mOnListener = listener;
    }

    public void disableCancle() {
        mCancleTv.setVisibility(View.GONE);
    }

    @OnClick({R.id.common_alert_dialog_sure_tv, R.id.common_alert_dialog_cancle_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_alert_dialog_sure_tv:
                if (null != mOnListener) {
                    if (mMsgTv.getVisibility() == View.VISIBLE) {
                        mOnListener.onSure();
                    } else if (mContentEdt.getVisibility() == View.VISIBLE) {
                        mOnListener.onSure(mContentEdt.getText().toString());
                    } else {
                        mOnListener.onSure(mIndex, mQtyString);
                    }
                }

                break;
            case R.id.common_alert_dialog_cancle_tv:
                if (null != mOnListener)
                    mOnListener.onCancel();
                break;
        }
    }

    private void setParams() {
        setContentView(R.layout.common_alert_dialog);
        Window window = getWindow();
        ButterKnife.bind(this);
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mQtyEdt.addTextChangedListener(mWatcher);
    }

    private TextWatcher mWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && !"".equals(s.toString())) {
                mQtyString = s.toString();
            } else {
                mQtyString = "0";
            }
        }
    };
}