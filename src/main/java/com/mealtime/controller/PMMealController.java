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

import com.mealtime.bean.PMFinalSubItems;
import com.mealtime.bean.PmItems;
import com.mealtime.bean.PmSubItems;
import com.mealtime.bean.UserWallet;
import com.mealtime.service.PMMealItemsService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class PMMealController {

	@Autowired
	private PMMealItemsService pmMealItemsService;
	
	private static final Logger logger = Logger.getLogger(PMMealController.class);
	
	@RequestMapping(value = "/getSubItemsList1", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus getSubItemsList(){
		WSResponseStatus wsResponseStatus = null;
		try{
			Map<Integer, List<PmSubItems>> itemsList = pmMealItemsService.getSubItemsList();
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(itemsList);
		}catch(Exception e){
			logger.error("Exception in getSubItemsList---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/getDinnerItem", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus getDinnerItem(@RequestParam(value="userId")String userId, @RequestParam(value="addId", required=false)Integer addId){
		WSResponseStatus wsResponseStatus = null;
		try{
			List<PmItems> pmItems = pmMealItemsService.getDinnerItem(userId,addId);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData(pmItems);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in getDinnerItem Method of Controller");
			logger.error("Exception in getDinnerItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/getDinnerSubItem", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody WSResponseStatus getDinnerSubItem(@RequestBody PMFinalSubItems pmMfinalSubItems){
		WSResponseStatus wsResponseStatus = null;
		try{
			PMFinalSubItems pmMfinalSubItems1 = pmMealItemsService.getDinnerSubItem(pmMfinalSubItems);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData(pmMfinalSubItems1);
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in getDinnerSubItem Method of Controller");
			logger.error("Exception in getDinnerSubItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	
	@RequestMapping(value = "/deleteDinnerSubItemAddon", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus deleteDinnerSubItemAddon(@RequestParam(value="itemId")Integer itemId, @RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			pmMealItemsService.deleteSubItemAddon(itemId,userId);
			wsResponseStatus=new WSResponseStatus();
			wsResponseStatus.setData("success");
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in deleteDinnerSubItemAddon Method of Controller");
			logger.error("Exception in deleteDinnerSubItemAddon---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/cancelDinnerItem", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus cancelDinnerItem(@RequestParam(value="itemIds")List<Integer> itemIds,@RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			pmMealItemsService.cancelItem(itemIds,userId);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in cancelDinnerItem Method of Controller");
			logger.error("Exception in cancelDinnerItem---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/walletCheckDinner", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus walletCheckDinner(@RequestParam(value="userId")String userId){
		WSResponseStatus wsResponseStatus = null;
		try{
			UserWallet userWallet = pmMealItemsService.walletCheck(userId);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(userWallet);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in walletCheckDinner Method of Controller");
			logger.error("Exception in walletCheckDinner---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value = "/dinnerPayment", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus dinnerPayment(@RequestParam(value="userId")String userId,@RequestParam(value="paidAmount")Double paidAmount){
		WSResponseStatus wsResponseStatus = null;
		try{
			pmMealItemsService.payment(userId,paidAmount);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}catch(Exception e){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Something went wrong in dinnerPayment Method of Controller");
			logger.error("Exception in dinnerPayment---"+e.getMessage());
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
