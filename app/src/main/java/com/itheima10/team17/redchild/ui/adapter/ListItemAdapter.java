package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.HomeEntity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.lhh.apst.library.ViewHolder;

import java.util.List;

/**
 * Created by Destiny on 2016/6/17.
 */
public class ListItemAdapter extends BaseAdapter {
    private final Context mContext;
    private final int[] mItemImageRes;
    private final String[] mItemNews;
    private List<HomeEntity.HomeTopicBean> mDatas;
    private ImageView mIcon;
    private TextView mTvName;


    public ListItemAdapter(Context mContext,int[]  itemImageRes,String[] itemNews) {
        mItemImageRes = itemImageRes;
        mItemNews = itemNews;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mItemImageRes.length;
    }

    @Override
    public Object getItem(int position) {
        return mItemImageRes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.home_item_list, null);
        }
        mIcon = ViewHolderUtil.getView(convertView, R.id.home_ll_iv);
        mTvName = ViewHolderUtil.getView(convertView, R.id.home_ll_tv);

        mIcon.setImageResource(mItemImageRes[position]);
        mTvName.setText(mItemNews[position]);
        return convertView;
    }
}