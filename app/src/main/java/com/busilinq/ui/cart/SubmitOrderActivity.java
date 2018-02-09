package com.busilinq.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.cart.ISubmitOrderView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.entity.OrderCreateASK;
import com.busilinq.data.entity.OrderGoodsPO;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.cart.SubmitOrderPresenter;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;

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
    private List<MainCartEntity> list;
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
     * 新增收货地址
     */
    @BindView(R.id.tv_add_address)
    TextView mAddAddress;
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
     * 备注
     */
    @BindView(R.id.et_remark)
    EditText mRemark;


    /**
     * 提交订单
     */
    @BindView(R.id.btn_settlement)
    Button mSettlement;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_submit_order);
    }

    @Override
    protected SubmitOrderPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new SubmitOrderPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void initUI() {
        mTitle.setText("确认订单");
        list = (List<MainCartEntity>) this.getIntent().getSerializableExtra(SubmitOrderActivity.class.getSimpleName());
        mPresenter.getDeaaultAddress(UserCache.get().getUserId());

    }

    private void tijiao() {
        OrderCreateASK orderCreateASK = new OrderCreateASK();
        orderCreateASK.setUserId(UserCache.GetUserId());
        List<OrderGoodsPO> goodsPOList = new ArrayList<>();
        for (int position = 0; position < list.size(); position++) {
            OrderGoodsPO orderGoodsPO = new OrderGoodsPO();
            orderGoodsPO.setGoodsId(list.get(position).getGoods().getGoods().getGoodsId());
            orderGoodsPO.setCount(list.get(position).getCart().getNumber());
            goodsPOList.add(orderGoodsPO);
        }
        orderCreateASK.setGoodsList(goodsPOList);
    }

    @OnClick({R.id.tv_back, R.id.tv_add_address, R.id.et_remark, R.id.btn_settlement})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            /**
             * 新增地址
             */
            case R.id.tv_add_address:
                JumpUtil.overlay(this, NewlyAddedAddressActivity.class);
                break;
            /**
             * 备注，默认不显示光标，点击后获取光标
             */
            case R.id.et_remark:
                mRemark.setCursorVisible(true);
                break;
            /**
             * 提交订单
             */
            case R.id.btn_settlement:
                JumpUtil.overlay(this, SubmitSuccessActivity.class);
                break;
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
     * @param addrDefaultEntity
     */
    @Override
    public void getDefaultAddress(UserShopAddrEntity addrDefaultEntity) {
        if (null != addrDefaultEntity) {
            mAddressEmpty.setVisibility(View.GONE);
            mAddressFull.setVisibility(View.VISIBLE);
            showAddress(addrDefaultEntity);
        }
    }

    /**
     * 显示收货地址
     *
     * @param addrDefaultEntity
     */
    private void showAddress(UserShopAddrEntity addrDefaultEntity) {
        mName.setText("收货人：" + addrDefaultEntity.getName());
        tvPhone.setText("电话：" + addrDefaultEntity.getCell());
        tvCompany.setText("收货单位：" + addrDefaultEntity.getCompany());
        mAddress.setText("收货地址：" + addrDefaultEntity.getProvince() + addrDefaultEntity.getCity() + addrDefaultEntity.getCity() + addrDefaultEntity.getSpecificAddr());
    }
}
