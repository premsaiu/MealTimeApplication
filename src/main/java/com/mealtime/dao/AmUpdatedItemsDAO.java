/*
 * Created on 5 Mar 2016 ( Time 18:48:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao;

import java.util.Date;

import com.mealtime.bean.AmUpdatedItems;

/**
 * AmUpdatedItems DAO interface
 * 
 * @author Telosys Tools
 */
public interface AmUpdatedItemsDAO {

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id
	 * @return the bean found or null if not found 
	 */
	public AmUpdatedItems find( Integer id ) ;
	
	public AmUpdatedItems findByUserId(String userId);

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param amUpdatedItems
	 * @return true if found, false if not found
	 */
	public boolean load( AmUpdatedItems amUpdatedItems ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param amUpdatedItems
	 * @return the generated value for the auto-incremented column
	 */
	public Integer insert(AmUpdatedItems amUpdatedItems) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param amUpdatedItems
	 * @return
	 */
	public int update(AmUpdatedItems amUpdatedItems) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id
	 * @return
	 */
	public int delete( Integer id ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param amUpdatedItems
	 * @return
	 */
	public int delete( AmUpdatedItems amUpdatedItems ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id
	 * @return
	 */
	public boolean exists( Integer id ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param amUpdatedItems
	 * @return
	 */
	public boolean exists( AmUpdatedItems amUpdatedItems ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;
	
	public int deleteUserRecord(int itemId,String userId);
	
	public int updateUserRecord(String userId,Date date,String Status);

}
