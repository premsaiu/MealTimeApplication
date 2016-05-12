/**
 * 
 */
package com.mealtime.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AlertBean;
import com.mealtime.service.AlertService;
import com.mealtime.util.MealTimeUtil;

@Service
@EnableScheduling
public class AlertScheduler {
	
	private static final String MAILSUB = "Gentle Reminder from MealTime ";
	private static final String MAILBODY = "Dear Customer, Your package will expire on ";
	
	private static final Logger logger = Logger.getLogger(AlertScheduler.class);
	
	@Autowired
	MealTimeUtil mealTimeUtil;
	
	@Autowired
	AlertService alertService;
	
	@Scheduled(cron = "10 * * * * *")
	public void cronTask(){
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
		logger.info("Cron Scheduler Initialized");
		alertBeanList = alertService.checkAlert();
		if(!alertBeanList.isEmpty()){
			logger.info("Quartz Scheduler list:"+alertBeanList.size());
			for(AlertBean bean:alertBeanList)
			{
				mealTimeUtil.sendEmail("u.premsai@gmail.com", bean.getEmail(), MAILSUB,
						MAILBODY+bean.getEnd_Date());
				logger.info("Mail Sent successfully");
				//mealTimeUtil.sendSMS(bean.getMobile_Number(),MAILBODY+bean.getEnd_Date());
			}
		}else{
			logger.info("Alert List is Empty. Hence No Job.");
		}
	}

}

