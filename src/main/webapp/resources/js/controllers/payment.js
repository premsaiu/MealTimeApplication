'use strict';

angular.module('miniMealApp.paymentCtrl', []).
controller('PaymentCtrl', function ($scope, $state, $rootScope, UserService) {
	
	if($rootScope.user == undefined || $rootScope.user == "" || $rootScope.user == null){
		//location.href = "#/addprofile";
		$state.go("addprofile");
	}else{
		$rootScope.isUserSubscribed = true;
		UserService.checkSubscription($rootScope.user.userId).then(
				function(response) {
					if(response.data.data != "" && response.data.data != null){
						console.log("Response :: "+response.data.data);
						$rootScope.subscribeUserDetails = response.data.data;
						$rootScope.isUserSubscribed = true;
						if($rootScope.subscribeUserDetails.status.toLowerCase() == "success"){
							$rootScope.isActive = true;
						}else{
							$rootScope.isActive = false;
						}
					}else{
						$rootScope.isUserSubscribed = false;
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
				}
		);
		
		
		$scope.proceed = function(){
			$scope.subscribeUser($rootScope.user);
		}
		
		$scope.subscribeUser = function(user){
			UserService.subscribeUser(user).then(
					function(response) {
						if(response.data.statusCode == 200){
							console.log("Response :: "+response.data);
							//location.href = "#/paymentsuccess";
							$state.go("paymentsuccess");
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