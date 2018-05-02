package com.example.huanghai91632.androidbase.db.dao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.huanghai91632.androidbase.db.entity.User;
import com.example.huanghai91632.androidbase.db.helper.UserDBHelper;

/**
 * Created by huanghai91632 on 2018/3/30.
 */

public class UserDao extends Activity {

    private static final String TAG = "UserDao";

    private UserDBHelper userDBHelper;
    public Context context;
    private SQLiteDatabase db;

    private static final String TABLE = "User";

    public UserDao() {
    }

    public boolean validateUser(String userName, String password) {
        try {
            db = userDBHelper.getWritableDatabase();
            String sql = "select * from User where userName = ?, password = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{userName, password});
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(User user) {
        try {
//            userDBHelper = new UserDBHelper(this, UserContract.DB_NAME);
//            db = userDBHelper.getWritableDatabase();
//            db.execSQL("insert into User(userId,password) values ('1231','1231');");
//            db.execSQL("insert into " + TABLE
//                            + "(userId, password)values(?,?)",
//                    new Object[]{
//                            user.getUserId(),
//                            user.getPassword()}
//            );
            Log.d(TAG, "add: " + user.getUserId());
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String userId) {
        db = userDBHelper.getWritableDatabase();
        db.execSQL("delete from "+ TABLE
                +" where userId = ?", new Object[]{userId}
        );
        if (db != null) {
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(User user) {
        try {
            db = userDBHelper.getWritableDatabase();
            db.execSQL("update " + TABLE
                            + "set password = ? where userId = ?",
                    new Object[]{user.getPassword()}
            );
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User get(String userId) {
        db = userDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE
                +" where id = ?", new String[]{userId}
        );
        if(cursor.moveToNext()) {
            User user = new User();;
            user.setUserId(cursor.getString(cursor.getColumnIndex("userId")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            cursor.close();
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return user;
        }
        return null;
    }
}
