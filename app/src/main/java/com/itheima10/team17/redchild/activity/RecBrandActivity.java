package com.itheima10.team17.redchild.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.RecBrandBean;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 刘国徽 on 2016/6/18.
 */
public class RecBrandActivity extends BaseActivity implements TitleBar.OnBtnClickListener, ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    private ExpandableListView mElv;
    private RecBrandAdapter    mRecBrandAdapter;
    private TitleBar           mTitleBar;
    private int mExpandPosion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recbrand);
        initView();
        initData();
    }

    private void initData() {
        //获取数据
        Call<RecBrandBean> call = sMarketIo.getBrandListData();
        call.enqueue(new Callback<RecBrandBean>() {
            @Override
            public void onResponse(Call<RecBrandBean> call, Response<RecBrandBean> response) {
                List<RecBrandBean.BrandBean> brandBeen = response.body().getBrand();
                getBrandData(brandBeen);
            }

            @Override
            public void onFailure(Call<RecBrandBean> call, Throwable t) {

            }
        });

    }

    private void getBrandData(List<RecBrandBean.BrandBean> brandBeen) {
        if (mRecBrandAdapter == null) {
            mRecBrandAdapter = new RecBrandAdapter(RecBrandActivity.this, brandBeen);
            mElv.setAdapter(mRecBrandAdapter);
        } else {
            mRecBrandAdapter.notifyDataSetChanged();
        }
    }


    public void initView() {
        mElv = (ExpandableListView) findViewById(R.id.recbrand_elv);
        mTitleBar = (TitleBar) findViewById(R.id.main_titlebar);
        mTitleBar.setOnBtnClickListener(this);
        mElv.setOnGroupClickListener(this);
        mElv.setOnChildClickListener(this);
    }

    //标题栏左右点击事件
    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    //点击一级条目的点击事件
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        //判断之前展开的条目
        if (mExpandPosion != groupPosition){
            mElv.collapseGroup(mExpandPosion);
        }
        //点击的时候是否展开折叠
        if (mElv.isGroupExpanded(groupPosition)) {
            mElv.collapseGroup(groupPosition);
        } else {
            mElv.expandGroup(groupPosition);
            //记录展开的位置
            mExpandPosion = groupPosition;
        }
        return true;
    }
//点击二级条目的点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        //跳转到商品详情


        return true;
    }


    public class RecBrandAdapter extends BaseExpandableListAdapter {
        private Context                      mContext;
        private List<RecBrandBean.BrandBean> mRecBrandBean;

        public RecBrandAdapter(Context context, List<RecBrandBean.BrandBean> recBrandBean) {
            mContext = context;
            mRecBrandBean = recBrandBean;
        }

        @Override
        public int getGroupCount() {

            return mRecBrandBean.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mRecBrandBean.get(groupPosition).getValue().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mRecBrandBean.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mRecBrandBean.get(groupPosition).getValue().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        //获取某一组
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.activity_recbrand_parent, null);
            }
            TextView tv = ViewHolderUtil.getView(convertView, R.id.activity_recbrand_brand);
            tv.setText(mRecBrandBean.get(groupPosition).getKey());
            return convertView;
        }

        //某一组的子条目
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.activity_recbrand_child, null);
            }
            TextView name = ViewHolderUtil.getView(convertView, R.id.activity_recbrand_child_tv);
            ImageView pic = ViewHolderUtil.getView(convertView, R.id.activity_recbrand_child_iv);
            name.setText(mRecBrandBean.get(groupPosition).getValue().get(childPosition).getName());
            Picasso.with(RecBrandActivity.this).load(UrlConstant.MARKET_URL + mRecBrandBean.get(groupPosition).getValue().get(childPosition).getPic()).into(pic);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {

            return true;
        }
    }
}
