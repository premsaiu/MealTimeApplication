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
import com.mealtime.bean.UserSubscription;
import com.mealtime.dao.OtpTableDAO;
import com.mealtime.dao.UserMasterDAO;
import com.mealtime.dao.UserSubscriptionDAO;
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
	
	@Autowired
	UserSubscriptionDAO userSubscriptionDAO;
	
	private static final Logger logger = Logger.getLogger(MealTimeService.class);
	
	public UserMaster checkUser(String mobileNumber){
		logger.info("checkUser() :: mobileNumber: "+mobileNumber);
		UserMaster userMaster = userMasterDAO.findByMobileNumber(mobileNumber);
		if(userMaster == null){
			userMaster =  new UserMaster();
			userMaster.setMobileNumber(mobileNumber);
			userMaster.setRoleId(3);
			userMaster.setCreatedDate(new Date());
			userMaster.setIsActive("NO");
			userMaster.setStatus("Visitor");
			userMasterDAO.insert(userMaster);
			userMaster = userMasterDAO.findByMobileNumber(mobileNumber);
			return userMaster;
		}else{
			return userMaster;
		}
		//return userMasterDAO.findByMobileNumber(mobileNumber);
	}
	
	public UserMaster checkMobileNumber(String mobileNumber){
		logger.info("checkMobileNumber() :: mobileNumber: "+mobileNumber);
		return userMasterDAO.findByMobileNumber(mobileNumber);
	}
	
	public UserMaster getUser(String userId){
		logger.info("getUser() :: userId: "+userId);
		return userMasterDAO.find(userId);
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
		//userMaster.setRoleId(2);
		userMaster.setUpdatedDate(new Date());
		userMaster.setIsActive("YES");
		//userMaster.setStatus("User");
		userMasterDAO.update(userMaster);
	}
	
	public boolean emailExists(String email){
		UserMaster userMaster = userMasterDAO.findByEmail(email);
		if(userMaster != null){
			return true;
		}else{
			return false;
		}
	}
	
	public String getLastUserId(){
		return userMasterDAO.getLastUserId();
	}
	
	public void subscribeUser(UserMaster user){
		UserSubscription userSubscription = new UserSubscription();
		userSubscription.setUserId(user.getUserId());
		userSubscription.setSubscriptionId(1);
		userSubscription.setStatus("Open");
		userSubscription.setIsActive("NO");
		userSubscription.setCreatedBy(user.getUserId());
		userSubscription.setCreatedDate(new Date());
		userSubscriptionDAO.insert(userSubscription);
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
