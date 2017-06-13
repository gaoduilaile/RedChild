package com.itheima10.team17.redchild.bean;

/**
 * Created by lxw on 16/6/16.
 */
public class SumbitOrder {
    public ErrEntity.ErrorBean error;
    /**
     * error : {"code":-4,"msg":"token已经过时，请重新登录！"}
     * response : error
     */

    public String  response;

    public ErrEntity.ErrorBean getError() {
        return error;
    }

    public void setError(ErrEntity.ErrorBean error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
