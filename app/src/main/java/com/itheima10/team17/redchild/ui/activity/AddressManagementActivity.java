package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.AddressListInfo;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.adapter.DefaultAddressAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.MListView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddressManagementActivity extends BaseActivity implements DefaultAddressAdapter.onNetRequestAdapter {

    private static final int REQUEST_ADD = 100;
    private static final int REQUEST_UPDATE = 101;
    private MListView mLvShowAddress;
    private DefaultAddressAdapter mAdapter;
    private Button mBtnAddNewAddress;
    private String user_id;
    private long token;
    private List<AddressListInfo.AddressListEntity> mAddressList;
    private Intent mIntent;
    private boolean mBooleanExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_management);
        mIntent = getIntent();
        mBooleanExtra = mIntent.getBooleanExtra(Constant.AAAAA, false);
        initView();//初始化控件
        initData();//初始化数据

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        mLvShowAddress = (MListView) findViewById(R.id.lv_amma_showaddress);
        mBtnAddNewAddress = (Button) findViewById(R.id.btn_amma_addnewAddress);
        mAddressList = new ArrayList<>();
        if (mBooleanExtra) {
            mLvShowAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AddressListInfo.AddressListEntity item = mAdapter.getItem(position);
                    Intent data = new Intent();
                    data.putExtra(Constant.TO_CHECKOUT_ADDINFO_NAME, item.getName());
                    data.putExtra(Constant.TO_CHECKOUT_ADDINFO_PHONE, item.getPhoneNumber());
                    data.putExtra(Constant.TO_CHECKOUT_ADDINFO_ADD, item.getAddressDetail());
                    data.putExtra(Constant.ADDRESS_ID, item.getId());
                    setResult(RESULT_OK, data);
                    finish();
                }
            });
        }
        mAdapter = new DefaultAddressAdapter(this, mAddressList);
        mAdapter.setOnnetRequestAdapter(this);
        //增加新地址点击事件
        mBtnAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressManagementActivity.this, AddNewAddressActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });

    }

    private void initData() {
        if (mUserInfo != null) {
            user_id = mUserCenterInfo.getUserInfo().getUser_id();
            token = mUserCenterInfo.getUserInfo().getToken();

            sMarketIo.getDefaultAddress(mUserInfo.getUser_id(), mUserInfo.getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addressListInfo1 -> {
                        if ("error ".equals(addressListInfo1.getResponse())) {
                            onError(addressListInfo1.getError());
                        } else {
                            if (mAddressList.size() != 0) {
                                addressListInfo1.getAddressList().get(0).isDefault = true;
                                mAddressList.addAll(addressListInfo1.getAddressList());
                            } else {
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }, Throwable::printStackTrace, () -> {
                        getDefaultAddress(mAddressList);
                    });
            sMarketIo.getAddressListinfo(mUserInfo.getUser_id(), mUserInfo.getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addressListInfo -> {
                        if ("error".equals(addressListInfo.getResponse())) {
                            onError(addressListInfo.getError());
                        } else {

                            mAddressList = addressListInfo.getAddressList();
                        }

                    }, Throwable::printStackTrace, () -> {
                        getDefaultAddress(mAddressList);
                    });
        } else {
            ToastUtil.show(this, "登录失效,请重新登录", ToastUtil.LENGTH_SHORT);
            finish();
        }
    }

    private void onError(ErrEntity.ErrorBean error) {
        ToastUtil.show(this, error.getMsg(), ToastUtil.LENGTH_SHORT);
    }

    private void getDefaultAddress(List<AddressListInfo.AddressListEntity> addressList) {

        if (mAdapter == null) {
            mAdapter = new DefaultAddressAdapter(AddressManagementActivity.this, addressList);
        } else {
            mAdapter.setData(addressList);
        }
        mLvShowAddress.setAdapter(mAdapter);
    }

    @Override
    public boolean addAddress() {

        return false;
    }

    @Override
    public boolean editDefault(int id) {
        sMarketIo.setModificationAddressInfo(id, token, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addressListInfo1 -> {
                    if ("error ".equals(addressListInfo1.getResponse())) {
                        onError(addressListInfo1.getError());
                    }
                }, Throwable::printStackTrace, () -> {
                    initData();
                });
        return false;
    }

    @Override
    public boolean delAddress(int id) {
        sMarketIo.deleteAddressInfo(id, token, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addressListInfo1 -> {
                    if ("error ".equals(addressListInfo1.getResponse())) {
                        onError(addressListInfo1.getError());
                    }
                }, Throwable::printStackTrace, () -> {
                    initData();
                });
        return false;
    }


}

