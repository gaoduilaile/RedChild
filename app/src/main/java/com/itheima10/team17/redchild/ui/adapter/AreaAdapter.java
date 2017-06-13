package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AreaBean;

import java.util.List;

/**
 * Created by asus on 2016-06-11.
 */
public class AreaAdapter extends BaseAdapter{

    private Context mContext;
    private  List<AreaBean> mList;

    public AreaAdapter(Context mContext, List<AreaBean> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.address_listview_item, parent, false);
        }
        TextView mTvName = (TextView) convertView.findViewById(R.id.tv_name);
        AreaBean areaBean = mList.get(position);
        mTvName.setText(areaBean.name);
        if (areaBean.isCheck) {
            mTvName.setBackgroundResource(R.mipmap.choose_item_selected);
        } else {
            mTvName.setBackgroundResource(android.R.color.transparent);
        }
        return convertView;
    }

}
