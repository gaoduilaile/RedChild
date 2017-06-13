package com.itheima10.team17.redchild.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Destiny on 2016/5/27.
 */
public class AndroidMPermissionUtil {

    private PermissionResultListener mPermissionResultListener;

    public boolean requestPermission(final Activity activity, String tips, final String... permission) {

        ArrayList<String> permissions = new ArrayList<>();
        for (String s : permission) {
            if (ContextCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(s);
                Log.d("AndroidMPermissionUtil", "需要权限" + s);
            }
        }

        if (permissions.size() == 0) {
            return false;
        }

        String[] requiredPermission = new String[permissions.size()];
        Iterator<String> iterator = permissions.iterator();
        int index = 0;
        while(iterator.hasNext()) {
            requiredPermission[index] = iterator.next();
            index++;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requiredPermission[0])) {
            request(activity, requiredPermission);
        } else {
            request(activity, requiredPermission);
        }
        return true;
    }


    public  void request(Activity activity, String... permission) {
        ActivityCompat.requestPermissions(activity, permission, 0);
    }

    public interface PermissionResultListener {
        abstract public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults);
    }

}
