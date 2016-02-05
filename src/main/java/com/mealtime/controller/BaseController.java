package com.mealtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mealtime.bean.UserMaster;
import com.mealtime.service.MealTimeService;
import com.mealtime.util.MealTimeUtil;

@RestController
public class BaseController {
	
	@Autowired
	MealTimeService mealTimeService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserMaster checkUser(@RequestParam("mobileNo")String mobileNumber){
		System.out.println("In Base Controller :: checkUser()");
		UserMaster user =  new UserMaster();
		user = mealTimeService.checkUser(mobileNumber);
		return user;
	}
	
	
	@RequestMapping(value="/sendOTP", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<?> sendOTP(@RequestParam("mobileNo")String mobileNumber, @RequestParam("email")String email){
		//generate otp
		int otp = mealTimeUtil.generateOTP();
		//save otp in table
		mealTimeService.saveOTP(otp, mobileNumber, email);
		//sms otp
		int flag = mealTimeService.smsOTP(otp, mobileNumber);
		//email otp
		mealTimeService.emailOTP(otp, email);
		if(flag == 0){
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/saveProfile", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseEntity<?> saveProfile(@RequestBody UserMaster userMaster){
		System.out.println("In Base Controller :: saveProfile()");
		return new ResponseEntity<UserMaster>(userMaster, HttpStatus.OK);
	}
	
}
