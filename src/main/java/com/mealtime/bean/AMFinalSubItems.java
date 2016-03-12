package com.mealtime.bean;

import java.util.List;

public class AMFinalSubItems {

	private String userId;
	
	private List<AmSubItems> amSubItemsList;
	
	private int addId;

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AmSubItems> getAmSubItemsList() {
		return amSubItemsList;
	}

	public void setAmSubItemsList(List<AmSubItems> amSubItemsList) {
		this.amSubItemsList = amSubItemsList;
	}
	
}
