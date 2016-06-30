/**
 * 
 */
package com.mealtime.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mealtime.bean.AlertBean;
import com.mealtime.service.AlertService;
import com.mealtime.service.SampleMealService;
import com.mealtime.service.ScheduleEnquiryService;
import com.mealtime.service.UserSubscriptionService;
import com.mealtime.util.MealTimeUtil;

@Configuration
@EnableScheduling
@PropertySource("classpath:jdbc.properties")
public class AlertScheduler {
	
	private static final String MAILSUB = "Gentle Reminder from MealTime ";
	private static final String MAILBODY = "Dear Customer, Your package will expire on ";
	
	private static final String ADMIN_MAILSUB = "Daily Status & Activities for Admin";
	private static final String ADMIN_MAILBODY = "Dear Admin, \n\n\t Please find attached PDF documents contains all the required data."
			+ "\n\nThanks & Regards, \n MealTime Team.";
	
	private static final Logger logger = Logger.getLogger(AlertScheduler.class);
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	AlertService alertService;
	
	@Autowired
	SampleMealService sampleMealService;
	
	@Autowired
	ScheduleEnquiryService scheduleEnquiryService;
	
	@Autowired
	UserSubscriptionService userSubscriptionService;
	
	@Value("${admin.eMail}")
	private String admineMail;
	
	@Scheduled(cron = "0 * 7 * * *")
	public void cronTask(){
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
		logger.info("Cron Scheduler Initialized");
		alertBeanList = alertService.checkAlert();
		if(!alertBeanList.isEmpty()){
			logger.info("Quartz Scheduler list:"+alertBeanList.size());
			for(AlertBean bean:alertBeanList)
			{
				mealTimeUtil.sendEmail(bean.getEmail(), "info@mealtime.co.in", MAILSUB,
						MAILBODY+bean.getEnd_Date());
				logger.info("Mail Sent successfully");
				mealTimeUtil.sendSMS(bean.getMobile_Number(),MAILBODY+bean.getEnd_Date());
			}
		}else{
			logger.info("Alert List is Empty. Hence No Job.");
		}
	}
	
	// cron = seconds minutes hours days months year
	@Scheduled(cron = "0 * 12 * * ?")
	public void cronTaskForAdmin(){
		//Method call for Sample Meal
		
		String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath+File.separator+"ADMIN");
        
		sampleMealService.sampleMealPDF();
		scheduleEnquiryService.scheduleEnquiryPDF();
		userSubscriptionService.userSubscriptionPDF();
		//Admin mail need to be set
		if(!dir.getAbsolutePath().isEmpty()) mealTimeUtil.sendEmail(admineMail,"",ADMIN_MAILSUB,ADMIN_MAILBODY,dir.getAbsolutePath());
		
		
	}
	
	

}

