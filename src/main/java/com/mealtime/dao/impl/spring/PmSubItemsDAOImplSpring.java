/*
 * Created on 8 Feb 2016 ( Time 19:50:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mealtime.bean.PmSubItems;
import com.mealtime.dao.PmSubItemsDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * PmSubItems DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
@Repository
public class PmSubItemsDAOImplSpring extends GenericDAO<PmSubItems> implements PmSubItemsDAO {

	private final static String SQL_SELECT = 
		"select item_id, item_name, item_desc, image_path, item_type, cost, created_date, updated_date, created_by, updated_by, status, is_active, version from pm_sub_items where item_id = ?";


	private final static String SQL_INSERT = 
		"insert into pm_sub_items ( item_id, item_name, item_desc, image_path, item_type, cost, created_date, updated_date, created_by, updated_by, status, is_active, version ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update pm_sub_items set item_name = ?, item_desc = ?, image_path = ?, item_type = ?, cost = ?, created_date = ?, updated_date = ?, created_by = ?, updated_by = ?, status = ?, is_active = ?, version = ? where item_id = ?";

	private final static String SQL_DELETE = 
		"delete from pm_sub_items where item_id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from pm_sub_items";

	private final static String SQL_COUNT = 
		"select count(*) from pm_sub_items where item_id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public PmSubItemsDAOImplSpring() {
		super();
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public PmSubItems find( Integer itemId ) {
		Object[] primaryKey = new Object[] { itemId };
		return super.doSelect(primaryKey);		
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public boolean load( PmSubItems pmSubItems ) {
		return super.doSelect(pmSubItems) ;
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public void insert(PmSubItems pmSubItems) {
		super.doInsert(pmSubItems);
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * DAO interface implementation
	 */
	
	public int update(PmSubItems pmSubItems) {
		return super.doUpdate(pmSubItems);
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
	
	public int delete( PmSubItems pmSubItems ) {
		return super.doDelete(pmSubItems);
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
	
	public boolean exists( PmSubItems pmSubItems ) {
		return super.doExists(pmSubItems);
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
	
	protected Object[] getValuesForInsert(PmSubItems pmSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY and DATA ( for SQL "SET x=?, y=?, ..." )
			pmSubItems.getItemId() , // "item_id" : java.lang.Integer
			pmSubItems.getItemName() , // "item_name" : java.lang.String
			pmSubItems.getItemDesc() , // "item_desc" : java.lang.String
			pmSubItems.getImagePath() , // "image_path" : java.lang.String
			pmSubItems.getItemType() , // "item_type" : java.lang.String
			pmSubItems.getCost() , // "cost" : java.lang.Double
			pmSubItems.getCreatedDate() , // "created_date" : java.util.Date
			pmSubItems.getUpdatedDate() , // "updated_date" : java.util.Date
			pmSubItems.getCreatedBy() , // "created_by" : java.lang.String
			pmSubItems.getUpdatedBy() , // "updated_by" : java.lang.String
			pmSubItems.getStatus() , // "status" : java.lang.String
			pmSubItems.getIsActive() , // "is_active" : java.lang.String
			pmSubItems.getVersion()  // "version" : java.lang.Integer
		};
	}
    //----------------------------------------------------------------------
	
	protected Object[] getValuesForUpdate(PmSubItems pmSubItems) {
		return new Object[] {		
			//--- Returns DATA first ( for SQL "SET x=?, y=?, ..." )
			pmSubItems.getItemName(), // "item_name" : java.lang.String
			pmSubItems.getItemDesc(), // "item_desc" : java.lang.String
			pmSubItems.getImagePath(), // "image_path" : java.lang.String
			pmSubItems.getItemType(), // "item_type" : java.lang.String
			pmSubItems.getCost(), // "cost" : java.lang.Double
			pmSubItems.getCreatedDate(), // "created_date" : java.util.Date
			pmSubItems.getUpdatedDate(), // "updated_date" : java.util.Date
			pmSubItems.getCreatedBy(), // "created_by" : java.lang.String
			pmSubItems.getUpdatedBy(), // "updated_by" : java.lang.String
			pmSubItems.getStatus(), // "status" : java.lang.String
			pmSubItems.getIsActive(), // "is_active" : java.lang.String
			pmSubItems.getVersion(), // "version" : java.lang.Integer
			//--- Returns PRIMARY KEY at the end ( for SQL "WHERE key=?, ..." )
			pmSubItems.getItemId()  // "item_id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	
	protected Object[] getValuesForPrimaryKey(PmSubItems pmSubItems)  {
		return new Object[] {
			//--- Returns PRIMARY KEY values ( for SQL "WHERE key=?, ..." )
			pmSubItems.getItemId()  // "item_id" : java.lang.Integer
		};
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<PmSubItems> getRowMapper(PmSubItems o)  {
		//--- RowMapper to populate the given bean instance
		return new PmSubItemsRowMapper(o) ;
	}
	//----------------------------------------------------------------------
	
	protected RowMapper<PmSubItems> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		return new PmSubItemsRowMapper( new PmSubItems() ) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param pmSubItems
	 * @throws SQLException
	 */
	private void populateBean(ResultSet rs, PmSubItems pmSubItems) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		pmSubItems.setItemId(rs.getInt("item_id")); // java.lang.Integer
		if ( rs.wasNull() ) { pmSubItems.setItemId(null); }; // not primitive number => keep null value if any
		pmSubItems.setItemName(rs.getString("item_name")); // java.lang.String
		pmSubItems.setItemDesc(rs.getString("item_desc")); // java.lang.String
		pmSubItems.setImagePath(rs.getString("image_path")); // java.lang.String
		pmSubItems.setItemType(rs.getString("item_type")); // java.lang.String
		pmSubItems.setCost(rs.getDouble("cost")); // java.lang.Double
		if ( rs.wasNull() ) { pmSubItems.setCost(null); }; // not primitive number => keep null value if any
		pmSubItems.setCreatedDate(rs.getDate("created_date")); // java.util.Date
		pmSubItems.setUpdatedDate(rs.getDate("updated_date")); // java.util.Date
		pmSubItems.setCreatedBy(rs.getString("created_by")); // java.lang.String
		pmSubItems.setUpdatedBy(rs.getString("updated_by")); // java.lang.String
		pmSubItems.setStatus(rs.getString("status")); // java.lang.String
		pmSubItems.setIsActive(rs.getString("is_active")); // java.lang.String
		pmSubItems.setVersion(rs.getInt("version")); // java.lang.Integer
		if ( rs.wasNull() ) { pmSubItems.setVersion(null); }; // not primitive number => keep null value if any
	}

    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class PmSubItemsRowMapper implements RowMapper<PmSubItems> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final PmSubItems bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		PmSubItemsRowMapper(PmSubItems bean) {
			this.bean = bean ;
		}
		
		
		public PmSubItems mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}
}