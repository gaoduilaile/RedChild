package com.itheima10.team17.redchild.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.itheima10.team17.redchild.constant.Constant;

/**
 * Created by tao on 2016/6/18.
 */
public class PackageUtils {
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();

        try {
            PackageInfo info = packageManager.getPackageInfo(Constant.PACKAGE_NAME, 0);
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //默认返回未知版本名
        return "未知版本名";
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();

        try {
            PackageInfo info = packageManager.getPackageInfo(Constant.PACKAGE_NAME, 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //默认返回1
        return 1;
    }
}
