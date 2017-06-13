package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.OrderBean;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by tao on 2016/6/17.
 */
public class OrderInfoActivity extends BaseActivity {
    @BindView(R.id.titlebar_order_info)
    TitleBar mTitlebarOrderInfo;
    @BindView(R.id.tv_order_info_order_num)
    TextView mTvOrderInfoOrderNum;
    @BindView(R.id.tv_order_info_address_name)
    TextView mTvOrderInfoAddressName;
    @BindView(R.id.tv_order_info_address_number)
    TextView mTvOrderInfoAddressNumber;
    @BindView(R.id.tv_order_info_address_address)
    TextView mTvOrderInfoAddressAddress;
    @BindView(R.id.tv_order_info_state)
    TextView mTvOrderInfoState;
    @BindView(R.id.tv_order_info_sendtype)
    TextView mTvOrderInfoSendtype;
    @BindView(R.id.tv_order_info_paytype)
    TextView mTvOrderInfoPaytype;
    @BindView(R.id.tv_order_info_time)
    TextView mTvOrderInfoTime;
    @BindView(R.id.tv_order_info_sendtime)
    TextView mTvOrderInfoSendtime;
    @BindView(R.id.tv_order_info_invoice_state)
    TextView mTvOrderInfoInvoiceState;
    @BindView(R.id.tv_order_info_invoice_head)
    TextView mTvOrderInfoInvoiceHead;
    @BindView(R.id.tv_order_info_sendrequest)
    TextView mTvOrderInfoSendrequest;
    @BindView(R.id.tv_order_info_name)
    TextView mTvOrderInfoName;
    @BindView(R.id.tv_order_info_color)
    TextView mTvOrderInfoColor;
    @BindView(R.id.tv_order_info_price)
    TextView mTvOrderInfoPrice;
    @BindView(R.id.tv_order_info_number)
    TextView mTvOrderInfoNumber;
    @BindView(R.id.tv_order_info_allcount)
    TextView mTvOrderInfoAllcount;
    @BindView(R.id.tv_order_info_originalpay)
    TextView mTvOrderInfoOriginalpay;
    @BindView(R.id.tv_order_info_sendpay)
    TextView mTvOrderInfoSendpay;
    @BindView(R.id.tv_order_info_cutpay)
    TextView mTvOrderInfoCutpay;
    @BindView(R.id.tv_order_info_shouldpay)
    TextView mTvOrderInfoShouldpay;
    @BindView(R.id.btn_order_info_cancel)
    Button   mBtnOrderInfoCancel;
    private RelativeLayout mRl_searchsend;
    private int mDeliveryType;
    private String mOrderid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
        mRl_searchsend = (RelativeLayout) findViewById(R.id.rl_order_info_searchsend);
        Intent intent = getIntent();
        OrderBean orderInfo = (OrderBean) intent.getSerializableExtra("orderInfo");
        OrderBean.OrderListEntity orderListEntity = orderInfo.getOrderList().get(0);
        //订单id
        Log.d("OrderInfoActivity", orderInfo.toString());

        ArrayList<OrderBean.OrderListEntity.CartsEntity> carts = orderListEntity.getCarts();

        mBtnOrderInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消订单
                finish();
            }
        });

        mRl_searchsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到物流查询
                Intent intent1 = new Intent(OrderInfoActivity.this, SearchLogisticsActivity.class);
                intent1.putExtra("orderId", mOrderid);
                startActivity(intent1);
            }
        });

        mTitlebarOrderInfo.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }
}
