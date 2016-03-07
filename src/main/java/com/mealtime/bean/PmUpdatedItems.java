/*
 * Created on 5 Mar 2016 ( Time 18:48:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.bean;

import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'PmUpdatedItems' entity
 * 
 * @author Telosys Tools
 *
 */
public class PmUpdatedItems implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id INT 
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : item_id INT 
    private Integer itemId;

    // DB : user_id INT 
    private Integer userId;

    // DB : modified_item_date DATE 
    private Date modifiedItemDate;

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
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setItemId( Integer itemId ) {
        this.itemId = itemId;
    }
    public Integer getItemId() {
        return this.itemId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setModifiedItemDate( Date modifiedItemDate ) {
        this.modifiedItemDate = modifiedItemDate;
    }
    public Date getModifiedItemDate() {
        return this.modifiedItemDate;
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
        sb.append(id);
        sb.append("|");
        sb.append(itemId);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(modifiedItemDate);
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
