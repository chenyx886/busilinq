package com.busilinq.ui.cart;


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
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.entity.OrderCreateASK;
import com.busilinq.data.entity.OrderGoodsPO;
import com.busilinq.presenter.cart.CartPresenter;
import com.busilinq.ui.MainActivity;
import com.busilinq.ui.cart.adapter.CartAdapter;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.ui.mine.UpdateAvatarActivity;
import com.busilinq.widget.MLoadingDialog;
import com.busilinq.xsm.ulits.StringParse;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.longsh.optionframelibrary.OptionCenterDialog;

import java.io.Serializable;
import java.util.ArrayList;
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

    /**
     *传递到下一页面的总价
     * @return
     */
    String passTotal;


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
    public void onResume() {
        super.onResume();
        if (UserCache.get() != null) {
            mPresenter.getOrderList(page);
        }
        Log.d(TAG, "onResume: ------------");
    }

    @Override
    protected void initUI() {

        if (UserCache.get() == null) {
            JumpUtil.startForResult(this, LoginActivity.class, LoginActivity.REQUEST, null);
        }
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
        mAdapter = new CartAdapter(getActivity(), new CartAdapter.DataUpdateListener() {
            @Override
            public void update(final int position, int number) {
                MainCartEntity item = mAdapter.getItem(position);
                mPresenter.UpdateCart(position, item.getCart().getCartId(), number, item.getGoods().getGoods().getPrice());
            }

            @Override
            public void updateCheck() {
                checkAll();
                totalMoney();
            }
        });
        mRecycleView.setAdapter(mAdapter);


//        /**
//         * 长按删除
//         */
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
                        mPresenter.deletedCart(position, UserCache.GetUserId(), item.getCart().getCartId());
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

    @OnClick({R.id.btn_purchase, R.id.btn_settlement, R.id.check_select, R.id.tv_confirm})
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
                List<MainCartEntity> list = new ArrayList<>();
                for (MainCartEntity mainCartEntity : mAdapter.getItems()) {
                    if (mainCartEntity.getCart().getIsChecked() == 1)
                        list.add(mainCartEntity);
                }
                if(list.size()>0)
                {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SubmitOrderActivity.class.getSimpleName(), (Serializable) list);
                    bundle.putString("passTotal",passTotal);
                    JumpUtil.overlay(getActivity(), SubmitOrderActivity.class, bundle);
                }
                else
                    ToastUtils.showShort("请选择需要购买的商品！");

                break;
            /**
             * 全选
             */
            case R.id.check_select:
                mAdapter.setAllCheck(mSelect.isChecked());
                totalMoney();
                break;

        }
    }

    @Override
    public void Success(int position, CartEntity data) {
        if (data != null) {
            MainCartEntity item = mAdapter.getItem(position);
            data.setIsChecked(item.getCart().getIsChecked());
            item.setCart(data);
            mAdapter.setEntityData(position, item);
        }
        checkAll();
        totalMoney();
    }

    private void totalMoney() {
        double totalMoney = 0;
        for (MainCartEntity entity : mAdapter.getItems()) {
            if (entity.getCart().getIsChecked() == 1)
                totalMoney = totalMoney + entity.getCart().getTotalMoney();
        }

        mTotalMoney.setText(StringParse.formatMoney(totalMoney));
        passTotal = StringParse.formatMoney(totalMoney);
    }

    private void checkAll() {
        if (mAdapter.getCheckNumber() == 0)
            mSelect.setChecked(false);
        else {
            if (mAdapter.getCheckNumber() == mAdapter.getItems().size())
                mSelect.setChecked(true);
            else
                mSelect.setChecked(false);
        }

    }

    @Override
    public void deleteItem(int position) {
        mAdapter.delectItem(position);
        checkAll();
        totalMoney();
    }

    /**
     * 购物车列表
     *
     * @param cartList
     */
    @Override
    public void CartList(PageEntity<MainCartEntity> cartList) {
        if (page == 1) {
            mAdapter.setData(cartList.getList());
            if (mRecycleView != null)
                mRecycleView.setLoadingMoreEnabled(true);
        } else {
            mAdapter.insert(mAdapter.getItemCount(), cartList.getList());
        }
        ++page;
        checkAll();
        totalMoney();
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