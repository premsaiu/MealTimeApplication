package com.mealtime.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ScheduleEnquiryController {
	
	@Autowired
	private ScheduleEnquiryService scheduleEnquiryService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	MealTimeService mealTimeService;
	
	@Autowired
	SampleMealService sampleMealService;
	
	@RequestMapping("/scheduleEnquiry")
	public @ResponseBody WSResponseStatus scheduleEnquiry(@RequestBody ScheduleEnquiry scheduleEnquiry){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster user = mealTimeService.checkUser(scheduleEnquiry.getMobileNumber());
		scheduleEnquiry.setUserId(user.getUserId());
		boolean isCheckSchedule = scheduleEnquiryService.checkScheduleEnquiry(scheduleEnquiry.getUserId());
		if(isCheckSchedule){
			boolean isScheduleEnquiry = scheduleEnquiryService.scheduleEnquiry(scheduleEnquiry);
			if(isScheduleEnquiry){
				MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				String message = "Hi, Your Schedule Enquiry has been fixed. Our team will visit you shortly.";
				//mealTimeUtil.sendSMS(scheduleEnquiry.getMobileNumber(), message);
				/*String subject = "MealTime - Schedule Enquriy";
				String msgBody = "<i>Hi!</i><br><br>";
				msgBody += "<b>Welcome to MealTime!</b><br>";
				msgBody += "Your Schedule Enquiry has been fixed. Our team will visit you shortly.<br><br>";
				msgBody += "Regards, <br>Meal Time Team";
				mealTimeUtil.sendEmail(scheduleEnquiry.getEmail(), "premcse41@gmail.com", subject, msgBody);*/
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Failed to Save in table");
			}
		}else{
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Schedule Enquiry Service already used");
		}
		return wsResponseStatus;
	}
	
	@RequestMapping("/checkSchedule")
	public @ResponseBody WSResponseStatus checkScheduleEnquiry(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("scheduleEnqDate") String scheduleEnqDate){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster userMaster = mealTimeService.checkMobileNumber(mobileNumber);
		if(userMaster == null){
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else if(userMaster != null && userMaster.getRoleId() == 3){
			boolean isCheckSchedule = scheduleEnquiryService.checkScheduleEnquiry(userMaster.getUserId());
			if(isCheckSchedule){
				SampleMeal sampleMeal = sampleMealService.getSampleMeal(userMaster.getUserId());
				if(sampleMeal == null){
					MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
				}else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date schedDate = null;
					try {
						schedDate = sdf.parse(scheduleEnqDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Date sampleMealDate = sampleMeal.getSampleMealDate();
					System.out.println("Sample Date :: "+sampleMealDate+" Schedule Date :: "+schedDate);
					if(!DateUtils.isSameDay(sampleMealDate, schedDate)){
						MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
					}else{
						MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Sample Meal and Schedule Enquiry Services should not be taken on the same day");
					}
				}
			}else{
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Schedule Enquiry Service already used");
			}
		}else{
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Subscribed User are not allowed for schedule enquiry service");
		}
		return wsResponseStatus;
	}

}
