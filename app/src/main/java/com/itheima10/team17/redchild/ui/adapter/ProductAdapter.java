package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.CategoryEntity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.itheima10.team17.redchild.view.MGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Destiny on 2016/6/17.
 */
public class ProductAdapter extends BaseAdapter {

    private Context mContext;
    private List<CategoryEntity.CategoryBean> mData;
    private List<CategoryEntity.CategoryBean> mSecondData;
    private List<CategoryEntity.CategoryBean> mThirdData;
    private ImageView imageView;
    private GridView gridView;
    private int selectedId;
    private MGridView mGridView;
    private TextView mTitle;
    private SparseArray<ProductDetailAdapter> mGridHolder;
    private ProductDetailAdapter mProductDetailAdapter;

    public ProductAdapter(Context context, List<CategoryEntity.CategoryBean> mData) {
        mContext = context;
        if (mSecondData == null) {
            mSecondData = new ArrayList<>();
        } else {
            mSecondData.clear();
        }
        if (mThirdData == null) {
            mThirdData = new ArrayList<>();
        } else {
            mThirdData.clear();
        }
        if (mData != null) {

            for (CategoryEntity.CategoryBean categoryBean : mData) {
                if (categoryBean.getId() > 9 && categoryBean.getId() < 100) {
                    mSecondData.add(categoryBean);
                } else if (categoryBean.getId() == 0) {

                } else {
                    mThirdData.add(categoryBean);
                }
            }
            this.mData = mData;
        }
        mGridHolder = new SparseArray<>();

    }

    public void setData(List<CategoryEntity.CategoryBean> data, int productid) {

        selectedId = productid;
        if (mSecondData == null) {
            mSecondData = new ArrayList<>();
        }
        mSecondData.clear();
        if (mThirdData == null) {
            mThirdData = new ArrayList<>();
        }
        mThirdData.clear();
        if (data != null) {
            if (mData != null) {
                mData.clear();
            } else {
                mData = new ArrayList<>();
            }
            for (CategoryEntity.CategoryBean categoryBean : data) {
                if (categoryBean.getId() > 9 && categoryBean.getId() < 100) {
                    if (categoryBean.getParentId() == selectedId) {
                        mSecondData.add(categoryBean);
                    }
                } else if (categoryBean.getParentId() == 0) {

                } else {
                    mThirdData.add(categoryBean);
                }
            }
            this.mData.addAll(data);
        }
    }

    @Override
    public int getCount() {
        return mSecondData.size();
    }

    @Override
    public CategoryEntity.CategoryBean getItem(int position) {
        return mSecondData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_category, null, false);
        }
        CategoryEntity.CategoryBean item = getItem(position);
        mGridView = ViewHolderUtil.getView(convertView, R.id.gvlayout_category);
        mTitle = ViewHolderUtil.getView(convertView, R.id.tv_product_title);
        mTitle.setText(item.getName());

        mProductDetailAdapter = mGridHolder.get(item.getId());
        if (mProductDetailAdapter == null) {
            mProductDetailAdapter = new ProductDetailAdapter(mContext, mThirdData, item.getId());
            if (BuildConfig.DEBUG)
            mGridHolder.put(item.getId(), mProductDetailAdapter);
        }

        mGridView.setAdapter(mProductDetailAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGridHolder.get(item.getId()).itemClick(position);
            }
        });
        return convertView;
    }
}
