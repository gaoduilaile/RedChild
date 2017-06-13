package com.itheima10.team17.redchild.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.itheima10.team17.redchild.R;

import org.askerov.dynamicgrid.DynamicGridView;

/**
 * Created by tao on 2016/6/19.
 */
public class CuttingLineGridView extends DynamicGridView {

    public CuttingLineGridView(Context context) {
        super(context);
    }

    public CuttingLineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CuttingLineGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View childView = getChildAt(0);
        int column = getWidth() / childView.getWidth();
        int childCount = getChildCount();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getContext().getResources().getColor(R.color.line));
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (i < 3) {        //第一行   画上边的线
                canvas.drawLine(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getTop(), paint);
            }
            if (i / column == 0) {      //第一列   画左边的线
                canvas.drawLine(childAt.getLeft(), childAt.getTop(), childAt.getLeft(), childAt.getBottom(), paint);
            }
            //右边
            canvas.drawLine(childAt.getRight(), childAt.getTop(), childAt.getRight(), childAt.getBottom(), paint);
            //下边
            canvas.drawLine(childAt.getRight(), childAt.getBottom(), childAt.getLeft(), childAt.getBottom(), paint);
        }
    }
}
