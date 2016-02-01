'use strict';

module.controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
	//alert("In Home Controller");
	
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
module.controller('AboutUsCtrl', function ($scope,$http) {
	
	alert("In About Us Controller");
	
  })
module.controller('AmMealCtrl', function ($scope,$http) {
	
	alert("In AM Meal Controller");
	
})
module.controller('PmMealCtrl', function ($scope,$http) {
	
	alert("In PM Meal Controller");
	
})
module.controller('ContactCtrl', function ($scope,$http) {
	
	alert("In Contact Us Controller");
	
})
module.controller('PaymentCtrl', function ($scope,$http) {
	
	alert("In Payment Controller");
	
})
module.controller('ProfileCtrl', function ($scope,$http) {
	
	alert("In Profile Controller");
	
})