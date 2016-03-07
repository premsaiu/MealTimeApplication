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

import com.mealtime.bean.AmItems;
import com.mealtime.bean.AmSubItems;
import com.mealtime.bean.AmUpdatedItems;
import com.mealtime.dao.AmItemsDAO;
import com.mealtime.dao.AmSubItemsDAO;
import com.mealtime.dao.AmUpdatedItemsDAO;

@Service
public class AMMealItemsService {
	
	@Autowired
	private AmSubItemsDAO amSubItemsDAO;
	
	@Autowired
	private AmUpdatedItemsDAO amUpdatedItemsDAO;
	
	@Autowired
	private AmItemsDAO amItemsDAO;
	
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
			AmUpdatedItems amUpdatedItems = amUpdatedItemsDAO.findByUserId(userId);
			if(amUpdatedItems != null && dateFormat.format(amUpdatedItems.getModifiedItemDate()).equals(dateFormat.format(date))){
					if(amUpdatedItems.getStatus().equalsIgnoreCase("cancelled")){
						if(addId != 0){
							//int i =	amUpdatedItemsDAO.updateUserRecord(userId,new Date(),"Active");
							
							//if(i == 0 && amUpdatedItems.getStatus().equalsIgnoreCase("Active")){
								AmItems amItems = amItemsDAO.getAMItemObj();
								if(dateFormat.format(amItems.getItemDate()).equals(dateFormat.format(date))){
									DateFormat hourFormat = new SimpleDateFormat("HH");
							        
							        int i = amUpdatedItemsDAO.deleteUserRecord(amItems.getItemId(),userId);
						    		if(i == 0){
						    			AmUpdatedItems amUpdatedItems1 = new AmUpdatedItems();
						    			amUpdatedItems1.setItemId(amItems.getItemId());
						    			amUpdatedItems1.setUserId(userId);
						    			amUpdatedItems1.setModifiedItemDate(new Date());
						    			amUpdatedItems1.setStatus("Active");
						    			amUpdatedItems1.setIsActive("YES");
						    			amUpdatedItemsDAO.insert(amUpdatedItems1);
						    		}
							        
							        if(Integer.parseInt(hourFormat.format(date.getTime())) <= 7){
							        	amItems.setStatus("Today's Breakfast Special");
							        }else{
							        	amItems.setStatus("Tommorow's Breakfast Special");
							        }
									return amItems;
								}else{
									return null;
								}
							//}
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
}
