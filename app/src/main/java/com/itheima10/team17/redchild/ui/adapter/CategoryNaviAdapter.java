package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.CategoryEntity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Destiny on 2016/6/17.
 */
public class CategoryNaviAdapter extends BaseAdapter {
    private Context mContext;
    private List<CategoryEntity.CategoryBean> mData;
    private List<CategoryEntity.CategoryBean> mNaviData;
    private TextView mTitle;

    public CategoryNaviAdapter(Context context, List<CategoryEntity.CategoryBean> data) {
        mContext = context;
        mData = data;
        mNaviData = new ArrayList<>();
        for (CategoryEntity.CategoryBean categoryBean : mData) {
            if (categoryBean.getParentId() == 0) {
                mNaviData.add(categoryBean);
            }
        }
    }

    @Override
    public int getCount() {

        return mNaviData.size();
    }

    public void addData(CategoryEntity.CategoryBean data) {
        mData.add(data);
        if (data.getParentId() == 0) {
            mNaviData.add(data);
        }
        mNaviData.get(0).setIsSelected(true);
    }

    @Override
    public CategoryEntity.CategoryBean getItem(int position) {
        return mNaviData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_categorynavi, null);
        }
        if (getItem(position).getIsSelected()) {
            convertView.setSelected(true);
        } else {
            convertView.setSelected(false);
        }
        CategoryEntity.CategoryBean item = getItem(position);
        mTitle = ViewHolderUtil.getView(convertView, R.id.tv_categorynavi_title);
        mTitle.setText(getItem(position).getName().trim());
        if (item.getIsSelected()) {
            mTitle.setTextColor(Color.RED);
        } else {
            mTitle.setTextColor(Color.GRAY);
        }
        return convertView;
    }

    public void itemClick(int position) {
        for (CategoryEntity.CategoryBean categoryBean : mNaviData) {
            categoryBean.setIsSelected(false);
        }
        getItem(position).setIsSelected(true);
        notifyDataSetChanged();

    }

}
