'use strict';

angular.module('miniMealApp.controllers',[])
.controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
	$scope.checkUser = function(){
		console.log("Mobile Number::"+$scope.mobileNumber);
		UserService.checkUser($scope.mobileNumber).then(
                function(response) {
                	if(response.data == "" || response.data == null){
                		$rootScope.user = {firstName : "Visitor", lastName : ""};
                	}else{
                		$rootScope.user = response.data;
                	}
               },
                function(errResponse){
                    console.error('Error while checking user');
                }
       );
		$('#myModal').modal('hide');
	}
	$scope.modalShow = function(){
		$('#myModal').modal('show');
	}
  })
.controller('AboutUsCtrl', function ($scope,$http) {
	alert("In About Us Controller");
  })
.controller('AmMealCtrl', function ($scope,$http) {
	alert("In AM Meal Controller");
})
.controller('PmMealCtrl', function ($scope,$http) {
	alert("In PM Meal Controller");
})
.controller('ContactCtrl', function ($scope,$http) {
	alert("In Contact Us Controller");
})
.controller('PaymentCtrl', function ($scope,$http) {
	alert("In Payment Controller");
})
.controller('ProfileCtrl', function ($scope,$http) {
	alert("In Profile Controller");
});