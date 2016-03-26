package com.mealtime.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mealtime.bean.ScheduleEnquiry;
import com.mealtime.dao.ScheduleEnquiryDAO;

public class ScheduleEnquiryService {
	
	@Autowired
	private ScheduleEnquiryDAO scheduleEnquiryDAO;
	
	public boolean scheduleEnquiry(ScheduleEnquiry scheduleEnquiry){
		System.out.println("Date Time :: "+scheduleEnquiry.getScheduleDateTime());
		scheduleEnquiry.setCreatedDate(new Date());
		scheduleEnquiry.setIsActive("YES");
		scheduleEnquiryDAO.insert(scheduleEnquiry);
		return false;
	}

}
