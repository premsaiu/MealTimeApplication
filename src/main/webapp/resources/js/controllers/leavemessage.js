'use strict';

angular.module('miniMealApp.leaveCtrl', ['ngStorage']).
controller('leaveCtrl', function ($scope, $rootScope, $state, UserService, $localStorage, commonCode) {
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
	
	$scope.leavemsg=function(){
		var leaveinformation= {"name":"Prem","email":"u.premsai@gmail.com","mobileNumber":"9700640022","comments":"Test Comment"}
		UserService.submitleavemessage(leaveinformation).then(function(response){
			if(response.data.statusCode == 200){
				var subject = "MealTime - Sample Meal - One Time Password(OTP)";
				$scope.leavemessageMsg=response.data;
			}else if(response.data.statusCode == 500){
				$scope.leavemessageErrorMsg = response.data.errorMsg;
			}
		});
	}
	
});