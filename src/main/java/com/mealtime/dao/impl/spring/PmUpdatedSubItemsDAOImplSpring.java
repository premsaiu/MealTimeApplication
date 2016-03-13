/*
 * Created on 11 Mar 2016 ( Time 22:46:38 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mealtime.bean.PmUpdatedSubItems;
import com.mealtime.dao.PmUpdatedSubItemsDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;

/**
 * PmUpdatedSubItems DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
@Repository
public class PmUpdatedSubItemsDAOImplSpring extends GenericDAO<PmUpdatedSubItems> implements PmUpdatedSubItemsDAO {

	private final static String SQL_SELECT = 
		"select id, sub_item_id, user_id, modified_item_date, created_date, updated_date, created_by, updated_by, status, is_active, version from pm_updated_sub_items where id = ?";

	private final static String SQL_SELECTBY_USERID = 
			"select id, sub_item_id, user_id, modified_item_date, created_date, updated_date, created_by, updated_by, status, is_active, version from pm_updated_sub_items where user_id = ? and sub_item_id = ?";
	
	private final static String SQL_SELECTBY_ID =
			"select id, sub_item_id, user_id, modified_item_date, created_date, updated_date, created_by, updated_by, status, is_active, version from pm_updated_sub_items where user_id = ?";

	// NB : This entity has an auto-incremented primary key : "id"
	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER ,  // "sub_item_id" : INT - java.lang.Integer
			java.sql.Types.VARCHAR ,  // "user_id" : VARCHAR(255) - java.lang.String
			java.sql.Types.DATE ,  // "modified_item_date" : DATE - java.util.Date
			java.sql.Types.DATE ,  // "created_date" : DATE - java.util.Date
			java.sql.Types.DATE ,  // "updated_date" : DATE - java.util.Date
			java.sql.Types.VARCHAR ,  // "created_by" : VARCHAR(255) - java.lang.String
			java.sql.Types.VARCHAR ,  // "updated_by" : VARCHAR(255) - java.lang.String
			java.sql.Types.VARCHAR ,  // "status" : VARCHAR(255) - java.lang.String
			java.sql.Types.VARCHAR ,  // "is_active" : VARCHAR(255) - java.lang.String
			java.sql.Types.INTEGER   // "version" : INT - java.lang.Integer
	};

	private final static String SQL_INSERT = 
		"insert into pm_updated_sub_items ( sub_item_id, user_id, modified_item_date, created_date, updated_date, created_by, updated_by, status, is_active, version ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update pm_updated_sub_items set sub_item_id = ?, user_id = ?, modified_item_date = ?, created_date = ?, updated_date = ?, created_by = ?, updated_by = ?, status = ?, is_active = ?, version = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from pm_updated_sub_items where id = ?";

	private final static String DELETE_ITEMRECORD =
			"delete from pm_updated_sub_items where sub_item_id = ? and user_id = ?";
	
	private final static String DELETE_RECORDBY_USERID =
			"delete from pm_updated_sub_items where user_id = ? and modified_item_date = ?";

	
	private final static String SQL_COUNT_ALL = 
		"select count(*) from pm_updated_sub_items";

	private final static String SQL_COUNT = 
		"select count(*) from pm_updated_sub_items where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public PmUpdatedSubItemsDAOImplSpring() {
		super(AUTO_INCREMENTED_COLUMN, SQL_INSERT_TYPES);
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public PmUpdatedSubItems find( Integer id ) {
		Object[] primaryKey = new Object[] { id };
		return super.doSelect(primaryKey);		
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public boolean load( PmUpdatedSubItems pmUpdatedSubItems ) {
		return super.doSelect(pmUpdatedSubItems) ;
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public Integer insert(PmUpdatedSubItems pmUpdatedSubItems) {
		Long key = super.doInsertAutoIncr(pmUpdatedSubItems);
		return key.intValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public int update(PmUpdatedSubItems pmUpdatedSubItems) {
		return super.doUpdate(pmUpdatedSubItems);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public int delete( Integer id ) {
		Object[] primaryKey = new Object[] { id };
		return super.doDelete(primaryKey);		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public int delete( PmUpdatedSubItems pmUpdatedSubItems ) {
		return super.doDelete(pmUpdatedSubItems);
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public boolean exists( Integer id ) {
		Object[] primaryKey = new Object[] { id };
		return super.doExists(primaryKey);
	}
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	public boolean exists( PmUpdatedSubItems pmUpdatedSubItems ) {
		return super.doExists(pmUpdatedSubItems);
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
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}
	//----------------------------------------------------------------------
	@Override
	protected Object[] getValuesForInsert(PmUpdatedSubItems pmUpdatedSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY and DATA ( for SQL "SET x=?, y=?, ..." )
			// "id" is auto-incremented => no set in insert		
			pmUpdatedSubItems.getSubItemId() , // "sub_item_id" : java.lang.Integer
			pmUpdatedSubItems.getUserId() , // "user_id" : java.lang.String
			pmUpdatedSubItems.getModifiedItemDate() , // "modified_item_date" : java.util.Date
			pmUpdatedSubItems.getCreatedDate() , // "created_date" : java.util.Date
			pmUpdatedSubItems.getUpdatedDate() , // "updated_date" : java.util.Date
			pmUpdatedSubItems.getCreatedBy() , // "created_by" : java.lang.String
			pmUpdatedSubItems.getUpdatedBy() , // "updated_by" : java.lang.String
			pmUpdatedSubItems.getStatus() , // "status" : java.lang.String
			pmUpdatedSubItems.getIsActive() , // "is_active" : java.lang.String
			pmUpdatedSubItems.getVersion()  // "version" : java.lang.Integer
		};
	}
    //----------------------------------------------------------------------
	@Override
	protected Object[] getValuesForUpdate(PmUpdatedSubItems pmUpdatedSubItems) {
		return new Object[] {		
			//--- Returns DATA first ( for SQL "SET x=?, y=?, ..." )
			pmUpdatedSubItems.getSubItemId(), // "sub_item_id" : java.lang.Integer
			pmUpdatedSubItems.getUserId(), // "user_id" : java.lang.String
			pmUpdatedSubItems.getModifiedItemDate(), // "modified_item_date" : java.util.Date
			pmUpdatedSubItems.getCreatedDate(), // "created_date" : java.util.Date
			pmUpdatedSubItems.getUpdatedDate(), // "updated_date" : java.util.Date
			pmUpdatedSubItems.getCreatedBy(), // "created_by" : java.lang.String
			pmUpdatedSubItems.getUpdatedBy(), // "updated_by" : java.lang.String
			pmUpdatedSubItems.getStatus(), // "status" : java.lang.String
			pmUpdatedSubItems.getIsActive(), // "is_active" : java.lang.String
			pmUpdatedSubItems.getVersion(), // "version" : java.lang.Integer
			//--- Returns PRIMARY KEY at the end ( for SQL "WHERE key=?, ..." )
			pmUpdatedSubItems.getId()  // "id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	@Override
	protected Object[] getValuesForPrimaryKey(PmUpdatedSubItems pmUpdatedSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY values ( for SQL "WHERE key=?, ..." )
			pmUpdatedSubItems.getId()  // "id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	@Override
	protected RowMapper<PmUpdatedSubItems> getRowMapper(PmUpdatedSubItems o)  {
		//--- RowMapper to populate the given bean instance
		return new PmUpdatedSubItemsRowMapper(o) ;
	}
	//----------------------------------------------------------------------
	@Override
	protected RowMapper<PmUpdatedSubItems> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		return new PmUpdatedSubItemsRowMapper( new PmUpdatedSubItems() ) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param pmUpdatedSubItems
	 * @throws SQLException
	 */
	private void populateBean(ResultSet rs, PmUpdatedSubItems pmUpdatedSubItems) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		pmUpdatedSubItems.setId(rs.getInt("id")); // java.lang.Integer
		if ( rs.wasNull() ) { pmUpdatedSubItems.setId(null); }; // not primitive number => keep null value if any
		pmUpdatedSubItems.setSubItemId(rs.getInt("sub_item_id")); // java.lang.Integer
		if ( rs.wasNull() ) { pmUpdatedSubItems.setSubItemId(null); }; // not primitive number => keep null value if any
		pmUpdatedSubItems.setUserId(rs.getString("user_id")); // java.lang.String
		pmUpdatedSubItems.setModifiedItemDate(rs.getDate("modified_item_date")); // java.util.Date
		pmUpdatedSubItems.setCreatedDate(rs.getDate("created_date")); // java.util.Date
		pmUpdatedSubItems.setUpdatedDate(rs.getDate("updated_date")); // java.util.Date
		pmUpdatedSubItems.setCreatedBy(rs.getString("created_by")); // java.lang.String
		pmUpdatedSubItems.setUpdatedBy(rs.getString("updated_by")); // java.lang.String
		pmUpdatedSubItems.setStatus(rs.getString("status")); // java.lang.String
		pmUpdatedSubItems.setIsActive(rs.getString("is_active")); // java.lang.String
		pmUpdatedSubItems.setVersion(rs.getInt("version")); // java.lang.Integer
		if ( rs.wasNull() ) { pmUpdatedSubItems.setVersion(null); }; // not primitive number => keep null value if any
	}

    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class PmUpdatedSubItemsRowMapper implements RowMapper<PmUpdatedSubItems> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final PmUpdatedSubItems bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		PmUpdatedSubItemsRowMapper(PmUpdatedSubItems bean) {
			this.bean = bean ;
		}
		
		public PmUpdatedSubItems mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}

	public PmUpdatedSubItems findByUserId(String userId, Integer itemId) {
		try{
			return getJdbcTemplate().queryForObject(SQL_SELECTBY_USERID,new Object[]{userId,itemId}, new BeanPropertyRowMapper<PmUpdatedSubItems>(PmUpdatedSubItems.class));
		}catch(EmptyResultDataAccessException e){
			System.out.println("Exception in findByUserId:::"+e.getMessage());
		}
		return null;
	}

	public int deleteUserRecord(int itemId, String userId) {
		try{
			int i = getJdbcTemplate().update(DELETE_ITEMRECORD, new Object[]{itemId,userId});
			return i;
		}catch(EmptyResultDataAccessException e){
			System.out.println("Exception in deleteUserRecord:::"+e.getMessage());
		}
		return 1;
	}

	public List<PmUpdatedSubItems> findById(String userId) {
		try{
			return getJdbcTemplate().query(SQL_SELECTBY_ID,new Object[]{userId}, new BeanPropertyRowMapper<PmUpdatedSubItems>(PmUpdatedSubItems.class));
		}catch(EmptyResultDataAccessException e){
			System.out.println("Exception in findById:::"+e.getMessage());
		}
		return null;
	}

	public int deleteCurDateUserRecord(String userId, String date) {
		try{
			int i = getJdbcTemplate().update(DELETE_RECORDBY_USERID, new Object[]{userId,date});
			return i;
		}catch(EmptyResultDataAccessException e){
			System.out.println("Exception in deleteUserRecord:::"+e.getMessage());
		}
		return 1;
	}

}