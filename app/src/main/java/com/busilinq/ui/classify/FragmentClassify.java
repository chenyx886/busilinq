package com.busilinq.ui.classify;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.classify.IClassifyView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.GoodsCategoryEntity;
import com.busilinq.presenter.classify.ClassifyPresenter;
import com.busilinq.ui.classify.adapter.CateLeftAdapter;
import com.busilinq.ui.classify.adapter.CateRightAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：分类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentClassify extends BaseMvpFragment<ClassifyPresenter> implements IClassifyView {

    public static String TAG = FragmentClassify.class.getName();
    /**
     * 返回图标
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 分类列表
     */
    @BindView(R.id.CateList)
    ListView mCatelist;
    /**
     * 子分类列表
     */
    @BindView(R.id.SonCateList)
    XRecyclerView mSonCateList;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 左边菜单
     */
    private CateLeftAdapter mCateLeftAdapter = null;
    /**
     * 右边菜单
     */
    private CateRightAdapter mCateRigthAdapter = null;

    private int mCateSelectedPostion = -1;
    /**
     * 主分类
     */
    private List<GoodsCategoryEntity> mCateDatalist;
    /**
     * 子分类
     */
    private List<GoodsCategoryEntity> mSonClassifyList;

    @Override
    protected ClassifyPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new ClassifyPresenter(this);
        }
        return mPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classify, container, false);
    }

    @Override
    protected void initUI() {
        mBack.setVisibility(View.GONE);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getClassifyList(UserCache.GetUserId());
            }
        });


        mCateLeftAdapter = new CateLeftAdapter(getActivity());
        mCatelist.setAdapter(mCateLeftAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mSonCateList.setLayoutManager(staggeredGridLayoutManager);
        mSonCateList.setLoadingMoreEnabled(false);
        mSonCateList.setPullRefreshEnabled(false);

        mCateRigthAdapter = new CateRightAdapter(getActivity());
        mSonCateList.setAdapter(mCateRigthAdapter);

        mCatelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCateRigthAdapter != null) {
                    mCateSelectedPostion = position;
                    setCateListSelected(mCateSelectedPostion);
                    mSonClassifyList = mCateLeftAdapter.getItem(mCateSelectedPostion).getList();
                    mCateRigthAdapter.setData(mSonClassifyList);
                }
            }
        });

        mCateRigthAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Bundle bundle = new Bundle();
                int cateId = mCateDatalist.get(mCateSelectedPostion).getList().get(position).getId();
                String cateName = mCateDatalist.get(mCateSelectedPostion).getList().get(position).getName();
                bundle.putString("classifyId", cateId + "");
                bundle.putString("cateName", cateName);
                JumpUtil.overlay(getActivity(), GoodsListActivity.class, bundle);
            }
        });
        mCatelist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    mSwipeRefreshLayout.setEnabled(true);
                else
                    mSwipeRefreshLayout.setEnabled(false);
            }
        });


        initData();
    }

    public void initData() {
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getClassifyList(UserCache.GetUserId());
    }

    /**
     * 商品列表
     *
     * @param categoryList
     */
    @Override
    public void CategoryList(List<GoodsCategoryEntity> categoryList) {
        mCateDatalist = categoryList;
        mCateLeftAdapter.setData(mCateDatalist);
        mCateSelectedPostion = 0;
        setCateListSelected(mCateSelectedPostion);
        mSonClassifyList = mCateDatalist.get(mCateSelectedPostion).getList();
        mCateRigthAdapter.setData(mSonClassifyList);

    }

    /**
     * 分类选中
     *
     * @param postion
     */
    private void setCateListSelected(int postion) {
        mCateLeftAdapter.setSections(postion);
    }


    @OnClick({R.id.et_search, R.id.iv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                JumpUtil.overlay(getActivity(), GoodSearchActivity.class, null);
                break;
            case R.id.iv_search:
                JumpUtil.overlay(getActivity(), GoodSearchActivity.class, null);
                break;

        }
    }


    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}