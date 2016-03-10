'use strict';

angular.module('miniMealApp.profileCtrl', []).
	controller('ProfileCtrl', function ($scope,$rootScope,$http) {
	
	if($rootScope.user == undefined || $rootScope.user == "" || $rootScope.user == null){
		location.href = "#/addprofile";
	}
	
	$rootScope.foodType = [{'label':'Veg','value':'veg'},{'label':'Non-Veg','value':'non-veg'}];
	$rootScope.foodStyle = [{'label':'North','value':'north'},{'label':'South','value':'south'}];
	
	console.log($rootScope.user);
	
	$scope.editProfile = function(){
		console.log($rootScope.user);
		$rootScope.sendOTP();
	}
	$scope.verifyOTP = function(){
		var isOTPValidated = $rootScope.validateOTP();
		if(isOTPValidated){
			$('#otpModal').modal('hide');
			console.log(response.data);
			//$scope.updateProfile();
		}else{
			console.log("Wrong OTP");
		}
	}
});