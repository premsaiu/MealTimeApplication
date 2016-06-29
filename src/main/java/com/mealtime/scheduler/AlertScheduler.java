/**
 * 
 */
package com.mealtime.scheduler;

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
import com.mealtime.util.MealTimeUtil;

@Configuration
@EnableScheduling
@PropertySource("classpath:jdbc.properties")
public class AlertScheduler {
	
	private static final String MAILSUB = "Gentle Reminder from MealTime ";
	private static final String MAILBODY = "Dear Customer, Your package will expire on ";
	
	private static final Logger logger = Logger.getLogger(AlertScheduler.class);
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	AlertService alertService;
	
	@Autowired
	SampleMealService sampleMealService;
	
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
	
	@Scheduled(cron = "0 * 12 * * ?")
	public void cronTaskForAdmin(){
		//Method call for Sample Meal
		String filepath=sampleMealService.sampleMealPDF();
		//Admin mail need to be set
		mealTimeUtil.sendEmail(admineMail,"","Sample Meal Suscription Details","PFA",filepath);
	}

}

