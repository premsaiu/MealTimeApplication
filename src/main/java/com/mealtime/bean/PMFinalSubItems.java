package com.mealtime.bean;

import java.util.List;

public class PMFinalSubItems {

private String userId;
	
	private List<PmSubItems> pmSubItemsList;
	
	private int addId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the pmSubItemsList
	 */
	public List<PmSubItems> getPmSubItemsList() {
		return pmSubItemsList;
	}

	/**
	 * @param pmSubItemsList the pmSubItemsList to set
	 */
	public void setPmSubItemsList(List<PmSubItems> pmSubItemsList) {
		this.pmSubItemsList = pmSubItemsList;
	}

	/**
	 * @return the addId
	 */
	public int getAddId() {
		return addId;
	}

	/**
	 * @param addId the addId to set
	 */
	public void setAddId(int addId) {
		this.addId = addId;
	}
	
	
}
