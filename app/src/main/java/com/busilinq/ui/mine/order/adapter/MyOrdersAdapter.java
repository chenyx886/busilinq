package com.busilinq.ui.mine.order.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.ui.mine.order.MyOrdersDetailActivity;
import com.busilinq.xsm.ulits.StringParse;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by yu on 2018/1/31.
 */
public class MyOrdersAdapter extends AbstractRecyclerViewAdapter<HomeOrderEntity> {

    public MyOrdersAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_my_orders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = ((ViewHolder) holder);
            final HomeOrderEntity entity = getItem(position);
            vHolder.mCodeTv.setText(entity.getOrder().getOrderNumber());
            vHolder.mTimeTv.setText(entity.getOrder().getOrderTime());
            vHolder.mAmountTv.setText(StringParse.formatMoney(entity.getOrder().getPayMoney()));
            vHolder.mStateTv.setText(entity.getOrder().getStatus());
            vHolder.mPaymentMethodTv.setText(entity.getShipping().getPayType());
            vHolder.mAgainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            vHolder.mOrdersItemLly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    String orderNum = entity.getOrder().getOrderNumber();
                    bundle.putString("orderNum", orderNum);
                    JumpUtil.overlay(mContext, MyOrdersDetailActivity.class, bundle);
                }
            });
        }
    }

    /**
     * 无数据时 显示
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_empty)
        public ImageView img;
        @BindView(R.id.empty_msg_tv)
        public TextView msgTv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 订单号
         */
        @BindView(R.id.order_code_tv)
        TextView mCodeTv;
        /**
         * 状态
         */
        @BindView(R.id.order_state_tv)
        TextView mStateTv;
        /**
         * 配送方式
         */
        @BindView(R.id.order_delivery_mode_tv)
        TextView mDeliveryModeTv;
        /**
         * 付款方式
         */
        @BindView(R.id.order_payment_method_tv)
        TextView mPaymentMethodTv;
        /**
         * 下单时间
         */
        @BindView(R.id.order_time_tv)
        TextView mTimeTv;
        /**
         * 订单金额
         */
        @BindView(R.id.order_amount_tv)
        TextView mAmountTv;
        /**
         * 再次订购
         */
        @BindView(R.id.order_again_btn)
        Button mAgainBtn;
        @BindView(R.id.my_orders_items_lly)
        LinearLayout mOrdersItemLly;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
