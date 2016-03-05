package com.mealtime.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.UserMaster;
import com.mealtime.bean.UserSubscription;
import com.mealtime.dao.UserSubscriptionDAO;

@Service
public class UserSubscriptionService {
	
	@Autowired
	UserSubscriptionDAO userSubscriptionDAO;
	
	public void subscribeUser(UserMaster user){
		UserSubscription userSubscription = new UserSubscription();
		userSubscription.setUserId(user.getUserId());
		userSubscription.setSubscriptionId(1);
		userSubscription.setStatus("In Progress");
		userSubscription.setIsActive("NO");
		/*Date date = new Date();
		DateFormat currentDate = DateFormat.getDateInstance();
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = currentDate.parse(currentDate.format(date.getTime() + 1 * 1000 * 60 * 60 * 24));
			endDate = currentDate.parse(currentDate.format(date.getTime() + 30L * 1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Start Date: "+startDate);
		System.out.println("End Date: "+endDate);
		userSubscription.setStartDate(startDate);
		userSubscription.setEndDate(endDate);*/
		userSubscriptionDAO.insert(userSubscription);
	}
	
	public UserSubscription checkSubscription(String userId){
		UserSubscription userSubscription = new UserSubscription();
		userSubscription = userSubscriptionDAO.findByUserId(userId);
		return userSubscription;
	}

}
