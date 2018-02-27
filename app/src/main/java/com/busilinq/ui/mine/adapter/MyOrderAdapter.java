package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.cache.UserCache;
import com.busilinq.ui.ToDevelopedActivity;
import com.busilinq.ui.mine.order.MyOrdersActivity;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Company：华科建邺
 * Class Describe：我的 适配器
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyOrderAdapter extends AbstractRecyclerViewAdapter<String> {


    private final String[] tvs = {"我的订单", "退货单", "付款单", "发货单"};

    private final int[] ic = {R.mipmap.ic_goods_order, R.mipmap.ic_return_order,
            R.mipmap.ic_payment_order, R.mipmap.ic_delivery_order};

    @Override
    public int getItemCount() {
        return ic.length;
    }

    public MyOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_order, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            ApplicationViewHolder appHolder = ((ApplicationViewHolder) holder);
            appHolder.tvName.setText(tvs[position]);
            appHolder.ivImage.setImageResource(ic[position]);


            JumpAcivity(position, appHolder);
        }
    }

    class ApplicationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView ivImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.ll_items_application)
        LinearLayout mLinearLayout;

        public ApplicationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 跳转页面
     *
     * @param pos
     */
    private void JumpAcivity(int pos, ApplicationViewHolder appHolder) {
        switch (pos) {

            //我的订单
            case 0:
                appHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserCache.get() != null) {
                            JumpUtil.overlay(mContext, MyOrdersActivity.class);
                        } else {
                            ToastUtils.showShort("请先登录");
                        }
                    }
                });
                break;

            //退货单
            case 1:
                appHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserCache.get() != null) {
                            Bundle bundle=new Bundle();
                            bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.REFUND);
                            JumpUtil.overlay(mContext, MyOrdersActivity.class,bundle);
                        } else {
                            ToastUtils.showShort("请先登录");
                        }

                    }
                });
                break;

            // 付款单
            case 2:
                appHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserCache.get() != null) {
                            Bundle bundle=new Bundle();
                            bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.WAIT_SEND);
                            JumpUtil.overlay(mContext, MyOrdersActivity.class,bundle);
                        } else {
                            ToastUtils.showShort("请先登录");
                        }

                    }
                });
                break;

            // 发货单
            case 3:
                appHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserCache.get() != null) {
                            Bundle bundle=new Bundle();
                            bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.WAIT_RECEIVE);
                            JumpUtil.overlay(mContext, MyOrdersActivity.class,bundle);
                        } else {
                            ToastUtils.showShort("请先登录");
                        }

                    }
                });
                break;

        }
    }

}
