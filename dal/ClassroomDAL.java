package com.example.xutong.toolbar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.jtds.jdbc.Driver;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.model.Classroom;

public class ClassroomDAL {
	public static String dbURL = "jdbc:jtds:sqlserver://localhost:1433/test1";
	//根据 ID 查教室
	public static Classroom getClassroom(int classroomID) throws ClassNotFoundException, SQLException{
		Classroom res = new Classroom();
		//通过SQL 语句 在数据库根据 ID 中找到这个教室 
		//包装之后 返回这个 classroom 类的对象
		  //连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver") ;   
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from classroom where classroomID = " + (classroomID + "");
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
	        res.classroomType = rs.getInt("classroomType");
	        res.building = rs.getInt("building");
	        res.floor = rs.getInt("floor");
	        res.door = rs.getString("door");
	        res.classroomID = rs.getInt("classroomID");
	        res.remarks = rs.getString("remarks");
	        res.seat = rs.getInt("seat");
	        return res;
        }
        else return new Classroom();
	}
	
	//根据条件查教室 如果 building=-1 表示任何 building 均可以  floor同理 door如果是空字符串也是任何都可以
	public static List<Classroom> searchClassroom(int building,int floor,String door) throws SQLException, ClassNotFoundException{
		List<Classroom> list = new ArrayList<Classroom>();
		// 通过 SQL
		//连接服务器和数据库test
		String userName = "wwt";  //默认用户名
		String userPwd = "310522";  //密码
		Connection dbConn;  
	    Class.forName("net.sourceforge.jtds.jdbc.Driver") ;   
	    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	    String sql = "select * from classroom ";
	    if(building == -1 && floor == -1 && door == "") {}
	    else
	    {
	    	sql = sql + "where ";
	    	if(building != -1)
		    {
		    	sql = sql + "building = " + building + "";
		    }
	    	if(floor != -1)
		    {
		    	sql = sql + " and floor = " + floor + "";
		    }
	    	if(door != "")
		    {
		    	sql = sql + " and door = " + door + "";
		    }
	    }
	    System.out.println(sql);
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Classroom res = new Classroom();
	        res.classroomType = rs.getInt("classroomType");
	        res.building = rs.getInt("building");
	        res.floor = rs.getInt("floor");
	        res.door = rs.getString("door");
	        res.classroomID = rs.getInt("classroomID");
	        res.remarks = rs.getString("remarks");
	        res.seat = rs.getInt("seat");
	        list.add(res);
        }
		return list;
	}
}
