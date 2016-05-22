package com.example.xutong.toolbar.model;

public class User {
	public long userID;
	public int userType;
	public boolean banned;
	public String username;
	public String password;
	
	public User(){
		userID=0;
		userType=0;
		banned = false;
		username="";
		password="";
	}
}
