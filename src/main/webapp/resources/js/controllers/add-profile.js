'use strict';

angular.module('miniMealApp.addprofileCtrl', ['ngStorage']).
controller('AddProfileCtrl', function ($scope, $rootScope, $state, UserService, $localStorage, commonCode) {
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
	
	$scope.addUser = angular.copy($rootScope.user);
	$scope.addProfileErrorMsg = "";
	$scope.addProfile = function(){
		$scope.addProfileErrorMsg = "";
		UserService.checkMobileOrEmail($scope.mobileNumber, $scope.email, $rootScope.user.userId).then(
				function(response){
					if(response.data.statusCode == 200){
						var subject = "MealTime - Create Profile - One Time Password(OTP)";
						$rootScope.sendOTP($scope.mobileNumber, $scope.email, subject);
						$scope.otp = "";
					}else if(response.data.statusCode == 500){
						$scope.addProfileErrorMsg = response.data.errorMsg;
                		console.log("Error while adding user"+response.data.errorMsg);
					}
		});
	}
	
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
				function(response) {
					if(response.data.statusCode == 200){
						$('#otpModal').modal('hide');
						$scope.submitProfile();
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
	
	$scope.submitProfile = function(){
		$scope.addProfileErrorMsg = "";
		$scope.otp = "";
		var jsonObj = {};
		jsonObj.userId = $rootScope.user.userId;
		jsonObj.firstName = $scope.addUser.firstName;
		jsonObj.lastName = $scope.addUser.lastName;
		jsonObj.email = $scope.addUser.email;
		jsonObj.mobileNumber = $scope.addUser.mobileNumber;
		jsonObj.address = $scope.addUser.address;
		jsonObj.foodStyleS1 = $scope.addUser.foodType;
		jsonObj.foodStyleS2 = $scope.addUser.foodStyle;
		console.log($scope.profilePic);
		var file = $('#profilePic')[0].files[0];
		console.log(file);
		UserService.addUser(jsonObj, file).then(
				 function(response) {
					 if(response.data.statusCode == 200){
					 		console.log(response.data.data);
					 		$rootScope.user = response.data.data;
					 		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	                		//location.href = "#/profile";
	                		$state.go("profile");
	                	}else if(response.data.statusCode == 500){
	                		$scope.addProfileErrorMsg = response.data.errorMsg;
	                		console.log("Error while adding user"+response.data.errorMsg);
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }
	       );
	}
	
});