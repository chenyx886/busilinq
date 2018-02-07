package com.busilinq.xsm.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

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

public class HeadBar extends LinearLayout {

    @BindView(R.id.headbar_left_tv)
    TextView mLeftTv;
    @BindView(R.id.headbar_mid_tv)
    TextView mMidTv;
    @BindView(R.id.headbar_rigth_tv)
    TextView mRigthTv;
    @BindView(R.id.headbar_rigth_igv)
    ImageView mRigthIgv;

    private Context mContext;
    private OnHeadBarListener mOnHeadBarListener;

    public TextView getMidTv() {
        return mMidTv;
    }

    public HeadBar(Context context) {
        super(context);
    }

    public HeadBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.headbar, this);
        ButterKnife.bind(this, view);
        setDefault();
    }

    public HeadBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置监听器
     */
    public void setOnHeadBarListener(OnHeadBarListener listener) {
        this.mOnHeadBarListener = listener;
    }

    /**
     * 设置最左边显示栏位提示信息
     */
    public void setLeftMsg(String msg) {
        mLeftTv.setText(msg);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_back_fff);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mLeftTv.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 设置HeadBar标题
     */
    public void setMidMsg(String msg) {
        mMidTv.setVisibility(View.VISIBLE);
        mMidTv.setText(msg);
    }

    /**
     * 设置最右边文字提示信息
     */
    public void setRigthMsg(String msg) {
        mRigthTv.setVisibility(View.VISIBLE);
        mRigthTv.setText(msg);
        mRigthTv.setDrawingCacheEnabled(false);
    }

    public void setRigthMsg(int drawableId, String msg) {
        mRigthTv.setVisibility(View.VISIBLE);
        mRigthTv.setText(msg);
        mRigthTv.setDrawingCacheEnabled(true);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mRigthTv.setCompoundDrawables(drawable, null, null, null);
    }

    public void setRigthIgv(int drawableId) {
        mRigthIgv.setVisibility(VISIBLE);
        mRigthIgv.setImageDrawable(getResources().getDrawable(drawableId));
    }

    private void setDefault() {
        mLeftTv.setText("");
        mLeftTv.setDrawingCacheEnabled(false);
        mMidTv.setText("");
        setRigthMsg("");
        mRigthTv.setVisibility(GONE);
        mRigthIgv.setVisibility(GONE);
    }

    @OnClick({R.id.headbar_left_tv, R.id.headbar_mid_tv, R.id.headbar_rigth_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headbar_left_tv:
                if (null != mOnHeadBarListener)
                    mOnHeadBarListener.onLeft();
                break;
            case R.id.headbar_mid_tv:
                if (null != mOnHeadBarListener)
                    mOnHeadBarListener.onMid();
                break;
            case R.id.headbar_rigth_layout:
                if (null != mOnHeadBarListener)
                    mOnHeadBarListener.onRight();
                break;

        }
    }
}
