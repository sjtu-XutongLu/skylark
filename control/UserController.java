package com.example.xutong.toolbar.control;

import com.example.xutong.toolbar.dal.UserDAL;

/**
 * Created by yuchenlin on 5/22/16.
 */
public class UserController {


    //判断登录情况
    public static boolean Login(long userID,String password){
        return UserDAL.getUserByID(userID).password==password;
    }
}
