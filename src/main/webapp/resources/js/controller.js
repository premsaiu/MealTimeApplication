module.controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
	//alert("In Home Controller");
	$rootScope.user = {firstName : "Visitor"};
	$scope.checkUser = function(){
		console.log("Mobile Number::"+$scope.mobileNumber);
		UserService.checkUser($scope.mobileNumber).then(
                function(response) {
                	$rootScope.user = response.data;
                	$('#myModal').modal('hide');
               },
                function(errResponse){
                    console.error('Error while checking user');
                }
       );
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