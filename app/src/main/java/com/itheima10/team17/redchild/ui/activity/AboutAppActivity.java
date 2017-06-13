package com.itheima10.team17.redchild.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.VersionBean;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.PackageUtils;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.ExplosionField;
import com.itheima10.team17.redchild.view.TitleBar;
import com.umeng.update.UmengUpdateAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutAppActivity extends BaseActivity {


    @BindView(R.id.titlebar_about)
    TitleBar mTitlebarAbout;
    @BindView(R.id.aboutegg)
    ImageView mAboutegg;
    @BindView(R.id.ipconfig)
    EditText mIpconfig;
    @BindView(R.id.btn_ipconfig)
    Button mBtnIpconfig;
    @BindView(R.id.tv_about_version)
    TextView mTvAboutVersion;
    @BindView(R.id.btn_about)
    Button mBtnAbout;
    @BindView(R.id.xiaoban)
    ImageView mXiaoban;
    private int mVersion;
    private String mVersionName;
    private ImageView mMEgg;
    private int num = 0;
    private ImageView xiaoban;
    private ExplosionField mExplosionField;
    private EditText mIpaddr;
    private Button mBtnIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        mMEgg = (ImageView) findViewById(R.id.aboutegg);
        xiaoban = (ImageView) findViewById(R.id.xiaoban);
        mIpaddr = (EditText) findViewById(R.id.ipconfig);
        mIpaddr.setHint(UrlConstant.MARKET_URL);
        mBtnIp = (Button) findViewById(R.id.btn_ipconfig);
        mBtnIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIpaddr != null) {
                    UrlConstant.MARKET_URL = "http://" + mIpaddr.getText().toString().trim() + "/market/";
                    ToastUtil.show(AboutAppActivity.this, "IP已更改为:" + UrlConstant.MARKET_URL, ToastUtil.LENGTH_SHORT);
                }
            }
        });
        initData();
        initListener();
    }

    private void initData() {
        // 获取版本号信息
        mVersion = PackageUtils.getVersionCode(this);
        mVersionName = PackageUtils.getVersionName(this);
        mTvAboutVersion.setText("版本号 :" + mVersionName);
        mMEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 5) {
                    xiaoban.setVisibility(View.VISIBLE);
                    if (BuildConfig.DEBUG) Log.d("AboutAppActivity", "num:" + num);
                } else {
                    num++;
                }
            }
        });
    }

    private void initListener() {
        mBtnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<VersionBean> versionData = sMarketIo.getVersionData(mVersion);
                versionData.enqueue(new Callback<VersionBean>() {
                    @Override
                    public void onResponse(Call<VersionBean> call, Response<VersionBean> response) {
                        if ("error".equals(response.body().getResponse())) {
                            Toast.makeText(AboutAppActivity.this, response.body().getError().getMsg(), Toast.LENGTH_SHORT).show();
                        } else if ("version".equals(response.body().getResponse())) {
                            initDialog(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<VersionBean> call, Throwable t) {
                        Toast.makeText(AboutAppActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mTitlebarAbout.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void initDialog(Response<VersionBean> response) {
        String desc = response.body().getVersion().getDesc();
        //弹出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(response.body().getVersion().getDesc() + ",是否下载?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO 下载逻辑待实现
                UmengUpdateAgent.forceUpdate(AboutAppActivity.this);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (num > 20) {
                return super.onKeyDown(keyCode, event);
            }
            if (xiaoban.getVisibility() == View.VISIBLE) {
                num = 40;
                mExplosionField = ExplosionField.attach2Window(this);
                mExplosionField.explode(xiaoban);
                return true;
            }


        }
        return super.onKeyDown(keyCode, event);
    }

}
