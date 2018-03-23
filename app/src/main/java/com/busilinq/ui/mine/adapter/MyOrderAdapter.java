package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;

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
        View view = getInflater().inflate(R.layout.item_mine_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder appHolder = ((ViewHolder) holder);
            appHolder.tvName.setText(tvs[position]);
            appHolder.ivImage.setImageResource(ic[position]);
            appHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView ivImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.ll_items_application)
        LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
