package com.itheima10.team17.redchild.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.activity.CheckoutCenterHomeActivity;
import com.itheima10.team17.redchild.api.MarketApi;
import com.itheima10.team17.redchild.bean.CartListEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.activity.LauncherActivity;
import com.itheima10.team17.redchild.ui.activity.MainActivity;
import com.itheima10.team17.redchild.ui.adapter.CartAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, CartAdapter.onAllCheckedChangedListener, SwipeRefreshLayout.OnRefreshListener {


    private MainActivity mContext;
    private View mView;
    private RecyclerView mCartList;
    private ArrayList<CartListEntity.CartBean> mData;
    private CartAdapter mCartAdapter;
    private MarketApi myApi;
    private CheckBox mSelectAll;
    private TextView mTotalPrice;
    private TextView mItemCount;
    private Button mBtnLogin;
    private TextView mErrInfo;
    private View mEmptyView;
    private View mLoginyView;
    private View mCartInfo;
    private MarketApi marketService;
    private View mOrderSubmit;
    private int mProgress;
    private float mPayMoney;

    private SwipeRefreshLayout mSwipeRefresh;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mSwipeRefresh.setRefreshing(false);
            return false;
        }
    });
    private Button mEmptyCart;


    public CartFragment() {
    }

    @SuppressLint("ValidFragment")
    public CartFragment(MainActivity context) {
        mContext = context;
    }

    public static CartFragment instance() {
        CartFragment view = new CartFragment();
        return view;
    }

    public static CartFragment instance(MainActivity context) {
        CartFragment view = new CartFragment(context);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fourth, null);
        if (BaseActivity.mUserInfo == null) {

        }

        initView();
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (BuildConfig.DEBUG) Log.d("FirstFragment", "event.getX():" + event.getX());
                return true;
            }
        });
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSelectAll.setChecked(false);
        initData();
    }

    private void initView() {
        mSelectAll = (CheckBox) mView.findViewById(R.id.rb_cart_selectall);
        mCartList = (RecyclerView) mView.findViewById(R.id.lv_cart_list);
        mTotalPrice = (TextView) mView.findViewById(R.id.tv_cart_totalprice);
        mEmptyView = mView.findViewById(R.id.lv_cart_list_emptyview);
        mLoginyView = mView.findViewById(R.id.lv_cart_list_login);
        mCartInfo = mView.findViewById(R.id.rl_cart_info);
        mErrInfo = (TextView) mView.findViewById(R.id.tv_cart_errinfo);
        mItemCount = (TextView) mView.findViewById(R.id.tv_cart_totalproductnum);
        mBtnLogin = (Button) mView.findViewById(R.id.btn_cart_login);
        mEmptyCart = (Button) mView.findViewById(R.id.btn_cart_empty);
        mOrderSubmit = mView.findViewById(R.id.btn_cart_ordersubmit);
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.srl_cart_refresh);


        mSwipeRefresh.setOnRefreshListener(this);
        mOrderSubmit.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mEmptyCart.setOnClickListener(this);
        mSelectAll.setOnCheckedChangeListener(this);
        mSelectAll.setOnClickListener(this);
        mData = new ArrayList<CartListEntity.CartBean>();
        mCartAdapter = new CartAdapter(getActivity(), mData);
        mCartList.setLayoutManager(new LinearLayoutManager(mContext));
        mCartList.setAdapter(mCartAdapter);
        mCartAdapter.setOnAllCheckedChangedListener(this);

    }


    private void initData() {
        if (!mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(true);
        }
        mData.clear();
        mEmptyView.setVisibility(View.GONE);
        mCartInfo.setVisibility(View.VISIBLE);
        mCartList.setVisibility(View.VISIBLE);
        mLoginyView.setVisibility(View.GONE);

        mSwipeRefresh.setRefreshing(true);

        if (BaseActivity.mUserInfo == null) {
            mErrInfo.setText("尚未登陆,现在去登陆");
            mCartInfo.setVisibility(View.INVISIBLE);
            mCartList.setVisibility(View.INVISIBLE);
            mEmptyView.setVisibility(View.GONE);
            mLoginyView.setVisibility(View.VISIBLE);
        } else {
            sMarketIo.getCartList(BaseActivity.mUserInfo.getUser_id(), BaseActivity.mUserInfo.getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map(cartListEntity -> {
                        if (cartListEntity.getCart() != null) {
                            return cartListEntity.getCart();
                        } else {
                            switch (cartListEntity.getError().getCode()) {
                                case 0:
                                case -2:
                                    mErrInfo.setText("尚未登陆,现在去登陆");
                                    mCartInfo.setVisibility(View.INVISIBLE);
                                    mCartList.setVisibility(View.INVISIBLE);
                                    mEmptyView.setVisibility(View.GONE);
                                    mLoginyView.setVisibility(View.VISIBLE);
                                    break;
                                case -4:
                                    mErrInfo.setText("登陆超时,请重新登陆");
                                    mCartInfo.setVisibility(View.INVISIBLE);
                                    mCartList.setVisibility(View.INVISIBLE);
                                    mEmptyView.setVisibility(View.GONE);
                                    mLoginyView.setVisibility(View.VISIBLE);
                                    break;
                            }
                            if (BuildConfig.DEBUG)
                                Log.d("CartFragment", "获取异常:" + cartListEntity.getError().getMsg() + "Code:" + cartListEntity.getError().getCode());
                            return new ArrayList<CartListEntity.CartBean>();
                        }
                    })
                    .flatMap(Observable::from)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(cartBeen -> {
                        mData.add(cartBeen);
//                        if (mData.size() == 0) {
//                            mEmptyView.setVisibility(View.VISIBLE);
//                        }

                    }, Throwable::printStackTrace, () -> {
                        mCartAdapter.notifyDataSetChanged();
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    });
        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rb_cart_selectall:
                mCartAdapter.toggleAll(mSelectAll.isChecked());
                mCartAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_cart_login:
                startActivity(new Intent(getActivity(), LauncherActivity.class));
                break;
            case R.id.btn_cart_ordersubmit:
                Intent intent = new Intent(getActivity(), CheckoutCenterHomeActivity.class);
                List<CartListEntity.CartBean> selectedData = mCartAdapter.getSelectedData();
                if (selectedData.size() > 0) {
                    CartListEntity cartListEntity = new CartListEntity();
                    cartListEntity.setPayMoney(mPayMoney);
                    cartListEntity.setCart(selectedData);
                    intent.putExtra(Constant.FROM_CART_DATA, cartListEntity);
                    startActivity(intent);
                } else {
                    ToastUtil.show(mContext,"还没有添加购物车哟",ToastUtil.LENGTH_SHORT);
                }
                break;
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onChedkedChanged(boolean isChecked, float totalPrice, int totalCount) {
        mSelectAll.setChecked(isChecked);
        mItemCount.setText("(" + totalCount + ")");
        if (mContext != null) {
            mContext.showDot(totalCount);
        }
        mPayMoney = totalPrice;
        mTotalPrice.setText(DecimalFormatUtil.getDecimal(totalPrice));
    }

    @Override
    public void onTotalPriceChanged(float totalPrice, int totalCount) {
        mTotalPrice.setText(DecimalFormatUtil.getDecimal(totalPrice));
        mItemCount.setText("(" + totalCount + ")");
        if (mContext != null) {
            mContext.showDot(totalCount);
        }
        mPayMoney = totalPrice;
        mCartAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCountChanged(CartListEntity.CartBean cartBean) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRefresh() {
        initData();
        onTotalPriceChanged(0.0f, 0);
    }
}