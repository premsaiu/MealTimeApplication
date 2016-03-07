/*
 * Created on 5 Mar 2016 ( Time 18:48:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao;

import com.mealtime.bean.PmItems;

/**
 * PmItems DAO interface
 * 
 * @author Telosys Tools
 */
public interface PmItemsDAO {

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param itemId
	 * @return the bean found or null if not found 
	 */
	public PmItems find( Integer itemId ) ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param pmItems
	 * @return true if found, false if not found
	 */
	public boolean load( PmItems pmItems ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param pmItems
	 * @return the generated value for the auto-incremented column
	 */
	public Integer insert(PmItems pmItems) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param pmItems
	 * @return
	 */
	public int update(PmItems pmItems) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param itemId
	 * @return
	 */
	public int delete( Integer itemId ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param pmItems
	 * @return
	 */
	public int delete( PmItems pmItems ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param itemId
	 * @return
	 */
	public boolean exists( Integer itemId ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param pmItems
	 * @return
	 */
	public boolean exists( PmItems pmItems ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;

}
