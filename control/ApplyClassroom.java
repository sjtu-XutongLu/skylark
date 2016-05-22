package com.example.xutong.toolbar.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;

import com.example.xutong.toolbar.dal.ApplicationDAL;
import com.example.xutong.toolbar.model.Application;

/**
 * Created by Xflick on 2016/5/17.
 */
public class ApplyClassroom {
    public static ArrayList<String> submitBookingMediumClassroomApplication(int userID, int classroomID,
                                                                     Date applyDate, int applyTime, Date postTime) throws SQLException, ClassNotFoundException {
        //申请中小型教室
        //申请的时段必须合法且空闲
        //Application须填有userID，classroomID，applyDate，applyTime，postTime
        //返回申请编号和申请密码，以ArrayList形式表示（[serialNumber, applyPassword]）
        //注意：返回的serialNumber、applyPassword均为String型
        ArrayList<String> res = null;
        Application application = new Application();
        application.serialNumber = serialNumber;
        serialNumber++;
        application.userID = userID;
        application.userID2 = -1;   //协同申请者为空
        application.userID3 = -1;
        application.adminID = -1;   //不需要管理员
        application.classroomID = classroomID;
        application.applyDate = applyDate;
        application.applyTime = applyTime;
        application.postTime = postTime;
        application.applyState = 1; //代表一人申请，该值为3时申请成功
        application.applyReason = "";
        application.remarks = "";

        res.add(new Integer(application.serialNumber).toString());
        Random random = new Random();
        int applyPassword = random.nextInt(999999);
        application.applyPassword = applyPassword;

        String passwordString = new Integer(applyPassword).toString();
        while (passwordString.length() < 6) {
            passwordString = "0" + passwordString;
        }
        res.add(passwordString);

        boolean isSuccess = ApplicationDAL.addApplication(application);
        if (!isSuccess) {
            res.remove(1);
            res.add("-1");
        }

        return res;
    }

    public static boolean submitBookingLargeClassroomApplication(int userID, int classroomID,
                                                          Date applyDate, int applyTime, Date postTime,
                                                          String applyReason, String remarks) throws SQLException, ClassNotFoundException {
        //申请大型教室，大致要求同上
        //返回是否成功
        boolean res = true;
        Application application = new Application();
        application.serialNumber = serialNumber;
        serialNumber++;
        application.userID = userID;
        application.userID2 = -1;   //协同申请者为空
        application.userID3 = -1;
        application.adminID = -1;
        application.classroomID = classroomID;
        application.applyDate = applyDate;
        application.applyTime = applyTime;
        application.postTime = postTime;
        application.applyState = 0; //表示待审核
        application.applyPassword = -1; //大型教室不需要密码
        application.applyReason = applyReason;
        application.remarks = remarks;

        boolean isSuccess = ApplicationDAL.addApplication(application);
        if (!isSuccess) {
            res = false;
        }

        return  res;
    }

    public static int submitJoinApplication(int userID, int applyNumber, int applyPassword) throws SQLException, ClassNotFoundException {
        //申请加入，输入为int类型的申请编号和申请密码
        //返回int值，代表当前有几个人申请了该房间，返回为3时申请成功
        int res = 0;
        Application application = ApplicationDAL.getApplication(applyNumber);
        if (application.applyPassword != applyPassword) {
            res = -1;   //密码错误
        }   else {
            if (application.applyState == 1) {
                application.userID2 = userID;
            }   else if (application.applyState == 2){
                application.userID3 = userID;
                ProcessApplication.clearConflictApplication(application.classroomID, application.serialNumber, application.applyDate, application.applyTime);
            }
            application.applyState++;
            res = application.applyState;
            boolean isSuccess = ApplicationDAL.alterApplication(application);
            if (!isSuccess) {
                res = -2;   //服务器出错
            }
        }

        return res;
    }

    private static int serialNumber = 1;
}
