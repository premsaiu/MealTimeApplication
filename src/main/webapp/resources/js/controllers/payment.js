'use strict';

angular.module('miniMealApp.paymentCtrl', ['ngStorage']).
controller('PaymentCtrl', function ($scope, $rootScope, $state, $localStorage, UserService) {
	
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
	
	if(angular.isDefined($rootScope.user) && $rootScope.user.roleId == 3){
		//location.href = "#/addprofile";
		$state.go("addprofile");
	}else{
		$rootScope.isUserSubscribed = true;
		UserService.checkSubscription($rootScope.user.userId).then(
				function(response) {
					if(response.data.data != "" && response.data.data != null){
						console.log("Response :: "+response.data.data);
						$rootScope.userWallet = response.data.data.userWallet;
						$rootScope.subscribeUserDetails = response.data.data.userSubscription;
						if($rootScope.subscribeUserDetails.status.toLowerCase() == "open"){
							$rootScope.isUserSubscribed = false;
						}else{
							$rootScope.isUserSubscribed = true;
						}
						if($rootScope.subscribeUserDetails.status.toLowerCase() == "success"){
							$rootScope.isActive = true;
							var userWallet = $rootScope.userWallet;
							var cash = parseFloat(userWallet.cash);
							$scope.cash = cash;
							if(cash < 1000.00){
								$scope.isCashEnough = true;
							}
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