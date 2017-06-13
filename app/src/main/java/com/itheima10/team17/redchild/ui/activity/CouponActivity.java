package com.itheima10.team17.redchild.ui.activity;

import android.os.Bundle;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

public class CouponActivity extends BaseActivity {

    private TitleBar mViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        mViewById = (TitleBar) findViewById(R.id.titlebar_coupon);
        mViewById.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }
}
