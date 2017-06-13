package com.itheima10.team17.redchild.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.api.MarketFactory;
import com.itheima10.team17.redchild.bean.SearchGoods;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {

    private ListView mLvShowGoods;
    private ImageView mIvEmpty;
    private SearchAdapter mSearchAdapter;
    private TitleBar mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);
        initView();//初始化控件
        initData();//初始化数据
    }

    private void initView() {
        mLvShowGoods = (ListView) findViewById(R.id.lv_searchfragment_showgoods);
        mIvEmpty = (ImageView) findViewById(R.id.iv_searchfragment_empty);
        mBtnBack = (TitleBar) findViewById(R.id.btn_searchfragment_back);
        mBtnBack.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {

            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void initData() {
        //将搜索界面传递过来的值去匹配数据库
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("searchKeyword");
        //2、查询服务器数据库，获得查询结果
        //搜索商品列表
        Call<SearchGoods> call = MarketFactory.getMarketIOSingleton().getSearchData(searchKeyword, 1, 10, "saleDown");
        call.enqueue(new Callback<SearchGoods>() {
            @Override
            public void onResponse(Call<SearchGoods> call, Response<SearchGoods> response) {
                List<SearchGoods.ProductListEntity> mSearchGoods = response.body().getProductList();
                getSearchGoodsData(mSearchGoods);

            }

            @Override
            public void onFailure(Call<SearchGoods> call, Throwable t) {
            }
        });
    }

    private void getSearchGoodsData(List<SearchGoods.ProductListEntity> mSearchGoods) {
        if (mSearchAdapter == null) {
            mSearchAdapter = new SearchAdapter(this, mSearchGoods);
            mLvShowGoods.setAdapter(mSearchAdapter);
            mLvShowGoods.setEmptyView(mIvEmpty);
            mLvShowGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SearchResultActivity.this, ProductDetailActivity.class);
                    intent.putExtra(Constant.PRODUCT_ID, mSearchGoods.get(position).getId());
                    startActivity(intent);
                }
            });
        } else {
            mSearchAdapter.notifyDataSetChanged();
        }
    }

    class SearchAdapter extends BaseAdapter {

        public Context mContext;
        public List<SearchGoods.ProductListEntity> mSearchGoods;

        public SearchAdapter(Context mContext, List<SearchGoods.ProductListEntity> mSearchGoods) {
            this.mContext = mContext;
            this.mSearchGoods = mSearchGoods;
        }

        @Override
        public int getCount() {
            return mSearchGoods.size();
        }

        @Override
        public Object getItem(int position) {
            return mSearchGoods.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.search_goods_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_searchfragment_icon);
                viewHolder.mTvSp = (TextView) convertView.findViewById(R.id.tv_searchfragment_sp);
                viewHolder.mTvPrice = (TextView) convertView.findViewById(R.id.tv_searchfragment_price);
                viewHolder.mTvDeleteprice = (TextView) convertView.findViewById(R.id.tv_searchfragment_deleteprice);
                viewHolder.mTvAssess = (TextView) convertView.findViewById(R.id.tv_searchfragment_assess);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            SearchGoods.ProductListEntity productListEntity = mSearchGoods.get(position);
            int price = productListEntity.getPrice();
            int marketPrice = productListEntity.getMarketPrice();
            System.out.println("price=" + price + "marketPrice=" + marketPrice);
            viewHolder.mTvSp.setText(productListEntity.getName());
            viewHolder.mTvPrice.setText(DecimalFormatUtil.getDecimal(price));
            viewHolder.mTvDeleteprice.setText(DecimalFormatUtil.getDecimal(marketPrice));
            DecimalFormatUtil.setDashLine(viewHolder.mTvDeleteprice);
            Picasso.with(mContext).load(UrlConstant.MARKET_URL + productListEntity.getPic()).into(viewHolder.mIvIcon);
            return convertView;
        }
    }

    class ViewHolder {
        public TextView mTvSp;
        public ImageView mIvIcon;
        public TextView mTvPrice;
        public TextView mTvDeleteprice;
        public TextView mTvAssess;
    }

}
