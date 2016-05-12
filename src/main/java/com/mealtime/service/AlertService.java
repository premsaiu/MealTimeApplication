package com.mealtime.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealtime.bean.AlertBean;
import com.mealtime.dao.AlertDAO;

@Service
public class AlertService {
	
	@Autowired
	AlertDAO alertDAO;
	
	public List<AlertBean> checkAlert()
	{
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
		alertBeanList = alertDAO.checkList();
		return alertBeanList;
	}

}
