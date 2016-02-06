package com.mealtime.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@RequestMapping(value="/saveProfile", method = RequestMethod.POST, produces="application/json", consumes = {"multipart/form-data"})
	public @ResponseBody ResponseEntity<?> saveProfile(@RequestPart("myObj") String objStr, @RequestPart("file") MultipartFile file){
		System.out.println("In Base Controller :: saveProfile()");
		UserMaster userMaster = null;
		if (!file.isEmpty()) {
			try {
				userMaster = new ObjectMapper().readValue(objStr, UserMaster.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
			}
            try {
                byte[] bytes = file.getBytes();
                String fileName = userMaster.getMobileNumber()+".jpg";
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Catalina Home::"+rootPath);
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
 
                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());
                userMaster.setFilePath(serverFile.getAbsolutePath());
                System.out.println("You successfully uploaded file=" + fileName);
            } catch (Exception e) {
                System.out.println("You failed to upload " + userMaster.getMobileNumber() + " => " + e.getMessage());
                return new ResponseEntity<String>("Failed to upload", HttpStatus.BAD_REQUEST);
            }
        } else {
            System.out.println("You failed to upload " + userMaster.getMobileNumber()
                    + " because the file was empty.");
            return new ResponseEntity<String>("No Image found", HttpStatus.BAD_REQUEST);
        }
		
		int userId = mealTimeService.saveProfile(userMaster);
		if(userId == 0){
			return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
		}else{
			return new ResponseEntity<UserMaster>(userMaster, HttpStatus.OK);
		}
	}
	
}
