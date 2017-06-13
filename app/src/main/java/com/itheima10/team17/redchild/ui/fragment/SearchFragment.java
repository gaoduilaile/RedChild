package com.itheima10.team17.redchild.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.HotSearch;
import com.itheima10.team17.redchild.bean.SearchHistroy;
import com.itheima10.team17.redchild.dao.SearchSQLdao;
import com.itheima10.team17.redchild.ui.activity.SearchResultActivity;
import com.itheima10.team17.redchild.view.SearchListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener, SearchListView.onCleanListener {

    private PopupWindow mPw;
    private SearchListView mSearchHistroy;
    private EditText mEtShow;
    private Button mBtnQuery;
    private HotSearchAdapter mHotSearchAdapter;
    private GridView mGvHotSearch;
    private List<SearchHistroy> mSearchHistroysData;
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private ImageView mIvAnim;
    private TextView mTv;
    private LinearLayout mLs;
    private AnimationDrawable mAnimation;

    public static SearchFragment instance() {
        SearchFragment mSearchFragment = new SearchFragment();
        return mSearchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        initView(view);//初始化控件
        initData();    //初始化数据
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();    //初始化数据
    }

    private void initData() {
        initPopUpWindow();  //搜索历史弹窗

        //初始化热门搜索
        Call<HotSearch> callHotSearch = sMarketIo.getHotSearch();
        callHotSearch.enqueue(new Callback<HotSearch>() {
            @Override
            public void onResponse(Call<HotSearch> call, Response<HotSearch> response) {
                //retrofit,可以在这里执行更新UI操作
                List<String> mHotSearchKeyData = response.body().getSearchKeywords();
                getSearchKeyData(mHotSearchKeyData);
            }

            @Override
            public void onFailure(Call<HotSearch> call, Throwable t) {
            }
        });

      /*  if (mSearchHistoryAdapter == null) {
            mSearchHistoryAdapter = new SearchHistoryAdapter(getContext(), mSearchHistroysData);
            mSearchHistroy.setAdapter(mSearchHistoryAdapter);
        }

        mSearchHistoryAdapter.notifyDataSetChanged();*/

        mSearchHistoryAdapter = new SearchHistoryAdapter(getContext(), mSearchHistroysData);
        mSearchHistroy.setAdapter(mSearchHistoryAdapter);
    }

    @Override
    public void cleanHistory() {
        //操作清除历史数据
        SearchSQLdao searchSQLdao = new SearchSQLdao(getContext());
        searchSQLdao.delete();
        //刷新
        mSearchHistroysData.clear();
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    class SearchHistoryAdapter extends BaseAdapter {

        public Context mContext;
        public List<SearchHistroy> mSearchHistroysData;

        public SearchHistoryAdapter(Context mContext, List<SearchHistroy> mSearchHistroysData) {
            this.mContext = mContext;
            this.mSearchHistroysData = mSearchHistroysData;
        }

        @Override
        public int getCount() {
            return mSearchHistroysData.size();
        }

        @Override
        public Object getItem(int position) {
            return mSearchHistroysData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.search_history_item, null);
            TextView mTvHotSearchItem = (TextView) view.findViewById(R.id.tv_searchframent_searchhistory);
            //判断是否为集合的最后一个元素，如果是就设为清空历史数据
            SearchHistroy searchHistroy = mSearchHistroysData.get(position);
            mTvHotSearchItem.setText(searchHistroy.keyword);
            //item监听事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView mTvSearchHistory = (TextView) v.findViewById(R.id.tv_searchframent_searchhistory);
                    String mSearchHistoryDatas = mTvSearchHistory.getText().toString().trim();
                    mEtShow.setText(mSearchHistoryDatas);
                    //执行搜索
                    searchDatas();
                }
            });
            return view;
        }
    }

    private void initPopUpWindow() {
        View view = View.inflate(getContext(), R.layout.search_history, null);
        mPw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mSearchHistroy = (SearchListView) view.findViewById(R.id.lv_searchfragment_histroy);
        mSearchHistroy.setOnCleanListener(this);
    }

    private void initView(View view) {
        mEtShow = (EditText) view.findViewById(R.id.et_searchfragment_show);//搜索编辑框
        mBtnQuery = (Button) view.findViewById(R.id.btn_searchfragment_query);//搜索图标
        mGvHotSearch = (GridView) view.findViewById(R.id.gv_searchfragment_hotsearch);//热搜
        mIvAnim = (ImageView) view.findViewById(R.id.iv_searchfragment_anim);
        mTv = (TextView) view.findViewById(R.id.tv_searchfragment_title1);
        mLs = (LinearLayout) view.findViewById(R.id.ll_searchfragment);
        mAnimation = (AnimationDrawable) mIvAnim.getDrawable();
        //设置点击事件
        mEtShow.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);

        //初始化历史搜索
        SearchSQLdao searchSQLdao = new SearchSQLdao(getContext());
        mSearchHistroysData = searchSQLdao.queryAll();

    }

    //获取数据库的数据,初始化推荐搜索数据
    private void getSearchKeyData(List<String> mSearchKeyData) {
        if (mSearchKeyData != null) {

                mHotSearchAdapter = new HotSearchAdapter(getContext(), mSearchKeyData);
                mGvHotSearch.setAdapter(mHotSearchAdapter);

                mHotSearchAdapter.notifyDataSetChanged();

        }
    }

    class HotSearchAdapter extends BaseAdapter {

        public Context mContext;
        public List<String> mSearchKeyData;

        public HotSearchAdapter(Context mContext, List<String> mSearchKeyData) {
            this.mContext = mContext;
            this.mSearchKeyData = mSearchKeyData;
        }

        @Override
        public int getCount() {
            return mSearchKeyData.size();
        }

        @Override
        public Object getItem(int position) {
            return mSearchKeyData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(mContext, R.layout.search_hotsearch_item, null);
            TextView mTvHotSearchItem = (TextView) view.findViewById(R.id.tv_searchfragment_hotsearch_item);
            String searchKeywords = mSearchKeyData.get(position);
            mTvHotSearchItem.setText(searchKeywords);
            //设置条目点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView mTvHotSearchItem = (TextView) v.findViewById(R.id.tv_searchfragment_hotsearch_item);
                    String hotSearchitemdata = mTvHotSearchItem.getText().toString().trim();
                    mEtShow.setText(hotSearchitemdata);

                    //搜索数据
                    mLs.setVisibility(View.VISIBLE);
                    //重新开始播放
                    mAnimation.start();

                    new Thread(new Runnable() {

                        private String searchKeyword;

                        @Override
                        public void run() {
                            SystemClock.sleep(2000);
                            //1、获取文本框中搜索关键字
                            searchKeyword = mEtShow.getText().toString().trim();
                            if (searchKeyword == null) {
                                Toast.makeText(getContext(), "搜索条件不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAnimation.stop();
                                    mLs.setVisibility(View.GONE);

                                    //2、将搜索关键字传递给搜索结果界面
                                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                                    intent.putExtra("searchKeyword", searchKeyword);
                                    //3、跳转到搜索界面
                                    startActivity(intent);
                                }
                            });
                        }

                    }).start();
                    //searchDatas();
                }
            });
            return view;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_searchfragment_show:
                //显示历史数据
                mPw.showAsDropDown(mEtShow);
                break;
            case R.id.btn_searchfragment_query:
                //搜索数据
                mLs.setVisibility(View.VISIBLE);
                //重新开始播放
                mAnimation.start();
                new Thread(new Runnable() {

                    private String searchKeyword;

                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        //1、获取文本框中搜索关键字
                        searchKeyword = mEtShow.getText().toString().trim();
                        SearchSQLdao searchSQLdao = new SearchSQLdao(getContext());
                        searchSQLdao.insert(searchKeyword);

                        if (searchKeyword == null) {
                            Toast.makeText(getContext(), "搜索条件不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAnimation.stop();
                                mLs.setVisibility(View.GONE);

                                //2、将搜索关键字传递给搜索结果界面
                                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                                intent.putExtra("searchKeyword", searchKeyword);
                                //3、跳转到搜索界面
                                startActivity(intent);
                            }
                        });
                    }

                }).start();
                break;
        }
    }

    private void searchDatas() {
        //1、获取文本框中搜索关键字
        String searchKeyword = mEtShow.getText().toString().trim();
        if (searchKeyword == null) {
            Toast.makeText(getContext(), "搜索条件不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            SearchSQLdao searchSQLdao = new SearchSQLdao(getContext());
            searchSQLdao.insert(searchKeyword);
            //2、将搜索关键字传递给搜索结果界面
            Intent intent = new Intent(getContext(), SearchResultActivity.class);
            intent.putExtra("searchKeyword", searchKeyword);
            //3、跳转到搜索界面
            startActivity(intent);
        }
    }
}