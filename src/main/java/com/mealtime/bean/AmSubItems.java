/*
 * Created on 8 Feb 2016 ( Time 19:50:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.bean;

import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'AmSubItems' entity
 * 
 * @author Telosys Tools
 *
 */
public class AmSubItems implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : item_id INT 
    private Integer itemId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : item_name VARCHAR 
    private String itemName;

    // DB : item_desc VARCHAR 
    private String itemDesc;

    // DB : image_path VARCHAR 
    private String imagePath;

    // DB : item_type VARCHAR 
    private String itemType;

    // DB : cost DOUBLE 
    private Double cost;

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
    public void setItemId( Integer itemId ) {
        this.itemId = itemId ;
    }

    public Integer getItemId() {
        return this.itemId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setItemName( String itemName ) {
        this.itemName = itemName;
    }
    public String getItemName() {
        return this.itemName;
    }

    public void setItemDesc( String itemDesc ) {
        this.itemDesc = itemDesc;
    }
    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setImagePath( String imagePath ) {
        this.imagePath = imagePath;
    }
    public String getImagePath() {
        return this.imagePath;
    }

    public void setItemType( String itemType ) {
        this.itemType = itemType;
    }
    public String getItemType() {
        return this.itemType;
    }

    public void setCost( Double cost ) {
        this.cost = cost;
    }
    public Double getCost() {
        return this.cost;
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
        sb.append(itemId);
        sb.append("|");
        sb.append(itemName);
        sb.append("|");
        sb.append(itemDesc);
        sb.append("|");
        sb.append(imagePath);
        sb.append("|");
        sb.append(itemType);
        sb.append("|");
        sb.append(cost);
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
