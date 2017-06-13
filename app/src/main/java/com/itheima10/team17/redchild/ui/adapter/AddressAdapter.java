package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AddressEntity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;

import java.util.List;

/**
 * Created by Destiny on 2016/6/11.
 */
public class AddressAdapter extends BaseAdapter {

    private final int mType;
    private List<AddressEntity.DataBean> mData;
    private Context mContext;

    public AddressAdapter(Context context, List<AddressEntity.DataBean> data,int type) {
        mContext = context;
        mData = data;
        mType = type;
    }

    public void setSelected(int position) {
        for (AddressEntity.DataBean bean : mData) {
            bean.isSelected = false;
        }
        getItem(position).isSelected = true;
        notifyDataSetChanged();
    }

    @Override

    public int getCount() {
        return mData.size();
    }

    @Override
    public AddressEntity.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_address, null);
        }
        TextView view = ViewHolderUtil.getView(convertView, R.id.tv_address);

        AddressEntity.DataBean item = getItem(position);
        if (item.isSelected) {
            view.setTextColor(Color.CYAN);
            view.setBackgroundColor(Color.MAGENTA);
        } else {
            view.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        switch (mType) {
            case 0:
                view.setText(item.getProv());
                break;
            case 1:
                view.setText(item.getCity());
                break;
            case 2:
                view.setText(item.getDistrict());
                break;
        }

        return convertView;
    }
}
