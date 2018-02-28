package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.OrderDetailsEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单详情列表
 */
public class MyOrderDetailAdapter extends AbstractRecyclerViewAdapter<OrderDetailsEntity> {

    public MyOrderDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.assem_selected_goods_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = (ViewHolder) holder;
            OrderDetailsEntity ge = items.get(position);
            vHolder.mTitle.setText(ge.getGoodsName());
            vHolder.mPrice.setText("￥" + ge.getGoodsPrice());
            vHolder.mNum.setText("x" + ge.getNumber());
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
         * 标题
         */
        @BindView(R.id.assem_sel_item_title)
        TextView mTitle;
        /**
         * 价格
         */
        @BindView(R.id.assem_sel_item_price)
        TextView mPrice;
        /**
         * 数量
         */
        @BindView(R.id.assem_sel_item_num)
        TextView mNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}