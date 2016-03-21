package com.mealtime.form;

import com.mealtime.bean.UserSubscription;
import com.mealtime.bean.UserWallet;

public class PaymentContainer {
	
	UserSubscription userSubscription;
	UserWallet userWallet;
	
	public UserSubscription getUserSubscription() {
		return userSubscription;
	}
	public void setUserSubscription(UserSubscription userSubscription) {
		this.userSubscription = userSubscription;
	}
	public UserWallet getUserWallet() {
		return userWallet;
	}
	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}
	
	
}
