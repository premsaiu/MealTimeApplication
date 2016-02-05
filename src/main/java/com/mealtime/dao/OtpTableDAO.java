/*
 * Created on 4 Feb 2016 ( Time 22:47:09 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao;

import com.mealtime.bean.OtpTable;

/**
 * OtpTable DAO interface
 * 
 * @author Telosys Tools
 */
public interface OtpTableDAO {

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @return the bean found or null if not found 
	 */
	public OtpTable find(  ) ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param otpTable
	 * @return true if found, false if not found
	 */
	public boolean load( OtpTable otpTable ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param otpTable
	 */
	public void insert(OtpTable otpTable) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param otpTable
	 * @return
	 */
	public int update(OtpTable otpTable) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @return
	 */
	public int delete(  ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param otpTable
	 * @return
	 */
	public int delete( OtpTable otpTable ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @return
	 */
	public boolean exists(  ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param otpTable
	 * @return
	 */
	public boolean exists( OtpTable otpTable ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;
	
	public OtpTable findByMobileNumber(String mobileNo);

}
