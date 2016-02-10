package com.mealtime.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealtime.bean.UserMaster;
import com.mealtime.service.MealTimeService;
import com.mealtime.util.MealTimeUtil;
import com.mealtime.util.WSResponseStatus;

@RestController
public class BaseController {
	
	@Autowired
	MealTimeService mealTimeService;
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	private static final Logger logger = Logger.getLogger(BaseController.class);
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserMaster checkUser(@RequestParam("mobileNo")String mobileNumber){
		logger.info("checkUser():: mobileNo: "+mobileNumber);
		UserMaster user =  new UserMaster();
		user = mealTimeService.checkUser(mobileNumber);
		return user;
	}
	
	
	@RequestMapping(value="/sendOTP", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus sendOTP(@RequestParam("mobileNo")String mobileNumber, @RequestParam("email")String email){
		logger.info("sendOTP():: mobileNo: "+mobileNumber+" ::email: "+email);
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		//generate otp
		int otp = mealTimeUtil.generateOTP();
		//save otp in table
		mealTimeService.saveOTP(otp, mobileNumber, email);
		//sms otp
		int flag = 1;
		//int flag = mealTimeService.smsOTP(otp, mobileNumber);
		//email otp
		mealTimeService.emailOTP(otp, email);
		if(flag == 0){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Failed to send OTP");
		}
		else{
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value="/verifyOTP", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WSResponseStatus verifyOTP(@RequestParam("mobileNo")String mobileNumber, @RequestParam("otp") String submittedOTP ){
		logger.info("mobileNo: "+mobileNumber+" ::otp: "+submittedOTP);
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		boolean bol = mealTimeService.verifyOTP(mobileNumber, submittedOTP);
		if(bol == true){
			logger.info("OTP is correct");
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		}else{
			logger.info("Invalid OTP");
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Invalid OTP");
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(value="/saveProfile", method = RequestMethod.POST, produces="application/json", consumes = {"multipart/form-data"})
	public @ResponseBody WSResponseStatus saveProfile(@RequestParam("model") String objStr, @RequestParam("file") MultipartFile file){
		logger.info("saveProfile() :: objStr: "+objStr+" ::file: "+file);
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		UserMaster userMaster = new UserMaster();
		if (!file.isEmpty()) {
			try {
				userMaster = new ObjectMapper().readValue(objStr, UserMaster.class);
			} catch (JsonParseException e) {
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
			} catch (JsonMappingException e) {
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
			} catch (IOException e) {
				MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
			}
            try {
                byte[] bytes = file.getBytes();
                String fileName = userMaster.getMobileNumber()+".jpg";
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                logger.info("Catalina Home::"+rootPath);
                File dir = new File(rootPath+File.separator+"MealTime_User_Images");
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                userMaster.setFilePath(serverFile.getAbsolutePath());
                logger.info("You successfully uploaded file=" + fileName);
            } catch (Exception e) {
                logger.error("You failed to upload " + userMaster.getMobileNumber() + " => " + e.getMessage());
                MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + userMaster.getMobileNumber()
                    + " because the file was empty.");
            MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "No Image Found");
        }
		mealTimeService.saveProfile(userMaster);
		/*if(userId == 0){
			MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, "Insert Failed");
		}else{*/
			MealTimeUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		//}
		wsResponseStatus.setData(userMaster);
		return wsResponseStatus;
	}
	
}
