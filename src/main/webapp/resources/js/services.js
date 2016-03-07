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
    this.adminchk = function(mobileNo){
    	var request = $http({
    		method:"get",
    		url:baseURL+'checkUserRole.spring?mobileNo='+mobileNo,
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
    
    this.getSubListItems1 = function(){
    	var request = $http({
    		method:"get",
    		url:baseURL+'getSubItemsList1.spring',
    		dataType: "application/json"
    	});
    	return request; 
    };
    
    this.payment = function (obj) {
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
    };
    
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
	/*this.checkUser = function (mobileNo) {
        var request = $http({
            method:"get",
            url:  baseURL+'checkUser.spring?mobileNo='+mobileNo,
            dataType: "application/json"
        });
        return request;
    };*/
	
}]);
