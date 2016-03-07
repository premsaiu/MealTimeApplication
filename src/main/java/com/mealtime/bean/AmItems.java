/*
 * Created on 5 Mar 2016 ( Time 18:48:36 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.mealtime.bean;

import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'AmItems' entity
 * 
 * @author Telosys Tools
 *
 */
public class AmItems implements Serializable {

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

    // DB : item_image VARCHAR 
    private String itemImage;

    // DB : item_price DOUBLE 
    private Double itemPrice;

    // DB : item_date DATE 
    private Date itemDate;

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

    public void setItemImage( String itemImage ) {
        this.itemImage = itemImage;
    }
    public String getItemImage() {
        return this.itemImage;
    }

    public void setItemPrice( Double itemPrice ) {
        this.itemPrice = itemPrice;
    }
    public Double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemDate( Date itemDate ) {
        this.itemDate = itemDate;
    }
    public Date getItemDate() {
        return this.itemDate;
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
        sb.append(itemImage);
        sb.append("|");
        sb.append(itemPrice);
        sb.append("|");
        sb.append(itemDate);
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