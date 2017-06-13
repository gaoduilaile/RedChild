package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by Destiny on 2016/6/21.
 */
public class FavoriteEntity {

    /**
     * productList : [{"available":true,"buyLimit":10,"commentCount":7,"id":110,"inventoryArea":"全国","isHot":7,"isNew":7,"leftTime":1448005533,"limitPrice":36,"marketPrice":43,"name":"成人尿不湿","pic":"/images/topic/product/3.jpg","price":59,"productDesc":"好玩具","product_property_id":"1,2,3,4,5","saleNum":36,"score":7,"shelves":1439543847925},{"available":true,"buyLimit":10,"commentCount":6,"id":111,"inventoryArea":"全国","isHot":6,"isNew":6,"leftTime":1448004519,"limitPrice":36,"marketPrice":43,"name":"洁牙护齿","pic":"/images/topic/product/6.jpg","price":52,"productDesc":"好商品","product_property_id":"0","saleNum":36,"score":6,"shelves":1439543857925}]
     * response : favorites
     * total : 2
     */

    private String response;
    private int total;
    public ErrEntity.ErrorBean error;
    /**
     * available : true
     * buyLimit : 10
     * commentCount : 7
     * id : 110
     * inventoryArea : 全国
     * isHot : 7
     * isNew : 7
     * leftTime : 1448005533
     * limitPrice : 36
     * marketPrice : 43
     * name : 成人尿不湿
     * pic : /images/topic/product/3.jpg
     * price : 59
     * productDesc : 好玩具
     * product_property_id : 1,2,3,4,5
     * saleNum : 36
     * score : 7
     * shelves : 1439543847925
     */

    private List<LimitbuyItemBean.ProductListBean> productList;

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

    public List<LimitbuyItemBean.ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<LimitbuyItemBean.ProductListBean> productList) {
        this.productList = productList;
    }


}
