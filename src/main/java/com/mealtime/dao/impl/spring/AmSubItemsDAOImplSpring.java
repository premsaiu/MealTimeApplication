/*
 * Created on 8 Feb 2016 ( Time 19:50:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mealtime.bean.AmSubItems;
import com.mealtime.dao.AmSubItemsDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * AmSubItems DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
@Repository
public class AmSubItemsDAOImplSpring extends GenericDAO<AmSubItems> implements AmSubItemsDAO {

	private final static String SQL_SELECT = 
		"select item_id, item_name, item_desc, image_path, item_type, cost, created_date, updated_date, created_by, updated_by, status, is_active, version from am_sub_items where item_id = ?";

	private final static String SQL_SELECT_ITEMS = 
			"select item_id, item_name, item_desc, image_path, item_type, cost, created_date, updated_date, created_by, updated_by, status, is_active, version from am_sub_items where item_id = ?";


	private final static String SQL_INSERT = 
		"insert into am_sub_items ( item_id, item_name, item_desc, image_path, item_type, cost, created_date, updated_date, created_by, updated_by, status, is_active, version ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update am_sub_items set item_name = ?, item_desc = ?, image_path = ?, item_type = ?, cost = ?, created_date = ?, updated_date = ?, created_by = ?, updated_by = ?, status = ?, is_active = ?, version = ? where item_id = ?";

	private final static String SQL_DELETE = 
		"delete from am_sub_items where item_id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from am_sub_items";

	private final static String SQL_COUNT = 
		"select count(*) from am_sub_items where item_id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public AmSubItemsDAOImplSpring() {
		super();
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public AmSubItems find( Integer itemId ) {
		Object[] primaryKey = new Object[] { itemId };
		return super.doSelect(primaryKey);		
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean load( AmSubItems amSubItems ) {
		return super.doSelect(amSubItems) ;
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public void insert(AmSubItems amSubItems) {
		super.doInsert(amSubItems);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int update(AmSubItems amSubItems) {
		return super.doUpdate(amSubItems);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int delete( Integer itemId ) {
		Object[] primaryKey = new Object[] { itemId };
		return super.doDelete(primaryKey);		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int delete( AmSubItems amSubItems ) {
		return super.doDelete(amSubItems);
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean exists( Integer itemId ) {
		Object[] primaryKey = new Object[] { itemId };
		return super.doExists(primaryKey);
	}
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean exists( AmSubItems amSubItems ) {
		return super.doExists(amSubItems);
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
	
	protected Object[] getValuesForInsert(AmSubItems amSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY and DATA ( for SQL "SET x=?, y=?, ..." )
			amSubItems.getItemId() , // "item_id" : java.lang.Integer
			amSubItems.getItemName() , // "item_name" : java.lang.String
			amSubItems.getItemDesc() , // "item_desc" : java.lang.String
			amSubItems.getImagePath() , // "image_path" : java.lang.String
			amSubItems.getItemType() , // "item_type" : java.lang.String
			amSubItems.getCost() , // "cost" : java.lang.Double
			amSubItems.getCreatedDate() , // "created_date" : java.util.Date
			amSubItems.getUpdatedDate() , // "updated_date" : java.util.Date
			amSubItems.getCreatedBy() , // "created_by" : java.lang.String
			amSubItems.getUpdatedBy() , // "updated_by" : java.lang.String
			amSubItems.getStatus() , // "status" : java.lang.String
			amSubItems.getIsActive() , // "is_active" : java.lang.String
			amSubItems.getVersion()  // "version" : java.lang.Integer
		};
	}
    //----------------------------------------------------------------------
	
	protected Object[] getValuesForUpdate(AmSubItems amSubItems) {
		return new Object[] {		
			//--- Returns DATA first ( for SQL "SET x=?, y=?, ..." )
			amSubItems.getItemName(), // "item_name" : java.lang.String
			amSubItems.getItemDesc(), // "item_desc" : java.lang.String
			amSubItems.getImagePath(), // "image_path" : java.lang.String
			amSubItems.getItemType(), // "item_type" : java.lang.String
			amSubItems.getCost(), // "cost" : java.lang.Double
			amSubItems.getCreatedDate(), // "created_date" : java.util.Date
			amSubItems.getUpdatedDate(), // "updated_date" : java.util.Date
			amSubItems.getCreatedBy(), // "created_by" : java.lang.String
			amSubItems.getUpdatedBy(), // "updated_by" : java.lang.String
			amSubItems.getStatus(), // "status" : java.lang.String
			amSubItems.getIsActive(), // "is_active" : java.lang.String
			amSubItems.getVersion(), // "version" : java.lang.Integer
			//--- Returns PRIMARY KEY at the end ( for SQL "WHERE key=?, ..." )
			amSubItems.getItemId()  // "item_id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	
	protected Object[] getValuesForPrimaryKey(AmSubItems amSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY values ( for SQL "WHERE key=?, ..." )
			amSubItems.getItemId()  // "item_id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<AmSubItems> getRowMapper(AmSubItems o)  {
		//--- RowMapper to populate the given bean instance
		return new AmSubItemsRowMapper(o) ;
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<AmSubItems> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		return new AmSubItemsRowMapper( new AmSubItems() ) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param amSubItems
	 * @throws SQLException
	 */
	private void populateBean(ResultSet rs, AmSubItems amSubItems) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		amSubItems.setItemId(rs.getInt("item_id")); // java.lang.Integer
		if ( rs.wasNull() ) { amSubItems.setItemId(null); }; // not primitive number => keep null value if any
		amSubItems.setItemName(rs.getString("item_name")); // java.lang.String
		amSubItems.setItemDesc(rs.getString("item_desc")); // java.lang.String
		amSubItems.setImagePath(rs.getString("image_path")); // java.lang.String
		amSubItems.setItemType(rs.getString("item_type")); // java.lang.String
		amSubItems.setCost(rs.getDouble("cost")); // java.lang.Double
		if ( rs.wasNull() ) { amSubItems.setCost(null); }; // not primitive number => keep null value if any
		amSubItems.setCreatedDate(rs.getDate("created_date")); // java.util.Date
		amSubItems.setUpdatedDate(rs.getDate("updated_date")); // java.util.Date
		amSubItems.setCreatedBy(rs.getString("created_by")); // java.lang.String
		amSubItems.setUpdatedBy(rs.getString("updated_by")); // java.lang.String
		amSubItems.setStatus(rs.getString("status")); // java.lang.String
		amSubItems.setIsActive(rs.getString("is_active")); // java.lang.String
		amSubItems.setVersion(rs.getInt("version")); // java.lang.Integer
		if ( rs.wasNull() ) { amSubItems.setVersion(null); }; // not primitive number => keep null value if any
	}

	public List<AmSubItems> getItemsList(){
		try{
			//not yet done
			
			return (List<AmSubItems>) getJdbcTemplate().queryForObject(SQL_SELECT_ITEMS, new Object[]{}, getRowMapper());
		}catch(EmptyResultDataAccessException e){
			System.out.println("Empty Result Access Exception occured in getItemsList method::"+e.getMessage());
			return null;
		}
	}
    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class AmSubItemsRowMapper implements RowMapper<AmSubItems> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final AmSubItems bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		AmSubItemsRowMapper(AmSubItems bean) {
			this.bean = bean ;
		}
		
		
		public AmSubItems mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}
}
