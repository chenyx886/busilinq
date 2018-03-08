package com.busilinq.ui.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.cart.ISubmitOrderView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.entity.OrderDetailsEntity;
import com.busilinq.data.entity.OrderEntity;
import com.busilinq.data.entity.OrderGoodsPO;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.data.event.RefreshCartEvent;
import com.busilinq.presenter.cart.SubmitOrderPresenter;
import com.busilinq.ui.mine.AddressActivity;
import com.busilinq.ui.mine.AddAddressActivity;
import com.busilinq.ui.mine.adapter.MyOrderDetailAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：购物车提交
 * Create Person：wenxin.li
 * Create Time：2018/1/31 12:42
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class SubmitOrderActivity extends BaseMvpActivity<SubmitOrderPresenter> implements ISubmitOrderView {

    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 有地址显示的布局
     */
    @BindView(R.id.line_address_full_layout)
    LinearLayout mAddressFull;
    /**
     * 无地址显示的布局
     */
    @BindView(R.id.line_address_empty_layout)
    LinearLayout mAddressEmpty;
    /**
     * 商品清单布局
     */
    @BindView(R.id.rel_item)
    RelativeLayout mRel_item;
    /**
     * 商品清单数量
     */
    @BindView(R.id.tv_list_num)
    TextView mListNum;
    /**
     * 底部显示的商品清单数量
     */
    @BindView(R.id.tv_total_num)
    TextView mTotalNum;
    /**
     * 底部显示的商品总价
     */
    @BindView(R.id.tv_total_money)
    TextView mTotalmoney;
    /**
     * 收货人姓名
     */
    @BindView(R.id.tv_name)
    TextView mName;
    /**
     * 收货人电话
     */
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    /**
     * 收货人单位
     */
    @BindView(R.id.tv_company)
    TextView tvCompany;
    /**
     * 收货人地址
     */
    @BindView(R.id.tv_address)
    TextView mAddress;
    /**
     * 配送方式总布局
     */
    @BindView(R.id.rel_take_way)
    RelativeLayout mRelTakeWay;
    /**
     * 配送方式子布局
     */
    @BindView(R.id.tv_take_way)
    TextView mTakeWay;
    /**
     * 支付类型总布局
     */
    @BindView(R.id.rel_total_pay)
    RelativeLayout mRelTotalPay;
    /**
     * 支付类型子布局
     */
    @BindView(R.id.tv_total_pay)
    TextView mTotalPay;
    /**
     * 备注
     */
    @BindView(R.id.et_remark)
    EditText medit_Remark;

    /**
     * 提交订单
     */
    @BindView(R.id.btn_settlement)
    Button mSettlement;

    private List<MainCartEntity> list;
    /**
     * 商品清单
     */
    @BindView(R.id.goodsList)
    XRecyclerView mGoodsList;

    private MyOrderDetailAdapter mAdapter;
    /**
     * 提交订单相关类
     *
     * @param bundle
     */
    private int addressId = -1;//收货地址Id
    private String shippingType;//配送方式
    private int activityId = 1;//活动Iid,暂未启用
    private String payType;//支付方式
    private String mRemark;//备注

    /**
     * 从上一页面传递的总价
     */
    private String passTotal;

    /**
     * 显示的商品清单
     */
    private List<OrderDetailsEntity> localgoodList;
    /**
     * 提交到后台的商品清单
     */
    private List<OrderGoodsPO> webNeedgoodList;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_submit_order);
    }

    @Override
    protected SubmitOrderPresenter createPresenter() {
        return new SubmitOrderPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText("确认订单");
        mPresenter.getDeaaultAddress(UserCache.GetUserId());
        list = (List<MainCartEntity>) this.getIntent().getSerializableExtra(SubmitOrderActivity.class.getSimpleName());
        passTotal = getIntent().getStringExtra("passTotal");
        mTotalmoney.setText(passTotal);
        handData();
        mListNum.setText("共" + localgoodList.size() + "种");
        mTotalNum.setText(localgoodList.size() + "");

        //商品清单
        mGoodsList.setLayoutManager(new LinearLayoutManager(this));
        mGoodsList.setNoMore(false);
        mGoodsList.setLoadingMoreEnabled(false);
        mAdapter = new MyOrderDetailAdapter(this);
        mAdapter.setData(localgoodList);
        mGoodsList.setAdapter(mAdapter);
    }

    @OnClick({R.id.tv_back, R.id.line_address_empty_layout, R.id.rel_item, R.id.rel_take_way, R.id.rel_total_pay, R.id.et_remark, R.id.btn_settlement, R.id.line_address_full_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 返回
             */
            case R.id.tv_back:
                finish();
                break;
            /**
             * 新增地址
             */
            case R.id.line_address_empty_layout:
                Bundle bundle = new Bundle();
                bundle.putString("come", "add");
                bundle.putString("isDefault", "1");
                JumpUtil.startForResult(this, AddAddressActivity.class, AddAddressActivity.ADDRESS_REQUESTCODE, bundle);
                break;
            /**
             * 商品清单的显示和隐藏
             */
            case R.id.rel_item:
                if (mGoodsList.getVisibility() == View.VISIBLE) {
                    mGoodsList.setVisibility(View.GONE);
                } else {
                    mGoodsList.setVisibility(View.VISIBLE);
                }
                break;
            /**
             * 配送方式
             */
            case R.id.rel_take_way:
                AlertDialog.Builder builder_take = new AlertDialog.Builder(this);
                //builder_take.setIcon(R.mipmap.ic_logo);
                builder_take.setTitle("配送方式");
                final String[] take_way = {"送货上门", "快递", "货运", "上门自取"};
                builder_take.setItems(take_way, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTakeWay.setText(take_way[which]);
                        switch (which) {
                            case 0:
                                shippingType = "DELIVERY";
                                break;
                            case 1:
                                shippingType = "EXPRESS";
                                break;
                            case 2:
                                shippingType = "FREIGHT";
                                break;
                            case 3:
                                shippingType = "ONDOOR";
                                break;
                            default:
                                break;
                        }
                        ;
                    }
                });
                builder_take.show();
                break;
            /**
             * 累计结算
             */
            case R.id.rel_total_pay:
                AlertDialog.Builder builder_total = new AlertDialog.Builder(this);
                builder_total.setTitle("请选择配送方式");
                final String[] total_pay = {"网上支付", "累计结算"};
                builder_total.setItems(total_pay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTotalPay.setText(total_pay[which]);
                        switch (which) {
                            case 0:
                                payType = "ONLINE";
                                break;
                            case 1:
                                payType = "SETTLEMENT";
                                break;
                            default:
                                break;
                        }
                        ;
                    }
                });
                builder_total.show();
                break;
            /**
             * 备注，默认不显示光标，点击后获取光标
             */
            case R.id.et_remark:
                medit_Remark.setCursorVisible(true);
                break;
            /**
             * 提交订单
             */
            case R.id.btn_settlement:
                if (addressId < 0) {
                    ToastUtils.showShort("请选择收货地址");
                } else if (TextUtils.isEmpty(shippingType)) {
                    ToastUtils.showShort("请选择配送方式");
                } else if (TextUtils.isEmpty(payType)) {
                    ToastUtils.showShort("请选择支付类型");
                } else {
                    mRemark = medit_Remark.getText().toString().trim();
                    mPresenter.submitOrder(addressId, shippingType, payType, activityId, mRemark, webNeedgoodList);
                }
                break;
            /**
             * 选择收货地址
             */
            case R.id.line_address_full_layout:
                Bundle bundle1 = new Bundle();
                bundle1.putString("order_req", "1");
                JumpUtil.startForResult(this, AddressActivity.class, AddressActivity.ADDRESS_REQUESTCODE, bundle1);
                break;
        }


    }

    /**
     * 选择地址时返回数据显示
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == AddressActivity.ADDRESS_REQUESTCODE) {
            mName.setText("收货人：" + data.getStringExtra("name"));
            tvPhone.setText("电话：" + data.getStringExtra("phone"));
            tvCompany.setText("收货单位：" + data.getStringExtra("company"));
            mAddress.setText("收货地址：" + data.getStringExtra("address"));
            addressId = data.getIntExtra("addressId", -1);
        } else if (resultCode == RESULT_OK && requestCode == AddAddressActivity.ADDRESS_REQUESTCODE) {
            mPresenter.getDeaaultAddress(UserCache.GetUserId());
        }
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }


    /**
     * 获取默认收货地址
     *
     * @param entity
     */
    @Override
    public void showDefaultAddress(UserShopAddrEntity entity) {
        if (null != entity) {
            mAddressEmpty.setVisibility(View.GONE);
            mAddressFull.setVisibility(View.VISIBLE);
            showAddress(entity);
        } else {
            mAddressEmpty.setVisibility(View.VISIBLE);
            mAddressFull.setVisibility(View.GONE);
        }
    }

    private OrderEntity mOrderEntity;

    @Override
    public void submitSuccess(OrderEntity orderEntity) {
        mOrderEntity = orderEntity;
        mPresenter.deleteCartList(list);
    }

    @Override
    public void deleteResult() {
        EventBus.getDefault().post(new RefreshCartEvent());
        Bundle bundle = new Bundle();
        bundle.putInt("orderId", mOrderEntity.getOrderId());
        JumpUtil.overlay(this, SubmitSuccessActivity.class, bundle);
        finish();
    }

    /**
     * 处理从购物车传递过来的数据
     */
    private void handData() {
        localgoodList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderDetailsEntity localgood = new OrderDetailsEntity();
            localgood.setNumber(list.get(i).getCart().getNumber());
            localgood.setGoodsName(list.get(i).getGoods().getGoods().getName());
            localgood.setGoodsPrice(list.get(i).getGoods().getPrice().getSalesPrice() * list.get(i).getCart().getNumber());
            localgoodList.add(localgood);
        }

        webNeedgoodList = new ArrayList<>();
        for (int j = 0; j < localgoodList.size(); j++) {
            OrderGoodsPO orderGoodsPO = new OrderGoodsPO();
            orderGoodsPO.setGoodsId(list.get(j).getGoods().getGoods().getGoodsId());
            orderGoodsPO.setCount(list.get(j).getCart().getNumber());
            orderGoodsPO.setMerchantId(activityId);//后台暂未启用，写死
            webNeedgoodList.add(orderGoodsPO);
        }
    }

    /**
     * 显示收货地址
     *
     * @param entity
     */
    private void showAddress(UserShopAddrEntity entity) {
        mName.setText("收货人：" + entity.getName());
        tvPhone.setText("电话：" + entity.getCell());
        tvCompany.setText("收货单位：" + entity.getCompany());
        mAddress.setText("收货地址：" + entity.getProvince() + entity.getCity() + entity.getCity() + entity.getSpecificAddr());
        addressId = entity.getAddrId();
    }
}
