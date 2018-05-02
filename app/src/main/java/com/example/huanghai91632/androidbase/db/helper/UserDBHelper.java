package com.example.huanghai91632.androidbase.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huanghai91632.androidbase.db.contract.UserContract;

/**
 * Created by Orchid on 2017/5/2.
 */

public class UserDBHelper extends SQLiteOpenHelper {

    public static final  int DB_VERSION = 1;

    public UserDBHelper(Context context, String DB_NAME) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + UserContract.TABLE_NAME);
        onCreate(db);
    }
}
