package com.busilinq.ui.mine.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.order.IMyOrdersDetailView;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.presenter.mine.order.MyOrdersDetailPresenter;
import com.busilinq.ui.ToDevelopedActivity;
import com.busilinq.ui.mine.adapter.MyOrderDetailAdapter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/9 11:22
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class MyOrdersDetailActivity extends BaseMvpActivity<MyOrdersDetailPresenter> implements IMyOrdersDetailView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 订单号
     */
    @BindView(R.id.tv_order_num)
    TextView mOrderNum;
    /**
     * 订单金额
     */
    @BindView(R.id.tv_order_money)
    TextView mOrderMoney;
    /**
     * 下单时间
     */
    @BindView(R.id.tv_order_time)
    TextView mOrderTime;
    /**
     * 订单状态
     */
    @BindView(R.id.tv_order_status)
    TextView mOrderStatus;
    /**
     * 收货人
     */
    @BindView(R.id.tv_take_person)
    TextView mTakePerson;
    /**
     * 电话
     */
    @BindView(R.id.tv_phone)
    TextView mPhone;
    /**
     * 地址
     */
    @BindView(R.id.tv_address)
    TextView mAddress;
    /**
     * 配送方式
     */
    @BindView(R.id.tv_distribution_way)
    TextView mDistributionWay;
    /**
     * 配送状态
     */
    @BindView(R.id.tv_distribution_status)
    TextView mDistributionStatus;
    /**
     * 支付方式
     */
    @BindView(R.id.tv_pay_way)
    TextView mPayWay;
    /**
     * 支付状态
     */
    @BindView(R.id.tv_pay_status)
    TextView mPayStatus;
    /**
     * 商品清单
     */
    @BindView(R.id.goodsList)
    XRecyclerView mGoodsList;
    /**
     * 删除订单
     */
    @BindView(R.id.btn_delete_order)
    Button mDeleteBtn;
    /**
     * 付款按钮
     */
    @BindView(R.id.btn_pay)
    Button mPayBtn;
    /**
     * 物流按钮
     */
    @BindView(R.id.btn_logistics)
    Button mLogisticsBtn;

    private MyOrderDetailAdapter mAdapter;

    private String orderNum;
    private int orderId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_orders_detail);
    }

    @Override
    protected MyOrdersDetailPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new MyOrdersDetailPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.my_orders_detail_title);
        orderNum = getIntent().getStringExtra("orderNum");
        mPresenter.getOrdersListDetail(orderNum);
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @OnClick({R.id.btn_logistics,R.id.tv_back, R.id.btn_delete_order, R.id.btn_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_logistics:
                JumpUtil.overlay(this, ToDevelopedActivity.class);
                break;
            case R.id.btn_delete_order:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确定要删除吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.deletedOrder(orderId, orderNum);
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
                break;
            case R.id.btn_pay:
                JumpUtil.overlay(this, ToDevelopedActivity.class);
                break;
        }
    }

    @Override
    public void OrdersListDetail(HomeOrderEntity detail) {

        orderId = detail.getOrder().getOrderId();

        //订单信息
        mOrderNum.setText(detail.getOrder().getOrderNumber());
        mOrderMoney.setText(detail.getOrder().getTotalMoney() + "");
        mOrderTime.setText(detail.getOrder().getOrderTime());
        mOrderStatus.setText(detail.getOrder().getStatus());
        if (detail.getOrder().getStatus().equals("待支付"))
            mPayBtn.setVisibility(View.VISIBLE);
        else
            mPayBtn.setVisibility(View.GONE);
        if(detail.getOrder().getStatus().equals("待接收")||detail.getOrder().getStatus().equals("完成"))
            mLogisticsBtn.setVisibility(View.VISIBLE);
        else
            mLogisticsBtn.setVisibility(View.GONE);
        //收货信息
        mTakePerson.setText(detail.getAddr().getName());
        mPhone.setText(detail.getAddr().getCell());
        mAddress.setText(detail.getAddr().getAddress());
        //支付及配送方式
        mDistributionWay.setText(detail.getShipping().getShippingType());
        mDistributionStatus.setText(detail.getShipping().getShippingStatus());
        mPayWay.setText(detail.getShipping().getPayType());
        mPayStatus.setText(detail.getShipping().getPayStatus());
        //商品清单
        mGoodsList.setLayoutManager(new LinearLayoutManager(this));
        mGoodsList.setNoMore(false);
        mGoodsList.setLoadingMoreEnabled(false);
        mAdapter = new MyOrderDetailAdapter(this);
        mAdapter.setData(detail.getDetails());
        mGoodsList.setAdapter(mAdapter);
    }

    /**
     * 删除成功
     */
    @Override
    public void success() {
        ToastUtils.showShort("删除成功");
        finish();
    }
}
