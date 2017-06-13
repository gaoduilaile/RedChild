package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by asus on 2016-06-15.
 */
public class SearchGoods {

    /**
     * total : 10
     * response : search
     * productList : [{"id":4,"marketPrice":198,"price":168,"name":"玩具小鸭子c","pic":"/images/topic/product/4.jpg"},{"id":5,"marketPrice":98,"price":108,"name":"玩具汽车b","pic":"/images/topic/product/5.jpg"},{"id":6,"marketPrice":43,"price":52,"name":"玩具积木a","pic":"/images/topic/product/6.jpg"},{"id":7,"marketPrice":43,"price":59,"name":"玩具积木t","pic":"/images/topic/product/6.jpg"},{"id":8,"marketPrice":33,"price":66,"name":"玩具积木c","pic":"/images/topic/product/5.jpg"},{"id":9,"marketPrice":43,"price":55,"name":"玩具积木d","pic":"/images/topic/product/6.jpg"},{"id":10,"marketPrice":52,"price":65,"name":"玩具积木e","pic":"/images/topic/product/6.jpg"},{"id":11,"marketPrice":43,"price":58,"name":"玩具积木f","pic":"/images/topic/product/6.jpg"},{"id":12,"marketPrice":46,"price":57,"name":"玩具积木g","pic":"/images/topic/product/6.jpg"},{"id":a13,"marketPrice":55,"price":63,"name":"玩具积木h","pic":"/images/topic/product/6.jpg"}]
     * listCount : 10
     */
    private int total;
    private String response;
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
    private List<ProductListEntity> productList;
    private int listCount;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setProductList(List<ProductListEntity> productList) {
        this.productList = productList;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public int getTotal() {
        return total;
    }

    public String getResponse() {
        return response;
    }

    public List<ProductListEntity> getProductList() {
        return productList;
    }

    public int getListCount() {
        return listCount;
    }

    public static class ProductListEntity {
        /**
         * id : 4
         * marketPrice : 198
         * price : 168
         * name : 玩具小鸭子c
         * pic : /images/topic/product/4.jpg
         */
        private int id;
        private int marketPrice;
        private int price;
        private String name;
        private String pic;

        public void setId(int id) {
            this.id = id;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getId() {
            return id;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public int getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }
    }
}
