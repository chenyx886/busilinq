package com.busilinq.ui.classify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.GoodsEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.chenyx.libs.glide.GlideShowImageUtils;

import butterknife.BindView;
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
public class GoodsAdapter extends AbstractRecyclerViewAdapter<HomeGoodsEntity> {

    public GoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder != null) {
            ViewHolder vHolder = ((ViewHolder) holder);
            HomeGoodsEntity entity = getItem(position);
            vHolder.mGoodTitleTIv.setText(entity.getGoods().getName());
            vHolder.tv_nickname.setText(entity.getGoods().getNickname());
            vHolder.mGoodPriceTv.setText("￥" + entity.getGoods().getPrice() + "/" + entity.getGoods().getUnit());
            GlideShowImageUtils.displayNetImage(mContext, entity.getGoods().getImage(), vHolder.mGoodPicIv, R.mipmap.default_error);
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 商品图片
         */
        @BindView(R.id.iv_good_pic)
        ImageView mGoodPicIv;
        /**
         * 商品介绍
         */
        @BindView(R.id.tv_title)
        TextView mGoodTitleTIv;
        /**
         * 商品价格
         */
        @BindView(R.id.tv_price)
        TextView mGoodPriceTv;

        /**
         * 商品别名
         */
        @BindView(R.id.tv_nickname)
        TextView tv_nickname;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
