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
 * Created by Destiny on 2016/6/14.
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private boolean mLeftIsBack;
    private boolean mTitleIsPicture;
    private String mLeftTitle;
    private String mRightTitle;
    private String mMainTitle;
    private Drawable mTitleDrawable;
    private ImageView mMainTitleDrawable;
    private TextView mBtnTitleLeft;
    private TextView mBtnTitleRight;
    private boolean mLeftIsVisible;
    private boolean mRightIsVisible;
    private TextView mTvTitle;
    private View mView;
    private OnBtnClickListener mOnBtnClickListener;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mLeftIsBack = a.getBoolean(R.styleable.TitleBar_leftIsBack, false);
        mTitleIsPicture = a.getBoolean(R.styleable.TitleBar_titleIsPicture, false);
        mLeftIsVisible = a.getBoolean(R.styleable.TitleBar_leftIsVisible, true);
        mRightIsVisible = a.getBoolean(R.styleable.TitleBar_rightIsVIsible, true);
        mLeftTitle = a.getString(R.styleable.TitleBar_leftTitle);
        mRightTitle = a.getString(R.styleable.TitleBar_rightTitle);
        mMainTitle = a.getString(R.styleable.TitleBar_mainTitle);
        mTitleDrawable = a.getDrawable(R.styleable.TitleBar_titleDrawable);
        a.recycle();
        mView = View.inflate(context, R.layout.view_titlebar, null);
        initFind();
        addView(mView);


        if (mTitleIsPicture) {
            if (mTitleDrawable == null) {
                throw new RuntimeException("titleDrawable must have value in XML");
            } else {
                mTvTitle.setVisibility(GONE);
                mMainTitleDrawable.setImageDrawable(mTitleDrawable);
            }
        } else {
            mMainTitleDrawable.setVisibility(GONE);
            mTvTitle.setText(mMainTitle);
        }

        if (mLeftIsVisible) {
            if (mLeftIsBack) {
                mBtnTitleLeft.setBackgroundResource(R.drawable.bar_head_back_bg);
            }
            mBtnTitleLeft.setText(mLeftTitle);
            mBtnTitleLeft.setOnClickListener(this);
        } else {
            mBtnTitleLeft.setVisibility(INVISIBLE);
        }

        if (mRightIsVisible) {
            mBtnTitleRight.setText(mRightTitle);
            mBtnTitleRight.setOnClickListener(this);
        } else {
            mBtnTitleRight.setVisibility(INVISIBLE);
        }
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    private void initFind() {

        mMainTitleDrawable = (ImageView) mView.findViewById(R.id.iv_main_tltle);
        mTvTitle = (TextView) mView.findViewById(R.id.tv_main_tltle);
        mBtnTitleLeft = (TextView) mView.findViewById(R.id.btn_title_left);
        mBtnTitleRight = (TextView) mView.findViewById(R.id.btn_title_right);
    }

//    public void leftClick(View view) {
//        if (mOnBtnClickListener != null) {
//            mOnBtnClickListener.onLeftClick();
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_left:
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onLeftClick();
                }
                break;
            case R.id.btn_title_right:
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onRightClick();
                }
                break;
        }
    }


    public void setTitle(String title) {
        if (title != null) {
            mTvTitle.setText(title);
        }
    }


    public interface OnBtnClickListener {
        public void onLeftClick();


        public void onRightClick();

    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.mOnBtnClickListener = onBtnClickListener;
    }
}
