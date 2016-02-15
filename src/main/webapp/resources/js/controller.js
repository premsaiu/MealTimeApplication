'use strict';

angular.module('miniMealApp.controllers', []).

	controller('HomeCtrl',  function ($scope,$rootScope, UserService) {
		$scope.modalShow = function(){
			$('#myModal').modal('show');
		}
		if($rootScope.loggedUser == undefined || $rootScope.loggedUser == false ){
			$scope.modalShow();
		}
		$scope.checkUser = function(){
			console.log("Mobile Number::"+$scope.mobileNumber);
			$rootScope.mobileNumber = $scope.mobileNumber;
			UserService.checkUser($scope.mobileNumber).then(
	                function(response) {
	                	$rootScope.loggedUser = true;
	                	if(response.data == "" || response.data == null){
	                		$rootScope.userName = "Visitor";
	                	}else{
	                		$rootScope.user = response.data;
	                		console.log($rootScope.user);
	                		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	                	}
	               },
	                function(errResponse){
	                    console.error('Error while checking user');
	                }
	       );
			$('#myModal').modal('hide');
		}
  }).

controller('AboutUsCtrl', function ($scope,$http) {
	
	alert("In About Us Controller");
	
  }).
  
controller('AmMealCtrl', function ($scope,$http,UserService) {
	
	/*$scope.complItems = [{
		"imagePath" : "resources/images/ammeal/meal_01.jpg",
		"itemName" : "Allam Chutney",
		"cost" : 20.00
	},{
		"imagePath" : "resources/images/ammeal/meal_02.jpg",
		"itemName" : "Coconut Chutney",
		"cost" : 10.00
	}];
	
	$scope.suppleItems = [{
		"imagePath" : "resources/images/ammeal/meal_03.jpg",
		"itemName" : "Idly",
		"cost" : 20.00
	},{
		"imagePath" : "resources/images/ammeal/meal_04.jpg",
		"itemName" : "Wada",
		"cost" : 15.00
	}];*/
	
	var brkfstObj = {
			status: "Today's Breakfast Special",
			imgSrc: "resources/images/ammeal/meal_01.jpg",
			imageName: "Masala Dosa",
			brkfstInfo: "Masala Dosa with Chutney",
			cost: 20.00
	}

	UserService.getSubListItems().then(function(response) {
            	$scope.complItems = response.data.data[1];
            	$scope.suppleItems = response.data.data[2];
            },function(errResponse){
                console.error('Error while retrieving getSubListItems');
     });
	
	
	
	$scope.brkfstObj = brkfstObj;
		
		$scope.totalAmt = 1000;
		
		$scope.complflag = false;
		$scope.supplflag = false;
		$scope.alertShow = false;
		
		var finalAmt = 0;
		
		/*$scope.chooseItems = function(){
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					value.selected = false;
				}
			});
			
			$scope.favoriteSuppl = "";
			$scope.showModal = true;
			
			$("#myAddonModal").modal('show');
		};*/
		$scope.cancelled = false;
		$scope.cancelAllItems = function(brkfstObj){
			if(confirm("Are you sure you want to cancel?")){
				UserService.checkUser($scope.mobileNumber).then(
						 function(response) {
			                	if(response.status == 200){
			                		$scope.email = response.data.email;
			                		UserService.sendOTP($scope.mobileNumber, $scope.email).then(
			        		                function(response) {
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
			        				
			        				$scope.verifyOTP = function(){
			        					UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
			        							 function(response) {
			        								 	if(response.status == 200){
			        				                		$('#otpModal').modal('hide');
			        				                		console.log(response.data);
			        				                		delete $scope.brkfstObj;
			        				        				//angular.element(brkfstObj).attr("imgSrc","");
			        				        				$scope.cancelled = true;
			        				        				$scope.show = false;
			        				                	}else{
			        				                		console.log("Bad Request");
			        				                	}
			        				               },
			        				                function(errResponse){
			        				                    console.error('Something went wrong!!');
			        				                }
			        				       );
			        				}
			                	}else{
			                		console.log("Bad Request");
			                	}
			               },
			                function(errResponse){
			                    console.error('Something went wrong!!');
			                }	
				);
			}else{
				return;
			}
		}
		
		$scope.payment = function(){
			if(!angular.isDefined($scope.finalData) || $scope.finalData.length == 0){
				if(confirm("Your default breakfast order to be placed")){
					$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.cost;
				}else{
					//$scope.totalAmt = $scope.totalAmt; // Final Amount from the Admin Table
					return;
				}
			}
			
			if(angular.isDefined($scope.finalData) && $scope.finalData.length !== 0){
				angular.forEach($scope.suppleItems, function(value,key){
					if(value.itemName == $scope.favoriteSuppl){
						if(confirm("Would you really want to replace default breakfast with supplementary Item?")){
							$scope.totalAmt = $scope.totalAmt - value.cost;
						}else{
							$scope.totalAmt = 1000;
							
							var index = $scope.finalData.indexOf(value);
							$scope.finalData.splice(index, 1);
							
							$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.cost;
							$scope.show = false;
							return false;
						}
					}
				});
			
				angular.forEach($scope.complItems, function(value,key){
					if(value.selected){
						if(confirm("Your want to add complemenatary Items with default breakfast?")){
							$scope.totalAmt = $scope.totalAmt - value.cost;
						}else{
							$scope.totalAmt = 1000;
							
							var index = $scope.finalData.indexOf(value);
							$scope.finalData.splice(index, 1);
							
							$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.cost;
							$scope.show = false;
							return false;
						}
					}
				});
			}
		}
		
		$scope.updateSelection = function(){
			$scope.supplflag = false;
			$scope.alertShow = false;
			/*angular.forEach($scope.complItems, function(value,key){
			if(value.selected){
				$scope.supplflag = true;
			}
		});*/
		
		angular.forEach($scope.suppleItems, function(value,key){
			if(value.itemName == $scope.favoriteSuppl){
				$scope.supplflag = true;
			}
		});
		
		if ($scope.supplflag) {
			if(confirm("Do you want to replace the selected item with existing?")){
				angular.forEach($scope.suppleItems, function(value,key){
					if(value.itemName == $scope.favoriteSuppl){
						//value.selected = false;
						$scope.supplflag = false;
						$scope.favoriteSuppl = "";
					}
				});
			}else{
				event.preventDefault();
				//$scope.supplflag = true;
				angular.forEach($scope.complItems, function(value,key){
					if(value.selected){
						value.selected = false;
					}
				});
			}
		}
	}
		
		$scope.favoriteSuppl = "" ;

		$scope.updateSelectionSuppl = function(){
			$scope.complflag = false;
			$scope.alertShow = false;
			/*angular.forEach($scope.suppleItems, function(value,key){
			if(value.Item==$scope.favoriteSuppl){
				$scope.complflag = true;
			}
		});*/
		
		angular.forEach($scope.complItems, function(value,key){
			if(value.selected){
				$scope.complflag = true;
			}
		});
		
		if ($scope.complflag) {
			if(confirm("Do you want to replace the selected item with existing?")){
				angular.forEach($scope.complItems, function(value,key){
					if(value.selected){
						value.selected = false;
						$scope.complflag = false;
					}
				});
			}else{
				event.preventDefault();
				//$scope.complflag = true;
				$scope.favoriteSuppl = "";
			}
		}
	}
		
		$scope.addFinalItems = function(){
			$scope.show = false;
			$scope.finalData = [];
			$scope.totalAmt = 1000;
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					$scope.show = true;
					$scope.finalData.push(value);
					finalAmt = $scope.totalAmt - value.cost;
					$scope.totalAmt = finalAmt;
				}
			});
			angular.forEach($scope.suppleItems, function(value1,key1){
				if(value1.itemName == $scope.favoriteSuppl && $scope.favoriteSuppl != ''){
					$scope.show = true;
					$scope.finalData.push(value1);
					finalAmt = $scope.totalAmt - value1.cost;
					$scope.totalAmt = finalAmt;
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
			$scope.totalAmt = finalAmt;
			
			if($scope.finalData.length == 0){
				$scope.show = false;
			}
			
			angular.forEach($scope.complItems, function(value,key){
				if(value.itemName == data.itemName){
					value.selected = false;
				}
			});
			
			angular.forEach($scope.suppleItems, function(value,key){
				if(value.itemName == data.itemName){
					$scope.favoriteSuppl = "";
				}
			});
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
	
	$rootScope.foodType = [{'label':'Veg','value':'veg'},{'label':'Non-Veg','value':'non-veg'}];
	$rootScope.foodStyle = [{'label':'North','value':'north'},{'label':'South','value':'south'}];
	
	console.log($rootScope.user);
	
	$scope.editProfile = function(){
		console.log($rootScope.user);
		$rootScope.sendOTP();
	}
	$scope.verifyOTP = function(){
		var isOTPValidated = $rootScope.validateOTP();
		if(isOTPValidated){
			$('#otpModal').modal('hide');
			console.log(response.data);
			//$scope.updateProfile();
		}else{
			console.log("Wrong OTP");
		}
	}
	
	
}).
controller('AddProfileCtrl', function ($scope,$rootScope,UserService) {
	
	$scope.addProfile = function(){
		$rootScope.sendOTP();
	}
	$rootScope.sendOTP =function(){
		UserService.sendOTP($scope.mobileNumber, $scope.email).then(
				function(response) {
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
		var isOTPValidated = $rootScope.validateOTP();
		if(isOTPValidated){
			$('#otpModal').modal('hide');
			console.log(response.data);
			$scope.submitProfile();
		}else{
			console.log("Wrong OTP");
		}
	}
	$rootScope.validateOTP = function(){
		UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
				function(response) {
					if(response.status == 200){
						return true;
					}else{
						console.log("Bad Request");
						return false;
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
					return false;
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
					 if(response.data.statusCode == 200){
					 		console.log(response.data.data);
					 		$rootScope.user = response.data.data;
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
