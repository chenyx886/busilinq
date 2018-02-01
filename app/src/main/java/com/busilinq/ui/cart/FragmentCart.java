package com.busilinq.ui.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.IBaseMvpView;
import com.busilinq.contract.cart.ICartView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BaseEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.cart.CartPresenter;
import com.busilinq.presenter.mine.MinePresenter;
import com.busilinq.ui.MainActivity;
import com.busilinq.ui.cart.adapter.CartAdapter;
import com.busilinq.ui.home.adapter.HomeAdapter;
import com.busilinq.ui.mine.FeedbackActivity;
import com.busilinq.ui.mine.ForgetPwdActivity;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：购物车
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentCart extends BaseMvpFragment<CartPresenter> implements ICartView {

    public static String TAG = FragmentCart.class.getName();
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 返回（此处须隐藏）
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 编辑
     */
    @BindView(R.id.tv_confirm)
    TextView mEdit;
    /**
     * 购物车为空显示的布局
     */
    @BindView(R.id.line_cart_empty_layout)
    LinearLayout cart_empty_layout;
    /**
     * 购物车有商品显示的布局
     */
    @BindView(R.id.rela_cart_full_layout)
    RelativeLayout cart_full_layout;
    /**
     * RecycleView
     */
    @BindView(R.id.recycleView)
    XRecyclerView mRecycleView;
    /**
     * 数据列表
     */
    private List<BaseEntity> baseEntities = new ArrayList<>();
    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;
    /**
     * 数据适配器
     */
    private CartAdapter mAdapter;
    /**
     * 选中全选
     */
    @BindView(R.id.check_select)
    CheckBox mSelect;
    /**
     * 合计（总价）
     */
    @BindView(R.id.tv_total_money)
    TextView mTotalMoney;
    /**
     * 结算
     */
    @BindView(R.id.btn_settlement)
    Button mSettlement;


    @Override
    protected CartPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new CartPresenter(this);
        }
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }


    @Override
    protected void initUI() {
        mTitle.setText("购物车");
        mEdit.setText("编辑");
        mEdit.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.GONE);

        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setNoMore(true);
        mRecycleView.setLoadingMoreEnabled(false);
        mRecycleView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecycleView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

        mAdapter = new CartAdapter(getActivity());
        mRecycleView.setAdapter(mAdapter);

        initData();
    }

    private void initData() {

        mRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecycleView.setLoadingMoreEnabled(false);
                state = STATE_PULL_REFRESH;
                //mPresenter.getBannerList("HOME");
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getOederList(page);
            }
        });
        mRecycleView.setRefreshing(true);
    }

    @OnClick({R.id.btn_settlement, R.id.check_select,R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 去结算
             */
            case R.id.btn_settlement:
                JumpUtil.overlay(getActivity(), SubmitOrderActivity.class);
                break;
            /**
             * 全选
             */
            case R.id.check_select:
                if (mSelect.isChecked()) {
                    ToastUtils.showShort("选中");
                } else {
                    ToastUtils.showShort("取消");
                }
                break;
            /**
             * 编辑
             */
            case R.id.tv_confirm:
                ToastUtils.showShort("编辑");
                break;
        }
    }

    /**
     * 购物车列表
     * @param cartList
     */
    @Override
    public void CartList(PageEntity<HomeGoodsEntity> cartList) {
        List<BaseEntity> baseEns = new ArrayList<>();
        //baseEns.addAll(list.getList());


//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mDataList.setLayoutManager(staggeredGridLayoutManager);

        mAdapter.insert(mAdapter.getItemCount(), baseEns);
        if (page == 1) {
            if (mRecycleView != null)
                mRecycleView.setLoadingMoreEnabled(true);
        }
        ++page;
    }


    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(getActivity(), message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }



}