package com.itheima10.team17.redchild.model;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Destiny on 2016/6/20.
 */

//"areaid": 101010100,
//        "city": "北京",
//        "district": "北京",
//        "prov": "北京"
@Table("address")
public class AddressModel {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int areaid;
    @NotNull
    public String city;
    @NotNull
    public String district;
    @NotNull
    public String prov;

}
