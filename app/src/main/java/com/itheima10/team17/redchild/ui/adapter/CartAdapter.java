package com.itheima10.team17.redchild.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.BuildConfig;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.activity.LimitBuyAvtivity;
import com.itheima10.team17.redchild.bean.CartListEntity;
import com.itheima10.team17.redchild.bean.ErrEntity;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.constant.UrlConstant;
import com.itheima10.team17.redchild.ui.activity.ProductDetailActivity;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.util.DecimalFormatUtil;
import com.itheima10.team17.redchild.util.ToastUtil;
import com.itheima10.team17.redchild.view.ExplosionField;
import com.itheima10.team17.redchild.view.ItemCountView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Destiny on 2016/6/15.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> implements ExplosionField.onExplosionListener {

    private static final int EMPTY_VIEW = 666;
    private final Activity mContext;
    private final ArrayList<CartListEntity.CartBean> mData;
    private ArrayList<CartListEntity.CartBean> mDeleteData;

    private float mTotalPrice;
    private boolean mIsAllChecked;
    private onAllCheckedChangedListener mOnAllCheckedChangedListener;
    private int mTotalCount;
    private Boolean mOnNumberChangedFlag;
    private ExplosionField mExplosionField;


    public CartAdapter(Activity context, ArrayList<CartListEntity.CartBean> cartBeen) {
        mContext = context;
        mData = cartBeen;
        mDeleteData = new ArrayList<>();
        mTotalPrice = 0f;
        mExplosionField = ExplosionField.attach2Window(mContext);
        mExplosionField.setOnexplisionListener(this);
    }

    public void toggleAll(boolean checked) {
        for (CartListEntity.CartBean cartBean : mData) {
            cartBean.setIsChecked(checked);
        }
        mOnAllCheckedChangedListener.onTotalPriceChanged(getTotalPrice(), mTotalCount);
    }

    public float getTotalPrice() {
        mTotalPrice = 0f;
        mTotalCount = 0;
        for (CartListEntity.CartBean cartBean : mData) {
            float totalPrice = Integer.parseInt(cartBean.getProductPrice()) * cartBean.getPnum();
            if (cartBean.getIsChecked()) {
                mTotalPrice += totalPrice;
                mTotalCount += cartBean.getPnum();
            }
        }
        return mTotalPrice;
    }

    public List<CartListEntity.CartBean> getSelectedData() {
        ArrayList<CartListEntity.CartBean> newData = new ArrayList<>();
        for (CartListEntity.CartBean cartBean : mData) {
            if (cartBean.getIsChecked()) {
                newData.add(cartBean);
            }
        }
        return newData;
    }

    @Override
    public int getItemViewType(int position) {
        if (0 == mData.size()) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = null;
        if (EMPTY_VIEW == viewType) {
            holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_cart_empty, parent,
                    false));
            holder.mEmptyCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, LimitBuyAvtivity.class));
                }
            });
        } else {
            holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_cart_itemdetail, parent,
                    false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (EMPTY_VIEW == getItemViewType(position)) {

            return;
        } else {

            CartListEntity.CartBean item = mData.get(position);
            holder.mCountView.setAdapter(this);
            holder.mDelProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.sMarketIo.delCart(BaseActivity.mUserInfo.getUser_id(), BaseActivity.mUserInfo.getToken(), item.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(editEntity -> {
                                if (editEntity.getResponse().toString().equals("cart")) {
                                    //item.setDelete(true);
                                    mData.remove(item);
                                    delItem(position);
                                    if (mOnAllCheckedChangedListener != null) {
                                        mOnAllCheckedChangedListener.onTotalPriceChanged(getTotalPrice(), mTotalCount);
                                    }
                                } else if (editEntity.getResponse().toString().equals("error")) {
                                    onError(editEntity.getError());
                                } else {
                                    //if (BuildConfig.DEBUG) Log.d("CartAdapter", "未知错误");
                                    //if (BuildConfig.DEBUG) Log.d("CartAdapter", "未知错误");
                                }
                            }, Throwable::printStackTrace, () -> {
                            });
                }
            });

            holder.mSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setIsChecked(isChecked);
                    if (isChecked) {
                        float totalPrice = Integer.parseInt(item.getProductPrice()) * item.getPnum();
                        boolean flag = true;
                        for (CartListEntity.CartBean cartBean : mData) {
                            flag = flag & cartBean.getIsChecked();
                        }
                        mIsAllChecked = flag;
                    } else {
                        float totalPrice = Integer.parseInt(item.getProductPrice()) * item.getPnum();
                        if (mIsAllChecked) {
                            mIsAllChecked = false;
                        }
                    }

                    if (mOnAllCheckedChangedListener != null) {
                        mOnAllCheckedChangedListener.onChedkedChanged(mIsAllChecked, getTotalPrice(), mTotalCount);
                    }


                }

            });

            holder.mCountView.setOnNumberChangedListener(new ItemCountView.OnNumberChangedListener() {
                @Override
                public boolean onNumberChanged(int mCount) {
                    mOnNumberChangedFlag = false;
                    BaseActivity.sMarketIo.editCart(BaseActivity.mUserInfo.getUser_id(), BaseActivity.mUserInfo.getToken(), item.getId(), item.getPnum() + mCount, item.getPid())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(editEntity -> {
                                if (editEntity.getResponse().toString().equals("cartedit")) {
                                    mOnNumberChangedFlag = true;
                                    item.setPnum(item.getPnum() + mCount);
                                    float totalPrice = Integer.parseInt(item.getProductPrice()) * item.getPnum();
                                    holder.mProductTotal.setText(DecimalFormatUtil.getDecimal(totalPrice));
                                    holder.mProductPrice.setText(DecimalFormatUtil.getDecimal(item.getProductPrice()));
                                    if (mOnAllCheckedChangedListener != null) {
                                        mOnAllCheckedChangedListener.onTotalPriceChanged(getTotalPrice(), mTotalCount);
                                        mOnAllCheckedChangedListener.onCountChanged(item);
                                    }
                                } else if (editEntity.getResponse().toString().equals("error")) {
                                    onError(editEntity.getError());
                                } else {
                                    //if (BuildConfig.DEBUG) Log.d("CartAdapter", "未知错误");
                                }

                            }, Throwable::printStackTrace, () -> {
                            });
                    return mOnNumberChangedFlag;
                }
            });
            if (item.getProductPrice() != null) {
                float totalPrice = Integer.parseInt(item.getProductPrice()) * item.getPnum();
                holder.mProductTotal.setText(DecimalFormatUtil.getDecimal(totalPrice));
            }
            holder.mSelected.setChecked(item.getIsChecked());
            holder.mCountView.setNumber(item.getPnum());
            holder.mProductName.setText(item.getProductName());
            holder.mProductPrice.setText(DecimalFormatUtil.getDecimal(item.getProductPrice()));
            if (item.isDelete()) {
                mDeleteData.add(item);
                mExplosionField.explode(holder.mProductPic);
            }
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetailActivity.class);
                    intent.putExtra(Constant.PRODUCT_ID, item.getPid());
                    mContext.startActivity(intent);
                }
            });

            Picasso.with(mContext).load(UrlConstant.MARKET_URL + item.getProductImageUrl()).into(holder.mProductPic);
        }
    }

    protected void delItem(int position) {
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return 0 == mData.size() ? 1 : mData.size();
    }


    public void setOnAllCheckedChangedListener(onAllCheckedChangedListener onAllCheckedChangedListener) {
        mOnAllCheckedChangedListener = onAllCheckedChangedListener;
    }

    @Override
    public void onExplosionStarted() {

    }

    @Override
    public void onExplosionEnded() {
        //mData.removeAll(mDeleteData);
        notifyDataSetChanged();
    }

    public interface onAllCheckedChangedListener {
        public void onChedkedChanged(boolean isChecked, float totalPrice, int totalCount);

        public void onTotalPriceChanged(float totalPrice, int totalCount);

        public void onCountChanged(CartListEntity.CartBean totalCount);
    }


    private void onError(ErrEntity.ErrorBean errorBean) {
        //Toast.makeText(mContext, errorBean.getMsg(), Toast.LENGTH_SHORT).show();
        if (BuildConfig.DEBUG) ToastUtil.show(mContext, errorBean.getMsg(), Toast.LENGTH_SHORT);
        //Log.d("CartAdapter", "errorBean.getCode():" + errorBean.getCode());

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private final Button mDelProduct;
        private final Button mEmptyCart;
        private final ImageView mProductPic;
        private final TextView mProductName;
        private final TextView mProductTotal;
        private final TextView mProductPrice;
        private final ItemCountView mCountView;
        private final CheckBox mSelected;
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            mDelProduct = (Button) view.findViewById(R.id.btn_product_del);
            mEmptyCart = (Button) view.findViewById(R.id.btn_cart_empty1);
            mProductPic = (ImageView) view.findViewById(R.id.iv_product_pic);
            mProductName = (TextView) view.findViewById(R.id.tv_product_name);
            mProductTotal = (TextView) view.findViewById(R.id.tv_product_totalprice);
            mProductPrice = (TextView) view.findViewById(R.id.tv_product_price);
            mCountView = (ItemCountView) view.findViewById(R.id.tv_product_countview);
            mSelected = (CheckBox) view.findViewById(R.id.rb_cart_selected);
        }
    }


}
