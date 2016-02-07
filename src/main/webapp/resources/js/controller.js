'use strict';

angular.module('miniMealApp.controllers', []).

	controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
		$rootScope.loginUser = false;
	$scope.checkUser = function(){
		console.log("Mobile Number::"+$scope.mobileNumber);
		$rootScope.mobileNumber = $scope.mobileNumber;
		UserService.checkUser($scope.mobileNumber).then(
                function(response) {
                	if(response.data == "" || response.data == null){
                		$rootScope.userName = "Visitor";
                	}else{
                		$rootScope.user = response.data;
                		console.log($rootScope.user);
                		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
                		$rootScope.loginUser = true;
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
controller('ProfileCtrl', function ($scope,$rootScope,$http) {
	
	if($rootScope.user == undefined || $rootScope.user == "" || $rootScope.user == null){
		location.href = "#/addprofile";
	}
	
	
}).
controller('AddProfileCtrl', function ($scope,$rootScope,UserService) {
	
	$scope.addProfile = function(){
		UserService.sendOTP($scope.mobileNumber, $scope.email).then(
                function(response) {
                	debugger;
                	if(response.status == 200){
                		$('#otpModal').modal('show');
                	}else{
                		console.log("Bad Request");
                	}
               },
                function(errResponse){
                    console.error('Something went wrong!!');
                }
       );
		
		
	}
	
	$scope.verifyOTP = function(){
		//$scope.file.files[0]
		UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
				 function(response) {
					 	if(response.status == 200){
	                		$('#otpModal').modal('hide');
	                		console.log(response.data);
	                		$scope.submitProfile();
	                	}else{
	                		console.log("Bad Request");
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }
	       );
	}
	
	$scope.submitProfile = function(){
		var jsonObj = {};
		jsonObj.firstName = $scope.firstName;
		jsonObj.lastName = $scope.lastName;
		jsonObj.email = $scope.email;
		jsonObj.mobileNumber = $scope.mobileNumber;
		jsonObj.address = $scope.address;
		jsonObj.foodStyleS1 = $scope.foodType;
		jsonObj.foodStyleS2 = $scope.foodStyle;
		console.log($scope.profilePic);
		var file = $('#profilePic')[0].files[0];
		console.log(file);
		UserService.addUser(jsonObj, file).then(
				 function(response) {
					 	if(response.status == 200){
					 		$rootScope.user = response.data;
					 		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	                		location.href = "#/profile";
	                	}else{
	                		console.log("Bad Request");
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }
	       );
	}
	
});