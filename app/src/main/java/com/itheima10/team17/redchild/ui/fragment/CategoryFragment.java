package com.itheima10.team17.redchild.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.CategoryEntity;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.ui.adapter.CategoryNaviAdapter;
import com.itheima10.team17.redchild.ui.adapter.ProductAdapter;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class CategoryFragment extends BaseFragment {

    private ListView mNaviList;
    private CategoryNaviAdapter mCartAdapter;
    private ArrayList<CategoryEntity.CategoryBean> mData;
    private ListView mDetailView;
    private ProductAdapter mProductAdapter;

    public static CategoryFragment instance() {
        CategoryFragment view = new CategoryFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }


    private void initView(View view) {
        mNaviList = (ListView) view.findViewById(R.id.lv_product_navi);
        mDetailView = (ListView) view.findViewById(R.id.gv_gategorydetail);
        mProductAdapter = new ProductAdapter(getActivity(), mData);
        mData = new ArrayList<>();
        mCartAdapter = new CategoryNaviAdapter(getActivity(), mData);
        mNaviList.setAdapter(mCartAdapter);
        mDetailView.setAdapter(mProductAdapter);
        mNaviList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCartAdapter.itemClick(position);
                mProductAdapter.setData(mData, mCartAdapter.getItem(position).getId());
                mProductAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initData() {
        sMarketIo.getCategory(0)
                .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(categoryEntity -> {
                    if (categoryEntity.getCategory() != null) {
                        return categoryEntity.getCategory();
                    } else if (categoryEntity.getResponse().equals("error")) {
                        onError(categoryEntity.getError());
                        return new ArrayList<CategoryEntity.CategoryBean>();
                    } else {
                        return new ArrayList<CategoryEntity.CategoryBean>();
                    }
                }).flatMap(Observable::from)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryBean -> {
                    mCartAdapter.addData(categoryBean);
                    mCartAdapter.notifyDataSetChanged();
                }, Throwable::printStackTrace, () -> {
                    for (CategoryEntity.CategoryBean categoryBean : mData) {
                        if (categoryBean.getParentId() == 0) {
                            mProductAdapter.setData(mData, categoryBean.getId());
                            mProductAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                });


    }


    private void onError(ErrEntity.ErrorBean errorBean) {
        Toast.makeText(getActivity(), errorBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

}