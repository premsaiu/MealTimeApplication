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
	
	public int subscribeUser(UserMaster user){
		int count = 0;
		UserSubscription userSubscription = userSubscriptionDAO.findByUserId(user.getUserId());
		if(userSubscription != null){
			userSubscription.setStatus("Pending");
			userSubscription.setUpdatedDate(new Date());
			userSubscription.setUpdatedBy(user.getUserId());
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
			count = userSubscriptionDAO.update(userSubscription);
		}
		return count;
	}
	
	public UserSubscription checkSubscription(String userId){
		UserSubscription userSubscription = new UserSubscription();
		userSubscription = userSubscriptionDAO.findByUserId(userId);
		return userSubscription;
	}

}
