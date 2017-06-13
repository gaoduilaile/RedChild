package com.itheima10.team17.redchild.constant;

/**
 * Created by Destiny on 2016/6/14.
 */
public class UrlConstant {

    public static final String MARKET_URL_DEFAULT = "http://169.254.163.120:8080/market/";
    public static String MARKET_URL = "http://169.254.163.120:8080/market/";


    /**
     * 搜索商品列表排序方式
     * saleDown(销量降序)，
     * priceUp(价格升序)，
     * priceDown(价格降序)，
     * commentDown(评价降序)，
     * shelvesDown(上架降序)。
     * 目前只有价格有双向排序，其他都只有倒序
     */
    public class OrderBy{
        public static final String SALE_DOWN = "saleDown";
        public static final String PRICE_UP = "priceUp";
        public static final String PRICE_DOWN = "priceDown";
        public static final String COMMENT_DOWN = "commentDown";
        public static final String SHELVES_DOWN = "shelvesDown";
    }
}
