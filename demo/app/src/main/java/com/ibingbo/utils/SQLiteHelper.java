package com.ibingbo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhangbingbing on 16/7/30.
 */
public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "test";

    private static final String TABLE_NAME = "user";
    private static final String SQL_CREATE_TABLE = "create table user(id integer primary key autoincrement,name text,password text,email text);";

    public SQLiteHelper(Context ctx){
        super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
