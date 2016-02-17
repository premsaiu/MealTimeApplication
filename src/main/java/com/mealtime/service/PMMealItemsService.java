package com.mealtime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.PmSubItems;
import com.mealtime.dao.PmSubItemsDAO;

@Service
public class PMMealItemsService {
	
	@Autowired
	private PmSubItemsDAO pmSubItemsDAO;
	
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

}
