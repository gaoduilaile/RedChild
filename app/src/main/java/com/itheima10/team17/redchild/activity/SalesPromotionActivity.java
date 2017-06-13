package com.itheima10.team17.redchild.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.api.MarketFactory;
import com.itheima10.team17.redchild.bean.SalesPromotionBean;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.ProductDetailActivity;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 刘国徽 on 2016/6/16.
 */
public class SalesPromotionActivity extends BaseActivity implements AdapterView.OnItemClickListener, TitleBar.OnBtnClickListener {

    private ListView mListView;
    private SalesPromotionAdapter mSalesPromotionAdapter;
    private TitleBar mTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutactivity_salespromotion);
        intitView();
        initData();
    }

    private void initData() {
        //获取网络数据
        Call<SalesPromotionBean> call = MarketFactory.getMarketIOSingleton().getTopicData(1, 10);
        call.enqueue(new Callback<SalesPromotionBean>() {

            @Override
            public void onResponse(Call<SalesPromotionBean> call, Response<SalesPromotionBean> response) {
                List<SalesPromotionBean.TopicBean> topicData = response.body().getTopic();
                getSaleTopicData(topicData);
            }

            @Override
            public void onFailure(Call<SalesPromotionBean> call, Throwable t) {

            }
        });
    }
    private void getSaleTopicData(List<SalesPromotionBean.TopicBean> topicData) {
        if (mSalesPromotionAdapter == null) {

            mSalesPromotionAdapter = new SalesPromotionAdapter(SalesPromotionActivity.this,topicData);
            mListView.setAdapter(mSalesPromotionAdapter);
        } else {
            mSalesPromotionAdapter.notifyDataSetChanged();
        }
    }

    private void intitView() {
        mTb = (TitleBar) findViewById(R.id.btn_searchfragment_back);
        mListView = (ListView) findViewById(R.id.salespromotion_lv);
        mListView.setOnItemClickListener(this);
        mTb.setOnBtnClickListener(this);
    }

    //条目点击事件 跳转到商品详情
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pid = mSalesPromotionAdapter.getItem(position).getId();
        startActivity(new Intent(SalesPromotionActivity.this, ProductDetailActivity.class));

    }
//标题栏中左右返回的点击事件
    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public class SalesPromotionAdapter extends BaseAdapter {
        private Context                            mContext;
        private List<SalesPromotionBean.TopicBean> mTopicData;

        public SalesPromotionAdapter(Context context, List<SalesPromotionBean.TopicBean> topicData) {
            this.mContext = context;
            this.mTopicData = topicData;
        }

        @Override
        public int getCount() {
            return mTopicData.size();
        }

        @Override
        public SalesPromotionBean.TopicBean getItem(int position) {
            return mTopicData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.salepromotion_list_item, null);
            }
            TextView name = ViewHolderUtil.getView(convertView, R.id.salespromotion_list_item_tv);
            ImageView pic = ViewHolderUtil.getView(convertView, R.id.salespromotion_list_item_iv);
            name.setText(mTopicData.get(position).getName());
            Picasso.with(SalesPromotionActivity.this).load(UrlConstant.MARKET_URL + mTopicData.get(position).getPic()).into(pic);
            return convertView;
        }
    }
}
