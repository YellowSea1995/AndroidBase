package com.ncspt.modular.user.model;

import java.io.Serializable;

/**
 * Created by huanghai91632 on 2019/3/26.
 * 用户实体
 */

public class User implements Serializable {

    private String userName;

    private String password;

    private int sex = 0;    //0：男，1：女

    private int age = 0;

    private int isVip = 0;  //0：普通，1：VIP
}
