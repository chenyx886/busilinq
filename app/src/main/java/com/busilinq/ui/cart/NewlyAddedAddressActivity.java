package com.busilinq.ui.cart;

import android.content.Intent;
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
        Intent intent = getIntent();
        Bundle bundle;
        UserShopAddrEntity entity = null;
        if (intent != null) {
            come = intent.getStringExtra("come");
        }
        if (come != null) {
            if (come.equals("edit")) {
                bundle = intent.getExtras();
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
        finish();
    }

    @Override
    public void addAddressInfo() {
        unit = new_unit_et.getText().toString();
        consignee = new_consignee_et.getText().toString();
        tell = new_tell_et.getText().toString();
        detailAddress = new_detail_address.getText().toString();
        UserShopAddrEntity entity = new UserShopAddrEntity();
        if (unit != null)
            entity.setCompany(unit);
        if (consignee != null)
            entity.setName(consignee);
        if (tell != null)
            entity.setCell(tell);
        if (detailAddress != null)
            entity.setSpecificAddr(detailAddress);

        if (come.equals("add")) {
            mPresenter.addAddress(entity);
        } else if (come.equals("edit")) {
            entity.setAddrId(addressId);
            mPresenter.editAddress(entity);
        }
    }
}
