package com.mealtime.form;

import java.util.Date;

public class UserForm {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String address;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String updatedBy;

    private String status;

    private String isActive;

    private Integer version;

    private Integer roleId;

    private String filePath;

    private String foodStyleS1;

    private String foodStyleS2;

    private String password;
    
    private String newPassword;

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setUserId( String userId ) {
        this.userId = userId ;
    }

    public String getUserId() {
        return this.userId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setMobileNumber( String mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
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

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setFilePath( String filePath ) {
        this.filePath = filePath;
    }
    public String getFilePath() {
        return this.filePath;
    }

    public void setFoodStyleS1( String foodStyleS1 ) {
        this.foodStyleS1 = foodStyleS1;
    }
    public String getFoodStyleS1() {
        return this.foodStyleS1;
    }

    public void setFoodStyleS2( String foodStyleS2 ) {
        this.foodStyleS2 = foodStyleS2;
    }
    public String getFoodStyleS2() {
        return this.foodStyleS2;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(userId);
        sb.append("|");
        sb.append(firstName);
        sb.append("|");
        sb.append(lastName);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(mobileNumber);
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
        sb.append("|");
        sb.append(roleId);
        sb.append("|");
        sb.append(filePath);
        sb.append("|");
        sb.append(foodStyleS1);
        sb.append("|");
        sb.append(foodStyleS2);
        return sb.toString(); 
    }

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	} 




}
