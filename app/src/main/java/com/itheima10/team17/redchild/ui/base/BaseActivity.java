package com.itheima10.team17.redchild.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.itheima10.team17.redchild.api.MarketApi;
import com.itheima10.team17.redchild.api.MarketFactory;
import com.itheima10.team17.redchild.bean.UserBean;
import com.itheima10.team17.redchild.bean.UserCenterBean;

/**
 *
 * SHA1 Key :8D0A001D36C52057C67033ADB1A10D043C730125
 * Created by yu895 on 2016/6/15.
 */
public class BaseActivity extends AppCompatActivity {
    public static final MarketApi sMarketIo = MarketFactory.getMarketIOSingleton();


    //账户信息对象,通过判空验证登录状态
    public static UserBean.UserInfoEntity mUserInfo;

    //账户中心信息对象
    public static UserCenterBean mUserCenterInfo;

    public BaseActivity() {

    }
}
