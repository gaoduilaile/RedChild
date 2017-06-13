package com.itheima10.team17.redchild.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.activity.HotProductActivity;
import com.itheima10.team17.redchild.activity.LimitBuyAvtivity;
import com.itheima10.team17.redchild.activity.RecBrandActivity;
import com.itheima10.team17.redchild.activity.SalesPromotionActivity;
import com.itheima10.team17.redchild.bean.HomeEntity;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.MainActivity;
import com.itheima10.team17.redchild.ui.adapter.ListItemAdapter;
import com.itheima10.team17.redchild.ui.anim.DepthPageTransformer;
import com.squareup.picasso.Picasso;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class FirstFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ConvenientBanner mConvenientBanner;
    private ListView         mListView;
    private int[]    itemImageRes = {R.mipmap.home_classify_01, R.mipmap.home_classify_02, R.mipmap.home_classify_03, R.mipmap.home_classify_04, R.mipmap.home_classify_05,};
    private String[] itemNews     = {"限时抢购", "促销快报", "新品上架", "热门单品", "推荐品牌"};
    private LinearLayout mLinearLayout;
    private HomeEntity   mData;

    private View         mView;
    private MainActivity mContext;
    private Button mButton;
    private TextView mTextView;


    public FirstFragment() {
    }

    @SuppressLint("ValidFragment")
    public FirstFragment(MainActivity context) {
        mContext = context;
    }

    public static FirstFragment instance() {
        FirstFragment views = new FirstFragment();
        return views;
    }

    public static FirstFragment instance(MainActivity context) {
        FirstFragment view = new FirstFragment(context);
        return view;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_first, container, false);
        initView(mView);
        initData(mView);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(mView);
        initData(mView);
    }

    private void initData(View view) {

        sMarketIo.getHome().map(HomeEntity::getHomeTopic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(homeTopicBeen -> {
                    mConvenientBanner.setPages(
                            new CBViewHolderCreator<LocalImageHolderView>() {
                                @Override
                                public LocalImageHolderView createHolder() {
                                    return new LocalImageHolderView();
                                }
                            }, homeTopicBeen)
                            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                            .setPageIndicator(new int[]{R.drawable.home_point_normal, R.drawable.home_point_pressed}).setPageTransformer(new DepthPageTransformer());
                    mListView.setAdapter(new ListItemAdapter(mContext, itemImageRes, itemNews));
                }, Throwable::printStackTrace, () -> {
                    mConvenientBanner.startTurning(2000);
                });
        //设置条目点击事件
        mListView.setOnItemClickListener(this);
    }

    private void initView(View view) {
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.first_frag_vp);
        mListView = (ListView) view.findViewById(R.id.home_listview);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_point_group);
        mData = new HomeEntity();
//-----------------------------------------------------------
        //搜索编辑栏
        mTextView = (TextView) view.findViewById(R.id.home_search_tv);
        mButton = (Button) view.findViewById(R.id.home_search_bt);
        //搜索按钮设置点击事件
        mButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);

    }
//这是搜索按钮的点击事件 将编辑栏中的数据传递到搜索页面中的编辑栏中 并且自动启动搜索页面中的搜索事件
    @Override
    public void onClick(View v) {
        //传递数据到搜索页面
//         String ProductName = mEditText.getText().toString().trim();
//        Intent intent = new Intent(getContext(),MainActivity.class);
//        intent.putExtra(Constant.PRODUCT_NAME,ProductName);
//        startActivity(intent);
        mContext.jumpTo(1);
    }



    public class LocalImageHolderView implements Holder<HomeEntity.HomeTopicBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, HomeEntity.HomeTopicBean data) {
            Picasso.with(getActivity()).load(UrlConstant.MARKET_URL + data.getPic()).into(imageView);
        }
    }


    //条目点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch (position) {
            case 0:
                startActivity(new Intent(parent.getContext(), LimitBuyAvtivity.class));
                break;
            case 1:
                startActivity(new Intent(parent.getContext(), SalesPromotionActivity.class));
                break;
            case 2:
                mContext.jumpTo(2);
                break;
            case 3:
                startActivity(new Intent(parent.getContext(),HotProductActivity.class));
                break;
            case 4:
                startActivity(new Intent(parent.getContext(), RecBrandActivity.class));
                break;
        }
    }
}
