package com.itheima10.team17.redchild.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.itheima10.team17.redchild.bean.SearchHistroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016-06-16.
 * 操作数据库增删改查
 */
public class SearchSQLdao {

    private SearchSQLiteOpenHelper mSsearch;
    public SearchSQLdao(Context context){
        mSsearch =new SearchSQLiteOpenHelper(context);
    }

    //插入数据
    public void insert(String keyword){
        SQLiteDatabase mDatabase = mSsearch.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SearchSQLConstant.SQL_FILE_KEYWORD , keyword);
        mDatabase.insert(SearchSQLConstant.SQL_TABLE_NAME, null, values);
        mDatabase.close();
    }
    //删除数据
    public void delete(){
        SQLiteDatabase mDatabase = mSsearch.getWritableDatabase();
        String sql = "delete from " + SearchSQLConstant.SQL_TABLE_NAME;
        mDatabase.execSQL(sql);
        mDatabase.close();
    }
    //查询全部数据
    public List<SearchHistroy> queryAll(){
        SQLiteDatabase mDatabase = mSsearch.getWritableDatabase();
        String[] Columns = new String[]{SearchSQLConstant.SQL_FILE_KEYWORD};
        Cursor cursor = mDatabase.query(SearchSQLConstant.SQL_TABLE_NAME, Columns, null, null, null, null, null);
        ArrayList<SearchHistroy> mData = new ArrayList<>();
        if (cursor!=null){
            while(cursor.moveToNext()){
                String mkeyword = cursor.getString(0);
                SearchHistroy mSearchHistroy = new SearchHistroy();
                mSearchHistroy.keyword = mkeyword;
                mData.add(mSearchHistroy);
            }
            cursor.close();
        }
        mDatabase.close();
        return mData;
    }

}
