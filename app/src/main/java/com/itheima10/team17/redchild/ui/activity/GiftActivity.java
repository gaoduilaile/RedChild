package com.itheima10.team17.redchild.ui.activity;

import android.os.Bundle;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

public class GiftActivity extends BaseActivity {

    private TitleBar mViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        mViewById = (TitleBar) findViewById(R.id.titlebar_gift);
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
