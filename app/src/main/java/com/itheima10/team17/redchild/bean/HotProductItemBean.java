package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by 刘国徽 on 2016/6/18.
 */
public class HotProductItemBean {

    /**
     * listCount : 15
     * productList : [{"id":1,"isHot":1,"marketPrice":100,"name":"灰色孕妇服","pic":"/images/topic/product/1.jpg","price":50}]
     * response : hotProduct
     * total : 1
     */

    private int listCount;
    private String response;
    private int    total;
    /**
     * id : 1
     * isHot : 1
     * marketPrice : 100
     * name : 灰色孕妇服
     * pic : /images/topic/product/1.jpg
     * price : 50
     */

    private List<ProductListBean> productList;
    private  ErrEntity.ErrorBean error;
    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private int    id;
        private int    isHot;
        private int    marketPrice;
        private String name;
        private String pic;
        private int    price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
