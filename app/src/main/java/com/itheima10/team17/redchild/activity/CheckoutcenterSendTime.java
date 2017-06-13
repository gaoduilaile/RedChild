package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.view.TitleBar;

public class CheckoutcenterSendTime extends AppCompatActivity implements View.OnClickListener, TitleBar.OnBtnClickListener {

    public static final String ALL_TIME       = "任何时间";
    public static final String EXCEPT_WORKDAY = "仅限工作日";
    public static final String ONLY_WORKDAY     = "仅限休息日";
    private String mCurrSendtimeDesc;//用来记录点击条目内容的常量
    public static final String SENDTIME  = "SENDTIME";
    private TitleBar     mMainTitlebar;
    private LinearLayout mLlSendtimeItem1;
    private TextView     mTvSendtimeDesc1;
    private ImageView    mIvSendtimeArrow1;
    private LinearLayout mLlSendtimeItem2;
    private TextView     mTvSendtimeDesc2;
    private ImageView    mIvSendtimeArrow2;
    private LinearLayout mLlSendtimeItem3;
    private TextView     mTvSendtimeDesc3;
    private ImageView    mIvSendtimeArrow3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutcenter_sendtime);
        initView();

        getFromCheckoutHomeData();
    }

    //从CheckoutHome返回的数据
    private void getFromCheckoutHomeData() {

        Intent intent = getIntent();
        mCurrSendtimeDesc = intent.getStringExtra(SENDTIME);

        if (mCurrSendtimeDesc != null){
            switch (mCurrSendtimeDesc){
                case ALL_TIME:
                    mIvSendtimeArrow1.setVisibility(View.VISIBLE);
                    break;
                case EXCEPT_WORKDAY:
                    mIvSendtimeArrow2.setVisibility(View.VISIBLE);
                    break;
                case ONLY_WORKDAY:
                    mIvSendtimeArrow3.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void initView() {
        mMainTitlebar = (TitleBar) findViewById(R.id.main_titlebar);
        mLlSendtimeItem1 = (LinearLayout) findViewById(R.id.ll_sendtime_item1);
        mTvSendtimeDesc1 = (TextView) findViewById(R.id.tv_sendtime_desc1);
        mIvSendtimeArrow1 = (ImageView) findViewById(R.id.iv_sendtime_arrow1);
        mLlSendtimeItem2 = (LinearLayout) findViewById(R.id.ll_sendtime_item2);
        mTvSendtimeDesc2 = (TextView) findViewById(R.id.tv_sendtime_desc2);
        mIvSendtimeArrow2 = (ImageView) findViewById(R.id.iv_sendtime_arrow2);
        mLlSendtimeItem3 = (LinearLayout) findViewById(R.id.ll_sendtime_item3);
        mTvSendtimeDesc3 = (TextView) findViewById(R.id.tv_sendtime_desc3);
        mIvSendtimeArrow3 = (ImageView) findViewById(R.id.iv_sendtime_arrow3);

        mMainTitlebar.setOnBtnClickListener(this);
        mLlSendtimeItem1.setOnClickListener(this);
        mLlSendtimeItem2.setOnClickListener(this);
        mLlSendtimeItem3.setOnClickListener(this);


    }


    @Override
    public void onLeftClick() {
        Intent intent = new Intent();
        intent.putExtra(SENDTIME, mCurrSendtimeDesc);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRightClick() {
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_sendtime_item1:
                //工作日、双休日、节假日均可送货
                mCurrSendtimeDesc = ALL_TIME;
                mIvSendtimeArrow1.setVisibility(View.VISIBLE);
                mIvSendtimeArrow2.setVisibility(View.INVISIBLE);
                mIvSendtimeArrow3.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_sendtime_item2:
                //只双休日、节假日送货
//                mTvSendtimeDesc2.getText().toString()
                mCurrSendtimeDesc = EXCEPT_WORKDAY;
                mIvSendtimeArrow1.setVisibility(View.INVISIBLE);
                mIvSendtimeArrow2.setVisibility(View.VISIBLE);
                mIvSendtimeArrow3.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_sendtime_item3:
                //只工作日送货
                mCurrSendtimeDesc = ONLY_WORKDAY;
                mIvSendtimeArrow1.setVisibility(View.INVISIBLE);
                mIvSendtimeArrow2.setVisibility(View.INVISIBLE);
                mIvSendtimeArrow3.setVisibility(View.VISIBLE);
                break;
        }
    }
}
