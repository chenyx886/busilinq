package com.busilinq.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.AddressListView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.mine.AddressPresenter;
import com.busilinq.ui.cart.NewlyAddedAddressActivity;
import com.busilinq.ui.mine.adapter.AddressAdapter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.Logs;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：收货地址
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class AddressActivity extends BaseMvpActivity<AddressPresenter> implements AddressListView, AddressAdapter.ButtonOnClickListener {

    public static final int ADDRESS_REQUESTCODE = 1;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    RecyclerView recyclerView;

    private AddressAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_address);
    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.my_receiv_address);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        //LayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AddressAdapter(this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnclickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getAddressList(UserCache.GetUserId());
    }

    @OnClick({R.id.tv_back, R.id.add_address_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_address_layout:
                Bundle bundle = new Bundle();
                bundle.putString("come", "add");
                JumpUtil.overlay(this, NewlyAddedAddressActivity.class, bundle);
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
    public void showAddressList(List<UserShopAddrEntity> list) {
        mAdapter.setData(list);
    }

    /**
     * 设置默认地址
     *
     * @param view
     * @param pos
     */
    @Override
    public void selectedClick(View view, int pos) {
        mPresenter.setDefaultAddress(UserCache.GetUserId(), mAdapter.getItem(pos).getAddrId());
    }

    /**
     * 删除地址
     *
     * @param view
     * @param pos
     */
    @Override
    public void deletedClick(View view, int pos) {
        mPresenter.deletedAddress(UserCache.GetUserId(), mAdapter.getItem(pos).getAddrId());
    }

    /**
     * 编辑地址
     *
     * @param view
     * @param pos
     */
    @Override
    public void editClick(View view, int pos) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("editAddressInfo", mAdapter.getItem(pos));
        bundle.putString("come", "edit");
        JumpUtil.overlay(this, NewlyAddedAddressActivity.class, bundle);
    }

    @Override
    public void itermClick(View view, int pos) {
        String a = getIntent().getStringExtra("order_req");
        if (null != a) {
            if (a.equals("1")) {
                UserShopAddrEntity item = mAdapter.getItem(pos);
                Intent data = new Intent();
                data.putExtra("name", item.getName());
                data.putExtra("phone", item.getCell());
                data.putExtra("company", item.getCompany());
                data.putExtra("address", item.getSpecificAddr());
                data.putExtra("addressId", item.getAddrId());
                Logs.d("AddressActivity", "执行");
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }
}