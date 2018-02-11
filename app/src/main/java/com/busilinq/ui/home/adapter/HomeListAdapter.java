package com.busilinq.ui.home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 类描述：首页数据适配器
 * 创建人：Chenyx
 * 创建时间：2017/3/4 15:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class HomeListAdapter extends AbstractRecyclerViewAdapter<HomeGoodsEntity> {


    public HomeListAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //数据列表
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goods, parent, false);
        return new GoodsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        GoodsListViewHolder vHolder = (GoodsListViewHolder) holder;
        if (getItem(position) != null) {
            final HomeGoodsEntity item = getItem(position);
            vHolder.mTitle.setText(item.getGoods().getName());
            vHolder.mPrice.setText("￥" + item.getPrice().getSalesPrice());
            GlideShowImageUtils.displayNetImage(mContext, item.getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);
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
