package com.mealtime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AmSubItems;
import com.mealtime.dao.AmSubItemsDAO;

@Service
public class AMMealItemsService {
	
	@Autowired
	private AmSubItemsDAO amSubItemsDAO;
	
	
	public List<AmSubItems> getSubItemsList(){
		List<AmSubItems> amSubItems = amSubItemsDAO.getItemsList();
		return amSubItems;
	}

}
