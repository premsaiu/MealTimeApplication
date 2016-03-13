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

import com.mealtime.bean.PMFinalSubItems;
import com.mealtime.bean.PmItems;
import com.mealtime.bean.PmSubItems;
import com.mealtime.bean.PmUpdatedItems;
import com.mealtime.bean.PmUpdatedSubItems;
import com.mealtime.bean.UserSubscription;
import com.mealtime.bean.UserWallet;
import com.mealtime.dao.PmItemsDAO;
import com.mealtime.dao.PmSubItemsDAO;
import com.mealtime.dao.PmUpdatedItemsDAO;
import com.mealtime.dao.PmUpdatedSubItemsDAO;
import com.mealtime.dao.UserSubscriptionDAO;
import com.mealtime.dao.UserWalletDAO;

@Service
public class PMMealItemsService {
	
	@Autowired
	private PmSubItemsDAO pmSubItemsDAO;
	
	@Autowired
	private PmUpdatedItemsDAO pmUpdatedItemsDAO;
	
	@Autowired
	private PmItemsDAO pmItemsDAO;
	
	@Autowired
	private UserWalletDAO userWalletDAO;
	
	@Autowired
	private UserSubscriptionDAO userSubscriptionDAO;
	
	@Autowired
	private PmUpdatedSubItemsDAO pmUpdatedSubItemsDAO;
	
	private static final Logger logger = Logger.getLogger(PMMealItemsService.class);
	
	private static String COMPLEMENTARY = "Complementary"; 
	
	
	public Map<Integer, List<PmSubItems>> getSubItemsList(){
		List<PmSubItems> amSubItems = pmSubItemsDAO.getItemsList();
		
		Map<Integer,List<PmSubItems>> map = new HashMap<Integer,List<PmSubItems>>();
		
		List<PmSubItems> complItemsList = new ArrayList<PmSubItems>();
		List<PmSubItems> supplItemsList = new ArrayList<PmSubItems>();
		
		for(PmSubItems item: amSubItems){
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
	public List<PmItems> getDinnerItem(String userId,Integer addId){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(userId != null){
			
			UserSubscription userSubscription = userSubscriptionDAO.findByUserId(userId);
			if(userSubscription != null && userSubscription.getIsActive().equalsIgnoreCase("YES") &&
					userSubscription.getEndDate().after(date)){
				
				PmUpdatedItems pmUpdatedItems = pmUpdatedItemsDAO.findByUserId(userId);
				if(pmUpdatedItems != null && dateFormat.format(pmUpdatedItems.getModifiedItemDate()).equals(dateFormat.format(date))){
					if(pmUpdatedItems.getStatus().equalsIgnoreCase("cancelled")){
						if(addId != 0){
							List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
							for (PmItems pmItems2 : pmItems) {
								if(dateFormat.format(pmItems2.getItemDate()).equals(dateFormat.format(date))){
									DateFormat hourFormat = new SimpleDateFormat("HH");
									/*AmUpdatedItems amUpdatedItems1 = new AmUpdatedItems();
									amUpdatedItems1.setItemId(amItems.getItemId());
									amUpdatedItems1.setUserId(userId);
									amUpdatedItems1.setModifiedItemDate(new Date());
									amUpdatedItems1.setStatus("Active");
									amUpdatedItems1.setIsActive("YES");
									int i =	amUpdatedItemsDAO.update(amUpdatedItems1);*/
									pmUpdatedItemsDAO.deleteUserRecord(pmItems2.getItemId(),userId);
									PmUpdatedItems pmUpdatedItems1 = new PmUpdatedItems();
									pmUpdatedItems1.setItemId(pmItems2.getItemId());
									pmUpdatedItems1.setUserId(userId);
									pmUpdatedItems1.setModifiedItemDate(new Date());
									pmUpdatedItems1.setStatus("Active");
									pmUpdatedItems1.setIsActive("YES");
									pmUpdatedItemsDAO.insert(pmUpdatedItems1);
									
									if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
										pmItems2.setStatus("Today's Dinner Special");
									}else{
										pmItems2.setStatus("Tommorow's Dinner Special");
									}
									return pmItems;
								}else{
									return null;
								}
							}
						}else {
							return null;
						}
					}else{
						List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
						
						for (PmItems pmItems2 : pmItems) {
							if(dateFormat.format(pmItems2.getItemDate()).equals(dateFormat.format(date))){
								DateFormat hourFormat = new SimpleDateFormat("HH");
								logger.debug(date.getTime());
								if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
									pmItems2.setStatus("Today's Dinner Special");
								}else{
									pmItems2.setStatus("Tommorow's Dinner Special");
								}
								return pmItems;
							}else{
								return null;
							}
						}
					}
				}else{
					List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
					
					for (PmItems pmItems2 : pmItems) {
						if(dateFormat.format(pmItems2.getItemDate()).equals(dateFormat.format(date))){
							DateFormat hourFormat = new SimpleDateFormat("HH");
							logger.debug(date.getTime());
							if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
								pmItems2.setStatus("Today's Dinner Special");
							}else{
								pmItems2.setStatus("Tommorow's Dinner Special");
							}
							return pmItems;
						}else{
							return null;
						}
					}
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		return null;
	}
	
	public PMFinalSubItems getDinnerSubItem(PMFinalSubItems pmMfinalSubItems){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<PmSubItems> pmSubItemsList = null;
		if(pmMfinalSubItems.getUserId() != null){
			PMFinalSubItems pmMfinalSubItems2 = null;
			UserSubscription userSubscription = userSubscriptionDAO.findByUserId(pmMfinalSubItems.getUserId());
			if(userSubscription != null && userSubscription.getIsActive().equalsIgnoreCase("YES") &&
					userSubscription.getEndDate().after(date)){
				if(pmMfinalSubItems.getAddId() != 0 && pmMfinalSubItems.getPmSubItemsList().size() > 0){
					pmMfinalSubItems2 = new PMFinalSubItems();
					List<PmSubItems> pmSubItems = new ArrayList<PmSubItems>();
					
					pmUpdatedSubItemsDAO.deleteCurDateUserRecord(pmMfinalSubItems.getUserId(),dateFormat.format(date));
					
					for (PmSubItems pmSubItems1 : pmMfinalSubItems.getPmSubItemsList()) {
						PmUpdatedSubItems pmUpdatedSubItems = pmUpdatedSubItemsDAO.findByUserId(pmMfinalSubItems.getUserId(),pmSubItems1.getItemId());
								pmSubItemsList = new ArrayList<PmSubItems>();
									if(pmUpdatedSubItems != null && dateFormat.format(pmUpdatedSubItems.getModifiedItemDate()).equals(dateFormat.format(date))){
											if(pmMfinalSubItems.getAddId() != 0 && pmUpdatedSubItems.getStatus().equalsIgnoreCase("cancelled")){
												List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
												//for (PmItems pmItems2 : pmItems) {
													if(dateFormat.format(pmItems.get(0).getItemDate()).equals(dateFormat.format(date))){
														pmUpdatedSubItemsDAO.deleteUserRecord(pmUpdatedSubItems.getSubItemId(),pmMfinalSubItems.getUserId());
														PmUpdatedSubItems pmUpdatedSubItems3 = new PmUpdatedSubItems();
														pmUpdatedSubItems3.setSubItemId(pmSubItems1.getItemId());
														pmUpdatedSubItems3.setUserId(pmMfinalSubItems.getUserId());
														pmUpdatedSubItems3.setModifiedItemDate(new Date());
														pmUpdatedSubItems3.setStatus("Active");
														pmUpdatedSubItems3.setIsActive("YES");
														pmUpdatedSubItemsDAO.insert(pmUpdatedSubItems3);
														pmSubItems1.setSelected(true);
														pmSubItemsList.add(pmSubItems1);
													}else{
														return null;
													}
												//}
										}else if(pmMfinalSubItems.getAddId() != 0 && pmUpdatedSubItems.getStatus().equalsIgnoreCase("Active")){
											List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
											for (PmItems pmItems2 : pmItems) {
												if(dateFormat.format(pmItems2.getItemDate()).equals(dateFormat.format(date))){
													pmSubItems1.setSelected(true);
													pmSubItemsList.add(pmSubItems1);
												}else{
													return null;
												}
											}
										}
										else{
											return null;
										}
									}else{
										//amUpdatedSubItemsDAO.deleteUserRecord(amUpdatedSubItems.getSubItemId(),amMfinalSubItems.getUserId());
										PmUpdatedSubItems pmUpdatedSubItems3 = new PmUpdatedSubItems();
										pmUpdatedSubItems3.setSubItemId(pmSubItems1.getItemId());
										pmUpdatedSubItems3.setUserId(pmMfinalSubItems.getUserId());
										pmUpdatedSubItems3.setModifiedItemDate(new Date());
										pmUpdatedSubItems3.setStatus("Active");
										pmUpdatedSubItems3.setIsActive("YES");
										pmUpdatedSubItemsDAO.insert(pmUpdatedSubItems3);
										pmSubItems1.setSelected(true);
										pmSubItemsList.add(pmSubItems1);
									}
									pmSubItems.addAll(pmSubItemsList);
					}
					pmMfinalSubItems2.setPmSubItemsList(pmSubItems);
					return pmMfinalSubItems2;
				}else{
					List<PmUpdatedSubItems> pmUpdatedSubItems = pmUpdatedSubItemsDAO.findById(pmMfinalSubItems.getUserId());
					//AmSubItems amSubItems1 = new AmSubItems();
					List<PmSubItems> pmSubItems = new ArrayList<PmSubItems>();
					pmMfinalSubItems2 = new PMFinalSubItems();
					for (PmUpdatedSubItems pmUpdatedSubItems2 : pmUpdatedSubItems) {
						if(pmUpdatedSubItems2 != null && dateFormat.format(pmUpdatedSubItems2.getModifiedItemDate()).equals(dateFormat.format(date))
								&& pmMfinalSubItems.getAddId() == 0 && pmUpdatedSubItems2.getStatus().equalsIgnoreCase("Active")){
							List<PmItems> pmItems = pmItemsDAO.getPMItemList(dateFormat.format(date));
							PmSubItems pmSubItems2 = pmSubItemsDAO.find(pmUpdatedSubItems2.getSubItemId());
							pmSubItemsList = new ArrayList<PmSubItems>();
							//for (PmItems pmItems2 : pmItems) {
								if(dateFormat.format(pmItems.get(0).getItemDate()).equals(dateFormat.format(date))){
									pmSubItems2.setSelected(true);
									pmSubItemsList.add(pmSubItems2);
								}else{
									return null;
								}
								pmSubItems.addAll(pmSubItemsList);
							//}
						}
					}
					pmMfinalSubItems2.setPmSubItemsList(pmSubItems);
					return pmMfinalSubItems2;
					}
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public void cancelItem(List<Integer> itemId,String userId){
		//if(i == 0){
		for (Integer ids : itemId) {
			int i = pmUpdatedItemsDAO.deleteUserRecord(ids,userId);
			PmUpdatedItems pmUpdatedItems = new PmUpdatedItems();
			pmUpdatedItems.setItemId(ids);
			pmUpdatedItems.setUserId(userId);
			pmUpdatedItems.setModifiedItemDate(new Date());
			pmUpdatedItems.setStatus("cancelled");
			pmUpdatedItems.setIsActive("YES");
			pmUpdatedItemsDAO.insert(pmUpdatedItems);
		}
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
		pmUpdatedSubItemsDAO.deleteUserRecord(subItemId,userId);
		PmUpdatedSubItems pmUpdatedSubItems3 = new PmUpdatedSubItems();
		pmUpdatedSubItems3.setSubItemId(subItemId);
		pmUpdatedSubItems3.setUserId(userId);
		pmUpdatedSubItems3.setModifiedItemDate(new Date());
		pmUpdatedSubItems3.setStatus("Cancelled");
		pmUpdatedSubItems3.setIsActive("YES");
		pmUpdatedSubItemsDAO.insert(pmUpdatedSubItems3);
	}

}
