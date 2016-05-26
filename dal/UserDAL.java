package com.example.xutong.toolbar.dal;

import com.example.xutong.toolbar.model.Admin;
import com.example.xutong.toolbar.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by yuchenlin on 5/22/16.
 */
public class UserDAL {
    public static String dbURL = DBAdapterConfig.dbURL;
    public static String userName = DBAdapterConfig.userName;  //默认用户名
    public static String userPwd = DBAdapterConfig.userPwd;  //密码

    public static User getUserByID(long userID) throws SQLException, ClassNotFoundException{
        User res = new User();
        String userName = "wwt";  //默认用户名
        String userPwd = "310522";  //密码
        Connection dbConn;
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
        String sql = "select * from user where userID = " + (userID + "");
        System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            res.userID = rs.getInt("userID");
            res.userType = rs.getInt("userType");
            res.password = rs.getString("password");
            res.banned = rs.getBoolean("banned");
            res.username = rs.getString("userName");
        }
        return res;
    }
}
