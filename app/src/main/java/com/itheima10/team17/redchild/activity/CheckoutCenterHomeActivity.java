package com.itheima10.team17.redchild.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.api.MarketFactory;
import com.itheima10.team17.redchild.bean.CartListEntity;
import com.itheima10.team17.redchild.bean.SumbitOrder;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.AddressManagementActivity;
import com.itheima10.team17.redchild.ui.activity.MyOrderAcrivity;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.itheima10.team17.redchild.view.MListView;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutCenterHomeActivity extends BaseActivity implements View.OnClickListener, TitleBar.OnBtnClickListener {

    private List<CartListEntity.CartBean> mFromCartDataList;//来自购物车的数据
    public static final int REQUEST_PAYMODE = 1001;//支付方式请求码
    public static final int REQUEST_SENDTIME = 1002;//发货时间请求码
    public static final int REQUEST_INVOICE = 1003;//索要发票请求码
    public static final int REQUEST_COUPON = 1004;//优惠券请求码
    public static final int REQUEST_ADDRESSINFO = 1005;//获取收件人信息请求码
    public static final String PAYMODEDATA = "paymodedata";
    private LinearLayout mLlCheckoutArriver;
    private TextView mTvArriverDesc;
    private LinearLayout mLlCheckoutPaymode;
    private TextView mTvPaymodeDesc;
    private LinearLayout mLlCheckoutSendtime;
    private TextView mTvSendtimeDesc;
    private LinearLayout mLlCheckoutSendmode;
    private TextView mTvSendmodeDesc;
    private LinearLayout mLlCheckoutCoupon;
    private TextView mTvCouponDesc;
    private LinearLayout mLlCheckoutInvoice;
    private TextView mTvInvoiceDesc;
    private ImageView mIvProductIcon;
    private TextView mTvProductName;
    private TextView mTvProductColor;
    private TextView mTvProductSize;
    private TextView mTvProductPrice;
    private TextView mTvProductNumber;
    private TextView mTvProductSubtotal;
    private Button mBtnCheckoutSubmitBottom;
    private String mFromPayModeData;
    private TitleBar mTitle_Bar;
    private String mFromSendTimeData;//从送货时间界面返回的数据
    private MListView mLv_checkoutcenter;
    private TextView mTvProductProperty;//产品属性，包括颜色和尺寸
    private TextView mTvProductPropertyValue;//产品属性值
    private TextView mTv_productDesc_totalNum;
    private String mSinglePrice;//listview条目中商品的单价
    private String mProductNum;//listview条目中商品的数量
    private String mHead;//发票抬头
    private String mType;//发票类型
    private String mDesc;//发票内容
    private TextView mOriginnal_money;
    private TextView mTv_account_payable;
    private int mPayable;
    private String mCids;
    private int mAdd_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutcenter_homeinfo);

        initData();
        initView();
    }

    private void initView() {
        mTitle_Bar = (TitleBar) findViewById(R.id.main_titlebar);
        mLlCheckoutArriver = (LinearLayout) findViewById(R.id.ll_checkout_arriver);
        mTvArriverDesc = (TextView) findViewById(R.id.tv_arriver_desc);
        mLlCheckoutPaymode = (LinearLayout) findViewById(R.id.ll_checkout_paymode);
        mTvPaymodeDesc = (TextView) findViewById(R.id.tv_paymode_desc);
        mLlCheckoutSendtime = (LinearLayout) findViewById(R.id.ll_checkout_sendtime);
        mTvSendtimeDesc = (TextView) findViewById(R.id.tv_sendtime_desc);
        mLlCheckoutSendmode = (LinearLayout) findViewById(R.id.ll_checkout_sendmode);
        mTvSendmodeDesc = (TextView) findViewById(R.id.tv_sendmode_desc);
        mLlCheckoutCoupon = (LinearLayout) findViewById(R.id.ll_checkout_coupon);
        mTvCouponDesc = (TextView) findViewById(R.id.tv_coupon_desc);
        mLlCheckoutInvoice = (LinearLayout) findViewById(R.id.ll_checkout_invoice);
        mTvInvoiceDesc = (TextView) findViewById(R.id.tv_invoice_desc);
        mBtnCheckoutSubmitBottom = (Button) findViewById(R.id.btn_checkout_submit_bottom);
        mLv_checkoutcenter = (MListView) findViewById(R.id.lv_checkoutcenter);
        mOriginnal_money = (TextView) findViewById(R.id.tv_original_money);//原始金额
        //应付金额
        mTv_account_payable = (TextView) findViewById(R.id.tv_account_payable);
        mTv_productDesc_totalNum = (TextView) findViewById(R.id.tv_productDesc_totalNum);//listview整个条目的商品数量
        //设置listview不获取焦点，解决启动时页面跳转到中间
        mLv_checkoutcenter.setFocusable(false);


        mTitle_Bar.setOnBtnClickListener(this);
        mLlCheckoutArriver.setOnClickListener(this);
        mLlCheckoutPaymode.setOnClickListener(this);
        mLlCheckoutSendtime.setOnClickListener(this);
        mLlCheckoutSendmode.setOnClickListener(this);
        mBtnCheckoutSubmitBottom.setOnClickListener(this);
        mLlCheckoutInvoice.setOnClickListener(this);
        mLlCheckoutCoupon.setOnClickListener(this);
        //listview设置设配器
        CheckoutAdapter adapter = new CheckoutAdapter();
        mLv_checkoutcenter.setAdapter(adapter);

    }


    public class CheckoutAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mFromCartDataList.size();
        }

        @Override
        public CartListEntity.CartBean getItem(int position) {
            return mFromCartDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(CheckoutCenterHomeActivity.this, R.layout.checkoutcenter_listview_item, null);
            }
            mIvProductIcon = ViewHolderUtil.getView(convertView, R.id.iv_product_icon);
            mTvProductName = ViewHolderUtil.getView(convertView, R.id.tv_product_name);
            mTvProductProperty = ViewHolderUtil.getView(convertView, R.id.tv_product_property);
            mTvProductPropertyValue = ViewHolderUtil.getView(convertView, R.id.tv_product_property_value);
            mTvProductPrice = ViewHolderUtil.getView(convertView, R.id.tv_product_price);
            mTvProductNumber = ViewHolderUtil.getView(convertView, R.id.tv_product_number);
            mTvProductSubtotal = ViewHolderUtil.getView(convertView, R.id.tv_product_subtotal);

            mTvProductName.setText(mFromCartDataList.get(position).getProductName());//产品名字
            mTvProductProperty.setText("颜色/尺码：" + mFromCartDataList.get(position).getProductPropertyName());//产品属性
            mSinglePrice = mFromCartDataList.get(position).getProductPrice();
            mTvProductPrice.setText("单价：" + DecimalFormatUtil.getDecimal(mSinglePrice));//单价
            mProductNum = String.valueOf(mFromCartDataList.get(position).getPnum());
            mTvProductNumber.setText("数量：" + mProductNum);//数量
            mTvProductSubtotal.setText("小计：" + DecimalFormatUtil.getDecimal(Integer.parseInt(mSinglePrice) * Integer.parseInt(mProductNum)));
            Picasso.with(CheckoutCenterHomeActivity.this).load(UrlConstant.MARKET_URL + mFromCartDataList.get(position).getProductImageUrl()).into(mIvProductIcon);
            mTv_productDesc_totalNum.setText("" + mFromCartDataList.size() * Integer.parseInt(mProductNum));
            //原始金额
            mOriginnal_money.setText(DecimalFormatUtil.getDecimal(Integer.parseInt(mSinglePrice) * Integer.parseInt(mProductNum) * mFromCartDataList.size()));
            //应付金额
            mPayable = Integer.parseInt(mSinglePrice) * Integer.parseInt(mProductNum) * mFromCartDataList.size();
            mTv_account_payable.setText(DecimalFormatUtil.getDecimal(mPayable - 40));
            return convertView;
        }
    }

    private void initData() {
        fromCartData();

    }

    //从购物车传递过来的数据
    private void fromCartData() {
        Intent intent = getIntent();
        CartListEntity parcelableExtra = intent.getParcelableExtra(Constant.FROM_CART_DATA);
        mFromCartDataList = parcelableExtra.getCart();
       /* for (CartListEntity.CartBean cartBean : cart) {
            if (BuildConfig.DEBUG) Log.d("CheckoutCenterHomeActiv", cartBean.getProductName() +": " +cartBean.getId());
        }*/
    }


    //条目点击事件
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_checkout_arriver://收货人信息
                Intent intent3 = new Intent(CheckoutCenterHomeActivity.this, AddressManagementActivity.class);
                intent3.putExtra(Constant.AAAAA, true);
                startActivityForResult(intent3, REQUEST_ADDRESSINFO);

                break;
            case R.id.ll_checkout_paymode://支付方式
                Intent intent = new Intent(getApplicationContext(), CheckoutcenterPayMode.class);
                if (mFromPayModeData != null) {
                    intent.putExtra(PAYMODEDATA, mFromPayModeData);
                }
                startActivityForResult(intent, REQUEST_PAYMODE);
                break;
            case R.id.ll_checkout_sendtime://送货时间
                Intent intent2 = new Intent(getApplicationContext(), CheckoutcenterSendTime.class);
                if (mFromSendTimeData != null) {
                    intent2.putExtra(CheckoutcenterSendTime.SENDTIME, mFromSendTimeData);
                }
                startActivityForResult(intent2, REQUEST_SENDTIME);
                break;
            case R.id.ll_checkout_sendmode://送货方式
                //送货方式对话框
                sendModeDialog();
                break;
            /*case R.id.ll_checkout_coupon://使用优惠券
                Intent intent1 = new Intent(getApplicationContext(), CheckoutCoupon.class);
                startActivityForResult(intent1, REQUEST_COUPON);
                break;*/
            case R.id.ll_checkout_invoice://索要发票:
                startActivityForResult(new Intent(getApplicationContext(), Checkout_InvoiceInfo.class), REQUEST_INVOICE);
                break;
            case R.id.btn_checkout_submit_bottom://底部提交订单


                //将订单数据提交到服务器
                for (int i = 0; i < mFromCartDataList.size(); i++) {
                    CartListEntity.CartBean cartBean = mFromCartDataList.get(i);
                    if (i == 0) {
                        mCids = cartBean.getId() + "";
                    } else {
                        mCids = mCids + "-" + cartBean.getId();
                    }
                }
                String userid = BaseActivity.mUserCenterInfo.getUserInfo().getUser_id();
                long token = BaseActivity.mUserCenterInfo.getUserInfo().getToken();
                int addressid = mAdd_id;//TODO 模拟地址Id数据
                String payMode = mTvPaymodeDesc.getText().toString().trim();
                int paymentType;
                if (TextUtils.equals("到付-现金", payMode)) {
                    paymentType = 1;
                } else if (TextUtils.equals("到付-pos", payMode)) {
                    paymentType = 2;
                } else {
                    paymentType = 3;
                }

                String sendTime = mTvSendtimeDesc.getText().toString().trim();
                int deliveryType;
                if (TextUtils.equals("任何时间", sendTime)) {
                    deliveryType = 1;
                } else if (TextUtils.equals("仅限工作日", sendTime)) {
                    deliveryType = 2;
                } else {
                    deliveryType = 3;
                }

                int invoiceType;
                if (TextUtils.equals("不需要", mType)) {
                    invoiceType = 0;
                } else if (TextUtils.equals("个人", mType)) {
                    invoiceType = 1;
                } else {
                    invoiceType = 2;
                }

                String invoiceTitle = mHead;
                String invoiceContent = mDesc;
                String couponid = "新用户10元代金券";

                //将订单信息提交到服务器
                //cids  userid  token  addressid  paymentType  deliveryType
                // invoiceType invoiceTitle  invoiceContent couponid
                Call<SumbitOrder> call = MarketFactory.getMarketIOSingleton().setOrderSumbitData(mCids, userid, String.valueOf(token), String.valueOf(addressid), String.valueOf(paymentType), String.valueOf(deliveryType), String.valueOf(invoiceType), invoiceTitle, invoiceContent, couponid);
                call.enqueue(new Callback<SumbitOrder>() {
                    @Override
                    public void onResponse(Call<SumbitOrder> call, Response<SumbitOrder> response) {
                        //TODO 提交订单后返回的集合响应
                        if (TextUtils.equals(response.body().response, "orderSumbit")) {
                            //response.body().getResponse().
                            Toast.makeText(CheckoutCenterHomeActivity.this, "提交订单成功", Toast.LENGTH_SHORT).show();
                            Intent intent4 = new Intent(getApplicationContext(), MyOrderAcrivity.class);
                            startActivity(intent4);
                            finish();
                        } else {
                            Toast.makeText(CheckoutCenterHomeActivity.this, "提交订单成功1", Toast.LENGTH_SHORT).show();
                            try {
                                if (BuildConfig.DEBUG)
                                    Log.d("CheckoutCenterHomeActiv", response.body().getError().getMsg());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<SumbitOrder> call, Throwable t) {

                    }
                });


                break;
        }

    }

    //跳转其他activity返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //支付方式界面返回的数据
        if (requestCode == CheckoutCenterHomeActivity.REQUEST_PAYMODE) {
            if (resultCode == RESULT_OK) {
                mFromPayModeData = data.getStringExtra(PAYMODEDATA);
                mTvPaymodeDesc.setText(mFromPayModeData);
            }
        }
        //送货时间界面返回的数据
        if (requestCode == CheckoutCenterHomeActivity.REQUEST_SENDTIME) {
            if (resultCode == RESULT_OK) {
                mFromSendTimeData = data.getStringExtra(CheckoutcenterSendTime.SENDTIME);
                mTvSendtimeDesc.setText(mFromSendTimeData);
            }
        }
        //发票信息界面返回的数据
        if (requestCode == CheckoutCenterHomeActivity.REQUEST_INVOICE) {
            if (resultCode == RESULT_OK) {
                mHead = data.getStringExtra(Constant.INVOICE_HEAD);
                mType = data.getStringExtra(Constant.INVOICETYPE);
                mDesc = data.getStringExtra(Constant.INVOICEDESC);
                if (TextUtils.equals(mHead, "不需要")) {
                    mTvInvoiceDesc.setText(mHead);
                } else {
                    mTvInvoiceDesc.setText(mType + "；" + mHead + "\n" + "类型：" + mDesc);
                }
            }

        }
        //收件人信息界面返回的数据
        if (requestCode == CheckoutCenterHomeActivity.REQUEST_ADDRESSINFO) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(Constant.TO_CHECKOUT_ADDINFO_NAME);
                String phoneNum = data.getStringExtra(Constant.TO_CHECKOUT_ADDINFO_PHONE);
                String add = data.getStringExtra(Constant.TO_CHECKOUT_ADDINFO_ADD);
                mAdd_id = data.getIntExtra(Constant.TO_CHECKOUT_ADDINFO_ID, 133);
                mTvArriverDesc.setText("姓名：" + name + "\n" + "电话号码：" + phoneNum + "\n" + "收货地址：" + add);
            }

        }
    }

    //送货方式对话框
    private void sendModeDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutCenterHomeActivity.this);
        builder.setTitle("请选择送货方式：");
        final String[] mode = new String[]{"送货上门", "自提"};
        builder.setItems(mode, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sendMode = mode[which];
                mTvSendmodeDesc.setText(sendMode);
                // Toast.makeText(CheckoutCenterHomeActivity.this, "您选择了：" + sendMode, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {
    }


}
