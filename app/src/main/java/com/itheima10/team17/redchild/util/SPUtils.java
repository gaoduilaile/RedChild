package com.itheima10.team17.redchild.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * Created by tao on 2016/6/15.
 */
public class SPUtils {

    private static final String FILE_NAME = "config";

    public static String getString(Context context, String key,String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(Context context,String key,int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static Long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }
}
