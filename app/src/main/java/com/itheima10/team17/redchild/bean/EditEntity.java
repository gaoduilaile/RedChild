package com.itheima10.team17.redchild.bean;

/**
 * Created by Destiny on 2016/6/16.
 */
public class EditEntity {

    /**
     * code : -2
     * msg : 超过该商品的最大购买数量：10
     */


    /**
     * error : {"code":-2,"msg":"超过该商品的最大购买数量：10"}
     * response : error
     */

    private String response;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

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

}
