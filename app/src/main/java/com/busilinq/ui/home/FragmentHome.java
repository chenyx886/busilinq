package com.busilinq.ui.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.home.IMainView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.home.MainPresenter;
import com.busilinq.ui.HtmlActivity;
import com.busilinq.ui.ToDevelopedActivity;
import com.busilinq.ui.classify.GoodSearchActivity;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.busilinq.ui.classify.SpecialGoodsListActivity;
import com.busilinq.ui.home.adapter.HomeListAdapter;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.ui.mine.MyCollectionActivity;
import com.busilinq.ui.mine.order.MyOrdersActivity;
import com.busilinq.widget.GridDividerItemDecoration;
import com.chenyx.libs.picasso.PicassoLoader;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Company：华科建邺
 * Class Describe：首页
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentHome extends BaseMvpFragment<MainPresenter> implements IMainView {

    public static String TAG = FragmentHome.class.getName();

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;
    /**
     * 下拉刷新 加载更多
     */
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    /**
     * 特价商品
     */
    LinearLayout llSpecialGoods;
    /**
     * 我的收藏
     */
    LinearLayout llCollectionGoods;
    /**
     * 我订过的
     */
    LinearLayout llBookedGoods;
    /**
     * 在线客服
     */
    LinearLayout llOnlineService;
    /**
     * 我的订单
     */
    LinearLayout llOrderGoods;
    /**
     * 付款单
     */
    LinearLayout llPaymentGoods;
    /**
     * 发货单
     */
    LinearLayout llDeliveryGoods;
    /**
     * 信息公告
     */
    LinearLayout llInfoNotice;

    /**
     * 数据适配器
     */
    private HomeListAdapter mAdapter;

    public List<HomeGoodsEntity> mDatas = new ArrayList<>();

    /**
     * 当前页
     */
    public int page = 1;


    public View view;
    /**
     * 轮播组件
     */
    ConvenientBanner mCBanner;
    /**
     * 广播索引值
     */
    private int bannerIndex;
    /**
     * 时间间隔值
     */
    private long scrollDuration = 4000;

    @Override
    protected MainPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new MainPresenter(this);
        }
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mDataList.setLayoutManager(staggeredGridLayoutManager);

        mDataList.addItemDecoration(new GridDividerItemDecoration(1, this.getResources().getColor(R.color.view_color)));

        mAdapter = new HomeListAdapter(getActivity());
        mDataList.setAdapter(mAdapter);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.include_home_menu, null);
        mCBanner = view.findViewById(R.id.cb_Banner);

        BindViewItem();
        mDataList.addHeaderView(view);
        initData();
        mAdapter.setOnItemViewClickListener(new AbstractRecyclerViewAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mDatas.get(position).getGoods().getGoodsId());
                JumpUtil.startForResult(getActivity(), GoodsDetailActivity.class, GoodsDetailActivity.HOME_REQUESTCODE, bundle);
            }
        });
    }

    private void initData() {
        refreshLayout.startRefresh();
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getBannerList("HOME");
                mPresenter.getGoodsList(page);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                ++page;
                mPresenter.getGoodsList(page);
            }
        });
    }

    /**
     * 显示轮播数据
     *
     * @param list
     */
    @Override
    public void BannerList(final List<BannerEntity> list) {
        mCBanner.stopTurning();
        mCBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, list).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        mCBanner.setCanLoop(true);
        mCBanner.setcurrentitem(bannerIndex);
        mCBanner.startTurning(scrollDuration);

        mCBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BannerEntity item = list.get(position);
                //商品详情
                if (item.getShowType() == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("goodsId", item.getGoodsId());
                    JumpUtil.overlay(getActivity(), GoodsDetailActivity.class, bundle);
                }
                //外部连接
                else if (item.getShowType() == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", item.getHref());
                    JumpUtil.overlay(getActivity(), HtmlActivity.class, bundle);
                }

            }
        });
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
            PicassoLoader.displayImage(context, data.getImageUrl(), imageView, R.mipmap.default_error);
        }
    }

    /**
     * 商品列表
     *
     * @param data
     */
    @Override
    public void GoodsList(PageEntity<HomeGoodsEntity> data) {
        if (page == 1)
            mDatas.clear();
        mDatas.addAll(data.getList());
        mAdapter.setData(mDatas);
    }

    /**
     * 菜单点击 跳转事件
     */
    private void BindViewItem() {

        //特价商品
        llSpecialGoods = view.findViewById(R.id.ll_special_goods);
        llSpecialGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overlay(getActivity(), SpecialGoodsListActivity.class);
            }
        });
        //我的收藏
        llCollectionGoods = view.findViewById(R.id.ll_collection_goods);
        llCollectionGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), MyCollectionActivity.class);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
            }
        });
        llBookedGoods = view.findViewById(R.id.ll_booked_goods);
        llBookedGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我订过的
                if (UserCache.get() != null) {
                    Bundle bundle=new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.COMPLETE);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class,bundle);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
            }
        });
        llOnlineService = view.findViewById(R.id.ll_online_service);
        llOnlineService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overlay(getActivity(), OnlineServiceActivity.class);
            }
        });
        llOrderGoods = view.findViewById(R.id.ll_order_goods);
        llOrderGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
            }
        });
        llPaymentGoods = view.findViewById(R.id.ll_payment_goods);
        llPaymentGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //付款單（待发货）
                if (UserCache.get() != null) {
                    Bundle bundle=new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.WAIT_SEND);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class,bundle);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
            }
        });
        llDeliveryGoods = view.findViewById(R.id.ll_delivery_goods);
        llDeliveryGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发货单（待接收）
                if (UserCache.get() != null) {
                    Bundle bundle=new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(),MyOrdersActivity.WAIT_RECEIVE);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class,bundle);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
            }
        });
        llInfoNotice = view.findViewById(R.id.ll_info_notice);
        llInfoNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overlay(getActivity(), InfoNoticeActivity.class);
            }
        });
    }


    @Override
    public void showProgress(String message) {

    }

    @OnClick({R.id.fl_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_search:
                Bundle bundle = new Bundle();
                bundle.putString("cateName", "请输入商品名称");
                JumpUtil.overlay(getActivity(), GoodSearchActivity.class, bundle);
                break;
        }
    }

    @Override
    public void hideProgress() {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
    }
}