package com.busilinq.ui.classify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.GoodsEntity;

import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：分类商品 适配器
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午4:08
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsAdapter extends AbstractRecyclerViewAdapter<GoodsEntity> {

    public GoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            ViewHolder vHolder = ((ViewHolder) holder);
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
