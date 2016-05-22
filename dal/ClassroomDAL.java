package com.example.xutong.toolbar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.microsoft.sqlserver.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import net.sourceforge.jtds.jdbc.Driver;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.model.Classroom;

public class ClassroomDAL {
	
	//���� ID �����
	public static String dbURL = "jdbc:sqlserver://10.189.131.157:1433; DatabaseName=test1";
	public static Classroom getClassroom(int classroomID) throws ClassNotFoundException, SQLException{
		Classroom res = new Classroom();
		//ͨ��SQL ��� �����ݿ���� ID ���ҵ�������� 
		//��װ֮�� ������� classroom ��Ķ���
		 //���ӷ����������ݿ�test
		String userName = "wwt";  //Ĭ���û���
		String userPwd = "310522";  //����
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
	
	//������������� ��� building=-1 ��ʾ�κ� building ������  floorͬ�� door����ǿ��ַ���Ҳ���κζ�����
	public static ArrayList<Classroom> searchClassroom(int building,int floor,String door) throws SQLException, ClassNotFoundException{
		ArrayList<Classroom> list = new ArrayList<Classroom>();
		// ͨ�� SQL
		//String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=test1";  //���ӷ����������ݿ�test
		String userName = "wwt";  //Ĭ���û���
		String userPwd = "310522";  //����
		Connection dbConn;  
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver") ;   
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
