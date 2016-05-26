package com.example.xutong.toolbar.dal;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.util.*;
import java.sql.*;
import java.sql.Date;

//import com.microsoft.sqlserver.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import com.example.xutong.toolbar.model.Application;

public class ApplicationDAL {
	public static String dbURL = "jdbc:jtds:sqlserver://59.78.44.148:1433/test1";
	//未完成1
	//注意 小教室还要判断userID2 和 3！！！！！！！！！！！！！！
	public static ArrayList<Application>  getApplicationByUser(long userID) throws ClassNotFoundException, SQLException{
		//传入用户ID，返回用户所有的预订记录
		ArrayList<Application> res = new ArrayList<Application>();
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where userID = " + (userID + "") + " or userID2 = " + 
	    			(userID + "") + " or userID3 = " + (userID + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Application element = new Application();
	        element.adminID = rs.getInt("adminID");
	        element.applyDate = rs.getDate("applyDate");
	        element.applyPassword = rs.getInt("applyPassword");
	        element.applyReason = rs.getString("applyReason");
	        element.applyState = rs.getInt("applyState");
	        element.applyTime = rs.getInt("applyTime");
	        element.classroomID = rs.getInt("classroomID");
	        element.postTime = rs.getDate("postTime");
	        element.remarks = rs.getString("remarks");
	        element.serialNumber = rs.getInt("serialNumber");
	        element.userID = rs.getLong("userID");
	        element.userID2 = rs.getLong("userID2");
	        element.userID3 = rs.getLong("userID3");
	        res.add(element);
        }
		return res;
	}
	
	//未完成2
	public static ArrayList<Application>  getApplicationByClassroom(int classroomID) throws SQLException, ClassNotFoundException{
		ArrayList<Application> res = new ArrayList<Application>();
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where classroomID = " + (classroomID + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Application element = new Application();
	        element.adminID = rs.getInt("adminID");
	        element.applyDate = rs.getDate("applyDate");
	        element.applyPassword = rs.getInt("applyPassword");
	        element.applyReason = rs.getString("applyReason");
	        element.applyState = rs.getInt("applyState");
	        element.applyTime = rs.getInt("applyTime");
	        element.classroomID = rs.getInt("classroomID");
	        element.postTime = rs.getDate("postTime");
	        element.remarks = rs.getString("remarks");
	        element.serialNumber = rs.getInt("serialNumber");
	        element.userID = rs.getLong("userID");
	        element.userID2 = rs.getLong("userID2");
	        element.userID3 = rs.getLong("userID3");
	        res.add(element);
        }
        return res;
	}
	
    public static Application getApplication(int serialNumber) throws SQLException, ClassNotFoundException {
        //给流水号返回Application
    	//连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");   
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where serialNumber = " + (serialNumber + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Application res = new Application();
        if(rs.next())
        {
	        res.adminID = rs.getLong("adminID");
	        res.applyDate = rs.getDate("applyDate");
	        res.applyPassword = rs.getInt("applyPassword");
	        res.applyReason = rs.getString("applyReason");
	        res.applyState = rs.getInt("applyState");
	        res.applyTime = rs.getInt("applyTime");
	        res.classroomID = rs.getInt("classroomID");
	        res.postTime = rs.getDate("postTime");
	        res.remarks = rs.getString("remarks");
	        res.serialNumber = rs.getInt("serialNumber");
	        res.userID = rs.getLong("userID");
	        res.userID2 = rs.getLong("userID2");
	        res.userID3 = rs.getLong("userID3");
	        return  res;
        }
        else return new Application();
    }
    public static boolean addApplication(Application application) throws SQLException, ClassNotFoundException {
        //����Application�������Ƿ�ɹ�
        boolean res = true;
        //String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=test1";  //���ӷ����������ݿ�test
		String userName = "wwt";  //Ĭ���û���
		String userPwd = "310522";  //����
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver") ;
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = " insert into [dbo].[apply]([userID],[classroomID],[applyTime],[applyReason],[remarks],[adminID],[applyDate],[postTime],[applyState],[applypassword],[userID2],[userID3]) VALUES ("

	    			+(application.userID + "") + ","
	    			+(application.classroomID + "") + ","
	    			+(application.applyTime + "") + ",\'"
	    			+(application.applyReason) + "\',\'"
	    			+(application.remarks) + "\',"
	    			+(application.adminID + "") + ",\'"
	    			+(application.applyDate) + "\',\'"
	    			+(application.postTime) + "\',"
	    			+(application.applyState + "") + ","
	    			+(application.applyPassword + "") + "," 
	    			+(application.userID2 + "") + ","
	    			+(application.userID3 + "") + ")";
	    System.out.println(sql);
        Statement stmt = dbConn.createStatement();
        try
        {
        	stmt.executeUpdate(sql);
        } catch(Exception e) {return false;}
        return res;
    }
   

    public static boolean alterApplication(Application application) throws ClassNotFoundException, SQLException {
        //传入原有的一张Application表，对其修改的部分在数据库中加以修改，返回是否成功
        //连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where serialNumber = " + (application.serialNumber + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
        	sql = "UPDATE [dbo].[apply] SET [serialNumber] = " + application.serialNumber + "" +
        			      ",[userID] = " + application.userID + "" +
        			      ",[classroomID] = " + application.classroomID + "" +
        			      ",[applyTime] = " + application.applyTime + "" +
        			      ",[applyReason] = \'" + application.applyReason + "\'" +
        			      ",[remarks] = \'" + application.remarks + "\'" +
        			      ",[adminID] = " + application.adminID + "" +
        			      ",[applyDate] = " + application.applyDate + "" +
        			      ",[postTime] = " + application.postTime + "" +
        			      ",[applyState] = " + application.applyState + "" +
        			      ",[applyPassword] = " + application.applyPassword + "" +
        			      ",[userID2] = " + application.userID2 + "" +
        			      ",[userID3] = " + application.userID3 + "" + " \n" +
        			 "where serialNumber = " + application.serialNumber + "";
        	System.out.println(sql);
        	ps = dbConn.prepareStatement(sql);
			try
			{
				ps.executeUpdate();
			} catch(Exception e) {System.out.println(e); return false;}
			return true;
        }
        else
        {
        	return false;
        }
    }
    
	public static ArrayList<Application> getApplicationByState(int applyState) throws ClassNotFoundException, SQLException {
        //返回所有applyState对应的Application，以ArrayList形式返回
        ArrayList<Application> res = new ArrayList<Application>();
        //连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where applyState = " + (applyState + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Application element = new Application();
	        element.adminID = rs.getInt("adminID");
	        element.applyDate = rs.getDate("applyDate");
	        element.applyPassword = rs.getInt("applyPassword");
	        element.applyReason = rs.getString("applyReason");
	        element.applyState = rs.getInt("applyState");
	        element.applyTime = rs.getInt("applyTime");
	        element.classroomID = rs.getInt("classroomID");
	        element.postTime = rs.getDate("postTime");
	        element.remarks = rs.getString("remarks");
	        element.serialNumber = rs.getInt("serialNumber");
	        element.userID = rs.getLong("userID");
	        element.userID2 = rs.getLong("userID2");
	        element.userID3 = rs.getLong("userID3");
	        res.add(element);
        }
        return res;
    }
    
    public static ArrayList<Application> getApplicationByTime(int classroomID, Date applyDate, int applyTime) throws ClassNotFoundException, SQLException {
        //返回所有时间和教室相同的Application，以ArrayList形式返回
        ArrayList<Application> res = new ArrayList<Application>();;
        //连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from apply where classroomID = " + (classroomID + "") + " and " + 
	    			"applyDate = \'" + applyDate.toString() + "\' and applyTime = " + (applyTime + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Application element = new Application();
	        element.adminID = rs.getLong("adminID");
	        element.applyDate = rs.getDate("applyDate");
	        element.applyPassword = rs.getInt("applyPassword");
	        element.applyReason = rs.getString("applyReason");
	        element.applyState = rs.getInt("applyState");
	        element.applyTime = rs.getInt("applyTime");
	        element.classroomID = rs.getInt("classroomID");
	        element.postTime = rs.getDate("postTime");
	        element.remarks = rs.getString("remarks");
	        element.serialNumber = rs.getInt("serialNumber");
	        element.userID = rs.getLong("userID");
	        element.userID2 = rs.getLong("userID2");
	        element.userID3 = rs.getLong("userID3");
	        res.add(element);
        }
        return res;
    }
}
