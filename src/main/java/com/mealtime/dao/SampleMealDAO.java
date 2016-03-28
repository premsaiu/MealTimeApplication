/*
 * Created on 24 Mar 2016 ( Time 07:53:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao;

import com.mealtime.bean.SampleMeal;

/**
 * SampleMeal DAO interface
 * 
 * @author Telosys Tools
 */
public interface SampleMealDAO {

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param sampleMealId
	 * @return the bean found or null if not found 
	 */
	public SampleMeal find( Integer sampleMealId ) ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param sampleMeal
	 * @return true if found, false if not found
	 */
	public boolean load( SampleMeal sampleMeal ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param sampleMeal
	 * @return the generated value for the auto-incremented column
	 */
	public Integer insert(SampleMeal sampleMeal) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param sampleMeal
	 * @return
	 */
	public int update(SampleMeal sampleMeal) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param sampleMealId
	 * @return
	 */
	public int delete( Integer sampleMealId ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param sampleMeal
	 * @return
	 */
	public int delete( SampleMeal sampleMeal ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param sampleMealId
	 * @return
	 */
	public boolean exists( Integer sampleMealId ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param sampleMeal
	 * @return
	 */
	public boolean exists( SampleMeal sampleMeal ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;
	
	public SampleMeal findByUserId( String userId ) ;

}
