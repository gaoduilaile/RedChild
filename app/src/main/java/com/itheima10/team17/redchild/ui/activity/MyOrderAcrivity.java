package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.ui.fragment.CancelOrderFragment;
import com.itheima10.team17.redchild.ui.fragment.RecentFragment;
import com.itheima10.team17.redchild.ui.fragment.RemoteFragment;
import com.itheima10.team17.redchild.view.TitleBar;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

/**
 * Created by tao on 2016/6/16.
 */
public class MyOrderAcrivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private AdvancedPagerSlidingTabStrip mTabs;
    private TitleBar mTb;
    private ViewPager mVp;

    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THIRD = 2;
    private static final int VIEW_SIZE = 3;

    private RecentFragment mRecentFragment= null;
    private RemoteFragment mRemoteFragment = null;
    private CancelOrderFragment mCancelFragment= null;
    private int mPageId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        Intent intent = getIntent();
        mPageId = intent.getIntExtra("pageId", 0);
        //根据pageid显示不同fragment
        findView();
        initData();
        initListener();
    }

    private void initListener() {
        mTabs.setOnPageChangeListener(this);
        mTb.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void initData() {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(mPageId);
        mTabs.setViewPager(mVp);
        mTabs.setSelectItem(mPageId);
    }

    private void findView() {
        mTb = (TitleBar) findViewById(R.id.titlebar_myorder);
        mVp = (ViewPager) findViewById(R.id.vp_myorder);
        mTabs = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs_myorder);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_FIRST:
                        if (null == mRecentFragment)
                            mRecentFragment = RecentFragment.instance();
                        return mRecentFragment;

                    case VIEW_SECOND:
                        if (null == mRemoteFragment)
                            mRemoteFragment = RemoteFragment.instance();
                        return mRemoteFragment;

                    case VIEW_THIRD:
                        if (null == mCancelFragment)
                            mCancelFragment = CancelOrderFragment.instance();
                        return mCancelFragment;

                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_FIRST:
                        return "近一个月订单";
                    case VIEW_SECOND:
                        return "一个月前订单";
                    case VIEW_THIRD:
                        return "已取消的订单";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_FIRST:
                        return null;
                    case VIEW_SECOND:
                        return null;
                    case VIEW_THIRD:
                        return null;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Integer getPageSelectIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_FIRST:
                        return null;
                    case VIEW_SECOND:
                        return null;
                    case VIEW_THIRD:
                        return null;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }
    }
}
