package com.busilinq.ui.mine;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.ISetView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.presenter.mine.SetPresenter;
import com.busilinq.ui.ToDevelopedActivity;
import com.busilinq.ulits.AppUtils;
import com.busilinq.ulits.CatchUtil;
import com.busilinq.widget.IconTextItem;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.Apps;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：设置
 * Create Person：Chenyx
 * Create Time：2018/2/1 上午9:29
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SetActivity extends BaseMvpActivity<SetPresenter> implements ISetView {

    public static final int REQUEST = 1;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 当前版本
     */
    @BindView(R.id.it_app_update)
    IconTextItem mAppUpdate;
    /**
     * 清除缓存
     */
    @BindView(R.id.it_cleanCache)
    IconTextItem mCleanCache;
    /**
     * 退出
     */
    @BindView(R.id.btn_quit)
    Button mQuit;

    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
    }

    @Override
    protected SetPresenter createPresenter() {
        return new SetPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText("设置");
        mAppUpdate.setRightTextView("V" + AppUtils.getVersionName(MApplication.getInstance()));
        mCleanCache.setRightTextView(CatchUtil.getInstance().getGlideCacheSize(this) + "");
        if (UserCache.get() != null) {
            mQuit.setVisibility(View.VISIBLE);
        } else {
            mQuit.setVisibility(View.GONE);
        }
    }

    // 版本更新动态请求权限
    private void initReadPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "版本更新失败，请在 设置-应用管理 中开启此应用的存储！", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            // 联网更新app
            mPresenter.upgrade();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已授权
                mPresenter.upgrade();
            } else {
                // 用户拒绝授权
                ToastUtils.showShort("版本更新失败，请在 设置-应用管理 中开启此应用的存储！");
            }
        }
    }


    @OnClick({R.id.it_app_update, R.id.tv_back, R.id.it_cleanCache, R.id.it_update_pwd, R.id.it_forget_password, R.id.it_about_me, R.id.btn_quit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.it_app_update:
                initReadPermissions();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.it_cleanCache:
                if (CatchUtil.getInstance().clearImageAllCache(MApplication.getAppContext())) {
                    ToastUtils.showShort("清除成功");
                }
                mCleanCache.setRightTextView(CatchUtil.getInstance().getGlideCacheSize(this) + "");
                break;
            case R.id.it_update_pwd:
                if (UserCache.get() != null) {
                    JumpUtil.overlay(mContext, UpdatePwdActivity.class);
                } else {
                    JumpUtil.overlay(mContext, LoginActivity.class);
                }
                break;
            case R.id.it_forget_password:
                Bundle bundle = new Bundle();
                bundle.putString("title", "忘记密码");
                JumpUtil.overlay(mContext, GetCodeActivity.class, bundle);
                break;
            case R.id.it_about_me:
                JumpUtil.overlay(mContext, ToDevelopedActivity.class);
//                JumpUtil.overlay(mContext, AboutMeActivity.class);
                break;
            case R.id.btn_quit:
                AppShowDialog();
                break;
        }
    }


    /**
     * 退出系统
     */
    private Dialog dialog;
    private Button mDialogSubmit, mDialogCancel;

    private void AppShowDialog() {
        dialog = new Dialog(this, R.style.AppShowDialog);
        dialog.setContentView(getDialogView());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        // 增加一些配置
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = Apps.getScreenWidth(this) * 85 / 100; // 宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        lp.alpha = 1.0f; // 透明度

        dialog.show();
    }

    private View getDialogView() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.appint_dialog_sheet, null);
        final EditText content = dialogView.findViewById(R.id.content);
        content.setVisibility(View.GONE);
        TextView title = dialogView.findViewById(R.id.appoint_dialog_center_name);
        title.setText("您确定要退出吗？");
        mDialogSubmit = dialogView.findViewById(R.id.appoint_dialog_submit);
        mDialogCancel = dialogView.findViewById(R.id.appoint_dialog_cancel);
        mDialogSubmit.setText("确定");
        mDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mDialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCache.clear();
                dialog.dismiss();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        return dialogView;
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