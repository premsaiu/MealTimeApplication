package com.mealtime.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.AMFinalSubItems;
import com.mealtime.bean.AmItems;
import com.mealtime.bean.AmSubItems;
import com.mealtime.bean.UserWallet;
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
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in getSubItemsList Method of Controller");
			logger.error("Exception in getSubItemsList---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/getBreakfastItem", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus getBreakfastItem(@RequestParam(value="userId")String userId, @RequestParam(value="addId", required=false)Integer addId){
		WSResponseStatus wsResponseStatus = null;
		try{
			AmItems amItems = amMealItemsService.getBreakfastItem(userId,addId);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData(amItems);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in getBreakfastItem Method of Controller");
			logger.error("Exception in getBreakfastItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/getBreakfastSubItem", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody WSResponseStatus getBreakfastSubItem(@RequestBody AMFinalSubItems amMfinalSubItems){
		WSResponseStatus wsResponseStatus = null;
		try{
			AMFinalSubItems amMfinalSubItems1 = amMealItemsService.getBreakfastSubItem(amMfinalSubItems);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData(amMfinalSubItems1);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in getBreakfastSubItem Method of Controller");
			logger.error("Exception in getBreakfastSubItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	
	@RequestMapping(value = "/deleteSubItemAddon", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus deleteSubItemAddon(@RequestParam(value="itemId")Integer itemId, @RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			amMealItemsService.deleteSubItemAddon(itemId,userId);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData("success");
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in deleteSubItemAddon Method of Controller");
			logger.error("Exception in getBreakfastSubItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/cancelItem", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus cancelItem(@RequestParam(value="itemId")int itemId,@RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			amMealItemsService.cancelItem(itemId,userId);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in cancelItem Method of Controller");
			logger.error("Exception in cancelItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/walletCheck", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus walletCheck(@RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			UserWallet userWallet = amMealItemsService.walletCheck(userId);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(userWallet);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in walletCheck Method of Controller");
			logger.error("Exception in walletCheck---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus payment(@RequestParam(value="userId")String userId,@RequestParam(value="paidAmount")Double paidAmount){
		WSResponseStatus wsResponseStatus = null;
		try{
			amMealItemsService.payment(userId,paidAmount);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			logger.error("Exception in payment---"+e.getMessage());
		}
		return wsResponseStatus;
	}
}
