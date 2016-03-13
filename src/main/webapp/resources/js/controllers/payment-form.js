'use strict';

angular.module('miniMealApp.paymentformCtrl', []).
controller('PaymentFormCtrl', function ($scope,$state,$rootScope,UserService,AdminService) {
	
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
	
});