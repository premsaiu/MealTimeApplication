package com.mealtime.bean;

import java.util.Date;

public class AlertBean {
	
	private String user_Id;
	private String email;
	private String mobile_Number;
	private Date end_Date;
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_Number() {
		return mobile_Number;
	}
	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}
	public Date getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}
	

}
