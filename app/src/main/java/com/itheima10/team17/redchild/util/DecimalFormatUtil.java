package com.itheima10.team17.redchild.util;

import android.graphics.Paint;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 格式化金额文字
 * Created by Destiny on 2016/6/15.
 */
public class DecimalFormatUtil {

    private static String sFormat = "¥###,###,##0.00";

    public static String getDecimal(String money) {
        DecimalFormat myformat = new DecimalFormat();
        Number parse = null;
        try {
            parse = myformat.parse(money);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myformat.applyPattern(sFormat);
        return  myformat.format(parse);
    }

    public static String getDecimal(int money) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(sFormat);
        return  myformat.format(money);
    }

    public static String getDecimal(float money) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(sFormat);
        return  myformat.format(money);
    }


    //为文字增加删除线
    public static void setDashLine(TextView view) {
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

}
