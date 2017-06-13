package com.itheima10.team17.redchild.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.MoreCommonItem;
import com.itheima10.team17.redchild.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpCenterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.titlebar_help_center)
    TitleBar mTitlebarHelpCenter;
    @BindView(R.id.mci_help_center_guide)
    MoreCommonItem mMciHelpCenterGuide;
    @BindView(R.id.mci_help_center_sercice)
    MoreCommonItem mMciHelpCenterSercice;
    @BindView(R.id.mci_help_center_sendWay)
    MoreCommonItem mMciHelpCenterSendWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        mMciHelpCenterGuide.setOnClickListener(this);
        mMciHelpCenterSercice.setOnClickListener(this);
        mMciHelpCenterSendWay.setOnClickListener(this);

        mTitlebarHelpCenter.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mci_help_center_guide:
                ToastUtil.show(this, "什么不会我教你", ToastUtil.LENGTH_SHORT);
                break;
            case R.id.mci_help_center_sercice:
                ToastUtil.show(this, "买了就不能退了", ToastUtil.LENGTH_SHORT);
                break;
            case R.id.mci_help_center_sendWay:
                ToastUtil.show(this, "怎么送是我的事", ToastUtil.LENGTH_SHORT);
                break;
            default:
                break;
        }

    }
}
