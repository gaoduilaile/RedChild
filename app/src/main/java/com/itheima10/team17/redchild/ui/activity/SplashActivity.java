package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AddressEntity;
import com.itheima10.team17.redchild.util.SHA1Util;
import com.litesuits.orm.LiteOrm;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            SplashActivity.this.finish();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                LiteOrm sAddressDbTemp = null;
                if (sAddressDbTemp == null) {
                    sAddressDbTemp = LiteOrm.newSingleInstance(getBaseContext(), "city.db");
                }
                if (sAddressDbTemp.queryCount(AddressEntity.class) == 0l) {
                    String add = SplashActivity.this.getString(R.string.address_longstring);
                    Gson gson = new Gson();
                    AddressEntity addressEntity = gson.fromJson(add, AddressEntity.class);
                    List<AddressEntity.DataBean> data = addressEntity.getData();
                    sAddressDbTemp.setDebugged(true);
                    sAddressDbTemp.save(data);
                }
            }
        }).start();
        mHandler.sendEmptyMessageDelayed(0, 1500);
        if (BuildConfig.DEBUG) Log.d("SplashActivity", SHA1Util.getSHA1(this));
    }
}
