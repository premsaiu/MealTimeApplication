package com.mealtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.SampleMeal;
import com.mealtime.bean.UserMaster;
import com.mealtime.service.MealTimeService;
import com.mealtime.service.SampleMealService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class SampleMealController {
	
	@Autowired
	SampleMealService sampleMealService;
	
	@Autowired
	MealTimeService mealTimeService;
	
	@RequestMapping("/sampleMeal")
	public @ResponseBody WSResponseStatus requestSampleMeal(@RequestBody SampleMeal sampleMeal){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster user = mealTimeService.checkUser(sampleMeal.getMobileNumber());
		sampleMeal.setUserId(user.getUserId());
		boolean isCheckSampleMeal = sampleMealService.checkSampleMeal(sampleMeal.getUserId());
		if(isCheckSampleMeal){
			boolean isSuccess = sampleMealService.requestSampleMeal(sampleMeal);
			if(isSuccess){
				MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				//String message = "Hi, Your Sample Meal request has been taken. Our team will deliver you on the requested date";
				//mealTimeUtil.sendSMS(sampleMeal.getMobileNumber(), message);
				/*String subject = "MealTime - Sample Meal";
				String msgBody = "<i>Hi!</i><br><br>";
				msgBody += "<b>Welcome to MealTime!</b><br>";
				msgBody += "Your Sample Meal request has been taken. Our team will deliver you on the requested date.<br><br>";
				msgBody += "Regards, <br>Meal Time Team";
				mealTimeUtil.sendEmail(sampleMeal.getEmail(), "premcse41@gmail.com", subject, msgBody);*/
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Failed to save Sample Meal");
			}
		}else{
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Sample meal service already used");
		}
		return wsResponseStatus;
	}
	
	@RequestMapping("/checkSampleMeal")
	public @ResponseBody WSResponseStatus checkSampleMeal(@RequestParam("mobileNumber")String mobileNumber){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster user = mealTimeService.checkUser(mobileNumber);
		if(user == null){
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else{
			boolean isCheckSampleMeal = sampleMealService.checkSampleMeal(user.getUserId());
			if(isCheckSampleMeal){
				MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Sample meal service already used");
			}
		}
		return wsResponseStatus;
	}

}
