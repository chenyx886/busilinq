package com.busilinq.ulits;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.busilinq.MApplication;
import com.busilinq.data.api.DownloadFileModel;
import com.busilinq.data.entity.TUpgradeEntity;
import com.chenyx.libs.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Company：华科建邺
 * Class Describe：版本更新辅助类
 * Create Person：yuzhenxing
 * Create Time：2018/2/2 11:02
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class DownApplUtil {
    private Activity mContext;
    private ProgressDialog progressDialog;
    private TUpgradeEntity mTUpgradeEntity;

    public DownApplUtil(Activity context) {
        mContext = context;
    }

    public void getVersionNo(TUpgradeEntity updateInfo) {
        mTUpgradeEntity = updateInfo;
        if (updateInfo != null)
            showUpdateDialog(updateInfo);
        else {
            ToastUtils.showShort("已是最新版本！");
        }
    }

    private void showUpdateDialog(final TUpgradeEntity updateInfo) {
        (mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("请升级APP至版本" + updateInfo.getVersionName());
                if (updateInfo.getRemark() != null)
                    builder.setMessage(updateInfo.getRemark());
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {

                            downFile(updateInfo.getUrl());
                        } else {
                            ToastUtils.showShort("SD卡不可用，请插入SD卡");
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        if (updateInfo.getUpdateType() == 2) {
//                            MApplication.getInstance().appManager.finishAllActivity();
//                            System.exit(0);
//                        }
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

    }

    private void downFail() {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                ToastUtils.showShort("更新失败,请开启手机存储权限");
            }
        });
    }

    private void setMax(final long total) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMax((int) total);
            }
        });
    }

    /**
     * 进度条
     *
     * @param i
     */
    private void downLoading(final int i) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setProgress(i);
            }
        });
    }

    // 下载最新app
    private void downFile(final String url) {

        progressDialog = new ProgressDialog(mContext);    //进度条，在下载的时候实时更新进度，提高用户友好度
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍候...");
        progressDialog.setProgressNumberFormat("%1d kb/%2d kb");
        progressDialog.setProgress(0);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                DownloadFileModel model = DownloadFileModel.getInstance();
                model.get(url, new okhttp3.Callback() {

                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        downFail();
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        InputStream is = null;//输入流
                        FileOutputStream fos = null;//输出流
                        try {
                            is = response.body().byteStream();//获取输入流
                            long total = response.body().contentLength();//获取文件大小
                            setMax(total);//为progressDialog设置大小
                            if (is != null) {
                                File file = new File(Environment.getExternalStorageDirectory(), "BaiSAndroidApp.apk");// 设置路径
                                fos = new FileOutputStream(file);
                                byte[] buf = new byte[1024];
                                int ch = -1;
                                int process = 0;
                                while ((ch = is.read(buf)) != -1) {
                                    fos.write(buf, 0, ch);
                                    process += ch;
                                    downLoading(process);//这里就是关键的实时更新进度了！
                                }

                            }
                            fos.flush();
                            // 下载完成
                            if (fos != null) {
                                fos.close();
                            }

                            downSuccess();

                        } catch (Exception e) {
                            downFail();
                            Log.d("SettingPresenter", e.toString());
                        } finally {
                            try {
                                if (is != null)
                                    is.close();
                            } catch (IOException e) {
                            }
                            try {
                                if (fos != null)
                                    fos.close();
                            } catch (IOException e) {
                            }
                        }
                    }

                });

            }
        }).start();

    }

    /**
     * 下载成功
     */
    private void downSuccess() {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("下载完成");
                builder.setMessage("是否安装");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //aandroid N的权限问题

                            Intent install = new Intent(Intent.ACTION_VIEW);
                            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(mContext, "com.omyni.mdmanager.fileprovider", new File(Environment.getExternalStorageDirectory(), "BaiSAndroidApp.apk"));
                            install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                            mContext.startActivity(install);
                            android.os.Process.killProcess(android.os.Process.myPid());//解决安装成功后不能自动打开应用的问题，需要重新发布一个版本测试（小米7.0）
                        } else {
                            Intent install = new Intent(Intent.ACTION_VIEW);
                            install.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "BaiSAndroidApp.apk")), "application/vnd.android.package-archive");
                            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(install);
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mTUpgradeEntity.getUpdateType() == 2) {
                            MApplication.getInstance().appManager.finishAllActivity();
                            System.exit(0);
                        }

                    }
                });
                builder.create().show();

            }
        });
    }
}
