package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by Destiny on 2016/6/18.
 */
public class ProductEntity {

    /**
     * available : true
     * bigPic : []
     * buyLimit : 10
     * commentCount : 1
     * id : 121
     * inventoryArea : 全国
     * leftTime : 1447004517
     * limitPrice : 30
     * marketPrice : 100
     * name : 韩版孕妇服饰
     * pics : []
     * price : 50
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 1
     */

    private ProductBean product;
    /**
     * product : {"available":true,"bigPic":[],"buyLimit":10,"commentCount":1,"id":121,"inventoryArea":"全国","leftTime":1447004517,"limitPrice":30,"marketPrice":100,"name":"韩版孕妇服饰","pics":[],"price":50,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":1}
     * response : product
     */

    private String response;

    private ErrEntity.ErrorBean error;

    private List<CommentEntity.ProductBean> commens;


    public List<CommentEntity.ProductBean> getCommens() {
        return commens;
    }

    public void setCommens(List<CommentEntity.ProductBean> commens) {
        this.commens = commens;
    }


    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public static class ProductBean {
        private boolean available;
        private int buyLimit;
        private int commentCount;
        private int id;
        private String inventoryArea;
        private int leftTime;
        private int limitPrice;
        private int marketPrice;
        private String name;
        private long lastTime;
        private int price;
        private int score;
        private String content;
        private long time;
        private String title;
        private String username;
        private List<?> bigPic;
        private List<?> pics;


        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */


        private List<ProductPropertyBean> productProperty;

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<?> getBigPic() {
            return bigPic;
        }

        public void setBigPic(List<?> bigPic) {
            this.bigPic = bigPic;
        }

        public List<?> getPics() {
            return pics;
        }

        public void setPics(List<?> pics) {
            this.pics = pics;
        }

        public List<ProductPropertyBean> getProductProperty() {
            return productProperty;
        }

        public void setProductProperty(List<ProductPropertyBean> productProperty) {
            this.productProperty = productProperty;
        }

        public static class ProductPropertyBean {
            private int id;
            private String k;
            private String v;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }
    }
}
