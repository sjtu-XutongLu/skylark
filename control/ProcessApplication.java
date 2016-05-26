package com.example.xutong.toolbar.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;

import com.example.xutong.toolbar.dal.ApplicationDAL;
import com.example.xutong.toolbar.model.Application;

/**
 * Created by Xflick on 2016/5/18.
 */
public class ProcessApplication {
    public static ArrayList<Application> getUnhandledApplication() throws SQLException, ClassNotFoundException {
        //返回所有未处理的大型教室申请，以ArrayList形式返回
        //每次accept或refuse后应再次调用该函数，进行刷新
        ArrayList<Application> res = ApplicationDAL.getApplicationByState(0);

        return res;
    }

    public static boolean acceptApplication(Application application, int adminID) throws SQLException, ClassNotFoundException {
        //输入：把ApplicationList里选中的Application传回来
        //接受申请，返回是否成功
        //后置条件：该申请被接受，相同时间段的申请被拒绝
        boolean res = true;
        application.adminID = adminID;
        application.applyState = 3; //申请成功
        clearConflictApplication(application.serialNumber, application.classroomID, application.applyDate, application.applyTime);
        res = ApplicationDAL.alterApplication(application);

        return  res;
    }

    public static boolean refuseApplication(Application application, int adminID) throws SQLException, ClassNotFoundException {
        //拒绝申请，返回是否成功
        boolean res = true;
        application.adminID = adminID;
        application.applyState = -1;    //申请失败
        res = ApplicationDAL.alterApplication(application);

        return  res;
    }

    public static void clearConflictApplication(int successSerial, int classroomID, Date applyDate, int applyTime) throws SQLException, ClassNotFoundException {
        //清除冲突对应时间的申请
        ArrayList<Application> applications = ApplicationDAL.getApplicationByTime(classroomID, applyDate, applyTime);
        for (Application i : applications) {
            if (i.serialNumber != successSerial) {  //若该申请冲突
                i.applyState = -1;  //该申请失效
                ApplicationDAL.alterApplication(i);
            }
        }
    }

    public static ArrayList<Application> getUserApplication(int userID) throws SQLException, ClassNotFoundException {
        ArrayList<Application> res=ApplicationDAL.getApplicationByUser(userID);
        return res;
    }

    public static boolean isBooked(int classroomID,Calendar targetDate,int start,int end) throws SQLException, ClassNotFoundException {
        ArrayList<Application> classBookList = ApplicationDAL.getApplicationByClassroom(classroomID);//传入教室的序列号，返回这间教室的预订时间的list

        int len = classBookList.size();//返回的预订列表的预订次数

        int year = targetDate.get(Calendar.YEAR);//获取查询的date的年份
        int month = targetDate.get(Calendar.MONTH);//获取查询的date的月份
        int day = targetDate.get(Calendar.DATE);//获取查询的date的日

        for(int i = 0;i < len;i++) {
            Application tmp=classBookList.get(i);
            if(tmp.applyState == 3 || (tmp.applyState >= 1 && (ManageClassroom.getClassrrom(tmp.classroomID)).classroomType == 0)) {
                //如果预订成功或是小教室但在审核中
                Calendar date = Calendar.getInstance();
                date.setTime(tmp.applyDate);
                if(date.get(Calendar.YEAR) == year && date.get(Calendar.MONTH) == month
                        && date.get(Calendar.DATE) == day) { //是同一天
                    int hour = tmp.applyTime;
                    if(hour >= start && hour<end)//因为只能整点预订，每次1小时，这里只判断时
                        return true;
                }
            }
        }
        return false;
    }
}
