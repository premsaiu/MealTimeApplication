package com.mealtime.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.OtpTable;
import com.mealtime.bean.UserMaster;
import com.mealtime.dao.OtpTableDAO;
import com.mealtime.dao.UserMasterDAO;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.SendSMS;

@Service
public class MealTimeService {

	@Autowired
	UserMasterDAO userMasterDAO;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	SendSMS sendSMS;
	
	@Autowired
	OtpTableDAO otpTableDAO;
	
	public UserMaster checkUser(String mobileNumber){
		System.out.println("In MealTimeService :: checkUser()");
		return userMasterDAO.findByMobileNumber(mobileNumber);
	}
	
	public void saveOTP(Integer otp, String mobileNo, String email){
		try{
			System.out.println("In MealTimeService :: saveOTP() :: mobileNumber: "+mobileNo+":: email: "+email);
			OtpTable otpTable = new OtpTable();
			otpTable.setMobileNumber(mobileNo);
			otpTable.setEmail(email);
			otpTable.setOtp(String.valueOf(otp));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(date);
			Date currentTime = sdf.parse(dateStr);
			otpTable.setOtpTime(currentTime);
			OtpTable otpTable2 = otpTableDAO.findByMobileNumber(mobileNo);
			if(otpTable2 == null){
				otpTableDAO.insert(otpTable);
			}else{
				otpTable2.setOtp(String.valueOf(otp));
				otpTable2.setOtpTime(currentTime);
				otpTableDAO.updateByMobileNumber(otpTable2);
			}
		}catch(ParseException pe){
			System.out.println("ParseException raised while parsing date in MealTime Service::saveOTP()"+pe.getMessage());
		}
	}
	
	public Integer smsOTP(int otp, String mobileNo){
		System.out.println("In MealTimeService :: smsOTP() :: mobileNumber: "+mobileNo);
		String message = "";
		message = "test message "+otp;
		//message = "test message."+otp+" is your OTP. Treat this as confidential. Sharing it with anyone gives them full access to your MealTime account.";
		System.out.println("otp:::"+otp);
		System.out.println("message::"+message);
		try {
			sendSMS.processSMS(mobileNo, message);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			return 0;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return otp;
	}
	
	public void emailOTP(Integer otp, String email){
		System.out.println("In MealTimeService :: emailOTP() :: email: "+email);
		String fromAddress = "premcse41@gmail.com";
		String toAddress = email;
		String subject = "MealTime - One Time Password(OTP) ";
		String msgBody = "<i>Hi!</i><br><br>";
		msgBody += "<b>Welcome to MealTime!</b><br>";
		msgBody += "Your One Time Password(OTP) is <b> "+otp+"</b>.<br>";
		msgBody += "Treat this as confidential. Sharing it with anyone gives them full access to your MealTime account.<br><br>";
		msgBody += "Regards, <br>Meal Time Team";
		mealTimeUtil.sendEmail(toAddress, fromAddress, subject, msgBody);
	}
	
	public int saveProfile(UserMaster userMaster){
		int userId = 0;
		userMaster.setRoleId(2);
		userId  = userMasterDAO.insert(userMaster);
		return userId;
	}
	
}
