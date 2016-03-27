package com.mealtime.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.SampleMeal;
import com.mealtime.dao.SampleMealDAO;

@Service
public class SampleMealService {
	
	@Autowired
	SampleMealDAO sampleMealDAO;
	
	public boolean requestSampleMeal(SampleMeal sampleMeal){
		try{
			sampleMeal.setCreatedDate(new Date());
			sampleMeal.setIsActive("YES");
			sampleMeal.setStatus("Open");
			sampleMealDAO.insert(sampleMeal);
			return true;
		}catch(Exception e){
			System.out.println("Exception in request Sample Meal :: "+e.getMessage());
			return false;
		}
	}

}
