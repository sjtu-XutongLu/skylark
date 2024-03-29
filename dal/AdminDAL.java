package com.example.xutong.toolbar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.xutong.toolbar.model.Admin;
import com.example.xutong.toolbar.model.Application;

/**
 * Created by yuchenlin on 5/22/16.
 */
public class AdminDAL {
    public static String dbURL = DBAdapterConfig.dbURL;
    public static String userName = DBAdapterConfig.userName;  //默认用户名
    public static String userPwd = DBAdapterConfig.userPwd;  //密码

    public static Admin getAdmin(long adminID) throws ClassNotFoundException, SQLException{
        Admin res = new Admin();
        Connection dbConn;
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
        String sql = "select * from apply where adminID = " + (adminID + "");
        System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            res.adminID = rs.getInt("adminID");
            res.name = rs.getString("name");
            res.password = rs.getString("password");
        }
        return res;
    }
}
