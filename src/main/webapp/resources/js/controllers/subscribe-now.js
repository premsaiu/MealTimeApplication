'use strict';


angular.module('miniMealApp.subscribeNowCtrl', ['ngStorage']).

controller('SubscribeNowCtrl',  function ($scope, $rootScope, $state, $localStorage, $timeout, $filter,$window, UserService, commonCode) {
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
	
	$scope.sbnotNow = function(){
		$('#myModal').modal('hide');
			$(".adminsection").hide();
			$state.go('home');
		}
	
	$rootScope.areaList = [{'value':'Madhapur Police Station'},{'value':'Madhapur 100 feet Road'},{'value':'Madhapur Kavuri Hills Phase I'},
	                       {'value':'Madhapur Kavuri Hills Phase II'},{'value':'Madhapur Megha Hills'},{'value':'Madhapur Ayyappa Society'},{'value':'Madhapur Hitech City'},
	                       {'value':'Madhapur Image Hospital Road'},{'value':'Madhapur Image Garden Road'}];
	
	$scope.subscribeNowErrorMsg = "";
	if($rootScope.loggedUser){
		$scope.mobile = $rootScope.user.mobileNumber;
		$scope.firstName = $rootScope.user.firstName;
		$scope.lastName = $rootScope.user.lastName;
		$scope.area = $rootScope.user.area;
	}
	
	$scope.requestSubscribeNow = function(valid){
		$scope.suberrorcheck=valid;
		if(valid){
		var subject = "MealTime - Subscribe Now - One Time Password(OTP)";
		/*$rootScope.sendOTP($scope.mobile, null, subject);
		$scope.otp = "";*/
		$window.location.href='https://www.instamojo.com/mealtime/subscribe-5802d/';
		$scope.subconfmtn();
		
	}
	}
	
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.mobile, $scope.otp).then(
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
			UserService.subscribeNow($scope.firstName,$scope.lastName,$scope.mobile,_date,$scope.area).then( 
					function(response){
						if(response.data.data != "" && response.data.statusCode == 200){
							$rootScope.user = response.data.data;
							$rootScope.user.firstName = $scope.firstName;
							$rootScope.user.lastName = $scope.lastName;	
							$rootScope.user.mobileNumber = $scope.mobile;
							$rootScope.user.address = $scope.area;
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