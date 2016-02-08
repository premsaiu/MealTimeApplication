package com.mealtime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.AmSubItems;
import com.mealtime.service.AMMealItemsService;

@RestController
public class AMMealController {

	@Autowired
	private AMMealItemsService amMealItemsService;
	
	
	@RequestMapping(value = "/subItems", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<AmSubItems> subItemsList(){
		
		List<AmSubItems> itemsList = amMealItemsService.getSubItemsList();
		return itemsList;
	}
	
	
}
