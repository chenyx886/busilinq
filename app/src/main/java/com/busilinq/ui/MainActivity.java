package com.busilinq.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.api.CartApi;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.event.MenuEvent;
import com.busilinq.data.event.RefreshNumEvent;
import com.busilinq.ui.cart.FragmentCart;
import com.busilinq.ui.classify.FragmentClassify;
import com.busilinq.ui.home.FragmentHome;
import com.busilinq.ui.mine.FragmentMine;
import com.busilinq.ulits.BugGoutAgent;
import com.chenyx.libs.utils.Logs;
import com.chenyx.libs.utils.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Company：华科建邺
 * Class Describe：主页
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MainActivity extends RxAppCompatActivity {

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

    /**
     * 数量消息
     */
    @BindView(R.id.tv_num)
    TextView mNum;

    private CompoundButton selectView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setTranslucentStatus();
        BugGoutAgent.init(this, false);
        EventBus.getDefault().register(this);
        initUI();
    }

    /**
     * UI初始化
     */
    private void initUI() {

        mHome.setTag(FragmentHome.TAG);
        mClassify.setTag(FragmentClassify.TAG);
        mCart.setTag(FragmentCart.TAG);
        mMine.setTag(FragmentMine.TAG);
        mHome.setChecked(true);//默认选中第一个

        QueryCartNum();

        getPersimmions();
    }


    /**
     * 菜单选中
     *
     * @param menuEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mMenuEvent(MenuEvent menuEvent) {
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.commit();

        if (menuEvent.getIndex() == 1) {
            mHome.setChecked(true);
        } else if (menuEvent.getIndex() == 2) {
            mClassify.setChecked(true);
        } else if (menuEvent.getIndex() == 3) {
            mCart.setChecked(true);
        } else if (menuEvent.getIndex() == 4) {
            mMine.setChecked(true);
        }
    }

    /**
     * 查询总数量
     *
     * @param mEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mRefreshNumEvent(RefreshNumEvent mEvent) {
        Logs.d("FragmentCart", "刷新数量");
        QueryCartNum();
    }

    /**
     * 查询购物车总数量
     */
    private void QueryCartNum() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS);

        OkHttpClient mOkHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApiFactory.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//对转换后的数据进行再包装
                .build();
        retrofit.create(CartApi.class)
                .cart(1, 10, UserCache.GetUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseData<PageEntity<MainCartEntity>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<PageEntity<MainCartEntity>> data) {
                        if (data.getCode().equals("0000")) {
                            RefreshNum(data.getData().getTotal());
                        } else {
                            RefreshNum(0);
                        }
                    }
                });
    }
    /**
     * 显示购物车数量
     *
     * @param total
     */
    private void RefreshNum(int total) {
        if (total > 0) {
            mNum.setVisibility(View.VISIBLE);
        } else {
            mNum.setVisibility(View.GONE);
        }
        if (total > 99)
            mNum.setText("99+");
        else
            mNum.setText(total + "");
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
                ToastUtils.showShort(getResources().getString(R.string.press_one_more_to_exit));
            }
            mKeyDownTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 沉浸式状态栏
     */
    protected void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;

    @TargetApi(23)
    private void getPersimmions() {

        ArrayList<String> permissions = new ArrayList<>();
        /***
         * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
         */
        //定位精确位置
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA);
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        //读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
        // 读写权限
        if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
        }
        // 读取电话状态权限
        if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
            permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
        }

        if (permissions.size() > 0) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
        }
    }


    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
    }
}
