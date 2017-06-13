package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by tao on 2016/6/18.
 */
public class FavoriteBean {

    private ErrEntity.ErrorBean error;
    /**
     * total : 2
     * response : favorites
     * productList : [{"inventoryArea":"全国","marketPrice":28,"limitPrice":1,"available":true,"isNew":2,"pic":"/images/topic/product/2.jpg","saleNum":1,"shelves":1439543897925,"commentCount":2,"productDesc":"好商品","score":2,"buyLimit":10,"product_property_id":"1,2,3,4,5","price":1,"name":"孕妇裙","leftTime":1448004566,"id":2,"isHot":2},{"inventoryArea":"大武汉","marketPrice":100,"limitPrice":90,"available":true,"isNew":3,"pic":"/images/topic/product/3.jpg","saleNum":90,"shelves":1439543827925,"commentCount":3,"productDesc":"好商品","score":3,"buyLimit":10,"product_property_id":"1,2,3,4,5","price":300,"name":"男士孕妇服","leftTime":1447084515,"id":3,"isHot":3}]
     */
    private int                 total;
    private String                  response;
    private List<ProductListEntity> productList;


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

    public void setProductList(List<ProductListEntity> productList) {
        this.productList = productList;
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


    public static class ProductListEntity {
        /**
         * inventoryArea : 全国
         * marketPrice : 28
         * limitPrice : 1
         * available : true
         * isNew : 2
         * pic : /images/topic/product/2.jpg
         * saleNum : 1
         * shelves : 1439543897925
         * commentCount : 2
         * productDesc : 好商品
         * score : 2
         * buyLimit : 10
         * product_property_id : 1,2,3,4,5
         * price : 1
         * name : 孕妇裙
         * leftTime : 1448004566
         * id : 2
         * isHot : 2
         */
        private String inventoryArea;
        private int     marketPrice;
        private int     limitPrice;
        private boolean available;
        private int     isNew;
        private String  pic;
        private int     saleNum;
        private long    shelves;
        private int     commentCount;
        private String  productDesc;
        private int     score;
        private int     buyLimit;
        private String  product_property_id;
        private int     price;
        private String  name;
        private int     leftTime;
        private int     id;
        private int     isHot;

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public void setShelves(long shelves) {
            this.shelves = shelves;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public void setProduct_property_id(String product_property_id) {
            this.product_property_id = product_property_id;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public boolean isAvailable() {
            return available;
        }

        public int getIsNew() {
            return isNew;
        }

        public String getPic() {
            return pic;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public long getShelves() {
            return shelves;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public String getProductDesc() {
            return productDesc;
        }

        public int getScore() {
            return score;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public String getProduct_property_id() {
            return product_property_id;
        }

        public int getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public int getId() {
            return id;
        }

        public int getIsHot() {
            return isHot;
        }

        @Override
        public String toString() {
            return "ProductListEntity{" +
                    "inventoryArea='" + inventoryArea + '\'' +
                    ", marketPrice=" + marketPrice +
                    ", limitPrice=" + limitPrice +
                    ", available=" + available +
                    ", isNew=" + isNew +
                    ", pic='" + pic + '\'' +
                    ", saleNum=" + saleNum +
                    ", shelves=" + shelves +
                    ", commentCount=" + commentCount +
                    ", productDesc='" + productDesc + '\'' +
                    ", score=" + score +
                    ", buyLimit=" + buyLimit +
                    ", product_property_id='" + product_property_id + '\'' +
                    ", price=" + price +
                    ", name='" + name + '\'' +
                    ", leftTime=" + leftTime +
                    ", id=" + id +
                    ", isHot=" + isHot +
                    '}';
        }
    }
}
