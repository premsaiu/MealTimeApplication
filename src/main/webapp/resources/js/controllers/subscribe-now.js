angular.module('miniMealApp.subscribeNowCtrl', ['ngStorage']).

controller('SubscribeNowCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter,$timeout) {
	
	$scope.subscribeNowErrorMsg = "";
	
	$scope.requestSubscribeNow = function(){
		var subject = "MealTime - Subscribe Now - One Time Password(OTP)";
		$rootScope.sendOTP($scope.mobile, null, subject);
		$scope.subscribeNow.otp = "";
	}
	
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.mobile, $scope.subscribeNow.otp).then(
				function(response) {
					if(response.data.statusCode == 200){
						$('#otpModal').modal('hide');
						$scope.subconfmtn();
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
	
	
	$scope.subconfmtn = function(){
		$scope.successMsg = false;
		$scope.subscribeNowErrorMsg = "";
		var area = $('#area').val();
		
		var _date = $filter('date')(new Date($('#subscribeDate').val()), 'yyyy-MM-dd');
		if($('#area').val() == 'select'){
			alert("Please Select the Area");
		}else{
			UserService.subscribeNow($scope.firstName,$scope.lastName,$scope.mobile,_date,area).then( 
					function(response){
						if(response.data.data != "" && response.data.statusCode == 200){
							$rootScope.user = response.data.data;
							$rootScope.user.firstName = $scope.firstName;
							$rootScope.user.lastName = $scope.lastName;	
							$rootScope.user.mobileNumber = $scope.mobile;
							$rootScope.user.address = area;
							window.scrollTo(0,0);
							$scope.successMsg = true;
							$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">x</button><strong>Subscription Done Successfully!!!...</strong></div>');
							$('#scesavemsg').delay(5000).fadeOut('slow');	
							$timeout(function() {
							      $state.go('profile');
							 }, 5000);						
							}else if(response.data.statusCode == 500){
								window.scrollTo(0,0);
								$scope.subscribeNowErrorMsg = "Something Went Wrong please check all your input fields!!!!";
							}
			});
		}
	}
	
});