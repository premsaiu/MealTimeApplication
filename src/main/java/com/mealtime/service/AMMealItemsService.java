package com.mealtime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AmSubItems;
import com.mealtime.dao.AmSubItemsDAO;

@Service
public class AMMealItemsService {
	
	@Autowired
	private AmSubItemsDAO amSubItemsDAO;
	
	private static String COMPLEMENTARY = "Complementary"; 
	
	
	public Map<Integer, List<AmSubItems>> getSubItemsList(){
		List<AmSubItems> amSubItems = amSubItemsDAO.getItemsList();
		
		Map<Integer,List<AmSubItems>> map = new HashMap<Integer,List<AmSubItems>>();
		
		List<AmSubItems> itemsList = new ArrayList<AmSubItems>();
		
		for(AmSubItems item: amSubItems){
			if(item.getItemType() == COMPLEMENTARY){
				itemsList.add(item);
				map.put(1, itemsList);
			}else{
				itemsList.add(item);
				map.put(2,itemsList);
			}
		}
		return map;
	}

}
