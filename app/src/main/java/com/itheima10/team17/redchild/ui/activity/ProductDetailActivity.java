package com.itheima10.team17.redchild.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.bean.HistoryEntity;
import com.itheima10.team17.redchild.bean.ProductEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.adapter.CommentAdapter;
import com.itheima10.team17.redchild.ui.anim.DepthPageTransformer;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.ItemCountView;
import com.itheima10.team17.redchild.view.MListView;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductDetailActivity extends BaseActivity {


    private Intent mIntent;
    private int mPid;
    private int mNum = 1;
    private ConvenientBanner mConvenientBanner;
    private FloatingActionButton mFab;
    private Toolbar mToolbar;
    private ProductEntity.ProductBean mProductBean;
    private ProductEntity.ProductBean mProduct;
    private TextView mTvContentProductname;
    private TextView mTvContentProductdesc;
    private TextView mTvContentProductprice;
    private TextView mTvContentMarketprice;
    private TextView mTvContentLefttime;
    private TextView mTvContentArea;
    private TextView mTvContentScore;
    private MListView mLvContentDesc;
    private Observable<Integer> mTimer;
    private Subscription mSubscribe;
    private CommentAdapter mCommentAdapter;
    private List<ProductEntity.ProductBean> mData;
    private ProductEntity mProductEntity;
    private ItemCountView mItemCount;
    private LiteOrm mHistoryDb;
    private HistoryEntity mHistoryEntity;
    private TextView mBuyBuyBuy;
    private TextView mFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail1);

        initData();
        initView();

    }


    private void initView() {
        mTimer = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (; ; ) {
                    subscriber.onNext(1);
                    SystemClock.sleep(1000);
                }
            }
        }).subscribeOn(Schedulers.newThread());
        mTvContentProductname = (TextView) findViewById(R.id.tv_content_productname);
        mTvContentProductdesc = (TextView) findViewById(R.id.tv_content_productdesc);
        mTvContentProductprice = (TextView) findViewById(R.id.tv_content_productprice);
        mTvContentMarketprice = (TextView) findViewById(R.id.tv_content_marketprice);
        mTvContentLefttime = (TextView) findViewById(R.id.tv_content_lefttime);
        mTvContentArea = (TextView) findViewById(R.id.tv_content_area);
        mTvContentScore = (TextView) findViewById(R.id.tv_content_score);
        mLvContentDesc = (MListView) findViewById(R.id.lv_content_desc);
        mItemCount = (ItemCountView) findViewById(R.id.tv_content_count);
        mBuyBuyBuy = (TextView) findViewById(R.id.buybuybuy);
        mFavorite = (TextView) findViewById(R.id.favorite);
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProductDetailActivity.this.startActivity(new Intent(this,MainActivity.class));
                if (mUserInfo != null) {
                    sMarketIo.addFavorite(mPid, mUserInfo.getUser_id(), mUserInfo.getToken())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(favoriteBean -> {
                                if (favoriteBean.getResponse().equals("error")) {
                                    onError(favoriteBean.getError());
                                } else {
                                    Snackbar.make(v, "添加收藏成功", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }, Throwable::printStackTrace, () -> {
                            });
                } else {
                    ToastUtil.show(ProductDetailActivity.this,"尚未登陆",ToastUtil.LENGTH_SHORT);
                    ProductDetailActivity.this.startActivity(new Intent(ProductDetailActivity.this,LauncherActivity.class));
                }
            }
        });
        mBuyBuyBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = null;
                if (mUserInfo == null) {
                    ToastUtil.show(ProductDetailActivity.this,"尚未登陆",ToastUtil.LENGTH_SHORT);
                    data = new Intent(ProductDetailActivity.this,LauncherActivity.class);
                } else {
                    data = new Intent(ProductDetailActivity.this,MainActivity.class);
                    data.putExtra("JUMP_TO", 3);
                }
                ProductDetailActivity.this.startActivity(data);
            }
        });
        mItemCount.setOnNumberChangedListener(new ItemCountView.OnNumberChangedListener() {
            @Override
            public boolean onNumberChanged(int mCount) {
                if (mNum + mCount <= mProduct.getBuyLimit()) {
                    mNum = mNum + mCount;
                    return true;
                } else if (mNum + mCount < 1) {
                    ToastUtil.show(ProductDetailActivity.this, "已经不能再少了", ToastUtil.LENGTH_SHORT);
                    return false;
                } else {
                    ToastUtil.show(ProductDetailActivity.this, "仓库没有这么多货了", ToastUtil.LENGTH_SHORT);
                    return false;
                }
            }
        });


        mConvenientBanner = (ConvenientBanner) findViewById(R.id.cb_productdetail_pic);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        Observable.zip(sMarketIo.getProduct(mPid),
                sMarketIo.getCommetn(mPid, 1, 100),
                (productEntity, commentEntity) -> {
                    productEntity.setCommens(commentEntity.getProduct());
                    return productEntity;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(Observable::just)
                .map(productEntity -> {
                    if ("product".equals(productEntity.getResponse())) {
                        mProductEntity = productEntity;
                        return productEntity.getProduct();
                    } else {
                        onError(productEntity.getError());
                        return new ProductEntity.ProductBean();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(productBean -> {
                    if (!TextUtils.isEmpty(productBean.getName())) {
                        mToolbar.setTitle(productBean.getName());
                    } else {
                        mToolbar.setTitle("商品详情");
                    }
                    setSupportActionBar(mToolbar);
                    mProductBean = productBean;
                    mConvenientBanner.setPages((CBViewHolderCreator<LocalImageHolderView>) LocalImageHolderView::new, productBean.getBigPic())
                            .setPageIndicator(new int[]{R.drawable.home_point_normal, R.drawable.home_point_pressed})
                            .setPageIndicator(new int[]{R.drawable.home_point_normal, R.drawable.home_point_pressed})
                            .setPageTransformer(new DepthPageTransformer()).startTurning(5000);
                }, Throwable::printStackTrace, () -> {
                    mCommentAdapter = new CommentAdapter(this, mProductEntity.getCommens());
                    mLvContentDesc.setAdapter(mCommentAdapter);
                    mSubscribe = mTimer.observeOn(AndroidSchedulers.mainThread()).subscribe(integer -> {
                        int leftTime = mProductBean.getLeftTime() - 1000;
                        mProductBean.setLeftTime(leftTime);
                        mTvContentLefttime.setText(new SimpleDateFormat("HH:mm:ss").format(leftTime));
                    });
                });


        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            addCartNetRequest(view);
        });
    }

    private void addCartNetRequest(View view) {
        if (mUserInfo != null) {
            if (mProductBean != null) {
                sMarketIo.addCart(mUserInfo.getUser_id(), mUserInfo.getToken(), mProductBean.getId(), mNum, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errEntity -> {
                            if ("cart".equals(errEntity.getResponse())) {
                                Snackbar.make(view, "添加购物车成功", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            } else {
                                onError(errEntity.getError());
                            }
                        }, Throwable::printStackTrace, () -> {
                        });
            } else {
                Snackbar.make(view, "并没有做什么微小的工作", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else {
            startActivityForResult(new Intent(this, LauncherActivity.class), Constant.ADDCART_LOGIN_REQUEST);
        }
    }


    private void initData() {
        mHistoryDb = LiteOrm.newSingleInstance(this, Constant.HISTORY_DB);
        mIntent = getIntent();
        mPid = mIntent.getIntExtra(Constant.PRODUCT_ID, -1);
        if (mPid != -1) {
            sMarketIo.getProduct(mPid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(productEntity -> {
                        if ("product".equals(productEntity.getResponse())) {
                            mProduct = productEntity.getProduct();
                        } else {
                            onError(productEntity.getError());
                        }
                    }, Throwable::printStackTrace, () -> {
                        setData(mProduct);
                        mHistoryEntity = new HistoryEntity();
                        mHistoryEntity.setId(mProduct.getId());
                        mHistoryEntity.setName(mProduct.getName());
                        mHistoryEntity.setLastTime(System.currentTimeMillis());
                        if (mHistoryDb.query(new QueryBuilder<HistoryEntity>(HistoryEntity.class).where("id=?", new Object[]{mHistoryEntity.getId()})).size() > 0) {
                            mHistoryDb.update(mHistoryEntity);
                        } else {
                            mHistoryDb.save(mHistoryEntity);
                        }

                    });
        } else {
            ToastUtil.show(this, "哎呀,您访问的页面已经飞到火星去啦", ToastUtil.LENGTH_SHORT);
            //finish();
        }
    }

    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            if (data != null) {
                Picasso.with(ProductDetailActivity.this).load(UrlConstant.MARKET_URL + data).into(imageView);
            }
        }
    }

    private void onError(ErrEntity.ErrorBean error) {
        if (BuildConfig.DEBUG) Log.d("ProductDetailActivity", "error.getCode():" + error.getCode());
        if (BuildConfig.DEBUG) Log.d("ProductDetailActivity", error.getMsg());
        ToastUtil.show(this, error.getMsg(), ToastUtil.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Constant.ADDCART_LOGIN_REQUEST == requestCode) {
            if (resultCode == RESULT_OK) {
                addCartNetRequest(mFab);
            }
        }
    }

    protected void setData(ProductEntity.ProductBean data) {
        mTvContentProductname.setText(data.getName());
        mTvContentProductdesc.setText(data.getName());
        mTvContentProductprice.setText(DecimalFormatUtil.getDecimal(data.getPrice()));
        mTvContentMarketprice.setText(DecimalFormatUtil.getDecimal(data.getMarketPrice()));
        DecimalFormatUtil.setDashLine(mTvContentMarketprice);
        mTvContentLefttime.setText(new SimpleDateFormat("HH:mm:ss").format(data.getLeftTime()));
        mTvContentScore.setText(data.getCommentCount() + " 分");
        mTvContentArea.setText(data.getInventoryArea());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null) {
            if (!mSubscribe.isUnsubscribed()) {
                mSubscribe.unsubscribe();
            }
        }

    }
}
