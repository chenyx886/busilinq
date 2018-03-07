package com.busilinq.ui.home;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.home.IInfoNoticeView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.InfoNoticeEntity;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.presenter.home.InfoNoticePresenter;
import com.busilinq.ui.home.adapter.InfoNoticeAdapter;
import com.chenyx.libs.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.longsh.optionframelibrary.OptionCenterDialog;

import java.util.ArrayList;

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
                if (checkApkExist(this, "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + mQq.getText().toString().trim() + "&version=1")));
                } else {
                    ToastUtils.showShort("本机未安装QQ应用");
                }
                break;
        }
    }

    /**
     * 检查手机是否安装qq
     * @param context
     * @param packageName
     * @return
     */
    public boolean checkApkExist(Context context, String packageName) {

        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}