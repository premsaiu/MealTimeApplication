'use strict';

angular.module('miniMealApp.adminpaymentCtrl', ['ngStorage']).
controller('AdminPaymentCtrl', function ($scope, $rootScope, $state, $localStorage, UserService, AdminService) {
	
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
	
	$scope.getPendingSubscribedUsers = function(){
		AdminService.getPendingSubscribedUsers().then(
				function(response){
					if(response.data.statusCode == 200){
						$rootScope.pendingSubscribedUsers = response.data.data;
					}else{
						console.log("Bad Request");
					}
				}
		);
	}
	$scope.getPendingSubscribedUsers();
	
	$scope.pay = function(subscribedUser){
		console.log("pay():: "+subscribedUser);
		$rootScope.selectedSubscribedUser = subscribedUser;
		$state.go('paymentForm');
	}
	
});