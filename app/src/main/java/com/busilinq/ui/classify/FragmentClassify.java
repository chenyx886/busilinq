package com.busilinq.ui.classify;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.classify.IClassifyView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.GoodsCategoryEntity;
import com.busilinq.presenter.classify.ClassifyPresenter;
import com.busilinq.ui.classify.adapter.CateLeftAdapter;
import com.busilinq.ui.classify.adapter.CateRightAdapter;
import com.busilinq.widget.PinnedHeaderListView;

import java.util.List;

import butterknife.BindView;

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
     * 分类列表
     */
    @BindView(R.id.CateList)
    ListView mCatelist;
    /**
     * 子分类列表
     */
    @BindView(R.id.SonCateList)
    PinnedHeaderListView mSonCateList;

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

    private List<GoodsCategoryEntity> mCateDatalist;

    @Override
    protected ClassifyPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new ClassifyPresenter(this);
        }
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classify, container, false);
    }


    @Override
    protected void initUI() {


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String userId = "";
                if (UserCache.get() != null)
                    userId = UserCache.get().getUserId() + "";
                mPresenter.getClassifyList(userId);

            }
        });


        mCateLeftAdapter = new CateLeftAdapter(getActivity());
        mCatelist.setAdapter(mCateLeftAdapter);
        mCatelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCateRigthAdapter != null) {
                    mCateSelectedPostion = position;
                    setCateListSelected(mCateSelectedPostion);
                    mCateRigthAdapter.appendList(getGoodsDisplayData(mCateLeftAdapter.getItem(mCateSelectedPostion)));
                }
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

        mSonCateList.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        String userId = "";
        if (UserCache.get() != null)
            userId = UserCache.get().getUserId() + "";
        mPresenter.getClassifyList(userId);
    }

    /**
     * 商品列表
     *
     * @param categoryList
     */
    @Override
    public void CategoryList(List<GoodsCategoryEntity> categoryList) {
        mCateDatalist = categoryList;

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mCateLeftAdapter.setData(mCateDatalist);
        mCateSelectedPostion = 0;
        setCateListSelected(mCateSelectedPostion);
        mCateRigthAdapter = new CateRightAdapter(getActivity(), mCateDatalist.get(mCateSelectedPostion));
        mSonCateList.setAdapter(mCateRigthAdapter);

    }

    /**
     * 分类选中
     *
     * @param postion
     */
    private void setCateListSelected(int postion) {
        mCateLeftAdapter.setSections(postion);
    }

    /**
     * 获取显示商品数据
     *
     * @param item
     * @return
     */
    private GoodsCategoryEntity getGoodsDisplayData(GoodsCategoryEntity item) {
        for (GoodsCategoryEntity gc : mCateDatalist) {
            if (gc.getId() == item.getId()) {
                return gc;
            }
        }
        return null;
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }


}