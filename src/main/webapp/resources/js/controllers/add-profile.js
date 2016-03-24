'use strict';

angular.module('miniMealApp.addprofileCtrl', []).
controller('AddProfileCtrl', function ($scope,$state,$rootScope,UserService) {
	
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
		jsonObj.firstName = $scope.firstName;
		jsonObj.lastName = $scope.lastName;
		jsonObj.email = $scope.email;
		jsonObj.mobileNumber = $scope.mobileNumber;
		jsonObj.address = $scope.address;
		jsonObj.foodStyleS1 = $scope.foodType;
		jsonObj.foodStyleS2 = $scope.foodStyle;
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