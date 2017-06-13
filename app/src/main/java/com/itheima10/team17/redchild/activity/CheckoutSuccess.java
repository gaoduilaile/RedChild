package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.activity.MyOrderAcrivity;
import com.itheima10.team17.redchild.ui.fragment.FirstFragment;
import com.itheima10.team17.redchild.view.TitleBar;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class CheckoutSuccess extends AppCompatActivity {

    @BindView(R.id.main_titlebar)
    TitleBar mMainTitlebar;
    @BindView(R.id.btn_goon_shopping)
    Button   mBtnGoonShopping;
    @BindView(R.id.btn_check_order)
    Button   mBtnCheckOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_success);
        ButterKnife.bind(this);
        mMainTitlebar.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                Intent intent = new Intent(CheckoutSuccess.this,CheckoutCenterHomeActivity.class);
                startActivity(intent);
                //finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }


    @OnClick({R.id.btn_goon_shopping, R.id.btn_check_order})
    public void onClick(View view) {
        switch (view.getId()) {
            //继续购物
            case R.id.btn_goon_shopping:
                Intent intent1 = new Intent(CheckoutSuccess.this, FirstFragment.class);
                startActivity(intent1);
                break;
            //查看订单
            case R.id.btn_check_order:
                Intent intent = new Intent(CheckoutSuccess.this,MyOrderAcrivity.class);
                startActivity(intent);
                break;
        }
    }
}
