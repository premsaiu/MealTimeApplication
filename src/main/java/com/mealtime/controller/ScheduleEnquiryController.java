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
	
	@RequestMapping("/scheduleEnquiry")
	public @ResponseBody WSResponseStatus scheduleEnquiry(@RequestBody ScheduleEnquiry scheduleEnquiry){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		boolean isScheduleEnquiry = scheduleEnquiryService.scheduleEnquiry(scheduleEnquiry);
		if(isScheduleEnquiry){
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else{
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Failed to Save in table");
		}
		return wsResponseStatus;
	}

}
