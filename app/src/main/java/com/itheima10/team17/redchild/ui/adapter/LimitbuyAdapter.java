package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.LimitbuyItemBean;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yu895 on 2016/6/20.
 */
public class LimitbuyAdapter extends BaseAdapter {
    private Context mContext;
    private List<LimitbuyItemBean.ProductListBean> mLimtbuyData;

    public LimitbuyAdapter(Context context, List<LimitbuyItemBean.ProductListBean> limtbuyData) {
        this.mContext = context;
        this.mLimtbuyData = limtbuyData;
    }

    @Override

    public int getCount() {
        return mLimtbuyData.size();
    }

    @Override
    public LimitbuyItemBean.ProductListBean getItem(int position) {
        return mLimtbuyData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.limitbuy_lv_item, null);


        }

        ImageView pic = ViewHolderUtil.getView(convertView, R.id.limitbuy_ll_iv);
        TextView name = ViewHolderUtil.getView(convertView, R.id.limitbuy_ll_intor);
        TextView price = ViewHolderUtil.getView(convertView, R.id.limitbuy_tv_price);
        TextView limitprice = ViewHolderUtil.getView(convertView, R.id.limitbuy_tv_limitprice);
        TextView leftTime = ViewHolderUtil.getView(convertView, R.id.limitbuy_tv_lasttime_tv);

        Picasso.with(mContext).load(UrlConstant.MARKET_URL + mLimtbuyData.get(position).getPic()).into(pic);

        name.setText(mLimtbuyData.get(position).getName());
        price.setText(DecimalFormatUtil.getDecimal(mLimtbuyData.get(position).getPrice()));
        price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        limitprice.setText(DecimalFormatUtil.getDecimal(mLimtbuyData.get(position).getLimitPrice()));
        int leftTime1 = mLimtbuyData.get(position).getLeftTime();
        if (leftTime1 > 0) {
            leftTime.setText(new SimpleDateFormat("HH:mm:ss").format(leftTime1));
        } else {
            leftTime.setText("抢购已结束");
        }
        return convertView;
    }

    public void updateTime() {
        for (LimitbuyItemBean.ProductListBean productListBean : mLimtbuyData) {
            int leftTime = productListBean.getLeftTime();
            if (leftTime > 1000) {
                productListBean.setLeftTime(leftTime - 1000);
            } else {
                productListBean.setLeftTime(0);
            }
        }
    }
}