package com.example.xutong.toolbar.model;

import java.math.BigInteger;
import java.sql.Date;

public class Application {
	public int serialNumber;//流水 ID 同时也是申请编号
	public long userID;
	public long adminID;
	public int classroomID;
	public Date applyDate;//申请使用教室的日期
	public int applyTime;//使用教室的时间 小时  比如 8表示 8~9点， 9表示9~10点
	public Date postTime;
	public int applyState;
	public int applyPassword;//6位密码
	public long userID2;
	public long userID3;

	public String applyReason;
	public String remarks;

}