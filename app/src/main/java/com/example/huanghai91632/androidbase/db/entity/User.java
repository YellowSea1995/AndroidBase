package com.example.huanghai91632.androidbase.db.entity;

/**
 * Created by huanghai91632 on 2018/3/30.
 */

public class User {

    private String userId;
    private String password;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
