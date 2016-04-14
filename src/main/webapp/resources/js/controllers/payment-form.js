'use strict';

angular.module('miniMealApp.paymentformCtrl', ['ngStorage']).
controller('PaymentFormCtrl', function ($scope, $rootScope, $state, $localStorage, UserService, AdminService, commonCode) {
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
	
	$scope.submitPay = function(){
		var paymentForm = {};
		paymentForm.userId = $rootScope.selectedSubscribedUser.userMaster.userId;
		paymentForm.userSubscriptionId = $rootScope.selectedSubscribedUser.userSubscriptionId;
		paymentForm.adminUserId = $rootScope.user.userId;
		paymentForm.amount = $scope.paymentForm.amount;
		//paymentForm.startDate = $scope.paymentForm.startDate;
		//paymentForm.endDate = $scope.paymentForm.endDate;
		paymentForm.startDate = $('#startDate').val();
		paymentForm.endDate = $('#endDate').val();
		paymentForm.email = $rootScope.selectedSubscribedUser.userMaster.email;
		paymentForm.mobileNumber = $rootScope.selectedSubscribedUser.userMaster.mobileNumber;
		AdminService.submitPayment(paymentForm).then(
				function(response){
					if(response.data.statusCode == 200){
						$('#paymentSuccessModal').modal('show');
					}else{
						console.log("Bad Request");
					}
				}
		);
		
	}
	
	$scope.closePaymentModal = function(){
		$('#paymentSuccessModal').modal('hide');
		$state.go('adminpayment');
	}
	
});