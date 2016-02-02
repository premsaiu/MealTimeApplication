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
  }).

controller('AboutUsCtrl', function ($scope,$http) {
	
	alert("In About Us Controller");
	
  }).
  
controller('AmMealCtrl', function ($scope,$http) {
	
	var dt = new Date();
	var time = dt.getHours() + ":" + dt.getMinutes()
	
	$scope.serverTime = time;
	
	if(new Date().getTime() == new Date($.now())){
		$scope.today = "Today's Breakfast Special";
		$scope.imageSrc = "resources/images/ammeal/meal_01.jpg";
		$scope.imageName = "Masala Dosa";
		$scope.brkfstInfo = "Masala Dosa with Chutney";
	}else{
		$scope.tomorrow = "Tomorrow's Breakfast Special";
		$scope.imageSrc = "resources/images/ammeal/meal_01.jpg";
		$scope.imageName = "Masala Dosa";
		$scope.brkfstInfo = "Masala Dosa with Chutney";
	}
}).

controller('PmMealCtrl', function ($scope,$http) {
	
	alert("In PM Meal Controller");
	
}).
controller('ContactCtrl', function ($scope,$http) {
	
	alert("In Contact Us Controller");
	
}).
controller('PaymentCtrl', function ($scope,$http) {
	
	alert("In Payment Controller");
	
}).
controller('ProfileCtrl', function ($scope,$http) {
	
	alert("In Profile Controller");
	
});