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
import org.springframework.web.multipart.MultipartFile;

import com.mealtime.bean.AmSubItems;
import com.mealtime.bean.UserMaster;
import com.mealtime.service.AdminService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class AdminController {

private static final Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/checkUserRole", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserMaster checkUser(@RequestParam("mobileNo")String mobileNumber){
		logger.info("checkUser():: mobileNo: "+mobileNumber);
		UserMaster user =  new UserMaster();
		user = adminService.checkUserRole(mobileNumber);
		return user;
	}
	
	@RequestMapping(value = "/checkAdminExistence", method = RequestMethod.POST)
		public @ResponseBody WSResponseStatus checkAdminExistence(@RequestBody UserMaster user){
		
		WSResponseStatus wsResponseStatus = null;
		try{
			user = adminService.checkAdminExistence(user);
			wsResponseStatus=new WSResponseStatus();
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
			wsResponseStatus.setData(user);
		}catch(Exception e){
			logger.error("Exception in checkAdminExistence---"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
}
