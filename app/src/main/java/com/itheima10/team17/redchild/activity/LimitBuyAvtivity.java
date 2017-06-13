package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.LimitbuyItemBean;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.activity.ProductDetailActivity;
import com.itheima10.team17.redchild.ui.adapter.LimitbuyAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 刘国徽 on 2016/6/16.
 */
public class LimitBuyAvtivity extends BaseActivity implements TitleBar.OnBtnClickListener {

    private ListView mListView;
    private LimitbuyAdapter mLimitbuyAdapter;
    private List<LimitbuyItemBean.ProductListBean> mData;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mLimitbuyAdapter.updateTime();
            mLimitbuyAdapter.notifyDataSetChanged();
            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ;
        setContentView(R.layout.activity_limitbuy);
        initView();
        initData();
    }

    private void initData() {
        //查询服务器数据库获取数据
        sMarketIo.getLimitBuyData(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(limitbuyItemBean -> {
                    mData=limitbuyItemBean.getProductList();
                }, Throwable::printStackTrace, () -> {
                    if (mData != null) {
                        if (mLimitbuyAdapter == null) {
                            mLimitbuyAdapter = new LimitbuyAdapter(this, mData);
                            mListView.setAdapter(mLimitbuyAdapter);
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.limitbuy_lv);
        TitleBar titleBar = (TitleBar) findViewById(R.id.main_titlebar);
        titleBar.setOnBtnClickListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //条目点击事件 跳转到商品详情
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LimitBuyAvtivity.this, ProductDetailActivity.class);
                intent.putExtra(Constant.PRODUCT_ID, mLimitbuyAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLeftClick() {
        //退出界面
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
