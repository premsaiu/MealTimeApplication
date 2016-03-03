package com.mealtime.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.OtpTable;
import com.mealtime.bean.UserMaster;
import com.mealtime.dao.OtpTableDAO;
import com.mealtime.dao.UserMasterDAO;
import com.mealtime.form.UserForm;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.SendSMS;

@Service
public class AdminService {

	@Autowired
	UserMasterDAO userMasterDAO;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	SendSMS sendSMS;
	
	@Autowired
	OtpTableDAO otpTableDAO;
	
	private static final Logger logger = Logger.getLogger(AdminService.class);
	
	public UserMaster checkUserRole(String mobileNumber){
		logger.info("checkUser() :: mobileNumber: "+mobileNumber);
		
		if(mobileNumber.matches("\\d{10}")){
			UserMaster userMaster = userMasterDAO.findByMobileNumber(mobileNumber);
			if(userMaster.getRoleId() == 1 && userMaster.getPassword() != null){
				//userMaster.setStatus("200");
				return userMaster;
			}else{
				return null;
			}
		}
		
		return null;		
	}
	
	public UserMaster checkAdminExistence(UserMaster userMaster){
		logger.info("checkUser() :: mobileNumber: "+userMaster.getMobileNumber());
		
		if(userMaster.getMobileNumber().matches("\\d{10}")){
			UserMaster userMaster1 = userMasterDAO.findByMobileNumber(userMaster.getMobileNumber());
			if(userMaster.getRoleId() == 1 && userMaster.getPassword() != null){
				if(userMaster.getPassword().equals(userMaster.getPassword())){
					userMaster.setStatus("400");
					return userMaster1;
				}
			}
		}
		return null;		
	}
	
	public boolean changePassword(UserForm user){
		UserMaster userMaster = userMasterDAO.find(user.getUserId());
		if(userMaster != null){
			if(userMaster.getPassword().equals(user.getPassword())){
				userMaster.setPassword(user.getNewPassword());
				int count = userMasterDAO.update(userMaster);
				if(count >0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void saveOTP(Integer otp, String mobileNo, String email){
		logger.info("saveOTP() :: mobileNumber: "+mobileNo+":: email: "+email);
		OtpTable otpTable = new OtpTable();
		otpTable.setMobileNumber(mobileNo);
		otpTable.setEmail(email);
		otpTable.setOtp(String.valueOf(otp));
		Date date = new Date();
		Timestamp currTime = new java.sql.Timestamp(date.getTime());
		otpTable.setOtpTime(currTime);
		OtpTable otpTable2 = otpTableDAO.findByMobileNumber(mobileNo);
		if(otpTable2 == null){
			otpTableDAO.insert(otpTable);
		}else{
			otpTable2.setOtp(String.valueOf(otp));
			otpTable2.setOtpTime(currTime);
			otpTableDAO.updateByMobileNumber(otpTable2);
		}
	}
	
	public Integer smsOTP(int otp, String mobileNo){
		logger.info("smsOTP() :: mobileNumber: "+mobileNo+" ::otp: "+otp);
		String message = "";
		message = "test message "+otp;
		//message = "test message."+otp+" is your OTP. Treat this as confidential. Sharing it with anyone gives them full access to your MealTime account.";
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
	
	public void emailOTP(Integer otp, String email, String subject){
		logger.info("emailOTP() :: email: "+email+" ::otp: "+otp);
		String fromAddress = "premcse41@gmail.com";
		String toAddress = email;
		//String subject = "MealTime - One Time Password(OTP) ";
		String msgBody = "<i>Hi!</i><br><br>";
		msgBody += "<b>Welcome to MealTime!</b><br>";
		msgBody += "Your One Time Password(OTP) is <b> "+otp+"</b>.<br>";
		msgBody += "Treat this as confidential. Sharing it with anyone gives them full access to your MealTime account.<br><br>";
		msgBody += "Regards, <br>Meal Time Team";
		mealTimeUtil.sendEmail(toAddress, fromAddress, subject, msgBody);
	}
	
	public boolean verifyOTP(String mobileNo, String submittedOTP){
		logger.info("verifyOTP() :: mobileNo: "+mobileNo+" ::submittedOTP: "+submittedOTP);
		OtpTable otpTable = otpTableDAO.findByMobileNumber(mobileNo);
		if(otpTable ==  null){
			return false;
		}else{
			Timestamp otpTime = otpTable.getOtpTime();
			logger.info("otpTime::"+otpTime.getTime());
			logger.info("otpTime in secs::"+otpTime.getTime()/1000);
			long diffTimeSecs = (new Date().getTime() - otpTime.getTime()) / 1000;
			logger.info("Time difference in secs :: "+diffTimeSecs);
			if(otpTable.getOtp().equals(submittedOTP) && diffTimeSecs < 900){
			//if(otpTable.getOtp().equals(submittedOTP)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public void saveProfile(UserMaster userMaster){
		userMaster.setRoleId(2);
		userMasterDAO.insert(userMaster);
	}
	
	public String getLastUserId(){
		return userMasterDAO.getLastUserId();
	}
	
	public int updateProfile(UserMaster userMaster){
		int count = 0;
		UserMaster user = userMasterDAO.find(userMaster.getUserId());
		user.setFilePath(userMaster.getFilePath());
		user.setFirstName(userMaster.getFirstName());
		user.setLastName(userMaster.getLastName());
		user.setMobileNumber(userMaster.getMobileNumber());
		user.setEmail(userMaster.getEmail());
		user.setFoodStyleS1(userMaster.getFoodStyleS1());
		user.setFoodStyleS2(userMaster.getFoodStyleS2());
		user.setAddress(userMaster.getAddress());
		count  = userMasterDAO.update(user);
		return count;
	}
	
}
