'use strict';

angular.module('miniMealApp.adminpaymentCtrl', []).
controller('AdminPaymentCtrl', function ($scope,$state,$rootScope,UserService,AdminService) {
	
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