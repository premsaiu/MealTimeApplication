package com.mealtime.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mealtime.bean.AlertBean;
import com.mealtime.service.AlertService;
import com.mealtime.util.MealTimeUtil;

public class AlertJob implements Job
{
	private static final String mailSub="Gentle Reminder from MealTime ";
	private static final String mailBody="Dear Customer, Your package will expire on";
	private static final Logger logger = Logger.getLogger(AlertJob.class);
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("Quartz scheduler initialized");
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
		AlertService alertService= new AlertService();
		MealTimeUtil util = new MealTimeUtil();
		System.out.println("Quartz scheduler initialized:::1");
		alertBeanList = alertService.checkAlert();
		logger.info("Quartz Scheduler list:"+alertBeanList.size());
		System.out.println("Quartz Scheduler list:"+alertBeanList.size());
		if(alertBeanList.size()>0){
			for(AlertBean bean:alertBeanList)
			{
				util.sendEmail("u.premsai@gmail.com", bean.getEmail(), mailSub,
						mailBody+bean.getEnd_Date());
				
				util.sendSMS(bean.getMobile_Number(),mailBody+bean.getEnd_Date());
			}
		}
		
		//Throw exception for testing
		throw new JobExecutionException("Testing Exception");
	}
	public static void main (String args[]){
		MealTimeUtil util =new MealTimeUtil();
		try{
		util.sendEmail("srilakshmi180892@gmail.com", "srilakshmi180892@gmail.com", "Test", "Test");
		
		util.sendSMS("9502907535","Test Message");
		}
		catch(NullPointerException e){
			System.out.println("NullPointerException");
		}
	}
}
