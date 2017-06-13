package com.itheima10.team17.redchild.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.enums.AssignType;

import java.util.List;

/**
 * Created by Destiny on 2016/6/20.
 */
public class AddressEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Table("address")
    public static class DataBean {
        @PrimaryKey(AssignType.AUTO_INCREMENT)
        @Unique
        private int areaid;
        @NotNull
        private String city;
        @NotNull
        private String district;
        @NotNull
        private String prov;

        public boolean isSelected;


        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

    }
}
