package com.busilinq.ui.classify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.GoodsCategoryEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：商品列表 适配器
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CateRightAdapter extends AbstractRecyclerViewAdapter<GoodsCategoryEntity> {


    public CateRightAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_classify_right, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = ((ViewHolder) holder);
            GoodsCategoryEntity entity = getItem(position);
            vHolder.mSonName.setText(entity.getName());
            showImageView(entity.getImage(), vHolder.mIvClassify, R.mipmap.default_error);
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
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
         * 分类图片
         */
        @BindView(R.id.iv_classify_img)
        ImageView mIvClassify;
        /**
         * 分类名称
         */
        @BindView(R.id.tv_son_name)
        TextView mSonName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
