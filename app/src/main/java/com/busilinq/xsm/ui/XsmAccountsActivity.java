package com.busilinq.xsm.ui;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.presenter.XsmAccountsPresenter;
import com.busilinq.xsm.ui.adapter.XsmAccountsAdapter;
import com.busilinq.xsm.viewinterface.IXsmAccountsView;
import com.busilinq.xsm.widget.CommonAlertDialog;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnAlertDialogListener;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2017/6/27.
 */

public class XsmAccountsActivity extends XsmBaseActivity implements IXsmAccountsView {

    @BindView(R.id.xsm_accouts_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.xsm_no_accounts_lly)
    LinearLayout mNoAcctountsLly;

    @BindView(R.id.xsm_accouts_swpLv)
    SwipeMenuListView menuListView;
    private int mLvPosition = -1;

    private XsmAccountsPresenter mPresenter;
    private CommonAlertDialog mDelAlertDialog;

    @Override
    public int initContentView() {
        return R.layout.xsm_accounts_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmAccountsPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onRestart() {
        mPresenter.start();
        super.onRestart();
    }

    private OnAlertDialogListener mOnAlertDialogListener = new OnAlertDialogListener() {
        @Override
        public void onSure() {
            super.onSure();
            //删除
            mPresenter.delAccount(mLvPosition);
            mDelAlertDialog.dismiss();
        }

        @Override
        public void onCancel() {
            super.onCancel();
            mDelAlertDialog.dismiss();
        }
    };
    private SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            SwipeMenuItem openItem = new SwipeMenuItem(mContext);
            openItem.setBackground(R.color.color_ed5656);
            openItem.setWidth((int) getResources().getDimension(R.dimen.space_200));
            openItem.setTitle("删除");
            openItem.setTitleSize(15);
            openItem.setTitleColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
            menu.addMenuItem(openItem);

        }
    };

    @Override
    public void initUi() {
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setMidMsg(mContext.getResources().getString(R.string.personal_xsm_accounts_title));
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);

        menuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        menuListView.setMenuCreator(creator);
        menuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                mLvPosition = position;
                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                mDelAlertDialog = new CommonAlertDialog(XsmAccountsActivity.this);
                mDelAlertDialog.setDialogListener(mOnAlertDialogListener);
                String name = mPresenter.getmAccounts().get(position).getName();
                mDelAlertDialog.setMsg("是否删除" + name + "?");
                mDelAlertDialog.setSureMsg(getResources().getString(R.string.myxsm_delete));
                mDelAlertDialog.show();
                return false;
            }
        });
        mPresenter.start();
    }


    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            XsmAccountsActivity.this.onBackPressed();
        }
    };

    @Override
    protected void onDestroy() {
        mPresenter.cancel();
        super.onDestroy();
    }


    @Override
    public void updateListView(XsmAccountsAdapter adapter) {
        menuListView.setAdapter(adapter);
    }

    @Override
    public void updateView(boolean isDatas) {
        if (isDatas) {
            menuListView.setVisibility(View.VISIBLE);
            mNoAcctountsLly.setVisibility(View.GONE);
        } else {
            menuListView.setVisibility(View.GONE);
            mNoAcctountsLly.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.xsm_accounts_add_account_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xsm_accounts_add_account_tv:
                mPresenter.login();
                break;
        }
    }


}
