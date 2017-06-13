package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.UserBean;
import com.itheima10.team17.redchild.bean.UserCenterBean;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.SPUtils;
import com.itheima10.team17.redchild.view.TitleBar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tao on 2016/6/15.
 */
public class LauncherActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEt_name;
    private EditText mEt_password;
    private Button   mBtn_launcher;
    private Button   mBtn_register;
    private TitleBar mTb;

    private int i = -1;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initListener() {
        mBtn_launcher.setOnClickListener(this);
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

    private void initView() {
        mTb = (TitleBar) findViewById(R.id.titlebar_launcher);
        mEt_name = (EditText) findViewById(R.id.et_launcher_name);
        mEt_password = (EditText) findViewById(R.id.et_launcher_password);
        mBtn_launcher = (Button) findViewById(R.id.btn_launcher_login);
        mBtn_register = (Button) findViewById(R.id.btn_launcher_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_launcher_login:
                String name = mEt_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = mEt_password.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* 访问网络验证登录
                 * 登录成功获取UserBean,将token值和username和password缓存到sp中
                 * 登录失败提示用户
                 */
                Call<UserBean> userData = sMarketIo.getUserData(name, pwd);
                userData.enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        //对response的值进行判断,如果是response,说明成功,如果是error,说明失败
                        if (TextUtils.equals(response.body().getResponse(), "response")) {
                            //登录成功,获取到用户信息对象
                            mUserInfo = response.body().getUserInfo();
                            //将token值和username和password缓存到sp中
                            SPUtils.putString(LauncherActivity.this, Constant.USER_NAME, mUserInfo.getUsername());
                            SPUtils.putString(LauncherActivity.this, Constant.USER_PASSWORD, mUserInfo.getPassword());
                            SPUtils.putLong(LauncherActivity.this, Constant.TOKEN, mUserInfo.getToken());
                            //获取用户中心数据
                            /**
                             * 因为这个垃圾数据库,我要在这里拿到用户中心的数据
                             */
                            Call<UserCenterBean> userInfoData = sMarketIo.getUserInfoData(mUserInfo.getUser_id(), mUserInfo.getToken());
                            userInfoData.enqueue(new Callback<UserCenterBean>() {

                                @Override
                                public void onResponse(Call<UserCenterBean> call, Response<UserCenterBean> response) {
                                    if (TextUtils.equals(response.body().getResponse(), "response")) {
                                        //登录成功,获取到用户信息对象
                                        mUserCenterInfo = response.body();
                                        setResult(RESULT_OK);
                                        final SweetAlertDialog pDialog = new SweetAlertDialog(LauncherActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                                                .setTitleText("Loading");
                                        pDialog.show();
                                        pDialog.setCancelable(false);
                                        new CountDownTimer(400 * 7, 400) {
                                            public void onTick(long millisUntilFinished) {
                                                // you can change the progress bar color by ProgressHelper every 800 millis
                                                i++;
                                                switch (i){
                                                    case 0:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                                        break;
                                                    case 1:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                                        break;
                                                    case 2:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                                        break;
                                                    case 3:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                                        break;
                                                    case 4:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                                        break;
                                                    case 5:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                                        break;
                                                    case 6:
                                                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                                        break;
                                                }
                                            }

                                            public void onFinish() {
                                                i = -1;
                                                pDialog.setTitleText("Success!")
                                                        .setConfirmText("OK")
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                //finish当前页面
                                                finish();
                                            }
                                        }.start();
                                    } else if (TextUtils.equals(response.body().getResponse(), "error")) {
                                        //服务器返回的失败
                                        Toast.makeText(LauncherActivity.this, response.body().getError().getMsg(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        //登录失败
                                        Toast.makeText(LauncherActivity.this, "登录失败,请重试", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserCenterBean> call, Throwable t) {
                                    Toast.makeText(LauncherActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else if (TextUtils.equals(response.body().getResponse(), "error")) {
                            //服务器返回的失败
                            Toast.makeText(LauncherActivity.this, response.body().getError().getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            //登录失败
                            Toast.makeText(LauncherActivity.this, "登录失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable throwable) {
                        Toast.makeText(LauncherActivity.this, "登录失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_launcher_register:
                //进入注册界面
                startActivity(new Intent(this, RegisterActivity.class));

                break;
            default:
                break;
        }
    }
}
