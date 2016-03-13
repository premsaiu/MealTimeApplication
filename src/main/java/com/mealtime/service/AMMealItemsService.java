package com.mealtime.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AMFinalSubItems;
import com.mealtime.bean.AmItems;
import com.mealtime.bean.AmSubItems;
import com.mealtime.bean.AmUpdatedItems;
import com.mealtime.bean.AmUpdatedSubItems;
import com.mealtime.bean.UserSubscription;
import com.mealtime.bean.UserWallet;
import com.mealtime.dao.AmItemsDAO;
import com.mealtime.dao.AmSubItemsDAO;
import com.mealtime.dao.AmUpdatedItemsDAO;
import com.mealtime.dao.AmUpdatedSubItemsDAO;
import com.mealtime.dao.UserSubscriptionDAO;
import com.mealtime.dao.UserWalletDAO;

@Service
public class AMMealItemsService {
	
	@Autowired
	private AmSubItemsDAO amSubItemsDAO;
	
	@Autowired
	private AmUpdatedItemsDAO amUpdatedItemsDAO;
	
	@Autowired
	private AmItemsDAO amItemsDAO;
	
	@Autowired
	private UserWalletDAO userWalletDAO;
	
	@Autowired
	private UserSubscriptionDAO userSubscriptionDAO;
	
	@Autowired
	private AmUpdatedSubItemsDAO amUpdatedSubItemsDAO;
	
	private static String COMPLEMENTARY = "Complementary"; 
	
	private static final Logger logger = Logger.getLogger(AMMealItemsService.class);
	
	public Map<Integer, List<AmSubItems>> getSubItemsList(){
		List<AmSubItems> amSubItems = amSubItemsDAO.getItemsList();
		
		Map<Integer,List<AmSubItems>> map = new HashMap<Integer,List<AmSubItems>>();
		
		List<AmSubItems> complItemsList = new ArrayList<AmSubItems>();
		List<AmSubItems> supplItemsList = new ArrayList<AmSubItems>();
		
		for(AmSubItems item: amSubItems){
			if(item.getItemType().equalsIgnoreCase(COMPLEMENTARY)){
				complItemsList.add(item);
			}else{
				supplItemsList.add(item);
			}
		}
		map.put(1, complItemsList);
		map.put(2,supplItemsList);
		return map;
	}

	public AmItems getBreakfastItem(String userId,Integer addId){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(userId != null){
			
			UserSubscription userSubscription = userSubscriptionDAO.findByUserId(userId);
			if(userSubscription != null && userSubscription.getIsActive().equalsIgnoreCase("YES") &&
					userSubscription.getEndDate().after(date)){
				
				AmUpdatedItems amUpdatedItems = amUpdatedItemsDAO.findByUserId(userId);
				if(amUpdatedItems != null && dateFormat.format(amUpdatedItems.getModifiedItemDate()).equals(dateFormat.format(date))){
					if(amUpdatedItems.getStatus().equalsIgnoreCase("cancelled")){
						if(addId != 0){
							AmItems amItems = amItemsDAO.getAMItemObj();
							if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
								DateFormat hourFormat = new SimpleDateFormat("HH");
								/*AmUpdatedItems amUpdatedItems1 = new AmUpdatedItems();
									amUpdatedItems1.setItemId(amItems.getItemId());
									amUpdatedItems1.setUserId(userId);
									amUpdatedItems1.setModifiedItemDate(new Date());
									amUpdatedItems1.setStatus("Active");
									amUpdatedItems1.setIsActive("YES");
									int i =	amUpdatedItemsDAO.update(amUpdatedItems1);*/
								amUpdatedItemsDAO.deleteUserRecord(amItems.getItemId(),userId);
								AmUpdatedItems amUpdatedItems1 = new AmUpdatedItems();
								amUpdatedItems1.setItemId(amItems.getItemId());
								amUpdatedItems1.setUserId(userId);
								amUpdatedItems1.setModifiedItemDate(new Date());
								amUpdatedItems1.setStatus("Active");
								amUpdatedItems1.setIsActive("YES");
								amUpdatedItemsDAO.insert(amUpdatedItems1);
								
								if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
									amItems.setStatus("Today's Breakfast Special");
								}else{
									amItems.setStatus("Tommorow's Breakfast Special");
								}
								return amItems;
							}else{
								return null;
							}
						}else {
							return null;
						}
					}else{
						AmItems amItems = amItemsDAO.getAMItemObj();
						if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
							DateFormat hourFormat = new SimpleDateFormat("HH");
							logger.debug(date.getTime());
							if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
								amItems.setStatus("Today's Breakfast Special");
							}else{
								amItems.setStatus("Tommorow's Breakfast Special");
							}
							return amItems;
						}else{
							return null;
						}
					}
				}else{
					AmItems amItems = amItemsDAO.getAMItemObj();
					if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
						DateFormat hourFormat = new SimpleDateFormat("HH");
						logger.debug(date.getTime());
						if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
							amItems.setStatus("Today's Breakfast Special");
						}else{
							amItems.setStatus("Tommorow's Breakfast Special");
						}
						return amItems;
					}else{
						return null;
					}
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public AMFinalSubItems getBreakfastSubItem(AMFinalSubItems amMfinalSubItems){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<AmSubItems> amSubItemsList = null;
		if(amMfinalSubItems.getUserId() != null){
			AMFinalSubItems amMfinalSubItems2 = null;
			UserSubscription userSubscription = userSubscriptionDAO.findByUserId(amMfinalSubItems.getUserId());
			if(userSubscription != null && userSubscription.getIsActive().equalsIgnoreCase("YES") &&
					userSubscription.getEndDate().after(date)){
				if(amMfinalSubItems.getAddId() != 0 && amMfinalSubItems.getAmSubItemsList().size() > 0){
					amMfinalSubItems2 = new AMFinalSubItems();
					List<AmSubItems> amSubItems = new ArrayList<AmSubItems>();
					
					amUpdatedSubItemsDAO.deleteCurDateUserRecord(amMfinalSubItems.getUserId(),dateFormat.format(date));
					
					for (AmSubItems amSubItems1 : amMfinalSubItems.getAmSubItemsList()) {
						AmUpdatedSubItems amUpdatedSubItems = amUpdatedSubItemsDAO.findByUserId(amMfinalSubItems.getUserId(),amSubItems1.getItemId());
								amSubItemsList = new ArrayList<AmSubItems>();
									if(amUpdatedSubItems != null && dateFormat.format(amUpdatedSubItems.getModifiedItemDate()).equals(dateFormat.format(date))){
											if(amMfinalSubItems.getAddId() != 0 && amUpdatedSubItems.getStatus().equalsIgnoreCase("cancelled")){
												AmItems amItems = amItemsDAO.getAMItemObj();
												if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
													amUpdatedSubItemsDAO.deleteUserRecord(amUpdatedSubItems.getSubItemId(),amMfinalSubItems.getUserId());
													AmUpdatedSubItems amUpdatedSubItems3 = new AmUpdatedSubItems();
													amUpdatedSubItems3.setSubItemId(amSubItems1.getItemId());
													amUpdatedSubItems3.setUserId(amMfinalSubItems.getUserId());
													amUpdatedSubItems3.setModifiedItemDate(new Date());
													amUpdatedSubItems3.setStatus("Active");
													amUpdatedSubItems3.setIsActive("YES");
													amUpdatedSubItemsDAO.insert(amUpdatedSubItems3);
													amSubItems1.setSelected(true);
													amSubItemsList.add(amSubItems1);
												}else{
													return null;
												}
										}else if(amMfinalSubItems.getAddId() != 0 && amUpdatedSubItems.getStatus().equalsIgnoreCase("Active")){
											AmItems amItems = amItemsDAO.getAMItemObj();
											if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
												amSubItems1.setSelected(true);
												amSubItemsList.add(amSubItems1);
											}else{
												return null;
											}
										}
										else{
											return null;
										}
									}else{
										//amUpdatedSubItemsDAO.deleteUserRecord(amUpdatedSubItems.getSubItemId(),amMfinalSubItems.getUserId());
										AmUpdatedSubItems amUpdatedSubItems3 = new AmUpdatedSubItems();
										amUpdatedSubItems3.setSubItemId(amSubItems1.getItemId());
										amUpdatedSubItems3.setUserId(amMfinalSubItems.getUserId());
										amUpdatedSubItems3.setModifiedItemDate(new Date());
										amUpdatedSubItems3.setStatus("Active");
										amUpdatedSubItems3.setIsActive("YES");
										amUpdatedSubItemsDAO.insert(amUpdatedSubItems3);
										amSubItems1.setSelected(true);
										amSubItemsList.add(amSubItems1);
									}
									amSubItems.addAll(amSubItemsList);
					}
					amMfinalSubItems2.setAmSubItemsList(amSubItems);
					return amMfinalSubItems2;
				}else{
					List<AmUpdatedSubItems> amUpdatedSubItems = amUpdatedSubItemsDAO.findById(amMfinalSubItems.getUserId());
					//AmSubItems amSubItems1 = new AmSubItems();
					List<AmSubItems> amSubItems = new ArrayList<AmSubItems>();
					amMfinalSubItems2 = new AMFinalSubItems();
					for (AmUpdatedSubItems amUpdatedSubItems2 : amUpdatedSubItems) {
						if(amUpdatedSubItems2 != null && dateFormat.format(amUpdatedSubItems2.getModifiedItemDate()).equals(dateFormat.format(date))
								&& amMfinalSubItems.getAddId() == 0 && amUpdatedSubItems2.getStatus().equalsIgnoreCase("Active")){
							AmItems amItems = amItemsDAO.getAMItemObj();
							AmSubItems amSubItems2 = amSubItemsDAO.find(amUpdatedSubItems2.getSubItemId());
							amSubItemsList = new ArrayList<AmSubItems>();
							if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
								amSubItems2.setSelected(true);
								amSubItemsList.add(amSubItems2);
							}else{
								return null;
							}
							amSubItems.addAll(amSubItemsList);
						}
					}
					amMfinalSubItems2.setAmSubItemsList(amSubItems);
					return amMfinalSubItems2;
					}
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public void cancelItem(int itemId,String userId){
		int i = amUpdatedItemsDAO.deleteUserRecord(itemId,userId);
		//if(i == 0){
			AmUpdatedItems amUpdatedItems = new AmUpdatedItems();
			amUpdatedItems.setItemId(itemId);
			amUpdatedItems.setUserId(userId);
			amUpdatedItems.setModifiedItemDate(new Date());
			amUpdatedItems.setStatus("cancelled");
			amUpdatedItems.setIsActive("YES");
		amUpdatedItemsDAO.insert(amUpdatedItems);
		//}
	}
	
	public UserWallet walletCheck(String userId){
		UserWallet userWallet = userWalletDAO.findByUserId(userId);
		if(userWallet.getCash() != 0 && userWallet.getIsActive().equalsIgnoreCase("YES")){
			return userWallet;	
		}else{
			return null;
		}
	}
	
	public void payment(String userId,Double paidAmount){
		//UserWallet userWallet = userWalletDAO.findByUserId(userId);
		int i = userWalletDAO.deleteUserRecord(userId);
		UserWallet userWallet2 = new UserWallet();
		userWallet2.setUserId(userId);
		userWallet2.setCash(paidAmount.intValue());
		userWallet2.setStatus("success");
		userWallet2.setIsActive("YES");
		userWallet2.setVersion(1);
		userWalletDAO.insert(userWallet2);
		//userWalletDAO.update(userWallet);
	}
	
	public void deleteSubItemAddon(Integer subItemId, String userId){
		amUpdatedSubItemsDAO.deleteUserRecord(subItemId,userId);
		AmUpdatedSubItems amUpdatedSubItems3 = new AmUpdatedSubItems();
		amUpdatedSubItems3.setSubItemId(subItemId);
		amUpdatedSubItems3.setUserId(userId);
		amUpdatedSubItems3.setModifiedItemDate(new Date());
		amUpdatedSubItems3.setStatus("Cancelled");
		amUpdatedSubItems3.setIsActive("YES");
		amUpdatedSubItemsDAO.insert(amUpdatedSubItems3);
	}
}
