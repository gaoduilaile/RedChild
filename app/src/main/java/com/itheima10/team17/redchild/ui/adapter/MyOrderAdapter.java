package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.OrderBean;
import com.itheima10.team17.redchild.util.ViewHolderUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tao on 2016/6/15.
 */
public class MyOrderAdapter extends BaseAdapter{

    Context mContext;
    OrderBean mOrderBean;
    List<OrderBean.OrderListEntity> mOrderList;

    public MyOrderAdapter(Context context, OrderBean orderBean) {
        mContext = context;
        mOrderBean = orderBean;
        mOrderList = mOrderBean.getOrderList();
    }

    @Override
    public int getCount() {
        return mOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_my_order,null);
        }
        TextView tv_number = ViewHolderUtil.getView(convertView, R.id.tv_item_my_order_number);
        TextView tv_money = ViewHolderUtil.getView(convertView, R.id.tv_item_my_order_money);
        TextView tv_state = ViewHolderUtil.getView(convertView, R.id.tv_item_my_order_state);
        TextView tv_time = ViewHolderUtil.getView(convertView, R.id.tv_item_my_order_time);
        tv_number.setText("订单编号 : " + mOrderList.get(position).getOrderid());
        tv_money.setText("订单总额 : ¥" + mOrderList.get(position).getPrice());
        if (mOrderList.get(position).getState() == 0) {
            tv_state.setText("状态 : 未结算" );
        } else if (mOrderList.get(position).getState() == 1) {
            tv_state.setText("状态 : 已经结算" );
        } else {
            tv_state.setText("状态 : 已经取消" );
        }
        long time = mOrderList.get(position).getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = format.format(time);
        tv_time.setText(date);
        return convertView;
    }
}
