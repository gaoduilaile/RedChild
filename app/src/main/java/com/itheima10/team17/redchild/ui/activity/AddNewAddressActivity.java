package com.itheima10.team17.redchild.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AddressEntity;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.adapter.AddressAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.itheima10.team17.redchild.view.sweetView.BlurEffect;
import com.itheima10.team17.redchild.view.sweetView.CustomDelegate;
import com.itheima10.team17.redchild.view.sweetView.SweetSheet;
import com.itheima10.team17.redchild.view.sweetView.SweetSheetWithBtn;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddNewAddressActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText mAddName;
    private EditText mAddPhone;
    private TextView mAddProvince;
    private TextView mAddCity;
    private TextView mAddCounty;
    private EditText mAddStreet;
    private Button mCaveAddress;
    private Intent mIntent;
    private int mAddressId;
    private RelativeLayout mFrame;
    private SweetSheetWithBtn mSweetSheet;
    private LiteOrm addressDb;
    private ListView mLv1;
    private ListView mLv2;
    private ListView mLv3;
    private ArrayList<AddressEntity.DataBean> mData1;
    private ArrayList<AddressEntity.DataBean> mData2;
    private ArrayList<AddressEntity.DataBean> mData3;
    private AddressAdapter mAdapter1;
    private AddressAdapter mAdapter2;
    private AddressAdapter mAdapter3;
    private TitleBar mTitleBar;
    private ScrollView mScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_amma_activity);
        mIntent = getIntent();
        mAddressId = mIntent.getIntExtra(Constant.ADDRESS_ID, 0);

        addressDb = LiteOrm.newSingleInstance(this, "city.db");
        initView();//初始化控件
    }

    private void initView() {
        mFrame = (RelativeLayout) findViewById(R.id.fl_addaddress_root);
        initPopup();
        mTitleBar = (TitleBar) findViewById(R.id.main_titlebar_addnewaddress);
        mAddName = (EditText) findViewById(R.id.tv_amma_addname);
        mAddPhone = (EditText) findViewById(R.id.tv_amma_addphone);
        mAddProvince = (TextView) findViewById(R.id.tv_amma_addprovince);
        mAddCity = (TextView) findViewById(R.id.tv_amma_addcity);
        mAddCounty = (TextView) findViewById(R.id.tv_amma_addcounty);
        mAddStreet = (EditText) findViewById(R.id.tv_amma_addstreet);
        mCaveAddress = (Button) findViewById(R.id.btn_caveaddress);
        mScroll = (ScrollView) findViewById(R.id.sv_amma);

        mAddProvince.setOnClickListener(this);
        mAddCity.setOnClickListener(this);
        mAddCounty.setOnClickListener(this);


        mAddCity.setOnClickListener(this);

        mCaveAddress.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //请求数据库保存数据
                        String mUserName = mAddName.getText().toString().trim();
                        String mUserPhone = mAddPhone.getText().toString().trim();
                        String mCity1 = mAddCity.getText().toString().trim();
                        String mProvince1 = mAddProvince.getText().toString().trim();
                        String mDistrict1 = mAddCounty.getText().toString().trim();
                        String mAddStreet1 = mAddStreet.getText().toString().trim();
                        if (!TextUtils.isEmpty(mUserName) && !TextUtils.isEmpty(mUserPhone) && !TextUtils.isEmpty(mCity1) && !TextUtils.isEmpty(mAddStreet1)) {


                           // sMarketIo.addAddress(0, 1466240833796l, 14662408201880, mUserName, mUserPhone, mCity1, mProvince1, mDistrict1, mAddStreet1, "110110")
                            sMarketIo.addAddress(mAddressId, Long.valueOf(mUserInfo.getToken()), mUserInfo.getUser_id(), mUserName, mUserPhone, mCity1, mProvince1, mDistrict1, mAddStreet1, "110110")
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(addNewAddress -> {
                                        if ("addresssave".equals(addNewAddress.getResponse())) {
                                            finish();
                                        } else {
                                            if (BuildConfig.DEBUG)
                                                Log.d("AddNewAddressActivity", "addNewAddress:" + addNewAddress);
                                            onError(addNewAddress.getError());
                                        }
                                    }, Throwable::printStackTrace, () -> {
                                    });
                        } else {
                            ToastUtil.show(AddNewAddressActivity.this, "请把信息填完喔", ToastUtil.LENGTH_SHORT);
                        }
                    }
                }

        );
    }

    private void initPopup() {
        // SweetSheet 控件,根据 rl 确认位置
        mSweetSheet = new SweetSheetWithBtn(mFrame);
//定义一个 CustomDelegate 的 Delegate ,并且设置它的出现动画.
        CustomDelegate customDelegate = new CustomDelegate(true, CustomDelegate.AnimationType.DuangLayoutAnimation);
        View view = LayoutInflater.from(this).inflate(R.layout.sweetsheet_window, null, false);
        customDelegate.setCustomView(view);
        mSweetSheet.setDelegate(customDelegate);
        mSweetSheet.setBackgroundEffect(new BlurEffect(15));
        mSweetSheet.setBackgroundClickEnable(true);
        mSweetSheet.setOnbacgroundClickListener(new SweetSheet.OnbacgroundListener() {
            @Override
            public boolean onBackgroundClick() {
                return true;
            }

            @Override
            public void onBackgroundShow() {
                mScroll.setVisibility(View.INVISIBLE);
                mData1 = addressDb.query(new QueryBuilder<>(AddressEntity.DataBean.class).groupBy("prov"));
                if (BuildConfig.DEBUG)
                    mAdapter1 = new AddressAdapter(AddNewAddressActivity.this, mData1, 0);
                mAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onBackgroundDismiss() {
                mScroll.setVisibility(View.VISIBLE);
            }
        });

//        CityPicker viewById = (CityPicker) view.findViewById(R.id.city_picker);
//        viewById.setOnSelectingListener(new CityPicker.OnSelectingListener() {
//            @Override
//            public void selected(boolean selected) {
//                System.out.println("selected: " + selected);
//                System.out.println("selected_code: " + viewById.getCity_code_string());
//                System.out.println("selected_str: " + viewById.getCity_string());
//            }
//        });

        mLv1 = (ListView) view.findViewById(R.id.lv1);
        mLv2 = (ListView) view.findViewById(R.id.lv2);
        mLv3 = (ListView) view.findViewById(R.id.lv3);
        mData1 = new ArrayList<AddressEntity.DataBean>();
        mData2 = new ArrayList<AddressEntity.DataBean>();
        mData3 = new ArrayList<AddressEntity.DataBean>();
        mAdapter1 = new AddressAdapter(this, mData1, 0);
        mAdapter2 = new AddressAdapter(this, mData2, 1);
        mAdapter3 = new AddressAdapter(this, mData3, 2);

        mLv1.setAdapter(mAdapter1);
        mLv1.setOnItemClickListener(this);
        mLv2.setAdapter(mAdapter2);
        mLv2.setOnItemClickListener(this);
        mLv3.setAdapter(mAdapter3);
        mLv3.setOnItemClickListener(this);
    }


    private void onError(ErrEntity.ErrorBean error) {
        if (BuildConfig.DEBUG) Log.d("AddNewAddressActivity", "error.getCode():" + error.getCode());
        ToastUtil.show(this, error.getMsg(), ToastUtil.LENGTH_SHORT);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_amma_addprovince:
                mData1.clear();
                mData1.addAll(addressDb.query(new QueryBuilder<>(AddressEntity.DataBean.class).groupBy("prov")));
                mLv1.setAdapter(mAdapter1);
                mAdapter1.notifyDataSetChanged();
                hideInput(this,v);
                mSweetSheet.show();
                break;
        }
    }

    private void hideInput(Context context,View view){
        InputMethodManager inputMethodManager =
                (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv1:
//                mData1 = addressDb.query(new QueryBuilder<>(AddressEntity.DataBean.class).groupBy("prov"));
                mData3.clear();
                mData2.clear();
                mAdapter1.setSelected(position);
                mData2.addAll(addressDb.query(new QueryBuilder<>(AddressEntity.DataBean.class).where("prov=?", new Object[]{mAdapter1.getItem(position).getProv()}).groupBy("city")));
                mAdapter3.notifyDataSetChanged();
                mAdapter2.notifyDataSetChanged();
                mAdapter1.notifyDataSetChanged();
                break;
            case R.id.lv2:
                mAdapter2.setSelected(position);
                mData3.clear();
                mData3.addAll(addressDb.query(new QueryBuilder<>(AddressEntity.DataBean.class).where("city=?", new Object[]{mAdapter2.getItem(position).getCity()}).groupBy("district")));
                mAdapter3.notifyDataSetChanged();
                break;
            case R.id.lv3:
                AddressEntity.DataBean item = mAdapter3.getItem(position);
                mAdapter3.setSelected(position);
                mAddProvince.setText(item.getProv());
                mAddCity.setText(item.getCity());
                mAddCounty.setText(item.getDistrict());
                mSweetSheet.dismiss();
                break;

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mSweetSheet.isShow()) {
                mSweetSheet.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}