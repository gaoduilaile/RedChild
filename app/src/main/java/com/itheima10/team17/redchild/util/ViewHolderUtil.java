package com.itheima10.team17.redchild.util;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Destiny on 2016/5/24.
 */
public class ViewHolderUtil {
    public static <T extends View> T getView(View view, int resId) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(resId);
        if (childView == null) {
            childView = view.findViewById(resId);
        }
        return (T) childView;
    }
}
