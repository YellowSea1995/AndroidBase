package com.example.huanghai91632.androidbase.db.contract;

import android.provider.BaseColumns;

/**
 * Created by Orchid on 2017/5/2.
 */

public class UserContract {

    public static final String DB_NAME = "user.db";
    public static final String TABLE_NAME = "User";

    public static final String SQL_CREATE_TABLE = "create table if not exists "
            + TABLE_NAME
            + " ("
            + TestEntry._ID
            + " txt primary key, "
            + TestEntry.PASSWORD
            + " txt)";

    public static abstract class TestEntry implements BaseColumns {
        public static final String _ID = "userId";
        public static final String PASSWORD = "password";
    }
}
