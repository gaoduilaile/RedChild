package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.view.TitleBar;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SearchLogisticsActivity extends AppCompatActivity {

    @BindView(R.id.titlebar_search_logistics)
    TitleBar mTitlebarSearchLogistics;
    @BindView(R.id.tv_search_logistics)
    TextView mTvSearchLogistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_logistics);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int orderId = intent.getIntExtra("orderId", 0);
        //拿到物流信息//接口有问题


        mTitlebarSearchLogistics.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
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
