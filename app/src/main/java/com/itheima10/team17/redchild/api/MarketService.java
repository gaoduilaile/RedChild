package com.itheima10.team17.redchild.api;

import com.itheima10.team17.redchild.constant.UrlConstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Destiny on 2016/6/14.
 */
public class MarketService {
    final MarketApi marketService;

    MarketService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.MARKET_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        marketService = retrofit.create(MarketApi.class);
    }

    public MarketApi getMarketService() {
        return marketService;
    }
}
