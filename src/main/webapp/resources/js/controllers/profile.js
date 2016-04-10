'use strict';

angular.module('miniMealApp.profileCtrl', ['ngStorage']).
controller('ProfileCtrl', function ($scope, $rootScope, $state, $localStorage, UserService) {
	
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
	
	console.log($rootScope.status);
	$scope.editUser = angular.copy($rootScope.user);
	//console.log(user);
	$scope.isEditForm=false;
	$scope.wrongOTPMsg="";
	$rootScope.profileShow = true;
	if(!angular.isDefined($rootScope.user)){
		//location.href = "#/addprofile";
		$state.go("addprofile");
	}else{
		if(angular.isDefined($rootScope.user) && $rootScope.user.roleId == 3){
			$scope.isEditForm=true;
		}
		//var mobileNumber = $scope.editUser.mobileNumber;
		var userId = $rootScope.user.userId;
		$rootScope.userProfilePic = "images/"+userId+".jpg";
		
		$rootScope.foodType = [{'label':'Veg','value':'veg'},{'label':'Non-Veg','value':'non-veg'}];
		$rootScope.foodStyle = [{'label':'North','value':'north'},{'label':'South','value':'south'}];
		
		
		$scope.editProfile = function(){
			console.log($scope.editUser);
			var subject = "MealTime - Edit Profile - One Time Password(OTP)";
			$rootScope.sendOTP($scope.editUser.mobileNumber, $scope.editUser.email, subject);
			$scope.otp = "";
			$scope.wrongOTPMsg ="";
		}
		$scope.verifyOTP = function(){
			UserService.verifyOTP($scope.editUser.mobileNumber, $scope.otp).then(
					function(response) {
						if(response.data.statusCode == 200){
							$('#otpModal').modal('hide');
							
							$scope.updateProfile();
							
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
		
		$scope.cancelEdit = function(modalId){
			$scope.editUser = angular.copy($rootScope.user);
			$rootScope.closeModal(modalId);
		}
		
		$scope.updateProfile = function(){
			$scope.otp = "";
			
			var user = $scope.editUser;
			var file = $('#profilePic')[0].files[0];
			console.log(file);
			if(file){
				$rootScope.userProfilePic = "";
			}
			UserService.updateUser(user, file).then(
					 function(response) {
						 if(response.data.statusCode == 200){
							 	$('#editSuccessModal').modal('show');
						 		console.log(response.data.data);
						 		$rootScope.user = response.data.data;
						 		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
						 		//var mobileNumber = $rootScope.user.mobileNumber;
						 		var userId = $rootScope.user.userId;
						 		$rootScope.userProfilePic = "images/"+userId+".jpg";
						 		$scope.isEditForm=false;
		                		//location.href = "#/profile";
						 		debugger;
						 		$('body').removeClass('modal-open');
								$('.modal-backdrop').remove();
		                		$state.go("profile");
		                	}else{
		                		console.log("Bad Request");
		                	}
		               },
		                function(errResponse){
		                    console.error('Something went wrong!!');
		                }
		       );
		}
	}
	
});