package com.itheima10.team17.redchild.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.adapter.CartAdapter;

/**
 * Created by Destiny on 2016/6/15.
 */
public class ItemCountView extends LinearLayout implements View.OnClickListener {

    private View mView;
    private ImageView mSub;
    private TextView mCount;
    private ImageView mPlus;
    private int mNumber = 1;
    private OnNumberChangedListener mOnNumberChangedListener;
    private CartAdapter mAdapter;

    public ItemCountView(Context context) {
        super(context);
    }

    public ItemCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.view_itemcountview, this, true);
        initFind();
    }

    public ItemCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private void initFind() {
        mSub = (ImageView) findViewById(R.id.iv_itemcount_sub);
        mCount = (TextView) findViewById(R.id.iv_itemcount_count);
        mPlus = (ImageView) findViewById(R.id.iv_itemcount_plus);

        mSub.setOnClickListener(this);
        mCount.setOnClickListener(this);
        mPlus.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_itemcount_sub:
                if (mNumber >= 2) {
                    if (mOnNumberChangedListener != null) {
                        if (mOnNumberChangedListener.onNumberChanged(-1)) {
                            mNumber--;
                            if (mAdapter != null) {
                                mAdapter.notifyDataSetChanged();
                            }
                            notifyNumber();
                            mCount.setText(Integer.toString(mNumber));
                        }
                    }
                }
                break;
            case R.id.iv_itemcount_count:
                //TODO 添加弹出窗口
                break;
            case R.id.iv_itemcount_plus:
                if (mOnNumberChangedListener != null) {
                    if (mOnNumberChangedListener.onNumberChanged(1)) {
                        mNumber++;
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                        notifyNumber();
                        mCount.setText(Integer.toString(mNumber));
                    }
                }
                break;

        }
    }

    private void notifyNumber() {
        if (mNumber < 2) {
            mSub.setImageResource(R.mipmap.sub_disable);
        } else {
            mSub.setImageResource(R.mipmap.sub_enable);
        }
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
        notifyNumber();
        mCount.setText(Integer.toString(mNumber));
    }

    public void setOnNumberChangedListener(OnNumberChangedListener numberChangedListener) {
        mOnNumberChangedListener = numberChangedListener;
    }

    public void setAdapter(CartAdapter adapter) {
        mAdapter = adapter;
    }


    public interface OnNumberChangedListener {
        public boolean onNumberChanged(int mCount);
    }

}
