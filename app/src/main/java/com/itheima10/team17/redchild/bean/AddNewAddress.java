package com.itheima10.team17.redchild.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asus on 2016-06-18.
 */
public class AddNewAddress {


    /**
     * addressList : [{"addressArea":"dsfd","addressDetail":"dfdsf","city":"sad","default":false,"id":178,"name":"111","phoneNumber":"123","province":"sda","userid":"14599337416420","zipCode":"1234"}]
     * response : addresssave
     */

    private String response;
    /**
     * addressArea : dsfd
     * addressDetail : dfdsf
     * city : sad
     * default : false
     * id : 178
     * name : 111
     * phoneNumber : 123
     * province : sda
     * userid : 14599337416420
     * zipCode : 1234
     */

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

    private List<AddressListBean> addressList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean {
        private String addressArea;
        private String addressDetail;
        private String city;
        @SerializedName("default")
        private boolean defaultX;
        private int id;
        private String name;
        private String phoneNumber;
        private String province;
        private String userid;
        private String zipCode;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return "AddressListBean{" +
                    "addressArea='" + addressArea + '\'' +
                    ", addressDetail='" + addressDetail + '\'' +
                    ", city='" + city + '\'' +
                    ", defaultX=" + defaultX +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", province='" + province + '\'' +
                    ", userid='" + userid + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AddNewAddress{" +
                "response='" + response + '\'' +
                ", error=" + error +
                ", addressList=" + addressList +
                '}';
    }
}
