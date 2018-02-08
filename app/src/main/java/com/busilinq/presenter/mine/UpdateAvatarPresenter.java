package com.busilinq.presenter.mine;

import android.graphics.Bitmap;
import android.os.Environment;

import com.busilinq.contract.mine.IUpdateAvatarView;
import com.busilinq.data.Constant;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UploadEntity;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.BasePresenter;
import com.busilinq.ulits.FilesUploadUtils;
import com.chenyx.libs.bitmap.BitmapRevition;
import com.chenyx.libs.utils.Files;
import com.chenyx.libs.utils.Logs;
import com.chenyx.libs.utils.SysConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Company：华科建邺
 * Class Describe： 修改用户头像 界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UpdateAvatarPresenter extends BasePresenter<IUpdateAvatarView> {

    private static final String TAG = "UpdateAvatarPresenter";


    public UpdateAvatarPresenter(IUpdateAvatarView MvpView) {
        super(MvpView);
    }

    /**
     * 获取用户资料
     *
     * @param userId
     * @param name
     */
    public void getUserInfo(String userId, String name) {
        addSubscription(RetrofitApiFactory.getMineApi().getInfo(userId, name), new SubscriberCallBack<com.busilinq.data.entity.UserEntity>() {
            @Override
            protected void onSuccess(com.busilinq.data.entity.UserEntity data) {
                MvpView.showUserInfo(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 修改用户头像
     *
     * @param head
     */
    public void modifyHead(String head) {
        param.put("head", head);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("修改中...");
        addSubscription(RetrofitApiFactory.getMineApi().modifyHead(body), new SubscriberCallBack<UserEntity>() {
            @Override
            protected void onSuccess(UserEntity user) {
                MvpView.Success("modifyHead", new ArrayList<String>(), new ArrayList<UploadEntity>());
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }


    public void compressImgs(final List<String> filePaths) {

        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                Logs.d(TAG, "call thread name:" + Thread.currentThread().getName());
                List<String> compressFilePaths = new ArrayList<>();
                for (String sourceFilePath : filePaths) {
                    if (new File(sourceFilePath).exists()) {
                        String comFileName = Files.getRandomFileName("jpg");
                        File comFile = Files.makeFile(Environment.getExternalStorageDirectory().getPath() + Constant.COMPRESS_IMAGE_CACHE_DIR + File.separator + comFileName);
                        Bitmap bit = BitmapRevition.getBitmapFromFile(sourceFilePath);
                        BitmapRevition.compressBitmap(bit, comFile);
                        compressFilePaths.add(comFile.getAbsolutePath());
                        FileInputStream fileInputStream = null;
                        try {
                            fileInputStream = new FileInputStream(new File(sourceFilePath));
                            Logs.d(TAG, "origpath:" + sourceFilePath);
                            Logs.d(TAG, "origpath size :" + fileInputStream.available() / 1024 + "kb");
                            Logs.d(TAG, "compressPath:" + comFile.getAbsolutePath());
                            Logs.d(TAG, "compressPath size:" + new FileInputStream(comFile).available() / 1024 + "kb");
                        } catch (Exception e) {

                        } finally {
                            if (null != fileInputStream) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {

                                }
                            }
                        }
                    }
                }
                subscriber.onNext(compressFilePaths);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        if (MvpView != null)
                            MvpView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onNext(filePaths);
                        if (MvpView != null)
                            MvpView.hideProgress();
                    }

                    @Override
                    public void onNext(List<String> filsPaths) {
                        MvpView.Success("compressImgs", filsPaths, new ArrayList<UploadEntity>());
                        Logs.d(TAG, "onNext thread name:" + Thread.currentThread().getName());
                    }
                });
    }


    /**
     * 上传文件
     *
     * @param filePaths
     * @param fileTypes
     */
    public void upload(List<String> filePaths, List<String> fileTypes) {

        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }

        List<MultipartBody.Part> multiPartFiles = FilesUploadUtils.multiPartFiles(files, fileTypes);
        MvpView.showProgress("上传中....");
        addSubscription(RetrofitApiFactory.getMineApi().upload(SysConfig.nullToInt(UserCache.GetUserId()), multiPartFiles), new SubscriberCallBack<List<UploadEntity>>() {
            @Override
            protected void onSuccess(List<UploadEntity> list) {
                MvpView.Success("upload", new ArrayList<String>(), list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
