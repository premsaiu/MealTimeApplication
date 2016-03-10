'use strict';

angular.module('miniMealApp.homeCtrl', []).

	controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
		$scope.modalShow = function(){
			$('#myModal').modal('show');
		}
		if($rootScope.loggedUser == undefined || $rootScope.loggedUser == false ){
			$scope.modalShow();
		}
		$scope.checkUser = function(){
			console.log("Mobile Number::"+$scope.mobileNumber);
			$rootScope.mobileNumber = $scope.mobileNumber;
			UserService.checkUser($scope.mobileNumber).then(
	                function(response) {
	                	$rootScope.loggedUser = true;
	                	if(response.data == "" || response.data == null){
	                		$rootScope.userName = "Visitor";
	                	}else{
	                		$rootScope.user = response.data;
	                		console.log($rootScope.user);
	                		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	                	}
	               },
	                function(errResponse){
	                    console.error('Error while checking user');
	                }
	       );
			$('#myModal').modal('hide');
		}
  });