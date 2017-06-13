package com.itheima10.team17.redchild.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2016-06-16.
 * 搜索数据库
 */
public class SearchSQLiteOpenHelper extends SQLiteOpenHelper {
    public SearchSQLiteOpenHelper(Context context) {
        super(context, SearchSQLConstant.SQL_NAME, null, SearchSQLConstant.BLACKLIST_SQL_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table searchhistory (_id integer primary key autoincrement,keyword varchar(20) unique);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
