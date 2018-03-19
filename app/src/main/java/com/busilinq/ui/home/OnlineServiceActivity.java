package com.busilinq.ui.home;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.chenyx.libs.utils.ToastUtils;
import com.longsh.optionframelibrary.OptionCenterDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：在线客服
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class OnlineServiceActivity extends BaseActivity {

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
     * qq
     */
    @BindView(R.id.tv_qq)
    TextView mQq;
    /**
     * 电话
     */
    @BindView(R.id.tv_phone)
    TextView mPhone;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_online_service);
    }

    @Override
    protected void initUI() {
        mBack.setVisibility(View.VISIBLE);
        mTitle.setText("在线客服");
        mQq.setText("1111111");
        mPhone.setText("15111111111");

        /**
         * 长按qq复制剪贴板
         */
        mQq.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final ArrayList<String> list = new ArrayList<>();
                list.add("复制");
                final OptionCenterDialog optionCenterDialog = new OptionCenterDialog();
                optionCenterDialog.show(OnlineServiceActivity.this, list);
                optionCenterDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cmb.setText(mQq.getText());
                        cmb.getText();
                        ToastUtils.showShort("已复制到剪贴板");
                        optionCenterDialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.tv_back, R.id.tv_phone, R.id.tv_qq})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mPhone.getText().toString().trim());
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_qq:
                if (isQQClientAvailable(this)) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + mQq.getText().toString().trim() + "&version=1")));
                } else {
                    ToastUtils.showShort("本机未安装QQ应用");
                }
                break;
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }



}