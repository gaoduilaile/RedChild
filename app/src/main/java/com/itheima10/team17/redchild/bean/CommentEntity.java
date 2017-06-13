package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by yu895 on 2016/6/20.
 */
public class CommentEntity {

    /**
     * product : [{"content":"这是一个好裙子","time":1439545897925,"title":"裙子","username":"赵日天"},{"content":"这是一个好裙子","time":1439545897925,"title":"裙子","username":"赵日天"},{"content":"这是一个好裙子","time":1439545897925,"title":"裙子","username":"赵日天"},{"content":"这是一个好裙子","time":1439545897925,"title":"裙子","username":"赵日天"}]
     * response : product
     * total : 4
     */

    private String response;
    private int total;
    private ErrEntity.ErrorBean error;
    /**
     * content : 这是一个好裙子
     * time : 1439545897925
     * title : 裙子
     * username : 赵日天
     */

    private List<ProductBean> product;

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

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        private String content;
        private long time;
        private String title;
        private String username;

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
    }
}
