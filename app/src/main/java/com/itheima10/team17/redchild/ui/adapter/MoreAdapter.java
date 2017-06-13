package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.MoreGridItemBean;
import com.itheima10.team17.redchild.util.ViewHolderUtil;

import java.util.List;

/**
 * Created by tao on 2016/6/15.
 */
public class MoreAdapter extends BaseAdapter{

    Context mContext;
    List<MoreGridItemBean> mList;

    public MoreAdapter(Context context, List<MoreGridItemBean> list) {
        mContext = context;
        mList = list;
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
            convertView = View.inflate(mContext, R.layout.item_more_gridview,null);
        }
        TextView tv = ViewHolderUtil.getView(convertView, R.id.tv_more_grid_item);
        tv.setText(mList.get(position).getName());
        return convertView;
    }
}
