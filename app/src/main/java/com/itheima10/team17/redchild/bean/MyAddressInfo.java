package com.itheima10.team17.redchild.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2016-06-18.
 */
public class MyAddressInfo {


    /**
     * address : {"zipCode":"11231","default":false,"addressDetail":"126号","phoneNumber":"18256085719","province":"安徽省","city":"合肥市","name":"fangwenquan","id":242,"userid":"0008","addressArea":"蜀山区"}
     * response : addressDetail
     */
    private AddressEntity address;
    private String response;

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getResponse() {
        return response;
    }

    public static class AddressEntity {
        /**
         * zipCode : 11231
         * default : false
         * addressDetail : 126号
         * phoneNumber : 18256085719
         * province : 安徽省
         * city : 合肥市
         * name : fangwenquan
         * id : 242
         * userid : 0008
         * addressArea : 蜀山区
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
