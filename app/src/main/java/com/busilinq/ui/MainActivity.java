package com.busilinq.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.ui.cart.FragmentCart;
import com.busilinq.ui.classify.FragmentClassify;
import com.busilinq.ui.home.FragmentHome;
import com.busilinq.ui.mine.FragmentMine;
import com.busilinq.ulits.AppUtils;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.Toasts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * Company：华科建邺
 * Class Describe：主页
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MainActivity extends BaseActivity {

    /**
     * 首页
     */
    @BindView(R.id.rb_home)
    RadioButton mHome;
    /**
     * 分类
     */
    @BindView(R.id.rb_classify)
    RadioButton mClassify;
    /**
     * 购物车
     */
    @BindView(R.id.rb_cart)
    RadioButton mCart;
    /**
     * 我的
     */
    @BindView(R.id.rb_mine)
    RadioButton mMine;

    private CompoundButton selectView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * UI初始化
     */

    @Override
    protected void initUI() {
        mHome.setTag(FragmentHome.TAG);
        mClassify.setTag(FragmentClassify.TAG);
        mCart.setTag(FragmentCart.TAG);
        mMine.setTag(FragmentMine.TAG);
        mHome.setChecked(true);//默认选中第一个
        getLocationPersimmions();
    }


    /**
     * showFragment
     *
     * @param curView
     */

    private void showFragment(CompoundButton curView) {

        if (selectView != null) {
            selectView.setChecked(false);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = (String) curView.getTag();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(R.id.fl_relcontent, Fragment.instantiate(this, tag), tag);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.show(fragmentManager.findFragmentByTag(tag));
            fragmentTransaction.commit();
        }
        selectView = curView;
    }

    /**
     * hideFragment
     *
     * @param lastView
     */
    private void hideFragment(CompoundButton lastView) {

        if (lastView != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            String tag = (String) lastView.getTag();
            if (fragmentManager.findFragmentByTag(tag) != null) {
                fragmentTransaction.hide(fragmentManager.findFragmentByTag(tag));
                fragmentTransaction.commit();
            }
        }
    }

    /**
     * 界面上单选按钮 OnCheckedChanged 事件
     *
     * @param buttonView
     * @param isChecked
     */
    @OnCheckedChanged({R.id.rb_home, R.id.rb_classify, R.id.rb_cart, R.id.rb_mine})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            showFragment(buttonView);
        } else {
            hideFragment(buttonView);
        }
    }


    private long mKeyDownTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mKeyDownTime < 800) { // 0.8s
                MApplication.getInstance().appManager.finishAllActivity();
                System.exit(0);
            } else {
                Toasts.showShort(MainActivity.this, R.string.press_one_more_to_exit);
            }
            mKeyDownTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void getLocationPersimmions() {

        ArrayList<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MApplication.getAppContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MApplication.getAppContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MApplication.getAppContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (permissions.size() > 0) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), AppUtils.POSTION_RQESTCODE);
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


}
