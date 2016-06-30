/*
 * Created on 13 Mar 2016 ( Time 20:02:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao;

import java.util.List;

import com.mealtime.bean.UserSubscription;

/**
 * UserSubscription DAO interface
 * 
 * @author Telosys Tools
 */
public interface UserSubscriptionDAO {

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param userSubscriptionId
	 * @return the bean found or null if not found 
	 */
	public UserSubscription find( Integer userSubscriptionId ) ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param userSubscription
	 * @return true if found, false if not found
	 */
	public boolean load( UserSubscription userSubscription ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param userSubscription
	 */
	public void insert(UserSubscription userSubscription) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param userSubscription
	 * @return
	 */
	public int update(UserSubscription userSubscription) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param userSubscriptionId
	 * @return
	 */
	public int delete( String userId ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param userSubscription
	 * @return
	 */
	public int delete( UserSubscription userSubscription ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param userSubscriptionId
	 * @return
	 */
	public boolean exists( Integer userSubscriptionId ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param userSubscription
	 * @return
	 */
	public boolean exists( UserSubscription userSubscription ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;
	
	public UserSubscription findByUserId(String userId);
	
	public List<UserSubscription> getPendingSubscribedUsers();

	public String userSubscriptionPDF();
}
