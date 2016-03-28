package com.mealtime.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.UserMaster;
import com.mealtime.form.PaymentContainer;
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
		int count = userSubscriptionService.subscribeUser(user);
		if(count > 0){
			String subject = "Meal Time Payment";
			String msgBody = "<i>Hi!</i><br><br>";
			msgBody += "<b>Welcome to MealTime!</b><br>";
			msgBody += "Your Payment request for Meal Time is in process</b>.<br>";
			msgBody += "Our excecutive will reach you shortly and collect the payment. After payment your subscription will become active<br><br>";
			msgBody += "Regards, <br>Meal Time Team";
			mealTimeUtil.sendEmail("premcse41@gmail.com", user.getEmail(), subject, msgBody);
			/*String message = "";
			message = "test message "+"Your Subscription request for Meal Time is in process";
			MealTimeUtil.sendSMS(user.getMobileNumber(), message);*/
			mealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else{
			mealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong");
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value="/subscribecheck", method = RequestMethod.GET)
	public @ResponseBody WSResponseStatus checkSubscribtion(@RequestParam("userId")String userId){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		PaymentContainer paymentContainer = userSubscriptionService.checkSubscription(userId);
		mealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(paymentContainer);
		return wsResponseStatus;
	}
	
	@RequestMapping(value="/subscribeNow", method = RequestMethod.GET)
	public @ResponseBody WSResponseStatus subscribeNow(@RequestParam("firstname")String firstname, @RequestParam("lastname")String lastname, @RequestParam("mobile")String mobile, 
			@RequestParam("date")String date, @RequestParam("area")String area){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster userMaster = userSubscriptionService.subscribeNow(firstname,lastname,mobile,date,area);
		mealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(userMaster);
		return wsResponseStatus;
	}
	
}
