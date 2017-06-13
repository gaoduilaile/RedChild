package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.util.SPUtils;
import com.itheima10.team17.redchild.view.TitleBar;

public class CheckoutcenterPayMode extends AppCompatActivity implements View.OnClickListener, TitleBar.OnBtnClickListener {
    public static final String PAYMODE_CASH     = "PAYMODE_CASH";//点击条目时，sp存储现金状态的键值
    public static final String PAY_MODE_ALI_PAY = "支付宝";
    public static final String PAY_MODE_POS     = "到付pos";
    public static final String PAY_MODE_CASH    = "到付现金";
    private LinearLayout mLlPaymode_Cash;
    private ImageView    mIvCash_Okarrow;
    private LinearLayout mLlPaymode_Arrive;
    private ImageView    mIvArrive_Okarrow;
    private LinearLayout mLlPaymode_Alipay;
    private ImageView    mIvAlipay_Okarrow;
    private TitleBar     mTitlebar;
    private TextView     mTvpaymode_Cash;
    private TextView     mTvpaymode_Pos;
    private TextView     mTvpaymode_Alipay;
    private String       mPayMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutcenter_pay_mode);

        initView();
        getCheckoutHomeData();//从CheckoutHome返回数据，进行回显

    }

    private void getCheckoutHomeData() {
        Intent intent = getIntent();
        mPayMode = intent.getStringExtra(CheckoutCenterHomeActivity.PAYMODEDATA);
        if (mPayMode != null) {
            switch (mPayMode) {
                case PAY_MODE_CASH:
                    mIvCash_Okarrow.setVisibility(View.VISIBLE);
                    break;
                case PAY_MODE_POS:
                    mIvArrive_Okarrow.setVisibility(View.VISIBLE);
                    break;
                case PAY_MODE_ALI_PAY:
                    mIvAlipay_Okarrow.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }


    private void initView() {

        mLlPaymode_Cash = (LinearLayout) findViewById(R.id.ll_paymode_cash);
        mIvCash_Okarrow = (ImageView) findViewById(R.id.iv_cash_okarrow);
        mLlPaymode_Arrive = (LinearLayout) findViewById(R.id.ll_paymode_arrive);
        mIvArrive_Okarrow = (ImageView) findViewById(R.id.iv_arrive_okarrow);
        mLlPaymode_Alipay = (LinearLayout) findViewById(R.id.ll_paymode_alipay);
        mIvAlipay_Okarrow = (ImageView) findViewById(R.id.iv_alipay_okarrow);
        mTitlebar = (TitleBar) findViewById(R.id.main_titlebar);
        mTvpaymode_Cash = (TextView) findViewById(R.id.tv_paymode_name_cash);
        mTvpaymode_Pos = (TextView) findViewById(R.id.tv_paymode_name_pos);
        mTvpaymode_Alipay = (TextView) findViewById(R.id.tv_paymode_name_alipay);


        mTitlebar.setOnBtnClickListener(this);
        mLlPaymode_Alipay.setOnClickListener(this);
        mLlPaymode_Arrive.setOnClickListener(this);
        mLlPaymode_Cash.setOnClickListener(this);
    }

    //支付方式界面的条目点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_paymode_cash:
                //到付-现金
                mPayMode = PAY_MODE_CASH;
                mIvCash_Okarrow.setVisibility(View.VISIBLE);
                mIvArrive_Okarrow.setVisibility(View.INVISIBLE);
                mIvAlipay_Okarrow.setVisibility(View.INVISIBLE);
                SPUtils.putBoolean(CheckoutcenterPayMode.this, PAYMODE_CASH, true);
                break;
            case R.id.ll_paymode_arrive:
                //到付-pos
                mPayMode = PAY_MODE_POS;
                mIvCash_Okarrow.setVisibility(View.INVISIBLE);
                mIvArrive_Okarrow.setVisibility(View.VISIBLE);
                mIvAlipay_Okarrow.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_paymode_alipay:
                //支付宝
                mPayMode = PAY_MODE_ALI_PAY;
                mIvCash_Okarrow.setVisibility(View.INVISIBLE);
                mIvArrive_Okarrow.setVisibility(View.INVISIBLE);
                mIvAlipay_Okarrow.setVisibility(View.VISIBLE);
                break;
        }
    }


    //titlebar的左侧按钮点击事件
    @Override
    public void onLeftClick() {
        Intent intent = new Intent();
        intent.putExtra(CheckoutCenterHomeActivity.PAYMODEDATA, mPayMode);
        setResult(RESULT_OK, intent);
        finish();
    }

    //titlebar的右侧按钮点击事件
    @Override
    public void onRightClick() {

    }
}
