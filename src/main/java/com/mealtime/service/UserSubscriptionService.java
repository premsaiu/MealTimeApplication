package com.mealtime.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.UserMaster;
import com.mealtime.bean.UserSubscription;
import com.mealtime.bean.UserWallet;
import com.mealtime.dao.UserMasterDAO;
import com.mealtime.dao.UserSubscriptionDAO;
import com.mealtime.dao.UserWalletDAO;
import com.mealtime.form.PaymentContainer;
import com.mealtime.form.PaymentForm;
import com.mealtime.util.MealTimeUtil;

@Service
public class UserSubscriptionService {
	
	@Autowired
	UserSubscriptionDAO userSubscriptionDAO;
	
	@Autowired
	UserMasterDAO userMasterDAO;
	
	@Autowired
	UserWalletDAO userWalletDAO;
	
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
	
	public PaymentContainer checkSubscription(String userId){
		UserSubscription userSubscription = new UserSubscription();
		PaymentContainer paymentContainer = new PaymentContainer();
		userSubscription = userSubscriptionDAO.findByUserId(userId);
		Date date = new Date();
		if(userSubscription != null && userSubscription.getEndDate() != null && userSubscription.getEndDate().after(date)){
			userSubscription.setConfirmation(true);
		}
		
		UserWallet userWallet = userWalletDAO.findByUserId(userId);
		paymentContainer.setUserSubscription(userSubscription);
		paymentContainer.setUserWallet(userWallet);
		return paymentContainer;
	}
	
	public List<UserSubscription> getPendingSubscribedUsers(){
		List<UserSubscription> userSubscriptions = new ArrayList<UserSubscription>();
		userSubscriptions = userSubscriptionDAO.getPendingSubscribedUsers();
		for(UserSubscription userSubscription : userSubscriptions){
			UserMaster userMaster = userMasterDAO.find(userSubscription.getUserId());
			userSubscription.setUserMaster(userMaster);
		}
		return userSubscriptions;
	}
	
	public int payUser(PaymentForm paymentForm){
		int count = 0;
		UserSubscription userSubscription = userSubscriptionDAO.find(paymentForm.getUserSubscriptionId());
		Date startDate = MealTimeUtil.convertStrDate(paymentForm.getStartDate());
		Date endDate = MealTimeUtil.convertStrDate(paymentForm.getEndDate());
		userSubscription.setStartDate(startDate);
		userSubscription.setEndDate(endDate);
		userSubscription.setStatus("Success");
		userSubscription.setIsActive("Yes");
		userSubscription.setUpdatedBy(paymentForm.getAdminUserId());
		userSubscription.setUpdatedDate(new Date());
		count = userSubscriptionDAO.update(userSubscription);
		return count;
	}
	
	public void addWallet(PaymentForm paymentForm){
		UserWallet userWallet = new UserWallet();
		userWallet.setUserId(paymentForm.getUserId());
		Double amt = paymentForm.getAmount() - 3650;
		userWallet.setCash(amt.intValue());
		userWallet.setIsActive("YES");
		userWallet.setStatus("Success");
		userWallet.setCreatedBy(paymentForm.getAdminUserId());
		userWalletDAO.insert(userWallet);
	}
	
	public UserMaster subscribeNow(String firstName,String lastName,String mobile,String date, String area){
		if(mobile.matches("\\d{10}")){
			UserMaster userMaster = userMasterDAO.findByMobileNumber(mobile);
			if(userMaster == null){
				
				userMaster =  new UserMaster();
				userMaster.setMobileNumber(mobile);
				userMaster.setRoleId(2);
				userMaster.setFirstName(firstName);
				userMaster.setLastName(lastName);
				userMaster.setCreatedDate(new Date());
				userMaster.setAddress(area);
				//userMaster.setIsActive("NO");
				//userMaster.setStatus("Visitor");
				userMasterDAO.insert(userMaster);
				
				userMaster = userMasterDAO.findByMobileNumber(mobile);
				
				if(userMaster != null){
					UserSubscription userSubscription = null;
					
					userSubscription = userSubscriptionDAO.findByUserId(userMaster.getUserId());
					
					if(userSubscription != null){
						userSubscription.setUserId(userMaster.getUserId());
						userSubscription.setSubscriptionId(1);
						userSubscription.setStartDate(new Date());
						userSubscription.setEndDate(DateUtils.addMonths(new Date(), 1));
						userSubscription.setCreatedBy(userMaster.getUserId());
						userSubscription.setStatus("Success");
						userSubscription.setIsActive("YES");
						userSubscriptionDAO.update(userSubscription);
						
						UserWallet userWallet = null;
						
						userWallet = userWalletDAO.findByUserId(userMaster.getUserId());
						
						if(userWallet != null){
							userWallet.setUserId(userMaster.getUserId());
							userWallet.setCash(1000);
							userWallet.setStatus("Success");
							userWallet.setIsActive("YES");
							userWalletDAO.update(userWallet);
						}else{
							
							userWalletDAO.deleteUserRecord(userMaster.getUserId());
							
							userWallet = new UserWallet();
							
							userWallet.setUserId(userMaster.getUserId());
							userWallet.setCash(1000);
							userWallet.setStatus("Success");
							userWallet.setIsActive("YES");
							userWalletDAO.insert(userWallet);
						}
						
					}else{
						
						userSubscriptionDAO.delete(userMaster.getUserId());
						
						userSubscription = new UserSubscription();
						
						userSubscription.setUserId(userMaster.getUserId());
						userSubscription.setSubscriptionId(1);
						userSubscription.setStartDate(new Date());
						userSubscription.setEndDate(DateUtils.addMonths(new Date(), 1));
						userSubscription.setCreatedBy(userMaster.getUserId());
						userSubscription.setStatus("Success");
						userSubscription.setIsActive("YES");
						userSubscriptionDAO.insert(userSubscription);
						
						UserWallet userWallet = null;
						
						userWallet = userWalletDAO.findByUserId(userMaster.getUserId());
						
						if(userWallet != null){
							userWallet.setUserId(userMaster.getUserId());
							userWallet.setCash(1000);
							userWallet.setStatus("Success");
							userWallet.setIsActive("YES");
							userWalletDAO.update(userWallet);
						}else{
							userWalletDAO.deleteUserRecord(userMaster.getUserId());
							
							userWallet = new UserWallet();
							
							userWallet.setUserId(userMaster.getUserId());
							userWallet.setCash(1000);
							userWallet.setStatus("Success");
							userWallet.setIsActive("YES");
							userWalletDAO.insert(userWallet);
						}
					}
				}
			return userMaster;
		}else{
			userMaster =  new UserMaster();
			
			userMaster.setMobileNumber(mobile);
			userMaster.setFirstName(firstName);
			userMaster.setLastName(lastName);
			userMaster.setRoleId(2);
			userMaster.setCreatedDate(new Date());
			userMaster.setAddress(area);
			userMasterDAO.update(userMaster);
			
			userMaster = userMasterDAO.findByMobileNumber(mobile);
			
			if(userMaster != null){
				UserSubscription userSubscription = null;
				
				userSubscription = userSubscriptionDAO.findByUserId(userMaster.getUserId());
				
				if(userSubscription != null){
					userSubscription.setUserId(userMaster.getUserId());
					userSubscription.setSubscriptionId(1);
					userSubscription.setStartDate(new Date());
					userSubscription.setEndDate(DateUtils.addMonths(new Date(), 1));
					userSubscription.setCreatedBy(userMaster.getUserId());
					userSubscription.setStatus("Success");
					userSubscription.setIsActive("YES");
					userSubscriptionDAO.update(userSubscription);
					
					UserWallet userWallet = null;
					
					userWallet = userWalletDAO.findByUserId(userMaster.getUserId());
					
					if(userWallet != null){
						userWallet.setUserId(userMaster.getUserId());
						userWallet.setCash(1000);
						userWallet.setStatus("Success");
						userWallet.setIsActive("YES");
						userWalletDAO.update(userWallet);
					}else{
						
						userWalletDAO.deleteUserRecord(userMaster.getUserId());
						
						userWallet = new UserWallet();
						
						userWallet.setUserId(userMaster.getUserId());
						userWallet.setCash(1000);
						userWallet.setStatus("Success");
						userWallet.setIsActive("YES");
						userWalletDAO.insert(userWallet);
					}
					
				}else{
					userSubscriptionDAO.delete(userMaster.getUserId());
					
					userSubscription = new UserSubscription();
					
					userSubscription.setUserId(userMaster.getUserId());
					userSubscription.setSubscriptionId(1);
					userSubscription.setStartDate(new Date());
					userSubscription.setEndDate(DateUtils.addMonths(new Date(), 1));
					userSubscription.setCreatedBy(userMaster.getUserId());
					userSubscription.setStatus("Success");
					userSubscription.setIsActive("YES");
					userSubscriptionDAO.insert(userSubscription);
					
					UserWallet userWallet = null;
					
					userWallet = userWalletDAO.findByUserId(userMaster.getUserId());
					
					if(userWallet != null){
						userWallet.setUserId(userMaster.getUserId());
						userWallet.setCash(1000);
						userWallet.setStatus("Success");
						userWallet.setIsActive("YES");
						userWalletDAO.update(userWallet);
					}else{
						userWalletDAO.deleteUserRecord(userMaster.getUserId());
						
						userWallet = new UserWallet();
						
						userWallet.setUserId(userMaster.getUserId());
						userWallet.setCash(1000);
						userWallet.setStatus("Success");
						userWallet.setIsActive("YES");
						userWalletDAO.insert(userWallet);
					}
				}
				
			}
			return userMaster;
		}
	  }else{
		  return null;
	  }
	}
}
