'use strict';

angular.module('miniMealApp.adminprofileCtrl', []).
controller('AdminProfileCtrl', function ($scope,$rootScope,UserService,AdminService) {
	$scope.isSelectedUser = false;
	$scope.isEditForm=false;
	$rootScope.foodType = [{'label':'Veg','value':'veg'},{'label':'Non-Veg','value':'non-veg'}];
	$rootScope.foodStyle = [{'label':'North','value':'north'},{'label':'South','value':'south'}];
	$scope.searchUser = function(){
		UserService.checkUser($scope.searchMobileNumber).then(
	            function(response) {
	            	if(response.data == "" || response.data == null){
	            		$scope.isSelectedUserExists = false;
	            		$scope.isSelectedUser = true;
	            	}else{
	            		$scope.isSelectedUser = true;
	            		$scope.isSelectedUserExists = true;
	            		$rootScope.selectedUser = response.data;
	            		$scope.selectedUserProfilePic = "images/"+$rootScope.selectedUser.userId+".jpg";
	            		$scope.selectedEditUser = angular.copy($rootScope.selectedUser);
	            	}
	           },
	            function(errResponse){
	                console.error('Error while checking user');
	            }
	   );
	}
	$scope.selectedUserEditProfile = function(){
		$('#selected-user-edit-confirm-modal').modal('show');
	}
	
	$scope.cancel = function(){
		$scope.selectedEditUser = angular.copy($rootScope.selectedUser);
		$scope.isEditForm=false;
		location.href = "#/admprofile";
	}
	
	$scope.selectedUserCancelEdit = function(modalId){
		//$scope.selectedEditUser = angular.copy($rootScope.selectedUser);
		$rootScope.closeModal(modalId);
	}
	
	$scope.selectedUserUpdateProfile = function(){
		$('#selected-user-edit-confirm-modal').modal('hide');
		var user = $scope.selectedEditUser;
		user.updatedBy = $rootScope.user.userId;
		var file = $('#selectedUserProfilePic')[0].files[0];
		console.log(file);
		if(file){
			$rootScope.selectedUserProfilePic = "";
		}
		AdminService.updateUser(user, file).then(
				 function(response) {
					 if(response.data.statusCode == 200){
						 	$('#selectedUserEditSuccessModal').modal('show');
					 		console.log(response.data.data);
					 		$rootScope.selectedUser = response.data.data;
					 		var userId = $rootScope.selectedUser.userId;
					 		$rootScope.selectedUserProfilePic = "images/"+userId+".jpg";
					 		$scope.isEditForm=false;
	                		location.href = "#/admprofile";
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