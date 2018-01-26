package com.busilinq.ulits;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chenyx.libs.utils.Logs;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by Administrator on 2016/9/28.
 */

public class AppUtils {
    /**
     * 位置权限
     */
    public static final int POSTION_RQESTCODE = 0x14;



    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        SharedPreferences mShare = context.getSharedPreferences("sysCacheMap", Context.MODE_PRIVATE);
        String uuid = "";
        if (mShare != null) {
            uuid = mShare.getString("uuid", "");
        }

        if (StringUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor edit = mShare.edit();
            edit.putString("uuid", uuid);
          edit.commit();
        }

        Logs.d("getUUID : " + uuid);
        return uuid;
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        String version = "1.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        int versionCode = 1;
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


}
