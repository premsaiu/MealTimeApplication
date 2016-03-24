/*
 * Created on 24 Mar 2016 ( Time 07:53:30 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.bean;

import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'ScheduleEnquiry' entity
 * 
 * @author Telosys Tools
 *
 */
public class ScheduleEnquiry implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : enquiry_id INT 
    private Integer enquiryId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : user_id VARCHAR 
    private String userId;

    // DB : schedule_date_time DATETIME 
    private Date scheduleDateTime;

    // DB : mobile_number VARCHAR 
    private String mobileNumber;

    // DB : name VARCHAR 
    private String name;

    // DB : address VARCHAR 
    private String address;

    // DB : created_date DATE 
    private Date createdDate;

    // DB : updated_date DATE 
    private Date updatedDate;

    // DB : created_by VARCHAR 
    private String createdBy;

    // DB : updated_by VARCHAR 
    private String updatedBy;

    // DB : status VARCHAR 
    private String status;

    // DB : is_active VARCHAR 
    private String isActive;

    // DB : version INT 
    private Integer version;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setEnquiryId( Integer enquiryId ) {
        this.enquiryId = enquiryId ;
    }

    public Integer getEnquiryId() {
        return this.enquiryId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setUserId( String userId ) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }

    public void setScheduleDateTime( Date scheduleDateTime ) {
        this.scheduleDateTime = scheduleDateTime;
    }
    public Date getScheduleDateTime() {
        return this.scheduleDateTime;
    }

    public void setMobileNumber( String mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setAddress( String address ) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setUpdatedDate( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    public void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setUpdatedBy( String updatedBy ) {
        this.updatedBy = updatedBy;
    }
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public void setIsActive( String isActive ) {
        this.isActive = isActive;
    }
    public String getIsActive() {
        return this.isActive;
    }

    public void setVersion( Integer version ) {
        this.version = version;
    }
    public Integer getVersion() {
        return this.version;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(enquiryId);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(scheduleDateTime);
        sb.append("|");
        sb.append(mobileNumber);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(updatedDate);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(updatedBy);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(isActive);
        sb.append("|");
        sb.append(version);
        return sb.toString(); 
    } 


}
