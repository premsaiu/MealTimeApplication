package com.mealtime.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.AmSubItems;
import com.mealtime.service.AMMealItemsService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class AMMealController {

	@Autowired
	private AMMealItemsService amMealItemsService;
	
	private static final Logger logger = Logger.getLogger(AMMealController.class);
	
	@RequestMapping(value = "/getSubItemsList", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus getSubItemsList(){
		WSResponseStatus wsResponseStatus = null;
		try{
			Map<Integer, List<AmSubItems>> itemsList = amMealItemsService.getSubItemsList();
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(itemsList);
		}catch(Exception e){
			logger.error("Exception in getSubItemsList---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	/*@RequestMapping(value = "/payment", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody WSResponseStatus payment(@RequestBody List<AmSubItems> amSubItems){
		WSResponseStatus wsResponseStatus = null;
		try{
			for(AmSubItems amSubItems1: amSubItems){
				
			}
			
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			//wsResponseStatus.setData(itemsList);
		}catch(Exception e){
			logger.error("Exception in payment---"+e.getMessage());
		}
		return wsResponseStatus;
	}*/ 
}
