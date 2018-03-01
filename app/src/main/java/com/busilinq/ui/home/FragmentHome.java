package com.busilinq.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.AbstractRecyclerViewAdapter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
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
import com.busilinq.ui.classify.GoodSearchActivity;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.busilinq.ui.classify.SpecialGoodsListActivity;
import com.busilinq.ui.home.adapter.HomeListAdapter;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.ui.mine.MyCollectionActivity;
import com.busilinq.ui.mine.order.MyOrdersActivity;
import com.busilinq.widget.GridDividerItemDecoration;
import com.busilinq.widget.NetworkImageHomeBanner;
import com.chenyx.libs.utils.JumpUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

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
    RecyclerView mDataList;
    /**
     * 下拉刷新 加载更多
     */
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

    /**
     * 数据适配器
     */
    private HomeListAdapter mAdapter;
    /**
     * 当前页
     */
    public int page = 1;

    /**
     * 轮播组件
     */
    @BindView(R.id.cb_Banner)
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

        initData();
        mAdapter.setOnItemViewClickListener(new AbstractRecyclerViewAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoodsId());
                JumpUtil.overlay(getActivity(), GoodsDetailActivity.class, bundle);
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
        mCBanner.setPages(new CBViewHolderCreator<NetworkImageHomeBanner>() {
            @Override
            public NetworkImageHomeBanner createHolder() {
                return new NetworkImageHomeBanner();
            }
        }, list).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
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

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mCBanner.startTurning(scrollDuration);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mCBanner.stopTurning();
    }


    /**
     * 商品列表
     *
     * @param data
     */
    @Override
    public void GoodsList(PageEntity<HomeGoodsEntity> data) {
        if (page == 1)
            mAdapter.setData(data.getList());
        else
            mAdapter.insert(mAdapter.getItemCount(), data.getList());

        if (data.getList().size() > 0)
            ++page;
    }

    /**
     * 菜单点击 跳转事件
     */


    @OnClick({R.id.fl_search, R.id.ll_special_goods, R.id.ll_collection_goods, R.id.ll_info_notice, R.id.ll_delivery_goods, R.id.ll_payment_goods, R.id.ll_order_goods, R.id.ll_booked_goods, R.id.ll_online_service})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_search:
                Bundle bundle = new Bundle();
                bundle.putString("cateName", "请输入商品名称");
                JumpUtil.overlay(getActivity(), GoodSearchActivity.class, bundle);
                break;
            //我订过的
            case R.id.ll_booked_goods:
                if (UserCache.get() != null) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.COMPLETE);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle3);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
                break;
            //我的收藏
            case R.id.ll_collection_goods:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), MyCollectionActivity.class);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
                break;
            //付款單（待发货）
            case R.id.ll_online_service:
                JumpUtil.overlay(getActivity(), OnlineServiceActivity.class);
                break;
            //特价商品
            case R.id.ll_special_goods:
                JumpUtil.overlay(getActivity(), SpecialGoodsListActivity.class, null);
                break;
            //付款單（待发货）
            case R.id.ll_order_goods:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
                break;
            //付款單（待发货）
            case R.id.ll_payment_goods:
                if (UserCache.get() != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.WAIT_SEND);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle2);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
                break;
            //信息公告
            case R.id.ll_info_notice:
                JumpUtil.overlay(getActivity(), InfoNoticeActivity.class);
                break;
            //发货单（待接收）
            case R.id.ll_delivery_goods:
                if (UserCache.get() != null) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.WAIT_RECEIVE);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle1);
                } else {
                    JumpUtil.startForResult(getActivity(), LoginActivity.class, LoginActivity.REQUEST, null);
                }
                break;
        }
    }

    @Override
    public void showProgress(String message) {

    }


    @Override
    public void hideProgress() {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
    }
}