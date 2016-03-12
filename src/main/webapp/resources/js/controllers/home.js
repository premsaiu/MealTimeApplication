'use strict';

angular.module('miniMealApp.homeCtrl', []).

controller('HomeCtrl',  function ($scope,$rootScope,$state,UserService) {
	
    $('.menuSelect').click(function(){
    	$('.menuSelect').removeClass('active');
         $(this).addClass("active");
    });

	$rootScope.status=true;
	$scope.modalShow = function(){
		$('#myModal').modal('show');
	}
	if($rootScope.loggedUser == undefined || $rootScope.loggedUser == false ){
		$scope.modalShow();
	}
	$rootScope.adminchk=function(){
		UserService.adminchk($scope.mobileNumber).then(
	            function(response) {
	            	$rootScope.loggedUser = true;
	            	if(response.data == "" || response.data == null){
	            		$rootScope.adminuser = "";
	            	}else{
	            		$rootScope.adminuser = response.data;
	            		console.log($rootScope.adminuser);
	            	}
	           },
	            function(errResponse){
	                console.error('Error while checking user');
	            }
	   );
	}
	$rootScope.checkUser = function(){
		$rootScope.loginError = "";
		console.log("Mobile Number::"+$scope.mobileNumber);
		$rootScope.mobileNumber = $scope.mobileNumber;
		
		if($scope.password){
			UserService.checkAdmin($scope.mobileNumber,$scope.password).then(
	            function(response) {
	            	$rootScope.loggedUser = true;
	            	if(response.data.data == "" || response.data.data == null){
	            		console.log("Visitor");
	            		$rootScope.loginError = "Invalid Credentials. Please try again later";
	            	}else{
	            		$rootScope.user = response.data.data;
	            		console.log($rootScope.user);
	            		$rootScope.status=false;
	            		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	            		$('#myModal').modal('hide');
	            		$state.go('adminhome')
	            		}
	           },
	            function(errResponse){
	        	    $scope.loginError = "Invalid Credentials. Please try again later";
	                console.error('Error while checking user');
	            });
				}else{
					UserService.checkUser($scope.mobileNumber).then(
	                function(response) {
	                	$rootScope.loggedUser = true;
	                	if(response.data == "" || response.data == null){
	                		$rootScope.userName = "Visitor";
	                	}else{
	                		$rootScope.user = response.data;
	                		console.log($rootScope.user);
	                		$rootScope.status=true;
	                		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	                	}
	               },
	                function(errResponse){
	                    console.error('Error while checking user');
	                }
	       );
			$('#myModal').modal('hide');
		}
	}
	
	$rootScope.closeModal =function(modalId){
		$('#'+modalId).modal('hide');
	}
	
	$rootScope.sendOTP =function(mobileNumber, email, subject){
		UserService.sendOTP(mobileNumber, email, subject).then(
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
})