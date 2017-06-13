package com.itheima10.team17.redchild.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.view.TitleBar;

public class CheckoutCoupon extends AppCompatActivity implements TitleBar.OnBtnClickListener, View.OnClickListener {

    private TitleBar     mMainTitlebar;
    private ImageView    mIvCouponEmpty;
    private LinearLayout mLlCouponItem1;
    private TextView     mTvCouponDesc1;
    private ImageView    mIvCouponOkarrow1;
    private LinearLayout mLlCouponItem2;
    private TextView     mTvCouponDesc2;
    private ImageView    mIvCouponOkarrow2;
    private LinearLayout mLlCouponItem3;
    private TextView     mTvCouponDesc3;
    private ImageView    mIvCouponOkarrow3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_coupon);

        initView();
    }

    private void initView() {
        mIvCouponEmpty = (ImageView) findViewById(R.id.iv_coupon_empty);
        mLlCouponItem1 = (LinearLayout) findViewById(R.id.ll_coupon_item1);
        mTvCouponDesc1 = (TextView) findViewById(R.id.tv_coupon_desc1);
        mIvCouponOkarrow1 = (ImageView) findViewById(R.id.iv_coupon_okarrow1);
        mLlCouponItem3 = (LinearLayout) findViewById(R.id.ll_coupon_item3);
        mTvCouponDesc3 = (TextView) findViewById(R.id.tv_coupon_desc3);
        mIvCouponOkarrow3 = (ImageView) findViewById(R.id.iv_coupon_okarrow3);
        mMainTitlebar = (TitleBar) findViewById(R.id.main_titlebar);


        mMainTitlebar.setOnBtnClickListener(this);
        mLlCouponItem1.setOnClickListener(this);
        mLlCouponItem3.setOnClickListener(this);
    }
    //左侧点击事件
    @Override
    public void onLeftClick() {
        finish();
    }
    //右侧点击事件
    @Override
    public void onRightClick() {

    }
    //条目点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_coupon_item1:
                //新用户10元代金券

            case R.id.ll_coupon_item3:
                //圣诞节大放送80元礼券


                break;
        }

    }
}
