package com.busilinq.ui.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.IUpdateAvatarView;
import com.busilinq.data.Constant;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UploadEntity;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.mine.UpdateAvatarPresenter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.Files;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
public class UpdateAvatarActivity extends BaseMvpActivity<UpdateAvatarPresenter> implements IUpdateAvatarView {

    public static final int REQUEST = 2;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 头像
     */
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    /**
     * 剪裁路径
     */
    private String cropPath;
    /**
     * 相机
     */
    private static final int REQUEST_CAMERA_CODE = 11;
    /**
     * 图片裁剪
     */
    private static final int REQUST_CODE_CROP = 33;

    @Override
    protected UpdateAvatarPresenter createPresenter() {
        return new UpdateAvatarPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_update_avatar);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.set_user_avatar);
        mPresenter.getUserInfo(UserCache.GetUserId(), "");
    }

    @Override
    public void showUserInfo(UserEntity user) {
        GlideShowImageUtils.displayCircleNetImage(mContext, UserCache.get().getHeadimgurl(), mIvAvatar, R.mipmap.ic_user);
    }

    @OnClick({R.id.tv_back, R.id.btn_album_selection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_album_selection:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.CAMERA_RQESTCODE);
                } else {
                    selectImgs();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == this.RESULT_OK) {
            //图片选择返回
            if (requestCode == REQUEST_CAMERA_CODE) {
                String filePath = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0);
                cropImage(filePath);
            }

            //图片剪裁返回
            else if (requestCode == REQUST_CODE_CROP) {
                List<String> filePaths = new ArrayList<>();
                filePaths.add(cropPath);
                mPresenter.compressImgs(filePaths);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.CAMERA_RQESTCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImgs();
            } else {
                Toasts.showShort(MApplication.getAppContext(), "请为百商银通开启相册权限");
            }
        }
    }


    /**
     * 图片选择
     */
    private void selectImgs() {

        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCarema(true); // 是否显示拍照， 默认false
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    /**
     * 图片剪裁
     *
     * @param filePath
     */
    private void cropImage(String filePath) {

        Uri imageUri;
        Uri outputUri;
        String newFileName = Files.getRandomFileName("jpg");
        File file = Files.makeFile(Constant.SDCardRoot + Constant.COMPRESS_IMAGE_CACHE_DIR + File.separator + newFileName);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageUri = FileProvider.getUriForFile(this, "com.busilinq.fprovider", new File(filePath));
            outputUri = Uri.fromFile(file);
        } else {
            imageUri = Uri.fromFile(new File(filePath));
            outputUri = Uri.fromFile(file);
        }
        cropPath = file.getAbsolutePath();
        buildIntent(intent, imageUri, outputUri, 1, 1, 600, 600);
        startActivityForResult(intent, REQUST_CODE_CROP);
    }

    /**
     * 配置剪裁参数
     *
     * @param intent
     * @param resourceUri
     * @param dataUri
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @return
     */
    private Intent buildIntent(Intent intent, Uri resourceUri, Uri dataUri, int aspectX, int aspectY, int outputX, int outputY) {

        intent.setDataAndType(resourceUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, dataUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    @Override
    public void Success(String type, List<String> filePaths, List<UploadEntity> list) {
        if (type.equals("compressImgs")) {
            List<String> typeStrs = new ArrayList<>();
            typeStrs.add("img/jpg");
            mPresenter.upload(filePaths, typeStrs);
        } else if (type.equals("upload") && list.size() > 0) {
            mPresenter.modifyHead(list.get(0).getUrl());
        } else if (type.equals("modifyHead")) {
            ToastUtils.showShort("头像更新成功");
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

}
