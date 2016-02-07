'use strict';

angular.module('miniMealApp.services',[])
.service('UserService', ['$http', '$q', function($http, $q){

	this.checkUser = function (mobileNo) {
        var request = $http({
            method:"get",
            url:  'http://localhost:8080/MealTimeApplication/checkUser.spring?mobileNo='+mobileNo,
            dataType: "application/json"
        });
        return request;
    };
    
    this.sendOTP = function (mobileNo, email) {
        var request = $http({
            method:"get",
            url:  'http://localhost:8080/MealTimeApplication/sendOTP.spring?mobileNo='+mobileNo+'&email='+email,
            dataType: "application/json"
        });
        return request;
    };
    
    this.verifyOTP = function (mobileNo, otp) {
        var request = $http({
            method:"get",
            url:  'http://localhost:8080/MealTimeApplication/verifyOTP.spring?mobileNo='+mobileNo+'&otp='+otp,
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
            url:  'http://localhost:8080/MealTimeApplication/saveProfile.spring',
            data : fd,
            headers : {
                'Content-Type' : undefined
               },
            transformRequest : angular.identity,
        });
        return request;
    };
    
}]);