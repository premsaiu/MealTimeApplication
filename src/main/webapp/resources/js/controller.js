'use strict';

angular.module('miniMealApp.controllers', []).

	controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
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
	
		$scope.status = "Today's Breakfast Special";
		$scope.imageSrc = "resources/images/ammeal/meal_01.jpg";
		$scope.imageName = "Masala Dosa";
		$scope.brkfstInfo = "Masala Dosa with Chutney";
		
		var totalAmt = 1000;
		
		$scope.complflag = false;
		$scope.supplflag = false;
		$scope.alertShow = false;
		
		$scope.complItems = [{
			"ImgSrc" : "resources/images/ammeal/meal_01.jpg",
			"Item" : "Allam Chutney",
			"cost" : 20.00
		},{
			"ImgSrc" : "resources/images/ammeal/meal_02.jpg",
			"Item" : "Coconut Chutney",
			"cost" : 10.00
		}];
		
		$scope.suppleItems = [{
			"ImgSrc" : "resources/images/ammeal/meal_03.jpg",
			"Item" : "Idly",
			"cost" : 20.00
		},{
			"ImgSrc" : "resources/images/ammeal/meal_04.jpg",
			"Item" : "Wada",
			"cost" : 15.00
		}];
		
		var finalAmt = 0;
		$scope.updateSelection = function(){
			$scope.supplflag = false;
			$scope.alertShow = false;
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					$scope.supplflag = true;
				}
			});
		}
		
		$scope.favoriteSuppl = "" ;

		$scope.updateSelectionSuppl = function(){
			$scope.complflag = false;
			$scope.alertShow = false;
			angular.forEach($scope.suppleItems, function(value,key){
				if(value.Item==$scope.favoriteSuppl){
					$scope.complflag = true;
				}
			});
		}
		
		$scope.addFinalItems = function(){
			$scope.show = false;
			$scope.finalData = [];
			var totalAmt = 1000;
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					$scope.show = true;
					$scope.finalData.push(value);
					finalAmt = totalAmt - value.cost;
					totalAmt = finalAmt;
				}
			});
			angular.forEach($scope.suppleItems, function(value1,key1){
				if(value1.Item==$scope.favoriteSuppl && $scope.favoriteSuppl != ''){
					$scope.show = true;
					$scope.finalData.push(value1);
					finalAmt = totalAmt - value1.cost;
					totalAmt = finalAmt;
				}
			});
			
			if($scope.show){
				$("#myAddonModal").modal('hide');
			}else{
				$scope.alertShow = true;
				$scope.alertMsg = "Add any item or click on cancel";
			}
		}
		
		$scope.deleteItem = function(data){
			var index = $scope.finalData.indexOf(data);
			$scope.finalData.splice(index, 1);
			finalAmt = finalAmt + data.cost;
			
			if($scope.finalData.length == 0){
				$scope.show = false;
			}
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