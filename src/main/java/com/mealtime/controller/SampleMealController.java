package com.mealtime.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.SampleMeal;
import com.mealtime.bean.ScheduleEnquiry;
import com.mealtime.bean.UserMaster;
import com.mealtime.service.MealTimeService;
import com.mealtime.service.SampleMealService;
import com.mealtime.service.ScheduleEnquiryService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class SampleMealController {
	
	@Autowired
	SampleMealService sampleMealService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	MealTimeService mealTimeService;
	
	@Autowired
	ScheduleEnquiryService scheduleEnquiryService;
	
	@RequestMapping("/sampleMeal")
	public @ResponseBody WSResponseStatus requestSampleMeal(@RequestBody SampleMeal sampleMeal){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster user = mealTimeService.checkUser(sampleMeal.getMobileNumber(), sampleMeal.getName());
		wsResponseStatus.setData(user);
		sampleMeal.setUserId(user.getUserId());
		boolean isCheckSampleMeal = sampleMealService.checkSampleMeal(sampleMeal.getUserId());
		if(isCheckSampleMeal){
			boolean isSuccess = sampleMealService.requestSampleMeal(sampleMeal, user);
			if(isSuccess){
				MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				String message = "Thank you for trying our Sample Meal option. Breakfast with Juice n Dinner with Dessert will be delivered for one day.(www.mealtime.co.in)- Team Meal Time.";
				mealTimeUtil.sendSMS(sampleMeal.getMobileNumber(), message);
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
	public @ResponseBody WSResponseStatus checkSampleMeal(@RequestParam("mobileNumber")String mobileNumber, @RequestParam("sampleMealDate") String sampleMealDate, @RequestParam("name") String name){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster user = mealTimeService.checkUser(mobileNumber, name);
		if(user == null){
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else{
			boolean isCheckSampleMeal = sampleMealService.checkSampleMeal(user.getUserId());
			if(isCheckSampleMeal){
				ScheduleEnquiry scheduleEnquiry = scheduleEnquiryService.getScheduleEnquiry(user.getUserId());
				if(scheduleEnquiry == null){
					MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				}else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date sampleDate = null;
					try {
						sampleDate = sdf.parse(sampleMealDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Timestamp scheduleDateTime = scheduleEnquiry.getScheduleDateTime();
					Date scheduleDate = new Date(scheduleDateTime.getTime());
					System.out.println("Sample Date :: "+sampleDate+" Schedule Date :: "+scheduleDate);
					if(!DateUtils.isSameDay(sampleDate, scheduleDate)){
						MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
					}else{
						MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Sample Meal and Schedule Enquiry Services should not be taken on the same day");
					}
				}
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Sample meal service already used");
			}
		}
		return wsResponseStatus;
	}

}
