'use strict';

angular.module('miniMealApp.services',[])
.service('UserService', ['$http', '$q', function($http, $q){
	var baseURL = "http://localhost:8080/MealTimeApplication/";
	this.checkUser = function (mobileNo) {
        var request = $http({
            method:"get",
            url:  baseURL+'checkUser.spring?mobileNo='+mobileNo,
            dataType: "application/json"
        });
        return request;
    };
   
    this.checkAdmin = function (mobileNo,password) {
		var logindata={
				mobileNumber:mobileNo,
				password:password
		}

    	var request = $http({
    		method:"post",
    		url:baseURL+'checkAdminExistence.spring',
    		dataType: "application/json",
    		data : logindata
    	});
    	return request;
    	};
    	
	this.checkSubItem = function (subItemsdata) {
		var request = $http({
    		method:"post",
    		url:baseURL+'getBreakfastSubItem.spring',
    		dataType: "application/json",
    		data : subItemsdata
    	});
    	return request;
	}
	
	this.checkDinnerSubItem = function (subItemsdata) {
		var request = $http({
    		method:"post",
    		url:baseURL+'getDinnerSubItem.spring',
    		dataType: "application/json",
    		data : subItemsdata
    	});
    	return request;
	}
	
	this.deleteSubItemAddon = function (itemId, userId) {
        var request = $http({
            method:"get",
            url:  baseURL+'deleteSubItemAddon.spring?itemId='+itemId+'&userId='+userId,
            dataType: "application/json"
        });
        return request;
    };
    
    this.deleteDinnerSubItemAddon = function (itemId, userId) {
        var request = $http({
            method:"get",
            url:  baseURL+'deleteDinnerSubItemAddon.spring?itemId='+itemId+'&userId='+userId,
            dataType: "application/json"
        });
        return request;
    };
        
    this.adminchk = function(mobileNo){
    	var request = $http({
    		method:"get",
    		url:baseURL+'checkUserRole.spring?mobileNo='+mobileNo,
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.checkMobileOrEmail = function (mobileNo, email) {
        var request = $http({
            method:"get",
            url:  baseURL+'checkMobileOrEmail.spring?mobileNumber='+mobileNo+'&email='+email,
            dataType: "application/json"
        });
        return request;
    };
   
    this.sendOTP = function (mobileNo, email, subject) {
        var request = $http({
            method:"get",
            url:  baseURL+'sendOTP.spring?mobileNo='+mobileNo+'&email='+email+'&subject='+subject,
            dataType: "application/json"
        });
        return request;
    };
    
    this.verifyOTP = function (mobileNo, otp) {
        var request = $http({
            method:"get",
            url:  baseURL+'verifyOTP.spring?mobileNo='+mobileNo+'&otp='+otp,
            dataType: "application/json"
        });
        return request;
    };
    
    
    this.addUser = function (obj, file) {
    	var fd = new FormData();
    	fd.append("file", file);
    	fd.append("model", JSON.stringify(obj));
        var request = $http({
            method:"post",
            url:  baseURL+'saveProfile.spring',
            data : fd,
            headers : {
                'Content-Type' : undefined
               },
            transformRequest : angular.identity,
        });
        return request;
    };
    
    this.updateUser = function (obj, file) {
    	var fd = new FormData();
    	fd.append("file", file);
    	fd.append("model", JSON.stringify(obj));
        var request = $http({
            method:"post",
            url:  baseURL+'updateProfile.spring',
            data : fd,
            headers : {
                'Content-Type' : undefined
               },
            transformRequest : angular.identity,
        });
        return request;
    };
    
    this.getSubListItems = function(){
    	var request = $http({
    		method:"get",
    		url:baseURL+'getSubItemsList.spring',
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.getBreakfastItem = function(userId,addId){
    	var request = $http({
    		method:"get",
    		url:baseURL+'getBreakfastItem.spring?userId='+userId+'&addId='+addId,
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.getDinnerItem = function(userId,addId){
    	var request = $http({
    		method:"get",
    		url:baseURL+'getDinnerItem.spring?userId='+userId+'&addId='+addId,
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.payment = function(userId,paidAmount){
    	var request = $http({
    		method:"get",
    		url:baseURL+'payment.spring?userId='+userId+'&paidAmount='+paidAmount,
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.cancelItems = function(itemId,userId) {
    	var request = $http({
    		method:"get",
    		url:baseURL+'cancelItem.spring?itemId='+itemId+'&userId='+userId,
    		dataType: "application/json"
    	});
    	return request; 
    }
    
    this.cancelDinnerItems = function(itemId,userId) {
    	var request = $http({
    		method:"get",
    		url:baseURL+'cancelDinnerItem.spring?itemIds='+itemId[0].itemId+'&itemIds='+itemId[1].itemId+'&itemIds='+itemId[1].itemId+'&userId='+userId,
    		dataType: "application/json"
    	});
    	return request; 
    }
    //ids[]=id1&ids[]=id2&ids[]=id3&ids[]=id4&ids[]=id5
    this.getSubListItems1 = function(){
    	var request = $http({
    		method:"get",
    		url:baseURL+'getSubItemsList1.spring',
    		dataType: "application/json"
    	});
    	return request; 
    };
    
   /* this.payment = function (obj) {
        var request = $http({
            method:"post",
            url:  baseURL+'payment.spring',
            data : obj,
            headers : {
                'Content-Type' : "application/json"
               },
            transformRequest : angular.identity,
        });
        return request;
    };*/
    
    this.subscribeUser = function(user){
    	var request = $http({
    		method:"post",
    		url:baseURL+'subscribeuser.spring',
    		dataType: "application/json",
    		data : user
    	});
    	return request; 
    };
    
    this.checkSubscription = function(userId){
    	var request = $http({
    		method:"get",
    		url:baseURL+'subscribecheck.spring?userId='+userId,
    		dataType: "application/json"
    	});
    	return request; 
    };

    this.walletCheck = function(userId) {
    	var request = $http({
    		method:"get",
    		url:baseURL+'walletCheck.spring?userId='+userId,
    		dataType: "application/json"
    	});
    	return request; 	
	};
	
    this.chngPasswordService = function (obj) {
        var request = $http({
            method:"post",
            url:  baseURL+'changepwd.spring',
            data : obj,
            dataType: "application/json"
        });
        return request;
    };

}]).
service('AdminService', ['$http', '$q', function($http, $q){
	
	var baseURL = "http://localhost:8080/MealTimeApplication/admin/";
	this.updateUser = function (obj, file) {
    	var fd = new FormData();
    	fd.append("file", file);
    	fd.append("model", JSON.stringify(obj));
        var request = $http({
            method:"post",
            url:  baseURL+'updateUser.spring',
            data : fd,
            headers : {
                'Content-Type' : undefined
               },
            transformRequest : angular.identity,
        });
        return request;
    };
	
}]);
