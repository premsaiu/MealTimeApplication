package com.mealtime.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.SampleMeal;
import com.mealtime.bean.UserMaster;
import com.mealtime.dao.SampleMealDAO;
import com.mealtime.dao.UserMasterDAO;

@Service
public class SampleMealService {
	
	@Autowired
	SampleMealDAO sampleMealDAO;
	
	@Autowired
	private UserMasterDAO userMasterDAO;
	
	public boolean requestSampleMeal(SampleMeal sampleMeal, UserMaster user){
		try{
			sampleMeal.setCreatedDate(new Date());
			sampleMeal.setIsActive("YES");
			sampleMeal.setStatus("Open");
			sampleMealDAO.insert(sampleMeal);
			user.setArea(sampleMeal.getAddress());
			userMasterDAO.update(user);
			return true;
		}catch(Exception e){
			System.out.println("Exception in request Sample Meal :: "+e.getMessage());
			return false;
		}
	}
	
	public boolean checkSampleMeal(String userId){
		SampleMeal sampleMeal = sampleMealDAO.findByUserId(userId);
		if(sampleMeal == null){
			return true;
		}else{
			return false;
		}
	}
	
	public SampleMeal getSampleMeal(String userId){
		return sampleMealDAO.findByUserId(userId);
	}

}
