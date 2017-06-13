package com.itheima10.team17.redchild.view.sweetView;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * Created by Destiny on 2016/6/1.
 */
public class SweetSheetWithBtn extends SweetSheet {
    private OnStatusChangedListener mListener;

    public SweetSheetWithBtn(RelativeLayout parentVG) {
        super(parentVG);
    }

    public SweetSheetWithBtn(FrameLayout parentVG) {
        super(parentVG);
    }

    public SweetSheetWithBtn(ViewGroup parentVG) {
        super(parentVG);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onStatusChanged();
        }
    }

    @Override
    public void show() {
        super.show();
        if (mListener != null) {
            mListener.onStatusChanged();
        }
    }

    @Override
    public void toggle() {
        super.toggle();
        if (mListener != null) {
            mListener.onStatusChanged();
        }
    }

    public void setOnStatusChangedListener(OnStatusChangedListener mListener) {
        this.mListener = mListener;
    }

    public interface OnStatusChangedListener {
        abstract void onStatusChanged();
    }
}
