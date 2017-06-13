package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.CategoryEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.ProductDetailActivity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Destiny on 2016/6/17.
 */
public class ProductDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<CategoryEntity.CategoryBean> mData;
    private int mParentId;
    private ImageView mPic;
    private TextView mTitle;

    public ProductDetailAdapter(Context context, List<CategoryEntity.CategoryBean> data, int parentId) {
        mContext = context;
        mParentId = parentId;
        mData = new ArrayList<>();
        if (mData != null) {
            for (CategoryEntity.CategoryBean categoryBean : data) {
                if (categoryBean.getParentId() == mParentId) {
                    mData.add(categoryBean);
                }
            }
        }

    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CategoryEntity.CategoryBean getItem(int position) {
        return mData.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_gridview_detail, null);
        }
        CategoryEntity.CategoryBean item = getItem(position);
        mPic = ViewHolderUtil.getView(convertView, R.id.iv_category_gridview_product_pic);
        mTitle = ViewHolderUtil.getView(convertView, R.id.iv_category_gridview_product_title);
        mTitle.setText(item.getName());


        if (!TextUtils.isEmpty(item.getPic())) {
            Picasso.with(mContext).load(UrlConstant.MARKET_URL + item.getPic()).into(mPic);
        }


        return convertView;
    }

    public void itemClick(int position) {
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra(Constant.PRODUCT_ID, getItem(position).getId());
        mContext.startActivity(intent);
    }

}
