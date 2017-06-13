package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.LogoutBean;
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
public class UserCenterActivity extends BaseActivity {

    private static final String TAG = "UserCenterActivity";
    private TitleBar mTb;
    private TextView mTv_name;
    private TextView mTv_level;
    private TextView mTv_bonus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initView();
        initListener();
    }

    private void initListener() {
        mTb.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                //按返回直接销毁当前页面
                finish();
            }

            @Override
            public void onRightClick() {
                new SweetAlertDialog(UserCenterActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("")
                        .setContentText("确认要注销登录吗?")
                        .setCancelText("我再看看")
                        .setConfirmText("狠心离开")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                    //注销也要访问网络
                                    Call<LogoutBean> userLogoutData = sMarketIo.getUserLogoutData(BaseActivity.mUserInfo.getUser_id());
                                    userLogoutData.enqueue(new Callback<LogoutBean>() {
                                        @Override
                                        public void onResponse(Call<LogoutBean> call, Response<LogoutBean> response) {
                                            if (TextUtils.equals(response.body().getResponse(),"logout")) {
                                                //确认注销,将mUserInfo重新置为空
                                                mUserInfo = null;
                                                //将token值置为-1,username和password重新置为空,缓存到sp中
                                                SPUtils.putString(UserCenterActivity.this, Constant.USER_NAME, "");
                                                SPUtils.putString(UserCenterActivity.this, Constant.USER_PASSWORD, "");
                                                SPUtils.putLong(UserCenterActivity.this, Constant.TOKEN, -1);
                                                //跳转到主界面的更多
                                                Intent intent = new Intent(UserCenterActivity.this, MainActivity.class);
                                                intent.putExtra(Constant.PAGE_ID, 5);
                                                startActivity(intent);
                                            } else if (TextUtils.equals(response.body().getResponse(),"error")) {
                                                new SweetAlertDialog(UserCenterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText("")
                                                        .setContentText("网络异常!")
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<LogoutBean> call, Throwable t) {
                                            new SweetAlertDialog(UserCenterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("")
                                                    .setContentText("网络异常!")
                                                    .show();
                                        }
                                    });
                                
                            }
                        })
                        .show();
            }
        });
    }

    private void initView() {
        mTb = (TitleBar) findViewById(R.id.titlebar_logout);
        mTv_name = (TextView) findViewById(R.id.tv_user_center_name);
        mTv_level = (TextView) findViewById(R.id.tv_user_center_level);
        mTv_bonus = (TextView) findViewById(R.id.tv_user_center_bonus);
        mTv_name.setText("用户名: "+mUserInfo.getUsername());
        mTv_level.setText("会员等级: "+mUserInfo.getLevel());
        mTv_bonus.setText("总积分: "+mUserInfo.getBonus());
    }
}
