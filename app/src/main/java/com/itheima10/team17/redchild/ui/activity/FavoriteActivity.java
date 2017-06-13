package com.itheima10.team17.redchild.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.FavoriteBean;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tao on 2016/6/18.
 */
public class FavoriteActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.titlebar_favorite)
    TitleBar  mTitlebarFavorite;
    @BindView(R.id.iv_favorite_empty)
    ImageView mIvFavoriteEmpty;
    @BindView(R.id.lv_favorite_showgoods)
    ListView  mLvFavoriteShowgoods;

    private FavoriteAdapter                      mFavoriteAdapter;
    private List<FavoriteBean.ProductListEntity> mProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initData();
        initListener();
    }

    private void initData() {
        //收藏夹商品列表
        if (BaseActivity.mUserInfo != null) {
            Call<FavoriteBean> favoriteData = sMarketIo.getFavoriteData(BaseActivity.mUserInfo.getUser_id(),
                    BaseActivity.mUserInfo.getToken(), "1", "10");
            favoriteData.enqueue(new Callback<FavoriteBean>() {
                @Override
                public void onResponse(Call<FavoriteBean> call, Response<FavoriteBean> response) {
                    if (TextUtils.equals("favorites", response.body().getResponse())) {
                        mProductList = response.body().getProductList();
                        //更新
                        initFavoriteGoodsData();

                    } else {
                        Toast.makeText(FavoriteActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FavoriteBean> call, Throwable t) {
                    Toast.makeText(FavoriteActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        }
    }

    private void initListener() {

        mLvFavoriteShowgoods.setOnItemClickListener(this);

        mTitlebarFavorite.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                mProductList.clear();
                initFavoriteGoodsData();
            }
        });
    }

    private void initFavoriteGoodsData() {
        if (mFavoriteAdapter == null) {
            mFavoriteAdapter = new FavoriteAdapter(this, mProductList);
            mLvFavoriteShowgoods.setAdapter(mFavoriteAdapter);
            mLvFavoriteShowgoods.setEmptyView(mIvFavoriteEmpty);
        } else {
            mFavoriteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pId = mProductList.get(position).getId();
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(Constant.PRODUCT_ID, pId);
        startActivity(intent);
    }

    class FavoriteAdapter extends BaseAdapter {

        public Context                              mContext;
        public List<FavoriteBean.ProductListEntity> mFavoriteGoods;

        public FavoriteAdapter(Context mContext, List<FavoriteBean.ProductListEntity> mSearchGoods) {
            this.mContext = mContext;
            this.mFavoriteGoods = mSearchGoods;
        }

        @Override
        public int getCount() {
            return mFavoriteGoods.size();
        }

        @Override
        public Object getItem(int position) {
            return mFavoriteGoods.get(position);
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
            if (mFavoriteGoods.get(position) != null) {
                FavoriteBean.ProductListEntity productListEntity = mFavoriteGoods.get(position);
                Log.d("goods", "getView: " + mFavoriteGoods);
                int marketPrice = productListEntity.getMarketPrice();
                int price = productListEntity.getPrice();
                viewHolder.mTvSp.setText(productListEntity.getName());
                viewHolder.mTvPrice.setText(DecimalFormatUtil.getDecimal(price));
                viewHolder.mTvDeleteprice.setText(DecimalFormatUtil.getDecimal(marketPrice));
                viewHolder.mTvDeleteprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                Picasso.with(mContext).load(UrlConstant.MARKET_URL + productListEntity.getPic()).into(viewHolder.mIvIcon);
            }
            return convertView;
        }
    }

    class ViewHolder {
        public TextView  mTvSp;
        public ImageView mIvIcon;
        public TextView  mTvPrice;
        public TextView  mTvDeleteprice;
        public TextView  mTvAssess;
    }
}
