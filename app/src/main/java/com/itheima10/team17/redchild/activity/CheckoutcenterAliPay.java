package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.api.MarketFactory;
import com.itheima10.team17.redchild.bean.Checkoutcenter;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutcenterAliPay extends AppCompatActivity implements TitleBar.OnBtnClickListener {

    @BindView(R.id.et_alipay_account)
    EditText mEt_AlipayAccount;//账户
    @BindView(R.id.et_alipay_password)
    EditText mEt_AlipayPassword;//密码
    //@BindView(R.id.et_alipay_confirm)
   // EditText mEt_AlipayConfirm;//确认密码
    @BindView(R.id.btn_alipay_submit)
    Button   mBtn_AlipaySubmit;//提交按钮
    @BindView(R.id.titlebar_alipay)
    TitleBar mTitlebar_Alipay;
    private String mFromCheckHomeData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutcenter_ali_pay);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mTitlebar_Alipay.setOnBtnClickListener(this);
        Intent intent = getIntent();
        mFromCheckHomeData = intent.getStringExtra(Constant.PAYABLE);
    }

    @OnClick(R.id.btn_alipay_submit)
    public void onClick() {
        //验证帐号栏
        String account = mEt_AlipayAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {

            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }
        //验证密码栏
        String aliPwd = mEt_AlipayPassword.getText().toString().trim();
        if (TextUtils.isEmpty(aliPwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("itheima@itcast.cn".equals(account) && "itheima123456".equals(aliPwd)) {
            Toast.makeText(this, "付款成功！", Toast.LENGTH_SHORT).show();
            //向服务器提交付款信息
            String userid = BaseActivity.mUserCenterInfo.getUserInfo().getUser_id();
            long token = BaseActivity.mUserCenterInfo.getUserInfo().getToken();
            String orderid = "1313131313";
            String alipaycount ="itheima@itcast.cn";
            String alipaypwd ="itheima123456";
            String paymoney = mFromCheckHomeData;


            Call<Checkoutcenter> call = MarketFactory.getMarketIOSingleton().setCheckoutData(userid, String.valueOf(token),
                    orderid, alipaycount, alipaypwd, paymoney);
           call.enqueue(new Callback<Checkoutcenter>() {
               @Override
               public void onResponse(Call<Checkoutcenter> call, Response<Checkoutcenter> response) {
                   
               }

               @Override
               public void onFailure(Call<Checkoutcenter> call, Throwable t) {

               }
           });
            Intent intent = new Intent();
            intent.setClass(CheckoutcenterAliPay.this, CheckoutSuccess.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "账号或密码不正确，请重新输入...", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    //titleBar左侧按钮点击事件
    @Override
    public void onLeftClick() {
        finish();
    }

    //titleBar右侧按钮点击事件
    @Override
    public void onRightClick() {
    }
}
