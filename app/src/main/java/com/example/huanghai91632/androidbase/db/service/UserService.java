package com.example.huanghai91632.androidbase.db.service;

import com.example.huanghai91632.androidbase.db.dao.UserDao;
import com.example.huanghai91632.androidbase.db.entity.User;

/**
 * Created by huanghai91632 on 2018/3/30.
 */

public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void addUser(User user) {
        userDao.add(user);
    }
}
