package com.busilinq.ui.home.adapter;

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
 * 类描述：首页数据适配器
 * 创建人：Chenyx
 * 创建时间：2017/3/4 15:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class HomeAdapter extends AbstractRecyclerViewAdapter<BaseEntity> {

    /**
     * 轮播
     */
    private static int BANNER_TYPE = 0;

    /**
     * 菜单
     */
    private static int NOTICE_TYPE = 1;

    /**
     * 数据列表
     */
    private static int PLAY_TYPE = 2;


    /**
     * 广播索引值
     */
    private int bannerIndex;

    /**
     * 时间间隔值
     */
    private long scrollDuration = 4000;

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_TYPE;
        } else if (position == 1) {
            return NOTICE_TYPE;
        }
        return PLAY_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //轮播
        if (viewType == BANNER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false);
            return new HomeBannerViewHolder(view);
        }

        //菜单
        else if (viewType == NOTICE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_menu, parent, false);
            return new HomeMenuViewHolder(view);
        }

        //数据列表
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goods, parent, false);
        return new GoodsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        //轮播
        if (holder instanceof HomeBannerViewHolder) {
            final PageEntity<BannerEntity> pageEntity = (PageEntity<BannerEntity>) getItem(position);
            HomeBannerViewHolder homeBannerViewHolder = (HomeBannerViewHolder) holder;
            if (pageEntity.getList().size() > 0) {
                homeBannerViewHolder.mCNews.stopTurning();
                homeBannerViewHolder.mCNews.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, pageEntity.getList()).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                homeBannerViewHolder.mCNews.setCanLoop(true);
                homeBannerViewHolder.mCNews.setcurrentitem(bannerIndex);
                homeBannerViewHolder.mCNews.startTurning(scrollDuration);

                homeBannerViewHolder.mCNews.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
            } else {
                homeBannerViewHolder.mCNews.setVisibility(View.GONE);
            }
        }
        //菜单
        else if (holder instanceof HomeMenuViewHolder) {
            HomeMenuViewHolder homeMenuViewHolder = (HomeMenuViewHolder) holder;

        }

        //推荐商品
        else {

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
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof HomeBannerViewHolder) {
            HomeBannerViewHolder homeBannerViewHolder = (HomeBannerViewHolder) holder;
            bannerIndex = homeBannerViewHolder.mCNews.getCurrentItem();
            homeBannerViewHolder.mCNews.stopTurning();
        }
    }


    public class NetworkImageHolderView implements Holder<BannerEntity> {
        private View view;

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.banner_item, null, false);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerEntity data) {
            ((TextView) view.findViewById(R.id.tv_title)).setText(data.getHref());
            ImageView imageView = view.findViewById(R.id.iv_image);
            GlideShowImageUtils.displayNetImage(context, data.getImageUrl(), imageView, R.mipmap.banner1);
        }
    }

    static class HomeBannerViewHolder extends RecyclerView.ViewHolder {

        /**
         * 轮播组件
         */
        @BindView(R.id.cb_news)
        ConvenientBanner mCNews;

        public HomeBannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class HomeMenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_special_goods)
        LinearLayout llSpecialGoods;
        @BindView(R.id.ll_collection_goods)
        LinearLayout llCollectionGoods;
        @BindView(R.id.ll_booked_goods)
        LinearLayout llBookedGoods;
        @BindView(R.id.ll_online_service)
        LinearLayout llOnlineService;
        @BindView(R.id.ll_order_goods)
        LinearLayout llOrderGoods;
        @BindView(R.id.ll_payment_goods)
        LinearLayout llPaymentGoods;
        @BindView(R.id.ll_delivery_goods)
        LinearLayout llDeliveryGoods;
        @BindView(R.id.ll_info_notice)
        LinearLayout llInfoNotice;

        public HomeMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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
