'use strict';

angular.module('miniMealApp.profileCtrl', ['ngStorage']).
controller('ProfileCtrl', function ($scope, $rootScope, $state, $localStorage, UserService, commonCode) {
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
	
	console.log($rootScope.status);
    $scope.editUser = angular.copy($rootScope.user);
	//console.log(user);
	$scope.isEditForm=false;
	$scope.wrongOTPMsg="";
	$rootScope.profileShow = true;
	if(!angular.isDefined($rootScope.user)){
		//location.href = "#/addprofile";
		$state.go("profile");
	}else{
		if(angular.isDefined($rootScope.user) && $rootScope.user.roleId == 3){
			$scope.isEditForm=true;
		}
		//var mobileNumber = $scope.editUser.mobileNumber;
		var userId = $rootScope.user.userId;
		$rootScope.userProfilePic = "images/"+userId+".jpg";
		
		$rootScope.foodType = [{'label':'North Indian','value':'North Indian'},{'label':'South Indian','value':'South Indian'}];
		$rootScope.foodStyle = [{'label':'Vegetarian','value':'vegetarian'},{'label':'Eggetarian','value':'Eggetarian'}];
		$rootScope.dinner = [{'label':'Rice','value':'rice'},{'label':'Roti','value':'roti'},{'label':'Rice & Roti','value':'Rice-Roti'}];
		$rootScope.packing = [{'label':'I love polythene','value':'I love polythene'},{'label':'Iam against polythene','value':' Iam against polythene'}];
		$rootScope.areaList = [{'value':'Madhapur Police Station'},{'value':'Madhapur 100 feet Road'},{'value':'Madhapur Kavuri Hills Phase I'},
		                       {'value':'Madhapur Kavuri Hills Phase II'},{'value':'Madhapur Megha Hills'},{'value':'Madhapur Ayyappa Society'},{'value':'Madhapur Hitech City'},
		                       {'value':'Madhapur Image Hospital Road'},{'value':'Madhapur Image Garden Road'}];
		$rootScope.cityList = [{'value':'Hyderabad'}];
		$scope.editProfile = function(valid){
			$scope.profileerrorcheck=valid;
			if(valid){
			console.log($scope.editUser);
			var subject = "MealTime - Edit Profile - One Time Password(OTP)";
			$rootScope.sendOTP($scope.editUser.mobileNumber, $scope.editUser.email, subject);
			$scope.otp = "";
			$scope.wrongOTPMsg ="";
		}
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
			$('#'+modalId).modal('hide');
		}
		
			$scope.updateProfile = function(valid){
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
						 		$scope.editUser = angular.copy($rootScope.user);
						 		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
						 		//var mobileNumber = $rootScope.user.mobileNumber;
						 		var userId = $rootScope.user.userId;
						 		$rootScope.userProfilePic = "images/"+userId+".jpg";
						 		$scope.isEditForm=false;
		                		//location.href = "#/profile";
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