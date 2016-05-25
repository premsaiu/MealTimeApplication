package com.mealtime.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.ScheduleEnquiry;
import com.mealtime.bean.UserMaster;
import com.mealtime.dao.ScheduleEnquiryDAO;
import com.mealtime.dao.UserMasterDAO;

@Service
public class ScheduleEnquiryService {
	
	@Autowired
	private ScheduleEnquiryDAO scheduleEnquiryDAO;
	
	@Autowired
	private UserMasterDAO userMasterDAO;
	
	public boolean scheduleEnquiry(ScheduleEnquiry scheduleEnquiry, UserMaster user){
		try{
			System.out.println("Date Time :: "+scheduleEnquiry.getScheduleDateTime());
			scheduleEnquiry.setCreatedDate(new Date());
			scheduleEnquiry.setIsActive("YES");
			scheduleEnquiryDAO.insert(scheduleEnquiry);
			user.setArea(scheduleEnquiry.getAddress());
			userMasterDAO.update(user);
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
	
	public ScheduleEnquiry getScheduleEnquiry(String userId){
		return scheduleEnquiryDAO.findByUserId(userId);
	}

}
