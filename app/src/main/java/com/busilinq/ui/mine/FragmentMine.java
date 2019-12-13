package com.busilinq.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpFragment;
import com.busilinq.contract.mine.IMineView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.mine.MinePresenter;
import com.busilinq.ui.mine.adapter.MyOrderAdapter;
import com.busilinq.ui.mine.order.MyOrdersActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：我的
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FragmentMine extends BaseMvpFragment<MinePresenter> implements IMineView {

    public static String TAG = FragmentMine.class.getName();

    /**
     * 订单管理
     */
    @BindView(R.id.rv_order_item)
    RecyclerView mOrderItem;
    /**
     * 真实姓名
     */
    @BindView(R.id.tv_name)
    TextView mName;
    /**
     * 手机号
     */
    @BindView(R.id.tv_phone)
    TextView mPhone;
    /**
     * 头像
     */
    @BindView(R.id.iv_user_ico)
    ImageView mUserIco;

    private MyOrderAdapter myOrderAdapter;

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void initUI() {
        myOrderAdapter = new MyOrderAdapter(getContext());
        mOrderItem.setAdapter(myOrderAdapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mOrderItem.setLayoutManager(staggeredGridLayoutManager);
        myOrderAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
//                if (UserCache.get() == null) {
//                    JumpUtil.overlay(getActivity(), LoginActivity.class);
//                    return;
//                }
                //我的订单
                if (position == 0) {
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class);
                }
                //退货单
                else if (position == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.REFUND);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle);
                }
                //付款单
                else if (position == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.WAIT_SEND);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle);
                }
                //发货单
                else if (position == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putString(MyOrdersActivity.class.getSimpleName(), MyOrdersActivity.WAIT_RECEIVE);
                    JumpUtil.overlay(getActivity(), MyOrdersActivity.class, bundle);
                }
            }
        });

        initData();
    }


    @OnClick({R.id.it_xsm, R.id.userinfo, R.id.it_address, R.id.it_collection, R.id.it_user_info, R.id.it_feedback, R.id.iv_set})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userinfo:
                if (UserCache.get() != null) {
                    JumpUtil.startForResult(this, UpdateAvatarActivity.class, UpdateAvatarActivity.REQUEST, null);
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.it_address:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), AddressActivity.class);
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.it_collection:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), MyCollectionActivity.class);
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.it_user_info:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), UserInfoActivity.class);
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.it_feedback:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(getActivity(), FeedbackActivity.class);
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.it_xsm:
                if (UserCache.get() != null) {
                    mPresenter.getService(getContext());
                } else {
                    JumpUtil.overlay(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.iv_set:
                JumpUtil.startForResult(this, SetActivity.class, SetActivity.REQUEST, null);
                break;

        }
    }

    /**
     * 获取用户成功
     *
     * @param user
     */
    @Override
    public void showUserInfo(UserEntity user) {
        UserEntity userEntity = UserCache.get();
        if (user != null) {
            userEntity.setHeadimgurl(user.getHeadimgurl());
        }
        UserCache.put(userEntity);
        initData();
    }

    public void showProgress(String message) {
        MLoadingDialog.show(getActivity(), message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == UpdateAvatarActivity.REQUEST) {
            mPresenter.getUserInfo(UserCache.GetUserId(), "");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 刷新界面数据
     */
    private void initData() {
        Logs.d(TAG, "刷新了");
        if (UserCache.GetUserId() > 0) {
            if (!TextUtils.isEmpty(UserCache.get().getRealName())) {
                mName.setText(UserCache.get().getRealName());
            } else {
                mName.setText("名称");
            }
            mPhone.setText(UserCache.get().getCell());
            GlideShowImageUtils.displayCircleNetImage(getActivity(), UserCache.get().getHeadimgurl(), mUserIco, R.mipmap.ic_user);
        } else {
            mName.setText("登录/注册");
            mPhone.setText("手机号");
            mUserIco.setImageResource(R.mipmap.ic_user);
        }
    }
}