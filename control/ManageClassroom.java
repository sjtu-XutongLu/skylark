package com.example.xutong.toolbar.control;
import com.example.xutong.toolbar.model.User;
import com.example.xutong.toolbar.model.Classroom;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.dal.ClassroomDAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;

public class ManageClassroom {
	public static Classroom getClassrrom(int classroomID) throws SQLException, ClassNotFoundException {
		Classroom res = ClassroomDAL.getClassroom(classroomID);
		return res;
	}

	public static ArrayList<Classroom> getAllAvailableClassroom(Date date,int start,int end) throws SQLException, ClassNotFoundException {
		ArrayList<Classroom> all = ClassroomDAL.searchClassroom(-1,-1,"");
		Iterator<Classroom> it = all.iterator();
		ArrayList<Classroom> res =  new ArrayList<Classroom>();

		while(it.hasNext()){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Classroom c = it.next();
			if(!ProcessApplication.isBooked(c.classroomID,calendar,start,end)){
				res.add(c);
			}
		}
		return res;
	}
}
