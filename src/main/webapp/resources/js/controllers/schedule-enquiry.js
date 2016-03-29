'use strict';

angular.module('miniMealApp.scheduleEnquiryCtrl', [])
.controller('ScheduleEnquiryCtrl',function ($scope, $rootScope, $state, UserService, $localStorage, $timeout) {
	
	$scope.schedEnqErrorMsg = "";
	var mobileNo = "";
	$scope.notNow = function(){
	$('#myModal').modal('hide');
		$(".adminsection").hide();
		$rootScope.newmenu = true;
		$rootScope.status=false;
		$state.go('ourstory');
	}
	
	$scope.submitsched=function(){
		var subject = "MealTime - Sample Meal - One Time Password(OTP)";
		mobileNo = $scope.sched.number;
		$rootScope.sendOTP(mobileNo, null, subject);
		console.log(mobileNo);
		$scope.sched.otp = "";
	}
	
	
	$scope.verifyOTP = function(){
		console.log(mobileNo);
		UserService.verifyOTP(mobileNo, $scope.sched.otp).then(
				function(response) {
					if(response.data.statusCode == 200){
						$('#otpModal').modal('hide');
						$scope.submitScheduleEnquiry();
					}else{
						$scope.wrongOTPMsg="Invalid OTP. Please enter Correct OTP";
						console.log("Bad Request");
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
				}
		);
	}
	
	
	$scope.submitScheduleEnquiry = function(){
		$scope.schedEnqMsg = false;
		$scope.schedEnqErrorMsg = "";
		UserService.subscribe($scope.sched).then(function(response) { 
					if(response.data.statusCode == 200){
						$scope.schedEnqMsg = true;
					}else if(response.data.statusCode == 500){
						$scope.schedEnqErrorMsg = response.data.errorMsg;
					}
					if(!angular.isDefined($rootScope.user)){
						  $rootScope.checkUserByMobile(mobileNo);
					}
					$timeout(function() {
						  $state.go('profile');
					 }, 10000);
		});
	}
	
});