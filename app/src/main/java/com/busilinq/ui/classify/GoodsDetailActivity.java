package com.busilinq.ui.classify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.classify.IGoodsDetailView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.GoodsDetailEntity;
import com.busilinq.data.entity.GoodsImgEntity;
import com.busilinq.presenter.classify.GoodsDetailPresenter;
import com.busilinq.ui.PhotoActivity;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：商品详情
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午5:20
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsDetailActivity extends BaseMvpActivity<GoodsDetailPresenter> implements IGoodsDetailView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 商品名称
     */
    @BindView(R.id.tv_name)
    TextView mName;
    /**
     * 商品价格
     */
    @BindView(R.id.tv_price)
    TextView mPrice;
    /**
     * 商品数量
     */
    @BindView(R.id.gruop_num)
    TextView mGruopNum;
    /**
     * 轮播组件
     */
    @BindView(R.id.cb_goods_banner)
    ConvenientBanner mCBanner;
    /**
     * 详情图
     */
    @BindView(R.id.ll_image)
    LinearLayout mLLImage;
    /**
     * 下拉刷新 加载更多
     */
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 商品数据
     */
    private int num = 1;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 广播索引值
     */
    private int bannerIndex;
    /**
     * 时间间隔值
     */
    private long scrollDuration = 4000;

    private View imgView;

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(this);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_goods_detail);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.goods_detail);
        goodsId = getIntent().getIntExtra("goodsId", -1);


        ProgressLayout headerView = new ProgressLayout(this);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);

        refreshLayout.startRefresh();
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mPresenter.getGoodsDetail(UserCache.GetUserId(), 1);
            }
        });
    }


    /**
     * 显示 商品详情
     *
     * @param data
     */
    @Override
    public void GoodsDetail(final GoodsDetailEntity data) {
        if (mLLImage != null)
            mLLImage.removeAllViews();
        //轮播图
        BindBanner(data.getBanner());
        //商品基本信息
        mName.setText(data.getGoods().getGoods().getName());
        mPrice.setText("￥" + data.getGoods().getGoods().getPrice() + "/" + data.getGoods().getGoods().getUnit());
        price = data.getGoods().getGoods().getPrice();
        //详情图
        for (int i = 0; i < data.getImage().size(); i++) {
            final String imageUrl[] = new String[data.getImage().size()];
            imageUrl[i] = data.getImage().get(i).getImage();
            imgView = LayoutInflater.from(this).inflate(R.layout.item_image, null);
            final ImageView imageView = imgView.findViewById(R.id.iv_image);
            GlideShowImageUtils.displayNetImage(mContext, data.getImage().get(i).getImage(), imageView, R.mipmap.default_error);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putStringArray("imageUrls", imageUrl);
                    b.putString("curImageUrl", data.getImage().get(0).getImage());
                    JumpUtil.overlay(mContext, PhotoActivity.class, b, Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            });
            mLLImage.addView(imgView);
        }
    }

    private void BindBanner(final List<GoodsImgEntity> bList) {
        mCBanner.stopTurning();
        mCBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bList).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        mCBanner.setCanLoop(true);
        mCBanner.setcurrentitem(bannerIndex);
        mCBanner.startTurning(scrollDuration);

        mCBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String imageUrl[] = new String[bList.size()];
                for (int i = 0; i < bList.size(); i++) {
                    imageUrl[i] = bList.get(i).getImage();
                }

                Bundle b = new Bundle();
                b.putStringArray("imageUrls", imageUrl);
                b.putString("curImageUrl", bList.get(position).getImage());
                JumpUtil.overlay(mContext, PhotoActivity.class, b, Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
    }


    @OnClick({R.id.btn_add_cart, R.id.btnadd, R.id.btndown, R.id.tv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            //加
            case R.id.btnadd:
                num += 1;
                mGruopNum.setText(num + "");
                break;
            //减
            case R.id.btndown:
                if (num <= 1)
                    return;
                num -= 1;
                mGruopNum.setText(num + "");
                break;
            case R.id.btn_add_cart:
                if (!TextUtils.isEmpty(UserCache.GetUserId()))
                    mPresenter.AddCart(goodsId, num, price);
                else
                    JumpUtil.startForResult(this, LoginActivity.class, LoginActivity.REQUEST, null);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public class NetworkImageHolderView implements Holder<GoodsImgEntity> {
        private View view;

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.goods_banner_item, null, false);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, GoodsImgEntity data) {
            ((TextView) view.findViewById(R.id.tv_title)).setText("");
            ImageView imageView = view.findViewById(R.id.iv_image);
            GlideShowImageUtils.displayNetImage(context, data.getImage(), imageView, R.mipmap.banner1);
        }
    }

    @Override
    public void Success(CartEntity data) {
        ToastUtils.showShort("加入购物车成功");
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
        MLoadingDialog.dismiss();
    }
}
