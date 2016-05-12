package com.mealtime.dao.impl.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mealtime.bean.AlertBean;
import com.mealtime.dao.AlertDAO;
import com.mealtime.dao.impl.spring.commons.GenericDAO;

@Repository
public class AlertDAOImplSpring extends GenericDAO<AlertBean> implements AlertDAO {

	private final static String SQL_ALERT = "select us.user_id as user_id,us.end_date as end_date,um.email as email,um.mobile_number as mobile_number from (select * from mealtime.user_subscription where (end_date-start_date)<=5)us"
			+ ",mealtime.user_master um where us.user_id=um.user_id";
	
	private static final Logger logger = Logger.getLogger(AlertDAOImplSpring.class);
	
	public List<AlertBean> checkList() {
		List<AlertBean> alertBeanList= new ArrayList<AlertBean>();
		try{
			logger.info("Quartz scheduler"+SQL_ALERT);
			alertBeanList=getJdbcTemplate().query(SQL_ALERT, getRowMapper());
			logger.info("Quartz scheduler"+alertBeanList.size());
			return alertBeanList;		
	}catch(Exception e){
		logger.error("Exception occured in checkList method::"+e.getMessage());
		return alertBeanList;
	}		
	}
	
	@Override
	protected String getSqlSelect() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSqlInsert() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSqlUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSqlDelete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSqlCount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getSqlCountAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Object[] getValuesForInsert(AlertBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Object[] getValuesForUpdate(AlertBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Object[] getValuesForPrimaryKey(AlertBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected RowMapper<AlertBean> getRowMapper(AlertBean bean) {
		// TODO Auto-generated method stub
		return new AlertRowMapper( bean ) ;
	}
	@Override
	protected RowMapper<AlertBean> getRowMapper() {
		// TODO Auto-generated method stub
		return new AlertRowMapper( new AlertBean() ) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	private class AlertRowMapper implements RowMapper<AlertBean> {

		/**
		 * The bean instance that will be populated from the ResultSet
		 */
		private final AlertBean bean ;
		
		/**
		 * Constructor
		 * @param bean the bean to be populated 
		 */
		AlertRowMapper(AlertBean bean) {
			this.bean = bean ;
		}
		
		
		public AlertBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			populateBean(rs, this.bean);
			return this.bean;
		}
	}
	
	private void populateBean(ResultSet rs, AlertBean alertBean) throws SQLException {
		logger.info("in populat ebean");
		//--- Set data from ResultSet to Bean attributes
		alertBean.setUser_Id(rs.getString("user_id"));// java.lang.String
		alertBean.setEmail(rs.getString("email"));// java.lang.String
		alertBean.setMobile_Number(rs.getString("mobile_number"));// java.lang.String
		alertBean.setEnd_Date(rs.getDate("end_date"));// java.util.Date
	}
}