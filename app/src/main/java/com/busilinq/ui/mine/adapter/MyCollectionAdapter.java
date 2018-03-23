package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.MyCollectionEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：我的收藏 列表适配器
 * Create Person：wangshimei
 * Create Time：18/2/6 下午2:53
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyCollectionAdapter extends AbstractRecyclerViewAdapter<MyCollectionEntity> {

    public MyCollectionAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_goods_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = ((ViewHolder) holder);
            MyCollectionEntity entity = getItem(position);
            vHolder.mGoodTitleTIv.setText(entity.getGoods().getGoods().getName());
            vHolder.tv_nickname.setText(entity.getGoods().getGoods().getNickname());
            vHolder.mGoodPriceTv.setText("￥" + entity.getGoods().getPrice().getSalesPrice() + "/" + entity.getGoods().getGoods().getUnit());
            showImageView(entity.getGoods().getGoods().getImage(), vHolder.mGoodPicIv, R.mipmap.default_error);
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });

            vHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, position);
                    return true;
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
