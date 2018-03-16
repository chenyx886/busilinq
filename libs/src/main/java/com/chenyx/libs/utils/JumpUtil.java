package com.chenyx.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 类描述：Activity 界面跳转
 * 创建人：陈永祥
 * 创建时间：2016/10/10 16:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JumpUtil {


    /**
     * 不带参数的跳转
     *
     * @param context
     * @param targetClazz
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz) {
        Intent mIntent = new Intent(context, targetClazz);
        if (context.getPackageManager().resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startActivity(mIntent);
        } else {
            ToastUtils.showShort("找不到指定的界面");
        }
    }

    /**
     * 带参数的跳转
     *
     * @param context
     * @param targetClazz
     * @param bundle
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz, Bundle bundle) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        if (context.getPackageManager().resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startActivity(mIntent);
        } else {
            ToastUtils.showShort("找不到指定的界面");
        }
    }

    /**
     * @param context
     * @param targetClazz
     * @param bundle
     * @param flags
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz, Bundle bundle, Integer flags) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        if (flags != null) {
            mIntent.setFlags(flags);
        }
        if (context.getPackageManager().resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startActivity(mIntent);
        } else {
            ToastUtils.showShort("找不到指定的界面");
        }
    }


    /**
     * @param context
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    public static void startForResult(Activity context, Class<? extends Activity> targetClazz, int requestCode, Bundle bundle) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        if (context.getPackageManager().resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startActivityForResult(mIntent, requestCode);
        } else {
            ToastUtils.showShort("找不到指定的界面");
        }
    }

    /**
     * @param fragment
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    public static void startForResult(Fragment fragment, Class<? extends Activity> targetClazz, int requestCode, Bundle bundle) {
        Intent mIntent = new Intent(fragment.getActivity(), targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        if (fragment.getActivity().getPackageManager().resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            fragment.startActivityForResult(mIntent, requestCode);
        } else {
            ToastUtils.showShort("找不到指定的界面");
        }
    }
}
