package com.itheima10.team17.redchild.bean;

/**
 * Created by asus on 2016-06-19.
 */
public class ModificationAddress {

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

    public boolean addressid;

    public boolean isAddressid() {
        return addressid;
    }

    public void setAddressid(boolean addressid) {
        this.addressid = addressid;
    }
}
