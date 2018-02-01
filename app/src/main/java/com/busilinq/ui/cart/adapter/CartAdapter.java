package com.busilinq.ui.cart.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.BaseEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Company：华科建邺
 * Class Describe：购物车适配器
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CartAdapter extends AbstractRecyclerViewAdapter<HomeGoodsEntity> {

    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //数据列表
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        final CartAdapter.ViewHolder vHolder = (CartAdapter.ViewHolder) holder;
        if (getItem(position) != null) {
            final HomeGoodsEntity item =   getItem(position);
            vHolder.mTitle.setText(item.getGoods().getName());
            vHolder.mNum.setText(item.getGoods().getNum()+"");
            GlideShowImageUtils.displayNetImage(mContext, item.getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);
            /**
             * 点击跳转到详情
             */
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("goodsId", item.getGoods().getGoodsId());
                    JumpUtil.overlay(mContext, GoodsDetailActivity.class, bundle);
                }
            });
            /**
             * 点击加号
             */
            vHolder.mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,position);
                }
            });
            /**
             * 点击减号
             */
            vHolder.mReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemViewClickListener.onViewClick(view,position);

                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * 是否选中
         */
        @BindView(R.id.check_select)
        CheckBox mIsCheck;
        /**
         * 图片
         */
        @BindView(R.id.img_item_pic)
        ImageView mItemPic;
        /**
         * 标题
         */
        @BindView(R.id.tv_title)
        TextView mTitle;
        /**
         * 价格：格式为：¥59.9/盒
         */
        @BindView(R.id.tv_price)
        TextView mPrice;
        /**
         * 增加
         */
        @BindView(R.id.img_add)
        ImageView mAdd;
        /**
         * 减少
         */
        @BindView(R.id.img_reduce)
        ImageView mReduce;
        /**
         * 数量
         */
        @BindView(R.id.tv_num)
        TextView mNum;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
