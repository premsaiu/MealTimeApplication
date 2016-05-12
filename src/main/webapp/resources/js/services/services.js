'use strict';

angular.module('miniMealApp.services', ['ngStorage'])
.service('UserService', ['$http', '$q', function($http, $q){
	var baseURL = "http://localhost:8181/MealTimeApplication/";
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
    
    this.checkMobileOrEmail = function (mobileNo, email, userId) {
        var request = $http({
            method:"get",
            url:  baseURL+'checkMobileOrEmail.spring?mobileNumber='+mobileNo+'&email='+email+'&userId='+userId,
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
    
    this.updateDinnerItem = function(itemId){
    	var request = $http({
    		method:"get",
    		url:baseURL+'updateDinnerItem.spring?itemId='+itemId,
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
    
    this.cancelDinnerItems = function(itemObj,userId) {
    	var request = $http({
    		method:"post",
    		data:itemObj,
    		url:baseURL+'cancelDinnerItem.spring?userId='+userId,
    		dataType: "application/json"
    	});
    	return request; 
    }
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

    this.subscribeNow = function(firstname,lastname,mobile,date,area){
    	var request = $http({
    		method:"get",
    		url:baseURL+'subscribeNow.spring?firstname='+firstname+'&lastname='+lastname+'&mobile='+mobile+'&date='+date+'&area='+area,
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
    
    this.requestSampleMeal = function (sampleMealObj) {
        var request = $http({
            method:"post",
            url:  baseURL+'sampleMeal.spring',
            data : sampleMealObj,
            dataType: "application/json"
        });
        return request;
    };

    this.subscribe = function (sched) {
    	//var as=sched.date.split('/');//"undefined-undefined-2016-05-02T12:00:00+0530"
    	//var date=as[2]+"-"+as[1]+"-"+as[0]+"T"+sched.time+"+0530";
    	var date=sched.date+"T"+sched.time+"+0530";
		var subscribedata={
				//"userId":"MT015",
				"mobileNumber":sched.number,
				"scheduleDateTime":date,
				"name":sched.name,
				"address":sched.area
					}

    	var request = $http({
    		method:"post",
    		url:baseURL+'scheduleEnquiry.spring',
    		dataType: "application/json",
    		data : subscribedata
    	});
    	return request;
  };
  this.submitleavemessage = function(leavedata) {
      var request = $http({
          method:"post",
          url:  baseURL+'saveFeedBack.spring',
          data : leavedata,
          dataType: "application/json"
      });
      return request;
  };
  this.checkSchedule = function(mobileNumber, scheduleDate) {
  	var request = $http({
  		method:"get",
  		url:baseURL+'checkSchedule.spring?mobileNumber='+mobileNumber+'&scheduleEnqDate='+scheduleDate,
  		dataType: "application/json"
  	});
  	return request; 	
	};
	
	this.checkSampleMeal = function(mobileNumber, sampleMealDate, name) {
	  	var request = $http({
	  		method:"get",
	  		url:baseURL+'checkSampleMeal.spring?mobileNumber='+mobileNumber+'&sampleMealDate='+sampleMealDate+'&name='+name,
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
    
    this.getPendingSubscribedUsers = function() {
    	var request = $http({
    		method:"get",
    		url:baseURL+'getPendingSubscribedUsers.spring',
    		dataType: "application/json"
    	});
    	return request; 	
	};
	
	this.submitPayment = function(paymentForm) {
        var request = $http({
            method:"post",
            url:  baseURL+'payUser.spring',
            data : paymentForm,
            dataType: "application/json"
        });
        return request;
    };
   
    
	
}])
.factory('commonCode', function ($window, $localStorage) {
        var root = {};
        root.logout = function(){
            $localStorage.$reset();
    		window.location.assign("");
        };
        return root;
});