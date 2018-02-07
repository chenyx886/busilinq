package com.busilinq.xsm.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.ulits.TeminalDevice;

import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class EmptyLayout extends LinearLayout {

    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NODATA_ENABLE_CLICK = 5;
    public static final int NO_LOGIN = 6;

    @BindView(R.id.error_igv)
    ImageView mIgv;
    @BindView(R.id.error_anim_progress)
    Loading mProgress;
    @BindView(R.id.error_tv)
    TextView mTv;
    @BindView(R.id.layout_error)
    LinearLayout mErrorLayout;

    private int mErrorState;
    private boolean mClickEnable;

    private String mNoDataContent = "";

    private Context mContext;

    public EmptyLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }


    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void dismiss() {
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }

    public void setErrorMessage(String msg) {
        mTv.setText(msg);
    }

    public void setErrorImag(int imgResource) {
        try {
            mIgv.setImageResource(imgResource);
        } catch (Exception e) {
        }
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"pagefailed_bg"));
                if (TeminalDevice.hasInternet()) {
                    mTv.setText(R.string.error_view_load_error_click_to_refresh);
                    mIgv.setBackgroundResource(R.mipmap.ic_tip_fail);
                } else {
                    mTv.setText(R.string.error_view_network_error_click_to_refresh);
                    mIgv.setBackgroundResource(R.mipmap.page_icon_network);
                }
                mIgv.setVisibility(View.VISIBLE);
                mProgress.stop();
                mProgress.setVisibility(View.GONE);
                mClickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                // mLoading.setBackgroundDrawable(SkinsUtil.getDrawable(context,"loadingpage_bg"));
                mProgress.setVisibility(View.VISIBLE);
                mProgress.start();
                mIgv.setVisibility(View.GONE);
                mTv.setText(R.string.error_view_loading);
                mClickEnable = false;
                break;
            case NODATA:
                mErrorState = NODATA;
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
                mIgv.setBackgroundResource(R.mipmap.page_icon_empty);
                mIgv.setVisibility(View.VISIBLE);
                mProgress.stop();
                mProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                mClickEnable = true;
                break;
            case HIDE_LAYOUT:
                mProgress.stop();
                setVisibility(View.GONE);
                break;
            case NODATA_ENABLE_CLICK:
                mErrorState = NODATA_ENABLE_CLICK;
                mIgv.setBackgroundResource(R.mipmap.page_icon_empty);
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
                mIgv.setVisibility(View.VISIBLE);
                mProgress.stop();
                mProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                mClickEnable = true;
                break;
            default:
                break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        mNoDataContent = noDataContent;
    }

    public void setTvNoDataContent() {
        if (!mNoDataContent.equals(""))
            mTv.setText(mNoDataContent);
        else
            mTv.setText(R.string.error_view_no_data);
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_error_layout, this);
        ButterKnife.bind(this, view);
    }
}
