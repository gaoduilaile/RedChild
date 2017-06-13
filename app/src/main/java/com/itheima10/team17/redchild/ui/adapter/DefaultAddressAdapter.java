package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AddressListInfo;
import com.itheima10.team17.redchild.ui.activity.AddNewAddressActivity;
import com.itheima10.team17.redchild.util.ToastUtil;

import java.util.List;

/**
 * Created by Destiny on 2016/6/20.
 */
public class DefaultAddressAdapter extends BaseAdapter {

    public Context mContext;
    public List<AddressListInfo.AddressListEntity> addressList;
    private onNetRequestAdapter mOnnetRequestAdapter;

    public DefaultAddressAdapter(Context mContext, List<AddressListInfo.AddressListEntity> addressList) {
        this.mContext = mContext;
        this.addressList = addressList;
        if (BuildConfig.DEBUG) Log.d("DefaultAddressAdapter", "mContext:" + mContext);
    }

    public void setData(List<AddressListInfo.AddressListEntity> addressList) {
        this.addressList = addressList;
        if (BuildConfig.DEBUG) Log.d("DefaultAddressAdapter", "addressList:" + addressList);
        notifyDataSetChanged();
    }


    public void remove(int position) {

    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public AddressListInfo.AddressListEntity getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.view_amma_item, null);
        TextView mAmmaName = (TextView) view.findViewById(R.id.tv_amma_name);
        TextView mAmmaPhone = (TextView) view.findViewById(R.id.tv_amma_phone);
        TextView mAmmadress = (TextView) view.findViewById(R.id.tv_amma_address);
        TextView mAmmatext = (TextView) view.findViewById(R.id.tv_amma_text);
        TextView mAmmaDelete = (TextView) view.findViewById(R.id.tv_amma_delete);
        TextView mEditView = (TextView) view.findViewById(R.id.tv_amma_edit);
        View mDefault = (View) view.findViewById(R.id.isdefault);
        AddressListInfo.AddressListEntity addressListEntity = addressList.get(position);
        AddressListInfo.AddressListEntity item = getItem(position);
        //获取地址数据表中的id
        mAmmaName.setText(addressListEntity.getName());
        mAmmaPhone.setText(addressListEntity.getPhoneNumber());
        boolean mDefaultX = addressListEntity.isDefaultX();
        int id = addressListEntity.getId();
        if (mDefaultX) {
            mDefault.setBackgroundResource(R.mipmap.b29);
            mAmmadress.setTextColor(Color.BLUE);
        } else {
            mAmmadress.setTextColor(Color.BLACK);
            mDefault.setBackgroundResource(R.mipmap.b28);
        }
        String address = addressListEntity.getProvince() + addressListEntity.getCity() + addressListEntity.getAddressArea() + addressListEntity.getAddressDetail();
        mAmmatext.setText(address);

        //设置item中每个控件点击事件
        //默认地址
        mAmmadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("要设置为默认地址?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //访问数据库设置为默认地址
                        AddressListInfo.AddressListEntity addressListEntity = (AddressListInfo.AddressListEntity) getItem(position);
                        int id = addressListEntity.getId();
                        if (mOnnetRequestAdapter != null) {
                            mOnnetRequestAdapter.editDefault(item.getId());
                        }
                        ToastUtil.show(mContext, "默认地址设置成功", Toast.LENGTH_SHORT);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        //删除操作
        mAmmaDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("确定要删除?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //访问数据库设置为默认地址
                        AddressListInfo.AddressListEntity addressListEntity = (AddressListInfo.AddressListEntity) getItem(position);
                        int id = addressListEntity.getId();
                        if (mOnnetRequestAdapter != null) {
                            mOnnetRequestAdapter.delAddress(item.getId());
                        }
                        ToastUtil.show(mContext, "恭喜您删除成功", Toast.LENGTH_SHORT);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        //编辑操作
        mEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AddNewAddressActivity.class));
            }
        });
        return view;
    }

    public void setOnnetRequestAdapter(onNetRequestAdapter onnetRequestAdapter){
        mOnnetRequestAdapter = onnetRequestAdapter;
    }


    public interface onNetRequestAdapter{
        public boolean addAddress();
        public boolean editDefault(int id);
        public boolean delAddress(int id);
    }

}
