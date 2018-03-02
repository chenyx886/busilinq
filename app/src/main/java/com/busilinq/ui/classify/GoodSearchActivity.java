package com.busilinq.ui.classify;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.classify.IGoodsSearchView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.classify.GoodsSearchPresenter;
import com.busilinq.ui.classify.adapter.GoodsAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：商品搜索
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午3:37
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodSearchActivity extends BaseMvpActivity<GoodsSearchPresenter> implements IGoodsSearchView {

    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;
    /**
     * 搜索框
     */
    @BindView(R.id.et_search)
    EditText mEtSearch;
    /**
     * 搜索图标
     */
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    /**
     * 人气
     */
    @BindView(R.id.tv_popularity)
    TextView mPopularity;
    /**
     * 时间
     */
    @BindView(R.id.tv_time)
    TextView mTime;
    /**
     * 价格
     */
    @BindView(R.id.tv_price)
    TextView mPrice;

    private GoodsAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    /**
     * 分类id
     */
    private String classifyId;
    /**
     * 名称
     */
    private String Name;
    /**
     * 排序方式ASC DESC逆序
     */
    private String sort = "DESC";
    /**
     * 排序字段按人气(销量):salesVolume,按价格:price,按时间:update_time
     */
    private String field;

    @Override
    protected GoodsSearchPresenter createPresenter() {
        return new GoodsSearchPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_goods_search_list);
    }

    @Override
    protected void initUI() {
        mBack.setVisibility(View.VISIBLE);
        classifyId = getIntent().getStringExtra("classifyId");

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setArrowImageView(R.mipmap.iconfont_downgrey);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallPulse);

        mAdapter = new GoodsAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoodsId());
                JumpUtil.overlay(GoodSearchActivity.this, GoodsDetailActivity.class, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                Name = mEtSearch.getText().toString().trim();
                mPresenter.getGoodsSearchList(UserCache.GetUserId(), classifyId, page, Name, sort, field);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                Name = mEtSearch.getText().toString().trim();
                mPresenter.getGoodsSearchList(UserCache.GetUserId(), classifyId, page, Name, sort, field);
            }
        });
    }


    /**
     * 商品列表
     *
     * @param list
     */
    @Override
    public void ShowGoodsList(PageEntity<HomeGoodsEntity> list) {
        if (state == STATE_PULL_REFRESH) {
            page = 1;
            mAdapter.setData(list.getList());
        } else if (state == STATE_LOAD_MORE && list.getLimit() > 0) {
            mAdapter.insert(mAdapter.getItemCount(), list.getList());
        } else {
            if (mDataList != null)
                mDataList.setNoMore(true);
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
        if (mAdapter.getItemCount() - 1 > 0) {
            mDataList.setLoadingMoreEnabled(true);
        } else {
            mDataList.setLoadingMoreEnabled(false);
        }
    }

    /**
     * 键盘上回车键 事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
            mDataList.setRefreshing(true);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @OnClick({R.id.tv_back, R.id.iv_search, R.id.tv_popularity, R.id.tv_time, R.id.tv_price})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_search:
                mDataList.setRefreshing(true);
                break;
            //人气
            case R.id.tv_popularity:
                field = "salesVolume";
                sort = mPopularity.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mPopularity.setTag("DESC");
                else
                    mPopularity.setTag("ASC");
                break;
            //时间
            case R.id.tv_time:
                field = "update_time";
                sort = mTime.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mTime.setTag("DESC");
                else
                    mTime.setTag("ASC");
                break;
            //价格
            case R.id.tv_price:
                field = "price";
                sort = mPrice.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mPrice.setTag("DESC");
                else
                    mPrice.setTag("ASC");
                break;
        }
    }
}