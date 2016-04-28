'use strict';

angular.module('miniMealApp.scheduleEnquiryCtrl', ['ngStorage'])
.controller('ScheduleEnquiryCtrl',function ($scope, $rootScope, $state, $localStorage, $timeout, $filter, UserService, commonCode) {
	$rootScope.commonCode = commonCode;
	$rootScope.loggedUser = $localStorage.loggedUser;
	$rootScope.adminuser = $localStorage.adminuser;
	$rootScope.newmenu = $localStorage.newmenu;
	$rootScope.status = $localStorage.status;
	$rootScope.adminuser = $localStorage.adminuser;
	$rootScope.mobileNumber = $localStorage.mobileNumber;
	$rootScope.user = $localStorage.user;
	$rootScope.regUser = $localStorage.regUser;
	$rootScope.userName = $localStorage.userName;
	$rootScope.profileShow = $localStorage.profileShow;
	
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
		$scope.schedEnqErrorMsg = "";
		var scheduleDate = $filter('date')(new Date($('#datetimePicker').val()), 'yyyy-MM-dd');
		var subject = "MealTime - Schedule Enquiry - One Time Password(OTP)";
		mobileNo = $scope.sched.number;
		UserService.checkSchedule(mobileNo, scheduleDate).then(function(response){
			if(response.data.statusCode == 200){
				$rootScope.sendOTP(mobileNo, null, subject);
				console.log(mobileNo);
				$scope.sched.otp = "";
			}else if(response.data.statusCode == 500){
				$scope.schedEnqErrorMsg = response.data.errorMsg;
			}
		});
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
		$scope.sched.date=$filter('date')(new Date($('#datetimePicker').val()), 'yyyy-MM-dd');
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