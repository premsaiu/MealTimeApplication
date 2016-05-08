package com.mealtime.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AlertBean;
import com.mealtime.dao.AlertDAO;
import com.mealtime.dao.impl.spring.AlertDAOImplSpring;

@Service
public class AlertService {
	
	
	private static final Logger logger = Logger.getLogger(AlertService.class);
	public List<AlertBean> checkAlert()
	{
		logger.info("checkAlert service intilaized");
		System.out.println("checkAlert service intilaized");
		AlertDAO alertDAO= new AlertDAOImplSpring();
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
				alertBeanList = alertDAO.checkList();
		
		return alertBeanList;
	}

}
