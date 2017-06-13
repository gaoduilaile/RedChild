package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by 刘国徽 on 2016/6/17.
 */
public class SalesPromotionBean {

    /**
     * response : topic
     * topic : [{"id":1,"name":"孕妇服装大特惠","pic":"/images/topic/1.jpg"},{"id":2,"name":"儿童玩具聚划算","pic":"/images/topic/2.jpg"}]
     * total : 2
     */

    private String response;
    private int total;
    /**
     * id : 1
     * name : 孕妇服装大特惠
     * pic : /images/topic/1.jpg
     */
    private  ErrEntity.ErrorBean error;
    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }

    private List<TopicBean> topic;

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

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public static class TopicBean {
        private int    id;
        private String name;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
