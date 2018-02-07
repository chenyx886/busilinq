package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.busilinq.R;
import com.busilinq.ui.mine.LoginActivity;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Organize;
import com.busilinq.xsm.data.entity.XsmAccount;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.ui.XsmOrdersActivity;
import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.ulits.ThreadManager;
import com.busilinq.xsm.ulits.UserCenterServiceManager;
import com.busilinq.xsm.viewinterface.IXsmLoginView;

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
 * Created by dingyi on 2016/12/16.
 */

public class XsmLoginPresenter extends XsmBasePresenter<IXsmLoginView> {


    private int mSelectedProvence;
    private int mSelectedComp;
    //省份和公司name集合
    private List<String> provinceNameList = new ArrayList<>();
    private List<String> companyNameList = new ArrayList<>();
    private List<String> accoutsNameList = new ArrayList<>();
    //省份和公司code集合
    private List<Organize> provinceList = new ArrayList<>();
    private List<Organize> companyList = new ArrayList<>();
    private List<XsmAccount> accoutsList = new ArrayList<>();
    private Resources mRes;
    private ACache aCache;

    @Override
    public void attachView(IXsmLoginView view) {
        super.attachView(view);
        this.mSelectedProvence = 0;
        mRes = mContext.getResources();
        aCache = ACache.get(mBaseView.getContext());
        HashMap<String, XsmAccount> xsmAccoutsMap = mXsmDbApi.getXsmAccounts();
        if (xsmAccoutsMap != null && xsmAccoutsMap.size() > 0) {
            Iterator iter = xsmAccoutsMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                XsmAccount account = (XsmAccount) entry.getValue();
                accoutsNameList.add(account.getName());
                accoutsList.add(account);
            }
            if (accoutsList.size() > 1) {
                mBaseView.updateAccoutsIv(true);
                return;
            }

        }
        mBaseView.updateAccoutsIv(false);

    }

    private OptionsPickerView optionsPickerView;

    @Override
    public void start() {
        getProvinceData();
    }

    public void showOptionsPicker(final int id) {
        optionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (id == R.id.myxsm_login_province_lly) {
                    mBaseView.updateProvinceView(provinceList.get(options1).getOrgName());
                    mSelectedProvence = options1;
                    mSelectedComp = 0;
                } else if (id == R.id.myxsm_login_account_iv) {
                    mBaseView.updateAccoutView(accoutsList.get(options1).getAccount());
                } else {
                    mBaseView.updateCompView(companyList.get(options1).getOrgName());
                    mSelectedComp = options1;
                }
            }
        }).setContentTextSize((int) mContext.getResources().getDimension(R.dimen.text_size12)).setCancelText(" ").setCancelColor(mRes.getColor(R.color.color_009999))
                .setSubmitColor(mRes.getColor(R.color.color_009999)).build();
        if (id == R.id.myxsm_login_firm_lly)
            optionsPickerView.setPicker(companyNameList);
        else if (id == R.id.myxsm_login_account_iv)
            optionsPickerView.setPicker(accoutsNameList);
        else
            optionsPickerView.setPicker(provinceNameList);
        optionsPickerView.show();
    }

    /**
     * 获取省份和省份公司列表数据
     */
    private void getProvinceData() {
        mBaseView.showProgressDialog("数据正在加载中");
        Subscription subscription = mXsmApi.organize("0")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Organize>>>() {
                    @Override
                    public void call(HttpEntity<List<Organize>> listHttpEntity) {
                        if (!listHttpEntity.isSuccess()) {
                            mBaseView.updateProvinceView("暂无数据");
                            mBaseView.updateCompView("暂无数据！");
                            provinceList.clear();
                            provinceNameList.clear();
                            companyList.clear();
                            companyNameList.clear();
                            mBaseView.closeProgressDialog();
                        }
                        provinceList = listHttpEntity.getData();
                        if (provinceNameList.size() > 0) {
                            provinceNameList.clear();
                        }
                        for (Organize org : listHttpEntity.getData()) {
                            provinceNameList.add(org.getOrgName());
                        }
                        if (provinceNameList.size() > 0) {
                            mBaseView.updateProvinceView(provinceNameList.get(0));
                            getCompanyData(provinceList.get(0).getId());
                        }
                    }
                }, getThrowableAction(""));
        mSubscriptions.add(subscription);
    }

    private void getCompanyData(int id) {
        //获取省份和公司
        Subscription subscription = mXsmApi.organize(String.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Organize>>>() {
                    @Override
                    public void call(HttpEntity<List<Organize>> listHttpEntity) {
                        if (listHttpEntity.isSuccess()) {
                            companyList = listHttpEntity.getData();
                            if (companyNameList.size() > 0) {
                                companyNameList.clear();
                            }
                            for (Organize org : listHttpEntity.getData()) {
                                companyNameList.add(org.getOrgName());
                            }
                            if (companyNameList.size() > 0) {
                                mBaseView.updateCompView(companyNameList.get(0));
                            }
                            mBaseView.closeProgressDialog();
                        } else {
                            companyList.clear();
                            mBaseView.closeProgressDialog();
                            companyNameList.clear();
                            mBaseView.updateCompView("暂无数据！");
                        }
                    }
                }, getThrowableAction(""));
        mSubscriptions.add(subscription);
    }

    public void authorize(String account, final String password) {
        if (!mHelper.isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(LoginActivity.class.getSimpleName(), false);
            mBaseView.showActivity((Activity) mContext, LoginActivity.class, bundle);
            return;
        }
        if (provinceNameList.size() == 0 || companyList.size() == 0) {
            mBaseView.toast("没有烟草公司数据，无法授权");
            return;
        }
        if (!check(account, password))
            return;
        String orgCode = companyList.get(mSelectedComp).getOrgCode();
        mBaseView.showProgressDialog("正在授权中...");
        Subscription subscription = mXsmApi.authorize(mHelper.getUser().getMemberId(), orgCode, mHelper.getUser().getCell(), account, password)
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
                            saveXsmAccount(merchant, password);
                            syncUserInformation(merchant.getCustCode());
                            loginSuccess();
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction("04002"));
        mSubscriptions.add(subscription);
    }

    private void saveXsmAccount(final Merchant merchant, final String password) {
        ThreadManager.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                XsmAccount account = new XsmAccount();
                account.setOrgCode(merchant.getCompId());
                account.setAccount(merchant.getCustCode());
                account.setOrgName(merchant.getCompName());
                account.setName(merchant.getCustName());
                account.setAddress(merchant.getBusiAddr());
                account.setPsw(password);
                mXsmDbApi.saveXsmAccount(account);
                mXsmDbApi.saveMerchant(merchant);
                HashMap<String, XsmAccount> map = (HashMap<String, XsmAccount>) mXsmDbApi.getXsmAccounts();
                if (null == map) {
                    map = new HashMap<>();
                }
                map.put(merchant.getCustCode(), account);
                mXsmDbApi.saveXsmAccounts(map);
            }
        });
    }


    /**
     * 判断新商盟，密码是否为空
     *
     * @param account
     * @param password
     * @return
     */
    private boolean check(String account, String password) {
        if (null == account || account.equals("")) {
            mBaseView.toast(mRes.getString(R.string.login_prompt_account));
            return false;
        }
        if (account.length() != 12) {
            mBaseView.toast(mRes.getString(R.string.login_format_prompt));
            return false;
        }
        if (null == password || password.equals("")) {
            mBaseView.toast(mRes.getString(R.string.login_prompt_password));
            return false;
        }
        return true;
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


    private void loginSuccess() {
        mBaseView.skipActivity((Activity) mContext, XsmOrdersActivity.class);
    }

    public Merchant getMerchant() {
        return mXsmDbApi.getMerchant();
    }
}
