package com.itheima10.team17.redchild.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.OrderBean;
import com.itheima10.team17.redchild.ui.activity.OrderInfoActivity;
import com.itheima10.team17.redchild.ui.adapter.MyOrderAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by linhonghong on 2015/8/11
 */
public class CancelOrderFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE = 130;
    private ListView                        mLv_cancel;
    private ImageView                       mIv_empty;
    private OrderBean                       mOrderBean;

    public static CancelOrderFragment instance() {
        CancelOrderFragment view = new CancelOrderFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_order, null);
        findView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        mLv_cancel.setOnItemClickListener(this);
    }

    private void initData() {
        if (BaseActivity.mUserInfo == null) {
            Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
        } else {
            Call<OrderBean> myOrderData = sMarketIo.getMyOrderData("2",
                    BaseActivity.mUserInfo.getToken(), BaseActivity.mUserInfo.getUser_id(), "1", "10");
            myOrderData.enqueue(new Callback<OrderBean>() {

                @Override
                public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                    if ("orderList".equals(response.body().getResponse())) {
                        //获取到数据
                        mOrderBean = response.body();
                        //设置适配器
                        getOrderInfo(mOrderBean);
                    } else {
                        Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OrderBean> call, Throwable t) {
                    Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void findView(View view) {
        mLv_cancel = (ListView) view.findViewById(R.id.lv_order_recent);
        mIv_empty = (ImageView) view.findViewById(R.id.iv_order_empty);
    }

    //设置适配器

    private void getOrderInfo(OrderBean orderBean) {
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(getContext(), orderBean);
        mLv_cancel.setAdapter(myOrderAdapter);
        mLv_cancel.setEmptyView(mIv_empty);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击进入订单详情,并把mOrderBean传过去,实现了Parcelable
        Intent intent = new Intent(getContext(), OrderInfoActivity.class);
        intent.putExtra("orderInfo", mOrderBean);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
