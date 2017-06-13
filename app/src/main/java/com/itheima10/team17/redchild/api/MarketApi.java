package com.itheima10.team17.redchild.api;

import com.itheima10.team17.redchild.bean.AddNewAddress;
import com.itheima10.team17.redchild.bean.AddressListInfo;
import com.itheima10.team17.redchild.bean.CartListEntity;
import com.itheima10.team17.redchild.bean.CategoryEntity;
import com.itheima10.team17.redchild.bean.Checkoutcenter;
import com.itheima10.team17.redchild.bean.CommentEntity;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.bean.FavoriteBean;
import com.itheima10.team17.redchild.bean.HomeEntity;
import com.itheima10.team17.redchild.bean.HotProductItemBean;
import com.itheima10.team17.redchild.bean.HotSearch;
import com.itheima10.team17.redchild.bean.LimitbuyItemBean;
import com.itheima10.team17.redchild.bean.LogoutBean;
import com.itheima10.team17.redchild.bean.ModificationAddress;
import com.itheima10.team17.redchild.bean.MyAddressInfo;
import com.itheima10.team17.redchild.bean.OrderBean;
import com.itheima10.team17.redchild.bean.ProductEntity;
import com.itheima10.team17.redchild.bean.RecBrandBean;
import com.itheima10.team17.redchild.bean.SalesPromotionBean;
import com.itheima10.team17.redchild.bean.SearchGoods;
import com.itheima10.team17.redchild.bean.SumbitOrder;
import com.itheima10.team17.redchild.bean.UserBean;
import com.itheima10.team17.redchild.bean.UserCenterBean;
import com.itheima10.team17.redchild.bean.VersionBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Destiny on 2016/6/14.
 */
public interface MarketApi {


    @GET("home")
    Observable<HomeEntity> getHome();


    @POST("category")
    Observable<CategoryEntity> getCategory(@Query("version") int version);


    @GET("product")
    Observable<ProductEntity> getProduct(@Query("pId") int pId);

    @GET("product/comment")
    Observable<CommentEntity> getCommetn(@Query("pId") int pId, @Query("page") int page, @Query("pageNum") int pageNum);

    /**
     * 获取购物车列表
     *
     * @param userid 由登录接口获得的用户标识
     * @param token  由登录接口获取到的token字段
     * @return
     */
    @POST("cart")
    Observable<CartListEntity> getCartList(@Query("userid") String userid, @Query("token") long token);


    /**
     * 修改购物车商品数量
     *
     * @param userid 用户id
     * @param token  用户token
     * @param cid    购物车id
     * @param pnum   商品数量
     * @param pid    商品id
     * @return
     */
    @POST("cart/edit")
    Observable<ErrEntity> editCart(@Query("userid") String userid,
                                   @Query("token") long token,
                                   @Query("cid") int cid,
                                   @Query("pnum") int pnum,
                                   @Query("pid") int pid);

    @POST("cart/delete")
    Observable<ErrEntity> delCart(@Query("userid") String userid,
                                  @Query("token") long token,
                                  @Query("cids") int cids);

    @POST("cart/add")
    Observable<ErrEntity> addCart(@Query("userid") String userid,
                                  @Query("token") long token,
                                  @Query("pid") int pid,
                                  @Query("pnum") int pnum,
                                  @Query("ppid") int ppid);


    @GET("addfavorites")
    Observable<FavoriteBean> addFavorite(@Query("pid") int pId,
                                         @Query("userid") String userid,
                                         @Query("token") long token);


    /**
     * By_lxw
     * 结算中心_提交订单
     */
    @FormUrlEncoded
    @POST("ordersumbit")
    Call<SumbitOrder> setOrderSumbitData(@Field("cids") String cartids, @Field("userid") String userid,
                                         @Field("token") String token, @Field("addressid") String addressid,
                                         @Field("paymentType") String paymentType, @Field("deliveryType") String deliveryType,
                                         @Field("invoiceType") String invoiceType, @Field("invoiceTitle") String invoiceTitle,
                                         @Field("invoiceContent") String invoiceContent, @Field("couponid") String couponid);


    /**
     * By_lxw
     * 结算中心_付款
     *
     * @return
     */
    @FormUrlEncoded
    @POST("checkout")
    Call<Checkoutcenter> setCheckoutData(@Field("userid") String userid, @Field("token") String token,
                                         @Field("orderid") String orderid, @Field("alipaycount") String alipaycount,
                                         @Field("alipaypwd") String alipaypwd, @Field("paymoney") String paymoney);

    /**
     * 热搜商品&历史搜索
     */
    @GET("search/recommend")
    Call<HotSearch> getHotSearch();

    /**
     * 搜索商品列表
     */
    @FormUrlEncoded
    @POST("search")
    Call<SearchGoods> getSearchData(@Field("keyword") String keyword, @Field("page") int page
            , @Field("pageNum") int pageNum, @Field("orderby") String orderby);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("login")
    Call<UserBean> getUserData(@Field("username") String username, @Field("password") String
            password);

    /**
     * 获取用户中心数据
     */
    @GET("userinfo")
    Call<UserCenterBean> getUserInfoData(@Query("userid") String userid, @Query("token") Long token);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("register")
    Call<UserBean> getUserRegisterData(@Field("username") String username, @Field("password") String
            password);

    /**
     * 注销
     */
    @FormUrlEncoded
    @POST("logout")
    Call<LogoutBean> getUserLogoutData(@Field("userid") String userid);

    /**
     * 获取订单数据
     */
    @FormUrlEncoded
    @POST("orderlist")
    Call<OrderBean> getMyOrderData(@Field("type") String type, @Field("token") Long token,
                                   @Field("userid") String userid, @Field("pageNum") String pageNum,
                                   @Field("pageSize") String pageSize);

    /**
     * 版本信息
     */
    @GET("version")
    Call<VersionBean> getVersionData(@Query("versioncode") int versioncode);

    /**
     * 获取默认地址列表
     */
    @GET("addressDefaultDetail")
    Observable<AddressListInfo> getDefaultAddress(@Query("userid") String userid, @Query("token") long token);

    /**
     * 限时抢购
     */
    @GET("limitbuy")
    Observable<LimitbuyItemBean> getLimitBuyData(@Query("page") int page, @Query("pageNum") int pagNum);

    /**
     * 促销快报
     */
    @GET("topic")
    Call<SalesPromotionBean> getTopicData(@Query("page") int page, @Query("pageNum") int pagNum);

    /**
     * 热门单品
     * getProductList
     */
    @GET("hotproduct")
    Call<HotProductItemBean> getProductListData(@Query("page") int page, @Query("pageNum") int pagNum, @Query("orderby") String orderby);

    /**
     * 推荐品牌
     * getBrand
     */
    @GET("brand")
    Call<RecBrandBean> getBrandListData();


    /**
     * 获取收藏夹数据
     */
    @GET("favorites")
    Call<FavoriteBean> getFavoriteData(@Query("userid") String userid, @Query("token") Long token,
                                       @Query("page") String page, @Query("pageNum") String pageNum);


    /**
     * 新增客户地址
     */

    @POST("addresssave")
    Observable<AddNewAddress> addAddress(@Query("id") int id, @Query("token") long token,
                                         @Query("userid") String userid, @Query("name") String name,
                                         @Query("phoneNumber") String phoneNumber, @Query("city") String city,
                                         @Query("province") String province, @Query("addressArea") String addressArea,
                                         @Query("addressDetail") String addressDetail, @Query("zipCode") String zipCode);

    /**
     * 获取自己的地址
     */
    @GET("addressDetail")
    Observable<MyAddressInfo> getMyAddressInfo(@Query("userid") String user_id, @Query("token") long token, @Query("addressid") int id);


    /**
     * 获取地址列表
     */
    @GET("addresslist")
    Observable<AddressListInfo> getAddressListinfo(@Query("userid") String user_id, @Query("token") long token);


    /**
     * 设置为默认地址
     */
    @GET("addressdefault")
    Observable<ModificationAddress> setModificationAddressInfo(@Query("id") int id, @Query("token") long token, @Query("userid") String user_id);

    /**
     * 删除地址
     */
    @GET("addressdelete")
    Observable<ModificationAddress> deleteAddressInfo(@Query("id") int id, @Query("token") long token, @Query("userid") String user_id);


}
