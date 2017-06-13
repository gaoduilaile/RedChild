package com.itheima10.team17.redchild.bean;

/**
 * Created by tao on 2016/6/17.
 */
public class LogoutBean { 

    private ErrEntity.ErrorBean error;
    /**
     * response : logout
     */
    private String              response;

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

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
