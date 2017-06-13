package com.itheima10.team17.redchild.ui.fragment;

import android.support.v4.app.Fragment;

import com.itheima10.team17.redchild.api.MarketApi;
import com.itheima10.team17.redchild.api.MarketFactory;

/**
 * Created by yu895 on 2016/6/15.
 */
public class BaseFragment extends Fragment {
    public static final MarketApi sMarketIo = MarketFactory.getMarketIOSingleton();
}
