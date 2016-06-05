package com.mealtime.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealtime.bean.UserMaster;
import com.mealtime.bean.UserSubscription;
import com.mealtime.form.PaymentForm;
import com.mealtime.form.UserForm;
import com.mealtime.service.AdminService;
import com.mealtime.service.UserSubscriptionService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class AdminController {

private static final Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	UserSubscriptionService userSubscriptionService;

	@RequestMapping(value = "/checkUserRole", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserMaster checkUser(@RequestParam("mobileNo")String mobileNumber){
		logger.info("checkUser():: mobileNo: "+mobileNumber);
		UserMaster user =  new UserMaster();
		user = adminService.checkUserRole(mobileNumber);
		return user;
	}
	
	@RequestMapping(value = "/checkAdminExistence", method = RequestMethod.POST)
		public @ResponseBody WSResponseStatus checkAdminExistence(@RequestBody UserMaster user){
		
		WSResponseStatus wsResponseStatus = null;
		try{
			user = adminService.checkAdminExistence(user);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(user);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong");
			logger.error("Exception in checkAdminExistence---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public @ResponseBody WSResponseStatus changePassword(@RequestBody UserForm user){
		WSResponseStatus wsResponseStatus = null;
		try{
			boolean isChangePassword = adminService.changePassword(user);
			wsResponseStatus=new WSResponseStatus();
			if(isChangePassword){
				MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				wsResponseStatus.setData(user);
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Bad Password");
			}
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong");
			logger.error("Exception in changePassword---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public @ResponseBody WSResponseStatus updateProfile(@RequestParam("model") String objStr, @RequestParam(value="file", required=false) MultipartFile file){
		logger.info("saveProfile() :: objStr: "+objStr+" ::file: "+file);
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster userMaster = new UserMaster();
		try {
			userMaster = new ObjectMapper().readValue(objStr, UserMaster.class);
		} catch (JsonParseException e) {
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
		} catch (JsonMappingException e) {
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
		} catch (IOException e) {
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
		}
		MealTimeUtil.uploadProfilePic(file, userMaster, userMaster.getUserId(), wsResponseStatus);
		int count = adminService.updateProfile(userMaster);
		if(count == 0){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Update Failed");
		}else{
			String message = "Hi, Your Profile has been updated by Admin.";
			//mealTimeUtil.sendSMS(userMaster.getMobileNumber(), message);
			String subject = "MealTime - Profile Updated";
			String msgBody = "<i>Hi!</i><br><br>";
			msgBody += "<b>Welcome to MealTime!</b><br>";
			msgBody += "Your Profile has been updated<br><br>";
			msgBody += "Regards, <br>Meal Time Team";
			mealTimeUtil.sendEmail(userMaster.getEmail(), "info@mealtime.co.in", subject, msgBody);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(userMaster);
		}
		return wsResponseStatus;
	}
	
	@RequestMapping("/admin/getPendingSubscribedUsers")
	public @ResponseBody WSResponseStatus getPendingSubscribedUsers(){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		List<UserSubscription> userSubscriptions = userSubscriptionService.getPendingSubscribedUsers();
		wsResponseStatus.setData(userSubscriptions);
		MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		return wsResponseStatus;
	}
	
	@RequestMapping("/admin/payUser")
	public @ResponseBody WSResponseStatus payUser(@RequestBody PaymentForm paymentForm){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		int count = userSubscriptionService.payUser(paymentForm);
		if(count > 0){
			userSubscriptionService.addWallet(paymentForm);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			// Email Notification
			String subject = "MealTime:: Payment Confirmation";
			String msgBody = "<i>Hi!</i><br><br>";
			msgBody += "<b>Welcome to MealTime!</b><br>";
			msgBody += "Your Payment amount of Rs.4650.00 has been recieved by Admin<br>";
			msgBody += "Your updated wallet balance is Rs.1000.00<br><br>";
			msgBody += "Regards, <br>Meal Time Team";
			mealTimeUtil.sendEmail(paymentForm.getEmail(), "info@mealtime.co.in", subject, msgBody);
			String message = "Your payment amount of Rs.4650.00 has been recieved. Your updated wallet balance is Rs.1000.00";
			//mealTimeUtil.sendSMS(paymentForm.getMobileNumber(), message);
		}else{
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Error while Updating User Subscription Table");
		}
		return wsResponseStatus;
	}
}
