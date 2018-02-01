package com.busilinq.ui.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.busilinq.R;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.BaseEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.chenyx.libs.glide.GlideShowImageUtils;

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
public class CartAdapter extends AbstractRecyclerViewAdapter<BaseEntity> {

    /**
     * 数据列表
     */
    private static int PLAY_TYPE = 2;



    /**
     * 时间间隔值
     */
    private long scrollDuration = 4000;

    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {

        return PLAY_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goods, parent, false);
        return new GoodsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
            GoodsListViewHolder vHolder = (GoodsListViewHolder) holder;
            if (getItem(position) != null) {
                final HomeGoodsEntity item = (HomeGoodsEntity) getItem(position);
                vHolder.mTitle.setText(item.getGoods().getName());
                GlideShowImageUtils.displayNetImage(mContext, item.getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);
                vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);

    }




    static class GoodsListViewHolder extends RecyclerView.ViewHolder {

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
         * 内容
         */
        @BindView(R.id.tv_content)
        TextView mContent;
        /**
         * 时间
         */
        @BindView(R.id.tv_time)
        TextView mTime;


        public GoodsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
