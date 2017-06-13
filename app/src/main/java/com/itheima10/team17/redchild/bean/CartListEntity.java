package com.itheima10.team17.redchild.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Destiny on 2016/6/15.
 */
public class CartListEntity implements Parcelable {


    /**
     * cart : [{"id":29,"orderid":"00000000000000000001","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":30,"orderid":"00000000000000000001","pid":2,"pnum":4,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":31,"orderid":"00000000000000000001","pid":3,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/3.jpg","productName":"男士孕妇服","productPrice":"300","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":32,"orderid":"00000000000000000001","pid":6,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/6.jpg","productName":"玩具积木a","productPrice":"52","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":54,"pid":6,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/6.jpg","productName":"玩具积木a","productPrice":"52","productPropertyName":"","state":1,"user_id":"14599337416420"}]
     * response : cart
     */

    private String response;
    /**
     * id : 29
     * orderid : 00000000000000000001
     * pid : 1
     * pnum : 2
     * ppid : 0
     * productImageUrl : /images/topic/product/1.jpg
     * productName : 灰色孕妇服
     * productPrice : 50
     * productPropertyName :
     * state : 1
     * user_id : 14599337416420
     */


    private ErrEntity.ErrorBean error;
    /**
     * error : {"code":-4,"msg":"token已经过时，请重新登录！"}
     * response : error
     */


    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }


    private List<CartBean> cart;


    private float payMoney;

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean implements Parcelable {
        private int id;
        private String orderid;
        private int pid;
        private int pnum;
        private int ppid;
        private String productImageUrl;
        private String productName;
        private String productPrice;
        private String productPropertyName;
        private int state;
        private String user_id;
        private boolean isDelete;

        private boolean isChecked;

        @Override
        public String toString() {
            return "CartBean{" +
                    "id=" + id +
                    ", orderid='" + orderid + '\'' +
                    ", pid=" + pid +
                    ", pnum=" + pnum +
                    ", ppid=" + ppid +
                    ", productImageUrl='" + productImageUrl + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productPrice='" + productPrice + '\'' +
                    ", productPropertyName='" + productPropertyName + '\'' +
                    ", state=" + state +
                    ", user_id='" + user_id + '\'' +
                    ", isDelete=" + isDelete +
                    ", isChecked=" + isChecked +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getProductImageUrl() {
            return productImageUrl;
        }

        public void setProductImageUrl(String productImageUrl) {
            this.productImageUrl = productImageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductPropertyName() {
            return productPropertyName;
        }

        public void setProductPropertyName(String productPropertyName) {
            this.productPropertyName = productPropertyName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public boolean getIsChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.orderid);
            dest.writeInt(this.pid);
            dest.writeInt(this.pnum);
            dest.writeInt(this.ppid);
            dest.writeString(this.productImageUrl);
            dest.writeString(this.productName);
            dest.writeString(this.productPrice);
            dest.writeString(this.productPropertyName);
            dest.writeInt(this.state);
            dest.writeString(this.user_id);
            dest.writeByte(this.isDelete ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        }

        public CartBean() {
        }

        protected CartBean(Parcel in) {
            this.id = in.readInt();
            this.orderid = in.readString();
            this.pid = in.readInt();
            this.pnum = in.readInt();
            this.ppid = in.readInt();
            this.productImageUrl = in.readString();
            this.productName = in.readString();
            this.productPrice = in.readString();
            this.productPropertyName = in.readString();
            this.state = in.readInt();
            this.user_id = in.readString();
            this.isDelete = in.readByte() != 0;
            this.isChecked = in.readByte() != 0;
        }

        public static final Creator<CartBean> CREATOR = new Creator<CartBean>() {
            @Override
            public CartBean createFromParcel(Parcel source) {
                return new CartBean(source);
            }

            @Override
            public CartBean[] newArray(int size) {
                return new CartBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.response);
        dest.writeParcelable(this.error, flags);
        dest.writeTypedList(this.cart);
        dest.writeFloat(this.payMoney);
    }

    public CartListEntity() {
    }

    protected CartListEntity(Parcel in) {
        this.response = in.readString();
        this.error = in.readParcelable(ErrEntity.ErrorBean.class.getClassLoader());
        this.cart = in.createTypedArrayList(CartBean.CREATOR);
        this.payMoney = in.readFloat();
    }

    public static final Creator<CartListEntity> CREATOR = new Creator<CartListEntity>() {
        @Override
        public CartListEntity createFromParcel(Parcel source) {
            return new CartListEntity(source);
        }

        @Override
        public CartListEntity[] newArray(int size) {
            return new CartListEntity[size];
        }
    };
}
