package com.itheima10.team17.redchild.api;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

import java.util.HashMap;

/**
 * Created by Destiny on 2016/6/14.
 */
public class MarketFactory {
    protected static final Object monitor = new Object();
    protected static final Object monitor2 = new Object();
    private static HashMap<String,LiteOrm> db = new HashMap<>();
    static MarketApi sMarketIOSingleton = null;

    public static MarketApi getMarketIOSingleton() {
        synchronized (monitor) {
            if (sMarketIOSingleton == null) {
                sMarketIOSingleton = new MarketService().getMarketService();
            }
            return sMarketIOSingleton;
        }
    }

    public static LiteOrm getLiteOrmSingleton(Context context,String name) {
        synchronized (monitor2) {
            LiteOrm liteOrm = db.get(name);
            if (liteOrm != null) {
                liteOrm = LiteOrm.newSingleInstance(context, name);
            }
            return liteOrm;
        }
    }

}
