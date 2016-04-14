'use strict';

angular.module('miniMealApp.changepwdCtrl', ['ngStorage']).
controller('ChangePwdCtrl', function ($scope, $rootScope, $state, $localStorage, UserService, commonCode) {
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
	
	var obj={};
	$scope.chgpwd = function(){
	if($scope.newPassword === $scope.reenterNewPassword){
		obj.userId = $rootScope.user.userId;
		obj.mobileNumber = $rootScope.user.mobileNumber;
		obj.password = $scope.opassword;
		obj.newPassword = $scope.newPassword;
		UserService.chngPasswordService(obj).then(
				 function(response) {
					 if(response.data.statusCode == 200){
					 		console.log(response.data.data);
					 		//$scope.changed="true";
					 		//fadeIn fadeout url
					 		/*http://jsfiddle.net/sunnypmody/XDaEk/
*/						 		$( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
					 		$scope.opassword='';
					 		$scope.newPassword='';
					 		$scope.reenterNewPassword='';
					 		$scope.form.$setPristine();
					 		 
	                	}else{
	                		console.log("Bad Request");
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }
	       );
	}else{
		console.log("error")
	}
	
	}
});