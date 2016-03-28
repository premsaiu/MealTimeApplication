package com.mealtime.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.ScheduleEnquiry;
import com.mealtime.dao.ScheduleEnquiryDAO;

@Service
public class ScheduleEnquiryService {
	
	@Autowired
	private ScheduleEnquiryDAO scheduleEnquiryDAO;
	
	public boolean scheduleEnquiry(ScheduleEnquiry scheduleEnquiry){
		try{
			System.out.println("Date Time :: "+scheduleEnquiry.getScheduleDateTime());
			scheduleEnquiry.setCreatedDate(new Date());
			scheduleEnquiry.setIsActive("YES");
			scheduleEnquiryDAO.insert(scheduleEnquiry);
			return true;
		}catch(Exception e){
			System.out.println("Exception while saving:: "+e.getMessage());
			return false;
		}
	}
	
	public boolean checkScheduleEnquiry(String userId){
		ScheduleEnquiry scheduleEnquiry = scheduleEnquiryDAO.findByUserId(userId);
		if(scheduleEnquiry == null){
			return true;
		}else{
			return false;
		}
	}

}
