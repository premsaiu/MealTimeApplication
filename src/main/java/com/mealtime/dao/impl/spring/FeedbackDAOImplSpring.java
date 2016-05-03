/*
 * Created on 3 May 2016 ( Time 23:25:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mealtime.bean.Feedback;
import com.mealtime.dao.FeedbackDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;

/**
 * Feedback DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
@Repository
public class FeedbackDAOImplSpring extends GenericDAO<Feedback> implements FeedbackDAO {

	private final static String SQL_SELECT = 
		"select name, email, mobile_number, comments, created_by, created_date, status, is_active, version from feedback where ";


	private final static String SQL_INSERT = 
		"insert into feedback ( name, email, mobile_number, comments, created_by, created_date, status, is_active, version ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update feedback set name = ?, email = ?, mobile_number = ?, comments = ?, created_by = ?, created_date = ?, status = ?, is_active = ?, version = ? where ";

	private final static String SQL_DELETE = 
		"delete from feedback where ";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from feedback";

	private final static String SQL_COUNT = 
		"select count(*) from feedback where ";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public FeedbackDAOImplSpring() {
		super();
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public Feedback find(  ) {
		Object[] primaryKey = new Object[] {  };
		return super.doSelect(primaryKey);		
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean load( Feedback feedback ) {
		return super.doSelect(feedback) ;
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public void insert(Feedback feedback) {
		super.doInsert(feedback);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int update(Feedback feedback) {
		return super.doUpdate(feedback);
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
	
	public int delete( Feedback feedback ) {
		return super.doDelete(feedback);
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
	
	public boolean exists( Feedback feedback ) {
		return super.doExists(feedback);
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
	
	protected Object[] getValuesForInsert(Feedback feedback)  {
		return new Object[] {
			//--- Returns PRIMARY KEY and DATA ( for SQL "SET x=?, y=?, ..." )
			feedback.getName() , // "name" : java.lang.String
			feedback.getEmail() , // "email" : java.lang.String
			feedback.getMobileNumber() , // "mobile_number" : java.lang.String
			feedback.getComments() , // "comments" : java.lang.String
			feedback.getCreatedBy() , // "created_by" : java.util.Date
			feedback.getCreatedDate() , // "created_date" : java.util.Date
			feedback.getStatus() , // "status" : java.lang.String
			feedback.getIsActive() , // "is_active" : java.lang.String
			feedback.getVersion()  // "version" : java.lang.Integer
		};
	}
    //----------------------------------------------------------------------
	
	protected Object[] getValuesForUpdate(Feedback feedback) {
		return new Object[] {		
			//--- Returns DATA first ( for SQL "SET x=?, y=?, ..." )
			feedback.getName(), // "name" : java.lang.String
			feedback.getEmail(), // "email" : java.lang.String
			feedback.getMobileNumber(), // "mobile_number" : java.lang.String
			feedback.getComments(), // "comments" : java.lang.String
			feedback.getCreatedBy(), // "created_by" : java.util.Date
			feedback.getCreatedDate(), // "created_date" : java.util.Date
			feedback.getStatus(), // "status" : java.lang.String
			feedback.getIsActive(), // "is_active" : java.lang.String
			feedback.getVersion(), // "version" : java.lang.Integer
			//--- Returns PRIMARY KEY at the end ( for SQL "WHERE key=?, ..." )
		};
	}
	//----------------------------------------------------------------------
	
	protected Object[] getValuesForPrimaryKey(Feedback feedback)  {
		return new Object[] {
			//--- Returns PRIMARY KEY values ( for SQL "WHERE key=?, ..." )
		};
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<Feedback> getRowMapper(Feedback o)  {
		//--- RowMapper to populate the given bean instance
		return new FeedbackRowMapper(o) ;
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<Feedback> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		return new FeedbackRowMapper( new Feedback() ) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param feedback
	 * @throws SQLException
	 */
	private void populateBean(ResultSet rs, Feedback feedback) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		feedback.setName(rs.getString("name")); // java.lang.String
		feedback.setEmail(rs.getString("email")); // java.lang.String
		feedback.setMobileNumber(rs.getString("mobile_number")); // java.lang.String
		feedback.setComments(rs.getString("comments")); // java.lang.String
		feedback.setCreatedBy(rs.getDate("created_by")); // java.util.Date
		feedback.setCreatedDate(rs.getDate("created_date")); // java.util.Date
		feedback.setStatus(rs.getString("status")); // java.lang.String
		feedback.setIsActive(rs.getString("is_active")); // java.lang.String
		feedback.setVersion(rs.getInt("version")); // java.lang.Integer
		if ( rs.wasNull() ) { feedback.setVersion(null); }; // not primitive number => keep null value if any
	}

    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class FeedbackRowMapper implements RowMapper<Feedback> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final Feedback bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		FeedbackRowMapper(Feedback bean) {
			this.bean = bean ;
		}
		
		
		public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}
}
