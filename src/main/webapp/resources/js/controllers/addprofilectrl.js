'use strict';

angular.module('miniMealApp.addprofileCtrl', []).
controller('AddProfileCtrl', function ($scope,$rootScope,UserService) {
	
	$scope.addProfile = function(){
		$rootScope.sendOTP();
	}
	$rootScope.sendOTP =function(){
		UserService.sendOTP($scope.mobileNumber, $scope.email).then(
				function(response) {
					if(response.status == 200){
						$('#otpModal').modal('show');
					}else{
						console.log("Bad Request");
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
				}
		);
	}
	$scope.verifyOTP = function(){
		var isOTPValidated = $rootScope.validateOTP();
		if(isOTPValidated){
			$('#otpModal').modal('hide');
			console.log(response.data);
			$scope.submitProfile();
		}else{
			console.log("Wrong OTP");
		}
	}
	$rootScope.validateOTP = function(){
		UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
				function(response) {
					if(response.status == 200){
						return true;
					}else{
						console.log("Bad Request");
						return false;
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
					return false;
				}
		);
	}
	
	$scope.submitProfile = function(){
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
