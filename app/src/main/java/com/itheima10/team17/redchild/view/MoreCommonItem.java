package com.itheima10.team17.redchild.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;

/**
 * Created by tao on 2016/6/15.
 */
public class MoreCommonItem extends RelativeLayout{

    public MoreCommonItem(Context context) {
        super(context);
    }

    public MoreCommonItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性数组//参数1是系统属性,参数2是自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonItemMore);
        int bgType = a.getInt(R.styleable.CommonItemMore_bgType, -1);
        String itemName = a.getString(R.styleable.CommonItemMore_itemName);
        Drawable drawableRight = a.getDrawable(R.styleable.CommonItemMore_rightDrawable);
        a.recycle();
        if (bgType == -1) {
            throw new RuntimeException("请设置背景");
        }
        View view = View.inflate(context, R.layout.view_common_item_more, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_more_common_item_name);
        tv.setText(itemName);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_more_common_item_icon);
        iv.setImageDrawable(drawableRight);
        switch (bgType) {
            case 1:
                view.setBackgroundResource(R.drawable.selector_common_item_more_bg_first);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.selector_common_item_more_bg_middle);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.selector_common_item_more_bg_last);
                break;
            default:
                break;
        }
        //自定义控件加入View对象
        addView(view);
    }

    public MoreCommonItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
