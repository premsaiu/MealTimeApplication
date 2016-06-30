package com.mealtime.form;

import java.util.Date;

public class ScheduleEnquiryForm {
	
	 private Integer userSubscriptionId;

	    //----------------------------------------------------------------------
	    // ENTITY DATA FIELDS 
	    //----------------------------------------------------------------------    
	    // DB : user_id VARCHAR 
	    private String userId;

	    // DB : subscription_id INT 
	    private Integer subscriptionId;

	    // DB : start_date DATE 
	    private Date startDate;

	    // DB : end_date DATE 
	    private Date endDate;
	    
	    private String name;
	    
	    private String mobileNumber;
	    
	    private String address;

		public Integer getUserSubscriptionId() {
			return userSubscriptionId;
		}

		public void setUserSubscriptionId(Integer userSubscriptionId) {
			this.userSubscriptionId = userSubscriptionId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Integer getSubscriptionId() {
			return subscriptionId;
		}

		public void setSubscriptionId(Integer subscriptionId) {
			this.subscriptionId = subscriptionId;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	    
	    

}
