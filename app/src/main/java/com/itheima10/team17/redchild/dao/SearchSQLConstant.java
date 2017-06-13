package com.itheima10.team17.redchild.dao;

/**
 * @author asus
 * @Description:数据库操作常量类
 * Created by asus on 2016-06-16
 */

public interface SearchSQLConstant {

    /**
     * 数据库版本信息
     */
    int BLACKLIST_SQL_VERSION = 1;

    /**
     * 数据库名称 字段 _id 字段 keyword（关键字）
     */
    String SQL_NAME = "searchhistory.db";
    String SQL_TABLE_NAME = "searchhistory";
    String SQL_FILE_ID = "_id";
    String SQL_FILE_KEYWORD = "keyword";

    /**
     * 创建数据库表
     */
    String SQL_CREATE_TABLE = "create table searchhistory (_id integer primary key autoincrement,keyword varchar(40) unique);";


}
