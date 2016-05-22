package com.example.xutong.toolbar.control;

import com.example.xutong.toolbar.dal.AdminDAL;

/**
 * Created by yuchenlin on 5/22/16.
 */
public class AdminController {
    //判断登录情况
    public static boolean Login(long adminID,String password){
        return AdminDAL.getAdmin(adminID).password==password;
    }
}
