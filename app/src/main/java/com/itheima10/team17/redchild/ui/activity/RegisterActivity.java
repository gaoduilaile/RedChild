package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.UserBean;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.SPUtils;
import com.itheima10.team17.redchild.view.TitleBar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tao on 2016/6/16.
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEt_email;
    private EditText mEt_password;
    private EditText mEt_confirm;
    private Button mBtn_register;
    private TitleBar mTb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        initListener();
    }

    private void initListener() {
        mBtn_register.setOnClickListener(this);
        mTb.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                //按titlebar的左边返回键直接销毁当前页面
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void findView() {
        mTb = (TitleBar) findViewById(R.id.titlebar_register);
        mEt_email = (EditText) findViewById(R.id.et_register_email);
        mEt_password = (EditText) findViewById(R.id.et_register_password);
        mEt_confirm = (EditText) findViewById(R.id.et_register_confirm);
        mBtn_register = (Button) findViewById(R.id.btn_register_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_register:
                String email = mEt_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "请输入Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = mEt_password.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String confirm = mEt_password.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.equals(pwd, confirm)) {
                    Toast.makeText(this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* 访问网络验证注册
                 * 注册成功获取UserBean,将token值和username和password缓存到sp中
                 * 注册失败提示用户
                 */
                Call<UserBean> userData = sMarketIo.getUserRegisterData(email, pwd);
                userData.enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        if (TextUtils.equals(response.body().getResponse(),"register")) {
                            //注册成功,获取到用户信息对象
                            mUserInfo = response.body().getUserInfo();
                            //将token值和username和password缓存到sp中
                            SPUtils.putString(RegisterActivity.this, Constant.USER_NAME,mUserInfo.getUsername());
                            SPUtils.putString(RegisterActivity.this, Constant.USER_PASSWORD,mUserInfo.getPassword());
                            SPUtils.putLong(RegisterActivity.this, Constant.TOKEN,mUserInfo.getToken());
                            //弹出吐司提示用户注册成功,跳转到主页面,让主界面每次onResume是检测登录状态,回显数据
                            //主界面设置为单顶模式,把登录界面和注册界面顶掉
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        } else if (TextUtils.equals(response.body().getResponse(),"error")) {
                            //服务器返回的失败
                            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("")
                                    .setContentText(response.body().getError().getMsg())
                                    .show();
                        } else {
                            //登录失败
                            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("")
                                    .setContentText("网络异常!")
                                    .show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserBean> call, Throwable throwable) {
                        new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("")
                                .setContentText("网络异常!")
                                .show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
