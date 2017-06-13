package com.itheima10.team17.redchild.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.HotProductItemBean;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.ProductDetailActivity;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 刘国徽 on 2016/6/18.
 */
public class HotProductActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, TitleBar.OnBtnClickListener {

    private Button   mBt1;
    private Button   mBt2;
    private Button   mBt3;
    private Button   mBt4;
    private ListView mListView;
    private int Count = 1;
    private int Deft1 = 1;
    private int Deft2 = 2;
    private int Deft3 = 3;
    private int Deft4 = 4;
    private HotProductAdapter mHotProductAdapter;
    private TitleBar mTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotproduct);
        intitView();
        initData();
    }

    private void initData() {
        //访问网络数据
        Call<HotProductItemBean> call = sMarketIo.getProductListData(1, 10, "saleDown");
        call.enqueue(new Callback<HotProductItemBean>() {
            @Override
            public void onResponse(Call<HotProductItemBean> call, Response<HotProductItemBean> response) {
                List<HotProductItemBean.ProductListBean> productList = response.body().getProductList();
                getHotProductData(productList);
            }

            @Override
            public void onFailure(Call<HotProductItemBean> call, Throwable t) {

            }
        });
    }

    private void getHotProductData(List<HotProductItemBean.ProductListBean> productList) {
        if (mHotProductAdapter == null) {
            mHotProductAdapter = new HotProductAdapter(this, productList);
            mListView.setAdapter(mHotProductAdapter);
            mHotProductAdapter.notifyDataSetChanged();
        } else {
            mHotProductAdapter.setData(productList);
            mHotProductAdapter.notifyDataSetChanged();
        }
    }


    private void intitView() {
        mTb = (TitleBar) findViewById(R.id.btn_hotproduct_back);
        mListView = (ListView) findViewById(R.id.hotporduct_lv);
        mBt1 = (Button) findViewById(R.id.hotproduct_bt1);
        mBt2 = (Button) findViewById(R.id.hotproduct_bt2);
        mBt3 = (Button) findViewById(R.id.hotproduct_bt3);
        mBt4 = (Button) findViewById(R.id.hotproduct_bt4);
        mListView.setOnItemClickListener(this);
        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);
        mBt4.setOnClickListener(this);
        mTb.setOnBtnClickListener(this);

    }


    //四个按钮的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotproduct_bt1:
                //设置默认背景和文字
                SetDeftBt();
                //设置背景颜色和文字颜色
                mBt1.setBackgroundResource(R.mipmap.segment_selected_1_bg);
                mBt1.setTextColor(Color.WHITE);
                Count = Deft1;
                break;
            case R.id.hotproduct_bt2:
                SetDeftBt();
                mBt2.setBackgroundResource(R.mipmap.segment_selected_2_bg);
                mBt2.setTextColor(Color.WHITE);
                Count = Deft2;
                break;
            case R.id.hotproduct_bt3:
                SetDeftBt();
                mBt3.setBackgroundResource(R.mipmap.segment_selected_2_bg);
                mBt3.setTextColor(Color.WHITE);
                Count = Deft3;
                break;
            case R.id.hotproduct_bt4:
                SetDeftBt();
                mBt4.setBackgroundResource(R.mipmap.segment_selected_3_bg);
                mBt4.setTextColor(Color.WHITE);
                Count = Deft4;
                break;
        }
    }

    //    private int Count = 1; 计算器
    //    private int Deft = 0; 默认
    private void SetDeftBt() {
        switch (Count) {
            case 1:
                mBt1.setBackgroundResource(R.mipmap.segment_normal_1_bg);
                mBt1.setTextColor(Color.GRAY);
                break;
            case 2:
                mBt2.setBackgroundResource(R.mipmap.segment_normal_2_bg);
                mBt2.setTextColor(Color.GRAY);
                break;
            case 3:
                mBt3.setBackgroundResource(R.mipmap.segment_normal_2_bg);
                mBt3.setTextColor(Color.GRAY);
                break;
            case 4:
                mBt4.setBackgroundResource(R.mipmap.segment_normal_3_bg);
                mBt4.setTextColor(Color.GRAY);
                break;

        }
    }

    //条目点击事件 跳转到商品详情
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int pid = mHotProductAdapter.getItem(position).getId();
        startActivity(new Intent(HotProductActivity.this, ProductDetailActivity.class));
    }
//标题栏左右点击事件
    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public class HotProductAdapter extends BaseAdapter {
        private Context                                  mContext;
        private List<HotProductItemBean.ProductListBean> mData;

        public HotProductAdapter(Context context, List<HotProductItemBean.ProductListBean> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public HotProductItemBean.ProductListBean getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.hotproduct_list_item, null);
            }
            ImageView pic = ViewHolderUtil.getView(convertView, R.id.hotproduct_ll_iv);
            TextView name = ViewHolderUtil.getView(convertView, R.id.hotproduct_ll_intor);
            TextView price = ViewHolderUtil.getView(convertView, R.id.hotproduct_tv_price);
            TextView marketprice = ViewHolderUtil.getView(convertView, R.id.hotproduct_tv_marketprice);
            TextView detal = ViewHolderUtil.getView(convertView, R.id.hotproduct_tv_detal);

            Picasso.with(HotProductActivity.this).load(UrlConstant.MARKET_URL + mData.get(position).getPic()).into(pic);
            name.setText(mData.get(position).getName());
            price.setText(DecimalFormatUtil.getDecimal(mData.get(position).getPrice()));
            marketprice.setText(DecimalFormatUtil.getDecimal(mData.get(position).getMarketPrice()));
            marketprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            detal.setText("已有" + mData.get(position).getIsHot() + "条评论");
            return convertView;
        }

        public void setData(List<HotProductItemBean.ProductListBean> data) {
            mData = data;
        }
    }
}
