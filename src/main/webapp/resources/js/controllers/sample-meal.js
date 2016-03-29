angular.module('miniMealApp.sampleMealCtrl', ['ngStorage']).

controller('SampleMealCtrl',  function ($scope, $rootScope, $state, UserService, $localStorage, $filter, $timeout) {
	
	$scope.sampleMealErrorMsg = "";
	
	$scope.requestSampleMeal = function(){
		var subject = "MealTime - Sample Meal - One Time Password(OTP)";
		$rootScope.sendOTP($scope.sampleMeal.mobileNumber, null, subject);
		$scope.sampleMeal.otp = "";
		
		/*UserService.requestSampleMeal(sampleMealObj).then( 
			function(response){
				debugger;
		});*/
	}
	
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.sampleMeal.mobileNumber, $scope.sampleMeal.otp).then(
				function(response) {
					if(response.data.statusCode == 200){
						$('#otpModal').modal('hide');
						$scope.submitSampleMeal();
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
	
	
	$scope.submitSampleMeal = function(){
		$scope.sampleMealMsg = false;
		$scope.sampleMealErrorMsg = "";
		var area = $('#area').val();
		var _date = $filter('date')(new Date($('#sampleMealDate').val()), 'yyyy-MM-dd');
		var sampleMealObj = {};
		sampleMealObj.mobileNumber = $scope.sampleMeal.mobileNumber;
		sampleMealObj.sampleMealDate = _date;
		sampleMealObj.name = $scope.sampleMeal.name;
		sampleMealObj.address = area;
		console.log(sampleMealObj);
		UserService.requestSampleMeal(sampleMealObj).then( 
				function(response){
					if(response.data.statusCode == 200){
						$scope.sampleMealMsg = true;
					}else if(response.data.statusCode == 500){
						$scope.sampleMealErrorMsg = response.data.errorMsg;
					}
					if(!angular.isDefined($rootScope.user)){
						  $rootScope.checkUserByMobile($scope.sampleMeal.mobileNumber);
					}
					$timeout(function() {
						  $state.go('profile');
					 }, 10000);
		});
	}
	
});