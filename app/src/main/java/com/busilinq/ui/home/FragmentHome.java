package com.busilinq.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.home.IMainView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.BaseEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.home.MainPresenter;
import com.busilinq.ui.home.adapter.HomeAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
     * 数据适配器
     */
    private HomeAdapter mAdapter;

    /**
     * 数据列表
     */
    private List<BaseEntity> baseEntities = new ArrayList<>();

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    protected void initUI() {
        mDataList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

        mAdapter = new HomeAdapter(getActivity());
        mDataList.setAdapter(mAdapter);

        initData();
    }


    private void initData() {

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mDataList.setLoadingMoreEnabled(false);
                state = STATE_PULL_REFRESH;
                mPresenter.getBannerList("HOME");
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getGoodsList(page);
            }
        });
        mDataList.setRefreshing(true);
    }

    /**
     * 显示轮播数据
     *
     * @param list
     */
    @Override
    public void BannerList(List<BannerEntity> list) {
        if (list != null) {
            baseEntities.clear();
            PageEntity<BannerEntity> pbList = new PageEntity<>();
            pbList.setList(list);
            baseEntities.add(pbList);
            page = 1;
            mAdapter.setData(baseEntities);
            mPresenter.getGoodsList(page);
        }
    }


    /**
     * 商品列表
     *
     * @param list
     */
    @Override
    public void GoodsList(PageEntity<HomeGoodsEntity> list) {
        List<BaseEntity> baseEns = new ArrayList<>();
        baseEns.addAll(list.getList());


//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mDataList.setLayoutManager(staggeredGridLayoutManager);

        mAdapter.insert(mAdapter.getItemCount(), baseEns);
        if (page == 1) {
            if (mDataList != null)
                mDataList.setLoadingMoreEnabled(true);
        }
        ++page;
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {
        if (state == STATE_PULL_REFRESH) {
            if (mDataList != null)
                mDataList.refreshComplete();
        } else if (state == STATE_LOAD_MORE) {
            if (mDataList != null)
                mDataList.loadMoreComplete();
        }
    }

}