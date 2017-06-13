package com.itheima10.team17.redchild.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asus on 2016-06-19.
 */
public class AddressListInfo {

    /**
     * addressList : [{"zipCode":"090","default":false,"addressDetail":"科学大道路7号","phoneNumber":"18656086719","province":"安徽省\\0","city":"合肥市\\0","name":"方文全","id":233,"userid":"0008","addressArea":"高新区\\0"},{"zipCode":"11231","default":false,"addressDetail":"126号","phoneNumber":"22","province":"天津市\\0","city":"天津市\\0","name":"11","id":244,"userid":"0008","addressArea":"河东区\\0"}]
     * response : response
     */
    private List<AddressListEntity> addressList;
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


    public void setAddressList(List<AddressListEntity> addressList) {
        this.addressList = addressList;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AddressListEntity> getAddressList() {
        return addressList;
    }

    public String getResponse() {
        return response;
    }

    public static class AddressListEntity {
        /**
         * zipCode : 090
         * default : false
         * addressDetail : 科学大道路7号
         * phoneNumber : 18656086719
         * province : 安徽省\0
         * city : 合肥市\0
         * name : 方文全
         * id : 233
         * userid : 0008
         * addressArea : 高新区\0
         */
        private String zipCode;
        @SerializedName("default")
        private boolean defaultX;
        private String addressDetail;
        private String phoneNumber;
        private String province;
        private String city;
        private String name;
        private int id;
        private String userid;
        private String addressArea;
        public boolean isDefault;

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getZipCode() {
            return zipCode;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getUserid() {
            return userid;
        }

        public String getAddressArea() {
            return addressArea;
        }
    }
}
