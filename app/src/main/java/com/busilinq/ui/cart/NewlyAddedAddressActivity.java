package com.busilinq.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.cart.INewlyAddedAddress;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.cart.NewlyAddedAddressPresenter;
import com.busilinq.widget.MLoadingDialog;
import com.busilinq.xsm.ulits.StringUtils;
import com.chenyx.libs.utils.Toasts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：新增收货地址
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class NewlyAddedAddressActivity extends BaseMvpActivity<NewlyAddedAddressPresenter> implements INewlyAddedAddress {

    public static final int ADDRESS_REQUESTCODE = 2;

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
     * 收货单位
     */
    @BindView(R.id.new_unit_et)
    EditText new_unit_et;

    /**
     * 联系人
     */
    @BindView(R.id.new_consignee_et)
    EditText new_consignee_et;

    /**
     * 联系方式
     */
    @BindView(R.id.new_tell_et)
    EditText new_tell_et;

    /**
     * 收货地址
     */
    @BindView(R.id.new_detail_address)
    EditText new_detail_address;

    private String isDefault; // 是否默认
    private String come; // 来源
    private String unit;
    private String consignee;
    private String tell;
    private String detailAddress;
    private Integer addressId;


    @Override
    protected NewlyAddedAddressPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new NewlyAddedAddressPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_newly_added_address);
    }

    @Override
    protected void initUI() {
        mTitle.setText("收货地址");

        Bundle bundle;
        UserShopAddrEntity entity = null;
        come = getIntent().getStringExtra("come");
        isDefault = getIntent().getStringExtra("isDefault");
        if (come != null) {
            if (come.equals("edit")) {
                bundle = getIntent().getExtras();
                entity = bundle.getParcelable("editAddressInfo");
                addressId = entity.getAddrId();
                new_unit_et.setText(entity.getCompany());
                new_consignee_et.setText(entity.getName());
                new_tell_et.setText(entity.getCell());
                new_detail_address.setText(entity.getSpecificAddr());
            }
        }

    }

    @OnClick({R.id.tv_back, R.id.btn_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_save: // 保存按钮
                addAddressInfo();
                break;
        }


    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void addAddressSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void addAddressInfo() {
        unit = new_unit_et.getText().toString();
        consignee = new_consignee_et.getText().toString();
        tell = new_tell_et.getText().toString();
        detailAddress = new_detail_address.getText().toString();
        UserShopAddrEntity entity = new UserShopAddrEntity();
        if (StringUtils.isEmpty(unit)) {
            Toasts.showShort(this, "请填写收货单位");
            return;
        }
        entity.setCompany(unit);
        if (StringUtils.isEmpty(consignee)) {
            Toasts.showShort(this, "请填写联系人");
            return;
        }
        entity.setName(consignee);
        if (StringUtils.isEmpty(tell)) {
            Toasts.showShort(this, "请填写联系电话");
            return;
        }
        entity.setCell(tell);
        if (StringUtils.isEmpty(detailAddress)) {
            Toasts.showShort(this, "请填写详细地址");
            return;
        }
        entity.setSpecificAddr(detailAddress);
        entity.setIsDefault(isDefault);

        if (come.equals("add")) {
            mPresenter.addAddress(entity);
        } else if (come.equals("edit")) {
            entity.setAddrId(addressId);
            mPresenter.editAddress(entity);
        }
    }
}
