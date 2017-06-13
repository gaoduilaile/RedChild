package com.itheima10.team17.redchild.bean;

/**
 * Created by lxw on 16/6/16.
 * 结算中心Home界面Bean
 */


public class Checkoutcenter {
    private ErrEntity.ErrorBean error;
    private String user_id;
    private long   token;
    private String orderid;
    private String alipaycount;
    private String alipaypwd;
    private String paymoney;

    public ErrEntity.ErrorBean getError() {
        return error;
    }
    public Checkoutcenter() {

    }

    public Checkoutcenter(String user_id, long token, String orderid, String alipaycount, String alipaypwd, String paymoney) {
        this.user_id = user_id;
        this.token = token;
        this.orderid = orderid;
        this.alipaycount = alipaycount;
        this.alipaypwd = alipaypwd;
        this.paymoney = paymoney;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAlipaycount() {
        return alipaycount;
    }

    public void setAlipaycount(String alipaycount) {
        this.alipaycount = alipaycount;
    }

    public String getAlipaypwd() {
        return alipaypwd;
    }

    public void setAlipaypwd(String alipaypwd) {
        this.alipaypwd = alipaypwd;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    @Override
    public String toString() {
        return "Checkoutcenter{" +
                "user_id='" + user_id + '\'' +
                ", token=" + token +
                ", orderid='" + orderid + '\'' +
                ", alipaycount='" + alipaycount + '\'' +
                ", alipaypwd='" + alipaypwd + '\'' +
                ", paymoney='" + paymoney + '\'' +
                '}';
    }
}
