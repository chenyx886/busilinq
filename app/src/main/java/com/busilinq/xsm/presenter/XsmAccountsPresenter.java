package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.os.Bundle;


import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.XsmAccount;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.ui.XsmLoginActivity;
import com.busilinq.xsm.ui.adapter.XsmAccountsAdapter;
import com.busilinq.xsm.ulits.UserCenterServiceManager;
import com.busilinq.xsm.viewinterface.IXsmAccountsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yu on 2017/6/27.
 */

public class XsmAccountsPresenter extends XsmBasePresenter<IXsmAccountsView> {

    private XsmAccount mDeletingAccount;
    private HashMap<String, XsmAccount> mAccountMap;
    private List<XsmAccount> mAccounts = new ArrayList<>();
    private XsmAccountsAdapter mAdapter;

    @Override
    public void attachView(IXsmAccountsView view) {
        super.attachView(view);

    }

    public void login() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(XsmLoginActivity.class.getName(), false);
        mBaseView.showActivity((Activity) mContext, XsmLoginActivity.class, bundle);
    }

    public List<XsmAccount> getmAccounts() {
        return mAccounts;
    }

    public void delAccount(int position) {
        XsmAccount account = mAccounts.get(position);
        mAccounts.remove(account);
        mAccountMap.remove(account.getAccount());
        mXsmDbApi.saveXsmAccounts(mAccountMap);
        updateList();
    }


    @Override
    public void start() {
        mAccounts.clear();
        mAccountMap = mXsmDbApi.getXsmAccounts();
        if (mAccountMap != null && mAccountMap.size() > 0) {
            Iterator iter = mAccountMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                XsmAccount account = (XsmAccount) entry.getValue();
                mAccounts.add(account);
            }
        }
        updateList();
    }

    private void updateList() {
        mBaseView.updateView((mAccounts.size() == 0) ? false : true);
        if (null == mAdapter) {
            mAdapter = new XsmAccountsAdapter(mContext, mAccounts);
            mBaseView.updateListView(mAdapter);
        } else {
            mAdapter.setAccounts(mAccounts);
        }
    }


    public void removeAccount() {
        if (null == mDeletingAccount)
            return;
        mAccountMap.remove(mDeletingAccount.getAccount());
        if (mAccountMap.size() == 0) {
            mXsmDbApi.clearAll();
        } else {
            XsmAccount account = mXsmDbApi.getXsmAccount();
            if (account.getAccount().equals(mDeletingAccount.getAccount())) {
                mXsmDbApi.clear(XsmAccount.class.getSimpleName());
                mXsmDbApi.clearRemoteAll();
            }
            mXsmDbApi.saveXsmAccounts(mAccountMap);
        }
        updateList();
    }

    public void removeCancle() {
        mDeletingAccount = null;
    }

    public void finish() {
        if (null == mXsmDbApi.getXsmAccount()) {
            ((Activity) mContext).finish();
        } else {
            ((Activity) mContext).finish();
        }
    }


    private void authorize(final XsmAccount account) {
        mBaseView.showProgressDialog("正在切换帐号中...");
        Subscription subscription = mXsmApi.authorize(mHelper.getUser().getMemberId(), account.getOrgCode(), mHelper.getUser().getCell(), account.getAccount(), account.getPsw())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<Merchant>>() {
                    @Override
                    public void call(HttpEntity<Merchant> httpEntity) {
                        mBaseView.closeProgressDialog();
                        if (httpEntity.isSuccess()) {
                            Merchant merchant = httpEntity.getData();
                            mXsmDbApi.clearOrdering();
                            mXsmDbApi.saveXsmAccount(account);
                            mXsmDbApi.saveMerchant(merchant);
                            syncUserInformation(merchant.getCustCode());
                            ((Activity) mContext).onBackPressed();
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction("04002"));
        mSubscriptions.add(subscription);
    }


    private void syncUserInformation(String account) {
        UserEntity user = UserCenterHelper.getInstance(mContext).getUser();
        Map<String, String> map = new HashMap<>();
        map.put("cell", user.getCell());
        map.put("sysAct", account);
        UserCenterServiceManager manager = UserCenterServiceManager.getInstance();
        manager.setUser(user);
        manager.saveUserInfo(map);
    }
}

