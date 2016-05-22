package com.example.xutong.toolbar.control;
import com.example.xutong.toolbar.model.User;
import com.example.xutong.toolbar.model.Classroom;
import com.example.xutong.toolbar.model.Application;
import com.example.xutong.toolbar.dal.ClassroomDAL;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageClassroom {
	public static Classroom getClassrrom(int classroomID) throws SQLException, ClassNotFoundException {
		Classroom res = ClassroomDAL.getClassroom(classroomID);
		return res;
	}

	//public static ArrayList<Classroom> search
	
}
