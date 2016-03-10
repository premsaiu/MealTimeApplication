'use strict';

angular.module('miniMealApp.addprofileCtrl', []).
controller('AddProfileCtrl', function ($scope,$rootScope,UserService) {
	
	$scope.addProfile = function(){
		var subject = "MealTime - Create Profile - One Time Password(OTP)";
		$rootScope.sendOTP($scope.mobileNumber, $scope.email, subject);
		$scope.otp = "";
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
		$scope.otp = "";
		var jsonObj = {};
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
	                		location.href = "#/profile";
	                	}else{
	                		console.log("Bad Request");
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }
	       );
	}
	
});
