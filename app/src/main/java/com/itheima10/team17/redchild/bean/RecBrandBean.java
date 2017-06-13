package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by 刘国徽 on 2016/6/18.
 */
public class RecBrandBean {

    /**
     * brand : [{"key":"孕妈专区","value":[{"id":1203,"name":"防辐射","pic":"/images/brand/2104_1333163839_9.jpg"},{"id":1204,"name":"飞利浦","pic":"/images/brand/288_1339146655_3.jpg"}]},{"key":"营养食品","value":[{"id":1205,"name":"枕工坊","pic":"/images/brand/301_1333165569_7.jpg"}]}]
     * response : brand
     */

    private String          response;
    /**
     * key : 孕妈专区
     * value : [{"id":1203,"name":"防辐射","pic":"/images/brand/2104_1333163839_9.jpg"},{"id":1204,"name":"飞利浦","pic":"/images/brand/288_1339146655_3.jpg"}]
     */

    private List<BrandBean> brand;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public static class BrandBean {
        private String key;
        /**
         * id : 1203
         * name : 防辐射
         * pic : /images/brand/2104_1333163839_9.jpg
         */

        private List<ValueBean> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
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
}
