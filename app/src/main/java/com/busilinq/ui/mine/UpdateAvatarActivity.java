package com.busilinq.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.chenyx.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：设置个人头像
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午5:21
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UpdateAvatarActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_update_avatar);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.set_user_avatar);

    }


    @OnClick({R.id.tv_back, R.id.btn_album_selection, R.id.btn_take_picture})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_album_selection:
                ToastUtils.showShort("调用相册");
                break;
            case R.id.btn_take_picture:
                ToastUtils.showShort("调用相机");
                break;
        }
    }
}
