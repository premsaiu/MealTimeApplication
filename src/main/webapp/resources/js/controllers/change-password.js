'use strict';

angular.module('miniMealApp.changepwdCtrl', []).
controller('ChangePwdCtrl', function ($scope, $rootScope,UserService) {
	
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