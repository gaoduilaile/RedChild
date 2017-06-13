红孩儿商城项目

部分代码使用了lambda表达式以及Rxjava+retrofit2结合

Api请求采用工厂方法创建单例访问网络,

部分代码示例

    MarketService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.MARKET_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        marketService = retrofit.create(MarketApi.class);
    }



	//================================================


    public static MarketApi getMarketIOSingleton() {
        synchronized (monitor) {
            if (sMarketIOSingleton == null) {
                sMarketIOSingleton = new MarketService().getMarketService();
            }
            return sMarketIOSingleton;
        }
    }


	//================================================

	public static final MarketApi sMarketIo = MarketFactory.getMarketIOSingleton();


	//================================================

      sMarketIo.setModificationAddressInfo(id, token, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addressListInfo1 -> {
                    if ("error ".equals(addressListInfo1.getResponse())) {
                        onError(addressListInfo1.getError());
                    }
                }, Throwable::printStackTrace, () -> {
                    initData();
                });

代码中使用了LiteORM数据库框架

部分界面UI尝试使用Material Design风格

时间仓促,很多逻辑代码还未完善
