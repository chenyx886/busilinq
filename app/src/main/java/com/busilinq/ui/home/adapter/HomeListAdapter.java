package com.busilinq.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.HomeGoodsEntity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Company：华科建邺
 * Class Describe：首页数据适配器
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class HomeListAdapter extends AbstractRecyclerViewAdapter<HomeGoodsEntity> {


    public HomeListAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_home_goods, parent, false);
        return new GoodsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof GoodsListViewHolder) {
            GoodsListViewHolder vHolder = (GoodsListViewHolder) holder;
            final HomeGoodsEntity item = getItem(position);
            vHolder.mTitle.setText(item.getGoods().getName());
            vHolder.mPrice.setText("￥" + item.getPrice().getSalesPrice());
            showImageView(item.getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);
        }
    }

    /**
     * 无数据时 显示
     */
    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_empty)
        public ImageView img;
        @BindView(R.id.empty_msg_tv)
        public TextView msgTv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class GoodsListViewHolder extends RecyclerView.ViewHolder {

        /**
         * 图片
         */
        @BindView(R.id.iv_good_pic)
        ImageView mItemPic;
        /**
         * 标题
         */
        @BindView(R.id.tv_title)
        TextView mTitle;
        /**
         * 价格
         */
        @BindView(R.id.tv_price)
        TextView mPrice;


        public GoodsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
