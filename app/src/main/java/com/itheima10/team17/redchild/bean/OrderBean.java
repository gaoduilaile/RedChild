package com.itheima10.team17.redchild.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tao on 2016/6/17.
 */
public class OrderBean implements Serializable {

    private ErrEntity.ErrorBean        error;
    /**
     * total : 1
     * response : orderList
     * orderList : [{"invoiceContent":"办公用品","orderid":"201604141212053","deliveryType":1,"couponid":"1","addressid":"144","paymentType":0,"carts":[{"productImageUrl":"/images/topic/product/4.jpg","pnum":1,"orderid":"201604141212053","user_id":"14599337416420","pid":4,"id":52,"state":2,"productName":"玩具小鸭子c","productPrice":"168","ppid":0}],"user_id":"14599337416420","price":168,"invoiceType":0,"id":36,"state":2,"time":1460607125843,"invoiceTitle":"悟空"}]
     */
    private int                        total;
    private String                     response;
    private ArrayList<OrderListEntity> orderList;

    @Override
    public String toString() {
        return "OrderBean{" +
                "error=" + error +
                ", total=" + total +
                ", response='" + response + '\'' +
                ", orderList=" + orderList +
                '}';
    }

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

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setOrderList(ArrayList<OrderListEntity> orderList) {
        this.orderList = orderList;
    }

    public int getTotal() {
        return total;
    }

    public String getResponse() {
        return response;
    }

    public ArrayList<OrderListEntity> getOrderList() {
        return orderList;
    }


    public static class OrderListEntity implements Serializable{
        @Override
        public String toString() {
            return "OrderListEntity{" +
                    "invoiceContent='" + invoiceContent + '\'' +
                    ", orderid='" + orderid + '\'' +
                    ", deliveryType=" + deliveryType +
                    ", couponid='" + couponid + '\'' +
                    ", addressid='" + addressid + '\'' +
                    ", paymentType=" + paymentType +
                    ", carts=" + carts +
                    ", user_id='" + user_id + '\'' +
                    ", price=" + price +
                    ", invoiceType=" + invoiceType +
                    ", id=" + id +
                    ", state=" + state +
                    ", time=" + time +
                    ", invoiceTitle='" + invoiceTitle + '\'' +
                    '}';
        }

        /**
         * invoiceContent : 办公用品
         * orderid : 201604141212053
         * deliveryType : 1
         * couponid : 1
         * addressid : 144
         * paymentType : 0
         * carts : [{"productImageUrl":"/images/topic/product/4.jpg","pnum":1,"orderid":"201604141212053","user_id":"14599337416420","pid":4,"id":52,"state":2,"productName":"玩具小鸭子c","productPrice":"168","ppid":0}]
         * user_id : 14599337416420
         * price : 168
         * invoiceType : 0
         * id : 36
         * state : 2
         * time : 1460607125843
         * invoiceTitle : 悟空
         */
        private String invoiceContent;
        private String            orderid;
        private int               deliveryType;
        private String            couponid;
        private String            addressid;
        private int               paymentType;
        private ArrayList<CartsEntity> carts;
        private String            user_id;
        private int               price;
        private int               invoiceType;
        private int               id;
        private int               state;
        private long              time;
        private String            invoiceTitle;

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public void setDeliveryType(int deliveryType) {
            this.deliveryType = deliveryType;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public void setCarts(ArrayList<CartsEntity> carts) {
            this.carts = carts;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public String getOrderid() {
            return orderid;
        }

        public int getDeliveryType() {
            return deliveryType;
        }

        public String getCouponid() {
            return couponid;
        }

        public String getAddressid() {
            return addressid;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public ArrayList<CartsEntity> getCarts() {
            return carts;
        }

        public String getUser_id() {
            return user_id;
        }

        public int getPrice() {
            return price;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public int getId() {
            return id;
        }

        public int getState() {
            return state;
        }

        public long getTime() {
            return time;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public static class CartsEntity implements Serializable{
            @Override
            public String toString() {
                return "CartsEntity{" +
                        "productImageUrl='" + productImageUrl + '\'' +
                        ", pnum=" + pnum +
                        ", orderid='" + orderid + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", pid=" + pid +
                        ", id=" + id +
                        ", state=" + state +
                        ", productName='" + productName + '\'' +
                        ", productPrice='" + productPrice + '\'' +
                        ", ppid=" + ppid +
                        '}';
            }

            /**
             * productImageUrl : /images/topic/product/4.jpg
             * pnum : 1
             * orderid : 201604141212053
             * user_id : 14599337416420
             * pid : 4
             * id : 52
             * state : 2
             * productName : 玩具小鸭子c
             * productPrice : 168
             * ppid : 0
             */
            private String productImageUrl;
            private int    pnum;
            private String orderid;
            private String user_id;
            private int    pid;
            private int    id;
            private int    state;
            private String productName;
            private String productPrice;
            private int    ppid;

            public void setProductImageUrl(String productImageUrl) {
                this.productImageUrl = productImageUrl;
            }

            public void setPnum(int pnum) {
                this.pnum = pnum;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public String getProductImageUrl() {
                return productImageUrl;
            }

            public int getPnum() {
                return pnum;
            }

            public String getOrderid() {
                return orderid;
            }

            public String getUser_id() {
                return user_id;
            }

            public int getPid() {
                return pid;
            }

            public int getId() {
                return id;
            }

            public int getState() {
                return state;
            }

            public String getProductName() {
                return productName;
            }

            public String getProductPrice() {
                return productPrice;
            }

            public int getPpid() {
                return ppid;
            }
        }
    }
}
