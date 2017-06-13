package com.itheima10.team17.redchild.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.ui.fragment.CartFragment;
import com.itheima10.team17.redchild.ui.fragment.CategoryFragment;
import com.itheima10.team17.redchild.ui.fragment.FifthFragment;
import com.itheima10.team17.redchild.ui.fragment.FirstFragment;
import com.itheima10.team17.redchild.ui.fragment.SearchFragment;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private FrameLayout mMFragment;
    private ViewPager mPager;
    private AdvancedPagerSlidingTabStrip mTabs;

    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THIRD = 2;
    private static final int VIEW_FOURTH = 3;
    private static final int VIEW_FIFTH = 4;
    private static final int VIEW_SIZE = 5;
    private long exitTime = 0;

    private FirstFragment mFirstFragment = null;
    private SearchFragment mSecondFragment = null;
    private CategoryFragment mThirdFragment = null;
    private CartFragment mCartFragment = null;
    private FifthFragment mFifthFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        findView();
        initView();
        setupUmeng();
        int jump_to = intent.getIntExtra("JUMP_TO", -1);
        if (jump_to != -1) {
            jumpTo(jump_to);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void findView() {
        mPager = (ViewPager) findViewById(R.id.vp_pager);
        mTabs = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
    }


    private void setupUmeng() {
        UmengUpdateAgent.update(this);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
    }

    private void initView() {
        //跳转到指定指定页码
        Intent intent = getIntent();
        int pageId = intent.getIntExtra(Constant.PAGE_ID, 0);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(pageId);
        mTabs.setViewPager(mPager);
        mTabs.setSelectItem(pageId);
        mTabs.setOnPageChangeListener(this);
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

    public void showDot(int num) {
        mTabs.showDot(VIEW_FOURTH, String.valueOf(num));
    }

    public void jumpTo(int num) {
        mPager.setCurrentItem(num, true);
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
                        if (null == mFirstFragment)
                            mFirstFragment = FirstFragment.instance(MainActivity.this);
                        return mFirstFragment;

                    case VIEW_SECOND:
                        if (null == mSecondFragment)
                            mSecondFragment = SearchFragment.instance();
                        return mSecondFragment;

                    case VIEW_THIRD:
                        if (null == mThirdFragment)
                            mThirdFragment = CategoryFragment.instance();
                        return mThirdFragment;

                    case VIEW_FOURTH:
                        if (null == mCartFragment)
                            mCartFragment = CartFragment.instance(MainActivity.this);
                        return mCartFragment;
                    case VIEW_FIFTH:
                        if (null == mFifthFragment)
                            mFifthFragment = FifthFragment.instance();
                        return mFifthFragment;
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
                        return "首页";
                    case VIEW_SECOND:
                        return "搜索";
                    case VIEW_THIRD:
                        return "品牌";
                    case VIEW_FOURTH:
                        return "购物车";
                    case VIEW_FIFTH:
                        return "更多";
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
                        return R.mipmap.bar_home_normal;
                    case VIEW_SECOND:
                        return R.mipmap.bar_search_normal;
                    case VIEW_THIRD:
                        return R.mipmap.bar_class_normal;
                    case VIEW_FOURTH:
                        return R.mipmap.bar_shopping_normal;
                    case VIEW_FIFTH:
                        return R.mipmap.bar_more_normal;
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
                        return R.mipmap.bar_home_selected;
                    case VIEW_SECOND:
                        return R.mipmap.bar_search_selected;
                    case VIEW_THIRD:
                        return R.mipmap.bar_class_selected;
                    case VIEW_FOURTH:
                        return R.mipmap.bar_shopping_selected;
                    case VIEW_FIFTH:
                        return R.mipmap.bar_more_selected;
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


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int jump_to = intent.getIntExtra("JUMP_TO", -1);
        if (jump_to != -1) {
            jumpTo(jump_to);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.show(getApplicationContext(), "再按一次退出程序", ToastUtil.LENGTH_SHORT);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.JUMP_TO) {
            jumpTo(data.getIntExtra("JUMP_TO", 0));
        }
    }
}
