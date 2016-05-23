angular.module('miniMealApp.sampleMealCtrl', ['ngStorage']).

controller('SampleMealCtrl',  function ($scope, $rootScope,$window,$location, $state, $localStorage, $filter, $timeout, UserService, commonCode) {
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
	
	$scope.sampleMealErrorMsg = "";
	if($rootScope.loggedUser){
		$scope.sampleMeal = {};
		$scope.sampleMeal.mobileNumber = $rootScope.user.mobileNumber;
		$scope.sampleMeal.name = $rootScope.user.firstName;
	}
	//data formatted code
	 var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
	 				  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	 				];
	 				var days = ["Sun","Mon","Tue","Wed","thr","Fri","Sat"]

	 				var d = new Date();
	 				function addDays(dateObj, numDays) {
	 				   dateObj.setDate(dateObj.getDate() + numDays);
	 				   return dateObj;
	 				}


	 				var tomorrow = addDays(new Date(), 2);
	 				var nextWeek = addDays(new Date(), 5);
	 				var now = new Date();
	 				var daysOfYear = [];
	 				for(var d = tomorrow; d <= nextWeek; d.setDate(d.getDate() + 1)) {

	 				if(d.getDay()!=0){
	 				    daysOfYear.push(new Date(d));
	 				    }
	 				}
	 				if(daysOfYear.length<5){
	 				var extradate=new Date(daysOfYear[3]);
	 				daysOfYear.push(new Date(extradate.setDate(extradate.getDate() + 1)));
	 				}
	 				var newdates1=[];
	 				for(var i=0;i<daysOfYear.length;i++){
	 				var asd=daysOfYear[i].getDate()+"-"+monthNames[daysOfYear[i].getMonth()]+"-"
	 				+daysOfYear[i].getFullYear();
	 				newdates1.push(asd);
	 				}
	 				$('.address_field').val(newdates1)
	$rootScope.sampcustomdates=newdates1;
	 				$scope.sanotNow = function(){
	 					$('#myModal').modal('hide');
	 						$(".adminsection").hide();
	 						$rootScope.newmenu = true;
	 						$rootScope.status=false;
	 						$state.go('home');
	 					}
	$scope.requestSampleMeal = function(vaild){
		$scope.samerrorcheck=vaild;
		if(vaild){
		$scope.sampleMealErrorMsg = "";
		var sampleMealDate = $filter('date')(new Date($('#sampleMealDate').val()), 'yyyy-MM-dd');
		UserService.checkSampleMeal($scope.sampleMeal.mobileNumber, sampleMealDate, $scope.sampleMeal.name).then(function(response){
			if(response.data.statusCode == 200){
				var subject = "MealTime - Sample Meal - One Time Password(OTP)";
				/*$rootScope.sendOTP($scope.sampleMeal.mobileNumber, null, subject);
				$scope.sampleMeal.otp = "";*/
				$window.location.href='https://www.instamojo.com/mealtime/samplemeal/';
				/*$window.location.href='https://www.instamojo.com/mealtime/dd-3e6bf/';
				*//*$state.go("samplepayment");*/
				$scope.submitSampleMeal();
			}else if(response.data.statusCode == 500){
				$scope.sampleMealErrorMsg = response.data.errorMsg;
			}
		});
	}
	}
	
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.sampleMeal.mobileNumber, $scope.sampleMeal.otp).then(
				function(response) {
					if(response.data.statusCode == 200){
						$('#otpModal').modal('hide');
						/*<a href="https://www.instamojo.com/mealtime/mealtime_sample/" rel="im-checkout" data-behaviour="remote" data-style="light" data-text="Checkout With Instamojo" data-token="f10e02fe7ff4eb38385af6ca55213f3c"></a>
*/
						$window.location.href='https://www.instamojo.com/mealtime/samplemeal/';
						/*$state.go("samplepayment");*/
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
		sampleMealObj.address =$scope.sampleMeal.area;
		console.log(sampleMealObj);
		UserService.requestSampleMeal(sampleMealObj).then( 
				function(response){
					if(response.data.statusCode == 200){
						$scope.sampleMealMsg = true;
					}else if(response.data.statusCode == 500){
						$scope.sampleMealErrorMsg = response.data.errorMsg;
					}
					/*if(!angular.isDefined($rootScope.user)){
						  $rootScope.checkUserByMobile($scope.sampleMeal.mobileNumber);
					}*/
					$rootScope.user = response.data.data;
					$timeout(function() {
						  $state.go('profile');
					 }, 10000);
		});
	}
	
});
