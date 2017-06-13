package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by asus on 2016-06-15.
 */
public class HotSearch {

    /**
     * response : searchrecommend
     * searchKeywords : ["玩具","男士","孕妇"]
     */
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

    private List<String> searchKeywords;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public String getResponse() {
        return response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }
}
