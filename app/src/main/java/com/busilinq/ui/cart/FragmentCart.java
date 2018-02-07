package com.busilinq.ui.cart;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.cart.ICartView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.AssembleProduct;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.BaseEntity;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.presenter.cart.CartPresenter;
import com.busilinq.ui.MainActivity;
import com.busilinq.ui.cart.adapter.CartAdapter;
import com.busilinq.widget.LinearDividerItemDecoration;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.longsh.optionframelibrary.OptionCenterDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
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
     * 购物车为空开始订购按钮
     */
    @BindView(R.id.btn_purchase)
    Button mBtnPurchase;
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
    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    /**
     * 0:增加数量
     * 1：减少数量
     */
    public int type = 0;
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
        mEdit.setVisibility(View.GONE);
        mEdit.setText("编辑");
        mBack.setVisibility(View.GONE);
        AssembleProduct.getInstance().clear();

        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setNoMore(true);
        mRecycleView.setLoadingMoreEnabled(false);
        mRecycleView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecycleView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

        mAdapter = new CartAdapter(getActivity(),  new CartAdapter.MyInterface() {
            @Override
            public void getCarInfo() {
                mTotalMoney.setText(AssembleProduct.getInstance().getSubPrice()+"");

            }
        });
        mRecycleView.setAdapter(mAdapter);

        /**
         * 增加
         */
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                type = 1;
                MainCartEntity item = mAdapter.getItem(position);
                mPresenter.UpdateCart(position, item.getCart().getCartId(), item.getCart().getNumber() + 1, item.getGoods().getGoods().getPrice());

            }
        });

        /**
         * 减少
         */
        mAdapter.setOnItemViewClickListener(new AbstractRecyclerViewAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                type = 0;
                MainCartEntity item = mAdapter.getItem(position);
                if (item.getCart().getNumber() == 1) {
                    ToastUtils.showShort("已经是底线了！");
                    return;
                }
                mPresenter.UpdateCart(position, item.getCart().getCartId(), item.getCart().getNumber() - 1, item.getGoods().getGoods().getPrice());

            }
        });
        /**
         * 长按删除
         */
        mAdapter.setOnItemLongClickListener(new AbstractRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final View itemView, final int positions) {

                final ArrayList<String> list = new ArrayList<>();
                list.add("删除");
                final OptionCenterDialog optionCenterDialog = new OptionCenterDialog();
                optionCenterDialog.show(getActivity(), list);
                optionCenterDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainCartEntity item = mAdapter.getItem(positions);
                        mPresenter.deletedCart(UserCache.GetUserId(),item.getCart().getCartId());
                        mPresenter.getOrderList(page);
                        optionCenterDialog.dismiss();
                    }
                });


            }
        });

        initData();
    }

    private void initData() {

        mRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecycleView.setLoadingMoreEnabled(false);
                state = STATE_PULL_REFRESH;
                page = 1;
                mPresenter.getOrderList(page);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getOrderList(page);
            }
        });
        mRecycleView.setRefreshing(true);
    }

    @OnClick({R.id.btn_purchase,R.id.btn_settlement, R.id.check_select, R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 开始订购
             */
            case R.id.btn_purchase:
                JumpUtil.overlay(getActivity(), MainActivity.class);
                break;
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
                ToastUtils.showShort("长按列表可删除");
                break;
        }
    }

    @Override
    public void Success(int position, CartEntity data) {

        MainCartEntity item = mAdapter.getItem(position);
        if (data != null) {
            CartEntity cartEntity = item.getCart();
            if (type == 1) {
                cartEntity.setNumber(item.getCart().getNumber() + 1);
                item.setCart(cartEntity);
            } else {
                cartEntity.setNumber(item.getCart().getNumber() - 1);
                item.setCart(cartEntity);
            }
            if( AssembleProduct.getInstance().getGoods()!=null&& AssembleProduct.getInstance().getGoods().contains(item))
            AssembleProduct.getInstance().increase(item);
        }
        mAdapter.notifyDataSetChanged();
        mTotalMoney.setText(AssembleProduct.getInstance().getSubPrice()+"");

    }


    /**
     * 购物车列表
     *
     * @param cartList
     */
    @Override
    public void CartList(PageEntity<MainCartEntity> cartList) {
        AssembleProduct.getInstance().clear();
        mTotalMoney.setText("");

        if (page == 1) {
            mAdapter.setData(cartList.getList());
            if (mRecycleView != null)
                mRecycleView.setLoadingMoreEnabled(true);
        }else {
            mAdapter.insert(mAdapter.getItemCount(), cartList.getList());
        }
        ++page;
    }


    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(getActivity(), message);
    }

    @Override
    public void hideProgress() {
        if (state == STATE_PULL_REFRESH) {
            if (mRecycleView != null)
                mRecycleView.refreshComplete();
        } else if (state == STATE_LOAD_MORE) {
            if (mRecycleView != null)
                mRecycleView.loadMoreComplete();
        }
    }

    /**
     * 购物车无数据时显示的布局
     */
    @Override
    public void showEmptyView() {
        mEdit.setVisibility(View.GONE);
        cart_full_layout.setVisibility(View.GONE);
        cart_empty_layout.setVisibility(View.VISIBLE);
    }

}