package com.example.huanghai91632.androidbase.test;

import android.provider.BaseColumns;

/**
 * Created by Orchid on 2017/5/2.
 */

public class TestContract {

    public static final String DB_NAME = "game.db";
    public static final String TABLE_NAME = "testTable";

    public static final String SQL_CREATE_TABLE = "create table if not exists "
            + TABLE_NAME
            + " ("
            + TestEntry._ID
            + " integer primary key autoincrement, "
            + TestEntry.COL_NAME0
            + " text, "
            + TestEntry.COL_NAME1
            + " integer, "
            + TestEntry.COL_NAME2
            + " integer)";

    public static abstract class TestEntry implements BaseColumns {
        public static final String _ID = "_id";
        public static final String COL_NAME0 = "学生姓名";
        public static final String COL_NAME1 = "年龄";
        public static final String COL_NAME2 = "成绩";
    }
}
