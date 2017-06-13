package com.itheima10.team17.redchild.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.More1GridItemBean;
import com.itheima10.team17.redchild.bean.MoreGridItemBean;
import com.itheima10.team17.redchild.ui.activity.AboutAppActivity;
import com.itheima10.team17.redchild.ui.activity.AddressManagementActivity;
import com.itheima10.team17.redchild.ui.activity.CouponActivity;
import com.itheima10.team17.redchild.ui.activity.CustomerServiceActivity;
import com.itheima10.team17.redchild.ui.activity.FavoriteActivity;
import com.itheima10.team17.redchild.ui.activity.FeedBackActivity;
import com.itheima10.team17.redchild.ui.activity.GiftActivity;
import com.itheima10.team17.redchild.ui.activity.HelpCenterActivity;
import com.itheima10.team17.redchild.ui.activity.LauncherActivity;
import com.itheima10.team17.redchild.ui.activity.MyOrderAcrivity;
import com.itheima10.team17.redchild.ui.activity.UserCenterActivity;
import com.itheima10.team17.redchild.ui.adapter.MoreAdapter;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.CuttingLineGridView;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;
import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class FifthFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";

    private LinearLayout        mLl_launcher;
    private RelativeLayout      mRl_order;
    private RelativeLayout      mRl_assets;
    private CuttingLineGridView mGv;
    private GridView            mGvAssets;
    private GridView            mGvOrder;
    private String[] mItemNames  = new String[]{"足迹", "地址管理", "帮助中心", "用户反馈", "咨询客服", "关于"};
    private int[]    mImageResId = new int[]{R.mipmap.foot1, R.mipmap.foot2, R.mipmap.foot3, R.mipmap.foot4, R.mipmap.foot5, R.mipmap.foot6};
    private String[] mItemOrder  = new String[]{"一个月内订单", "一个月前订单", "已取消的订单"};
    private String[] mItemAssets = new String[]{"收藏夹", "优惠券", "礼品卡"};
    private List<More1GridItemBean> mItemInfos1;
    private List<MoreGridItemBean>  mItemOrderInfos;
    private List<MoreGridItemBean>  mItemAssetsInfos;
    private More1Adapter            mMore1Adapter;
    private MoreAdapter             mMoreOrderAdapter;
    private MoreAdapter             mMoreAssetsAdapter;
    private TextView                mTv_order_num;
    private TextView                mTv_login_name;
    private ImageView               mIv_user_icon;

    public static FifthFragment instance() {
        FifthFragment view = new FifthFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fifth, null);
        findView(view);
        initData();
        initListener();
        return view;
    }

    /**
     * 每次onResume判断登录状态,回显数据
     */
    @Override
    public void onResume() {
        super.onResume();
        if (BaseActivity.mUserInfo != null) {
            mTv_login_name.setText(BaseActivity.mUserInfo.getUsername());
            mTv_order_num.setText(BaseActivity.mUserCenterInfo.getUserInfo().getOrderCount() + "");
            mIv_user_icon.setBackgroundResource(R.mipmap.tou);
        } else {
            mTv_login_name.setText("点击登录");
            mTv_order_num.setText("");
            mIv_user_icon.setBackgroundResource(R.mipmap.user_icon);
        }
    }

    private void initListener() {
        mLl_launcher.setOnClickListener(this);
        mRl_order.setOnClickListener(this);
        mRl_assets.setOnClickListener(this);
        mGv.setOnItemClickListener(this);
        mGvOrder.setOnItemClickListener(this);
        mGvAssets.setOnItemClickListener(this);

        mGv.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
                mGv.stopEditMode();
            }
        });

        mGv.setOnDragListener(new DynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {
                Log.d(TAG, "drag started at position " + position);
            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {
                //对集合重新排序

            }
        });
        mGv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mGv.startEditMode(position);
                return true;
            }
        });
    }

    private void initData() {
        mItemInfos1 = new ArrayList<>();
        for (int i = 0; i < mItemNames.length; i++) {
            More1GridItemBean gridItemBean1 = new More1GridItemBean();
            gridItemBean1.setName(mItemNames[i]);
            gridItemBean1.setImageResId(mImageResId[i]);
            gridItemBean1.setOriginalId(i);
            mItemInfos1.add(gridItemBean1);
        }
        mMore1Adapter = new More1Adapter(getContext(), mItemInfos1, 3);
        mGv.setAdapter(mMore1Adapter);

        initGridItemData(mItemOrderInfos, mItemOrder, mMoreOrderAdapter, mGvOrder);
        initGridItemData(mItemAssetsInfos, mItemAssets, mMoreAssetsAdapter, mGvAssets);
    }

    private void initGridItemData(List itemInfos, String[] itemNames, MoreAdapter moreAdapter, GridView gv) {
        itemInfos = new ArrayList<>();
        for (int i = 0; i < itemNames.length; i++) {
            MoreGridItemBean gridItemBean = new MoreGridItemBean();
            gridItemBean.setName(itemNames[i]);
            itemInfos.add(gridItemBean);
        }
        moreAdapter = new MoreAdapter(getContext(), itemInfos);
        gv.setAdapter(moreAdapter);
    }

    private void findView(View view) {
        mLl_launcher = (LinearLayout) view.findViewById(R.id.ll_more_launcher);
        mRl_order = (RelativeLayout) view.findViewById(R.id.rl_more_order);
        mRl_assets = (RelativeLayout) view.findViewById(R.id.rl_more_assets);
        mGvAssets = (GridView) view.findViewById(R.id.gv_assets);
        mGvOrder = (GridView) view.findViewById(R.id.gv_order);
        mGv = (CuttingLineGridView) view.findViewById(R.id.gv_more);
        mTv_order_num = (TextView) view.findViewById(R.id.tv_more_all_order_num);
        mTv_login_name = (TextView) view.findViewById(R.id.tv_more_login_name);
        mIv_user_icon = (ImageView) view.findViewById(R.id.iv_more_user_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_more_launcher:
                if (BaseActivity.mUserInfo == null) {
                    //进入登录界面
                    startActivity(new Intent(getContext(), LauncherActivity.class));
                } else {
                    //进入账户中心界面
                    startActivity(new Intent(getContext(), UserCenterActivity.class));
                }
                break;
            case R.id.rl_more_order:
                if (BaseActivity.mUserInfo == null) {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("")
                            .setContentText("当前未登录,是否登录")
                            .setCancelText("取消")
                            .setConfirmText("确定")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    startActivity(new Intent(getContext(), LauncherActivity.class));
                                    sDialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    //进入我的订单
                    startActivity(new Intent(getContext(), MyOrderAcrivity.class));
                }
                break;
            case R.id.rl_more_assets:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gv_more:
                //取出原始id
                More1GridItemBean gridItemBean1 = (More1GridItemBean) parent.getAdapter().getItem(position);
                position = gridItemBean1.getOriginalId();
                switch (position) {
                    case 0:
                        if (BaseActivity.mUserInfo != null) {
                            Toast.makeText(getContext(), "足迹", Toast.LENGTH_SHORT).show();
                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        break;
                    case 1:
                        if (BaseActivity.mUserInfo != null) {

                            //ASUS-FWQ 修改Intent
                            Intent intent = new Intent(getContext(), AddressManagementActivity.class);
                            startActivity(intent);

                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), HelpCenterActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), FeedBackActivity.class));
                        break;
                    case 4:
                        /*Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + 95599));*/
                        Intent intent = new Intent(getContext(),CustomerServiceActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        startActivity(new Intent(getContext(), AboutAppActivity.class));
                        break;
                    default:
                        break;
                }
                break;
            case R.id.gv_order:
                Intent intent;
                switch (position) {
                    case 0:
                        if (BaseActivity.mUserInfo == null) {
                            //进入登录界面
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        } else {
                            //进入我的订单
                            intent = new Intent(getContext(), MyOrderAcrivity.class);
                            intent.putExtra("pageId", 0);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        if (BaseActivity.mUserInfo == null) {
                            //进入登录界面
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        } else {
                            //进入我的订单
                            intent = new Intent(getContext(), MyOrderAcrivity.class);
                            intent.putExtra("pageId", 1);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (BaseActivity.mUserInfo == null) {
                            //进入登录界面
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        } else {
                            //进入我的订单
                            intent = new Intent(getContext(), MyOrderAcrivity.class);
                            intent.putExtra("pageId", 2);
                            startActivity(intent);
                        }
                    default:
                        break;
                }
                break;
            case R.id.gv_assets:
                switch (position) {
                    case 0:
                        if (BaseActivity.mUserInfo != null) {
                            startActivity(new Intent(getContext(), FavoriteActivity.class));
                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        break;
                    case 1:
                        if (BaseActivity.mUserInfo != null) {
                            startActivity(new Intent(getContext(), CouponActivity.class));
                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        break;
                    case 2:
                        if (BaseActivity.mUserInfo != null) {
                            startActivity(new Intent(getContext(), GiftActivity.class));
                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("")
                                    .setContentText("当前未登录,是否登录")
                                    .setCancelText("取消")
                                    .setConfirmText("确定")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            startActivity(new Intent(getContext(), LauncherActivity.class));
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    class More1Adapter extends BaseDynamicGridAdapter {


        public More1Adapter(Context context, List<?> items, int columnCount) {
            super(context, items, columnCount);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //More1GridItemBean more1GridItemBean = mItemInfos1.get(position);
            More1GridItemBean more1GridItemBean = (More1GridItemBean) getItem(position);
            CheeseViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_more1_gridview, null);
                holder = new CheeseViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (CheeseViewHolder) convertView.getTag();
            }
            holder.build(more1GridItemBean.getName(), more1GridItemBean.getImageResId());
            return convertView;
        }
    }

    private class CheeseViewHolder {
        private TextView  titleText;
        private ImageView image;

        private CheeseViewHolder(View view) {
            titleText = (TextView) view.findViewById(R.id.tv_more1_grid_item);
            image = (ImageView) view.findViewById(R.id.iv_more1_grid_item);
        }

        void build(String title, int resId) {
            titleText.setText(title);
            image.setImageResource(resId);
        }
    }
}