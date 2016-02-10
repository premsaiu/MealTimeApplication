/*
 * Created on 10 Feb 2016 ( Time 11:17:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mealtime.bean.UserSubscription;
import com.mealtime.dao.UserSubscriptionDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * UserSubscription DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
@Repository
public class UserSubscriptionDAOImplSpring extends GenericDAO<UserSubscription> implements UserSubscriptionDAO {

	private final static String SQL_SELECT = 
		"select user_id, subscription_id, start_date, end_date, created_by, updated_by, status, is_active, version from user_subscription where ";


	private final static String SQL_INSERT = 
		"insert into user_subscription ( user_id, subscription_id, start_date, end_date, created_by, updated_by, status, is_active, version ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update user_subscription set user_id = ?, subscription_id = ?, start_date = ?, end_date = ?, created_by = ?, updated_by = ?, status = ?, is_active = ?, version = ? where ";

	private final static String SQL_DELETE = 
		"delete from user_subscription where ";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from user_subscription";

	private final static String SQL_COUNT = 
		"select count(*) from user_subscription where ";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public UserSubscriptionDAOImplSpring() {
		super();
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public UserSubscription find(  ) {
		Object[] primaryKey = new Object[] {  };
		return super.doSelect(primaryKey);		
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean load( UserSubscription userSubscription ) {
		return super.doSelect(userSubscription) ;
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public void insert(UserSubscription userSubscription) {
		super.doInsert(userSubscription);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int update(UserSubscription userSubscription) {
		return super.doUpdate(userSubscription);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int delete(  ) {
		Object[] primaryKey = new Object[] {  };
		return super.doDelete(primaryKey);		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int delete( UserSubscription userSubscription ) {
		return super.doDelete(userSubscription);
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean exists(  ) {
		Object[] primaryKey = new Object[] {  };
		return super.doExists(primaryKey);
	}
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean exists( UserSubscription userSubscription ) {
		return super.doExists(userSubscription);
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public long count() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	// Super class abstract methods implementation
    //----------------------------------------------------------------------
	
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}
	//----------------------------------------------------------------------
	
	protected Object[] getValuesForInsert(UserSubscription userSubscription)  {
		return new Object[] {
			//--- Returns PRIMARY KEY and DATA ( for SQL "SET x=?, y=?, ..." )
			userSubscription.getUserId() , // "user_id" : java.lang.String
			userSubscription.getSubscriptionId() , // "subscription_id" : java.lang.Integer
			userSubscription.getStartDate() , // "start_date" : java.util.Date
			userSubscription.getEndDate() , // "end_date" : java.util.Date
			userSubscription.getCreatedBy() , // "created_by" : java.lang.String
			userSubscription.getUpdatedBy() , // "updated_by" : java.lang.String
			userSubscription.getStatus() , // "status" : java.lang.String
			userSubscription.getIsActive() , // "is_active" : java.lang.String
			userSubscription.getVersion()  // "version" : java.lang.Integer
		};
	}
    //----------------------------------------------------------------------
	
	protected Object[] getValuesForUpdate(UserSubscription userSubscription) {
		return new Object[] {		
			//--- Returns DATA first ( for SQL "SET x=?, y=?, ..." )
			userSubscription.getUserId(), // "user_id" : java.lang.String
			userSubscription.getSubscriptionId(), // "subscription_id" : java.lang.Integer
			userSubscription.getStartDate(), // "start_date" : java.util.Date
			userSubscription.getEndDate(), // "end_date" : java.util.Date
			userSubscription.getCreatedBy(), // "created_by" : java.lang.String
			userSubscription.getUpdatedBy(), // "updated_by" : java.lang.String
			userSubscription.getStatus(), // "status" : java.lang.String
			userSubscription.getIsActive(), // "is_active" : java.lang.String
			userSubscription.getVersion(), // "version" : java.lang.Integer
			//--- Returns PRIMARY KEY at the end ( for SQL "WHERE key=?, ..." )
		};
	}
	//----------------------------------------------------------------------
	
	protected Object[] getValuesForPrimaryKey(UserSubscription userSubscription)  {
		return new Object[] {
			//--- Returns PRIMARY KEY values ( for SQL "WHERE key=?, ..." )
		};
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<UserSubscription> getRowMapper(UserSubscription o)  {
		//--- RowMapper to populate the given bean instance
		return new UserSubscriptionRowMapper(o) ;
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<UserSubscription> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		return new UserSubscriptionRowMapper( new UserSubscription() ) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param userSubscription
	 * @throws SQLException
	 */
	private void populateBean(ResultSet rs, UserSubscription userSubscription) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		userSubscription.setUserId(rs.getString("user_id")); // java.lang.String
		userSubscription.setSubscriptionId(rs.getInt("subscription_id")); // java.lang.Integer
		if ( rs.wasNull() ) { userSubscription.setSubscriptionId(null); }; // not primitive number => keep null value if any
		userSubscription.setStartDate(rs.getDate("start_date")); // java.util.Date
		userSubscription.setEndDate(rs.getDate("end_date")); // java.util.Date
		userSubscription.setCreatedBy(rs.getString("created_by")); // java.lang.String
		userSubscription.setUpdatedBy(rs.getString("updated_by")); // java.lang.String
		userSubscription.setStatus(rs.getString("status")); // java.lang.String
		userSubscription.setIsActive(rs.getString("is_active")); // java.lang.String
		userSubscription.setVersion(rs.getInt("version")); // java.lang.Integer
		if ( rs.wasNull() ) { userSubscription.setVersion(null); }; // not primitive number => keep null value if any
	}

    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class UserSubscriptionRowMapper implements RowMapper<UserSubscription> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final UserSubscription bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		UserSubscriptionRowMapper(UserSubscription bean) {
			this.bean = bean ;
		}
		
		
		public UserSubscription mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}
}
