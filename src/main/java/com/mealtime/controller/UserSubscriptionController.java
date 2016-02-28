package com.mealtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.UserMaster;
import com.mealtime.service.UserSubscriptionService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class UserSubscriptionController {
	
	@Autowired
	UserSubscriptionService userSubscriptionService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@RequestMapping(value="/subscribeuser", method = RequestMethod.POST)
	public @ResponseBody WSResponseStatus subscribeUser(@RequestBody UserMaster user){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		userSubscriptionService.subscribeUser(user);
		String subject = "Meal Time Subscription";
		String msgBody = "<i>Hi!</i><br><br>";
		msgBody += "<b>Welcome to MealTime!</b><br>";
		msgBody += "Your Subscription request for Meal Time is in process</b>.<br>";
		msgBody += "Our excecutive will reach you shortly and collect the payment. After payment your subscription will become active<br><br>";
		msgBody += "Regards, <br>Meal Time Team";
		mealTimeUtil.sendEmail("premcse41@gmail.com", user.getEmail(), subject, msgBody);
		/*String message = "";
		message = "test message "+"Your Subscription request for Meal Time is in process";
		MealTimeUtil.sendSMS(user.getMobileNumber(), message);*/
		mealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		return wsResponseStatus;
	}
}
