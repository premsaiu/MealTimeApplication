package com.mealtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.UserMaster;
import com.mealtime.dao.UserMasterDAO;

@Service
public class MealTimeService {

	@Autowired
	UserMasterDAO userMasterDAO;
	
	public UserMaster checkUser(String mobileNumber){
		System.out.println("In MealTimeService :: checkUser()");
		return userMasterDAO.findByMobileNumber(mobileNumber);
	}
}
