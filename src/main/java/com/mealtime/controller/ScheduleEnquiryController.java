package com.mealtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.ScheduleEnquiry;
import com.mealtime.service.ScheduleEnquiryService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class ScheduleEnquiryController {
	
	@Autowired
	private ScheduleEnquiryService scheduleEnquiryService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@RequestMapping("/scheduleEnquiry")
	public @ResponseBody WSResponseStatus scheduleEnquiry(@RequestBody ScheduleEnquiry scheduleEnquiry){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
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
		return wsResponseStatus;
	}

}
