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
	
	$rootScope.closeModal =function(modalId){
		$('#'+modalId).modal('hide');
	}
	
	$rootScope.sendOTP =function(mobileNumber, email, subject){
		UserService.sendOTP(mobileNumber, email, subject).then(
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
}).

controller('AboutUsCtrl', function ($scope,$http) {
	
	alert("In About Us Controller");
	
  }).
  
controller('AmMealCtrl', function ($rootScope,$scope,$http,$state,UserService) {
	
	if ($rootScope.loggedUser == undefined || $rootScope.loggedUser == false || $rootScope.userName == "Visitor") {
    	$state.go("profile");
    	return false;
    }
	
	$scope.paymentbtn = false;
	$scope.addbrkbtn = false;
	$scope.showbtn = true;
	
	$scope.cancelledMsg = false;
	
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
		
		$scope.addbrkfst = function(brkfstObj){
			
			var brkfstObj = {
					status: "Today's Breakfast Special",
					imgSrc: "resources/images/ammeal/meal_01.jpg",
					imageName: "Masala Dosa",
					brkfstInfo: "Masala Dosa with Chutney",
					cost: 20.00
			}
			
			$scope.brkfstObj = brkfstObj;
			$scope.cancelled = false;
			$scope.addbrkbtn = false;
			$scope.showbtn = true;
			$scope.cancelledMsg = false;
			$scope.paymentbtn = false;
		};
		
		
		$scope.cancelled = false;
		$scope.cancelAllItems = function(brkfstObj){
			if(confirm("Are you sure you want to cancel?")){
				$scope.confirmation(brkfstObj);
				
				angular.forEach($scope.complItems, function(value,key){
					if(value.selected){
						value.selected = false;
					}
				});
				
				angular.forEach($scope.suppleItems, function(value,key){
						$scope.favoriteSuppl = "";
				});
				
			}else{
				return;
			}
		}
		
		$scope.confirmation = function(Obj){
			UserService.checkUser($scope.mobileNumber).then(
				 function(response) {
                	if(response.status == 200){
                		$scope.email = response.data.email;
                		var subject = "";
                		if(angular.isDefined(Obj)){
                			subject = "MealTime - Breakfast Cancellation - One Time Password(OTP)";
                		}else{
                			subject = "MealTime - Breakfast Payment - One Time Password(OTP)";
                		}
                		UserService.sendOTP($scope.mobileNumber, $scope.email, subject).then(
        		                function(response) {
        		                	if(response.status == 200){
        		                		$("#otp").val('');
        		                		$('#otpModal').modal('show');
        		                	}else{
        		                		console.log("Bad Request");
        		                	}
        		               },
        		                function(errResponse){
        		                    console.error('Something went wrong!!');
        		                });
		        				
                		$scope.verifyOTP = function(){
        					UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
    							 function(response) {
    								 	if(response.data.statusCode == 200){
    				                		$('#otpModal').modal('hide');
    				                		console.log(response.data);
    				                		
    				                		if(angular.isDefined(Obj)){
	    				                		delete $scope.brkfstObj;
	    				        				//angular.element(brkfstObj).attr("imgSrc","");
	    				        				$scope.cancelled = true;
	    				        				$scope.show = false;
	    				        				$scope.paymentbtn = false;
	    				        				
	    				        				$scope.totalAmt = 1000;
	    				        				$scope.addbrkbtn = true;
	    				        				$scope.showbtn = false;
	    				        				
	    				        				if(angular.isDefined($scope.otp)){
    				                				$scope.otp = "";
    				                			}else{
    				                				$("#otp").val('');
    				                			}	
	    				        				
	    				        				$scope.cancelledMsg = true;
	    				        				$scope.alertCancelledMsg = "Your Breakfast is Cacelled!!!";
    				                		}else{
//    				                			alert("Payment Done Successfully!!!");
    				                			window.scrollTo(0,0);
    				                			$scope.paymentbtn = false;
    				                			$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">×</button><strong>Payment Done Successfully!!!...</strong></div>');
    				                			$('#scesavemsg').delay(5000).fadeOut('slow');
    				                		}
    				                	}else{
    				                		if(confirm("Invalid OTP Entered!!!")){
    				                			
    				                		}else{
    				                			$('#otpModal').modal('hide');
    				                			if(angular.isDefined($scope.otp)){
    				                				$scope.otp = "";
    				                			}else{
    				                				$("#otp").val('');
    				                			}
    				                		}
    				                		console.log("Bad Request");
    				                	}
    				               },
    				                function(errResponse){
    				                    console.error('Something went wrong!!');
    				                }
    				       )}}else{
		                		console.log("Bad Request");
		                	}
		               },
		                function(errResponse){
		                    console.error('Something went wrong!!');
		                }	
			)};

		
		$scope.payment = function(){
			if(!angular.isDefined($scope.finalData) || $scope.finalData.length == 0){
				if(confirm("Your default breakfast order to be placed")){
					$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.cost;
					$scope.confirmation();
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
							$scope.confirmation();
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
							$scope.confirmation();
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
				$scope.paymentbtn = true;
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
				$scope.paymentbtn = false;
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

controller('PmMealCtrl', function ($rootScope, $scope, $http, $state, UserService) {
	
	if ($rootScope.loggedUser == undefined || $rootScope.loggedUser == false || $rootScope.userName == "Visitor") {
    	$state.go("profile");
    	return false;
    }
	
	$scope.paymentbtn = false;
	$scope.addbrkbtn = false;
	$scope.showbtn = true;
	
	$scope.cancelledMsg = false;
	
	$scope.complItems = [{
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
	}];
	
	var brkfstObj1 = {
			status: "Today's Dinner Special",
			imgSrc: "resources/images/ammeal/meal_01.jpg",
			imageName: "Chapathi",
			brkfstInfo: "Chapathi with Curry",
			cost: 20.00
	}
	
	$scope.dinnerObjList =[{
		imgSrc: "resources/images/ammeal/meal_01.jpg",
		imageName: "Rice",
		brkfstInfo: "Rice with Curry",
		cost: 20.00
	},{
		imgSrc: "resources/images/ammeal/meal_02.jpg",
		imageName: "Rice with Chapathi",
		brkfstInfo: "Rice & Chapathi with Curry",
		cost: 20.00
	},{
		imgSrc: "resources/images/ammeal/meal_03.jpg",
		imageName: "Chapathi",
		brkfstInfo: "Chapathi with Curry",
		cost: 20.00
	}];

	/*UserService.getSubListItems1().then(function(response) {
            	$scope.complItems = response.data.data[1];
            	$scope.suppleItems = response.data.data[2];
            },function(errResponse){
                console.error('Error while retrieving getSubListItems');
     });*/
	$scope.brkfstObj1 = brkfstObj1;
	
	$scope.totalAmt = 1000;
	
	$scope.complflag = false;
	$scope.supplflag = false;
	$scope.alertShow = false;
	$scope.favoriteDinner = "Rice";
	
	var finalAmt = 0;
	
	/*$scope.updateSelectionDinner = function(e){
		
	}*/
	
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
	
	$scope.addbrkfst = function(brkfstObj){
		
		var brkfstObj = {
				status: "Today's Breakfast Special",
				imgSrc: "resources/images/ammeal/meal_01.jpg",
				imageName: "Masala Dosa",
				brkfstInfo: "Masala Dosa with Chutney",
				cost: 20.00
		}
		
		$scope.brkfstObj1 = brkfstObj;
		
		$scope.dinnerObjList =[{
			imgSrc: "resources/images/ammeal/meal_01.jpg",
			imageName: "Rice",
			brkfstInfo: "Rice with Curry",
			cost: 20.00
		},{
			imgSrc: "resources/images/ammeal/meal_02.jpg",
			imageName: "Rice with Chapathi",
			brkfstInfo: "Rice & Chapathi with Curry",
			cost: 20.00
		},{
			imgSrc: "resources/images/ammeal/meal_03.jpg",
			imageName: "Chapathi",
			brkfstInfo: "Chapathi with Curry",
			cost: 20.00
		}];
		
		$scope.cancelled = false;
		$scope.addbrkbtn = false;
		$scope.showbtn = true;
		$scope.cancelledMsg = false;
		$scope.paymentbtn = false;
	};
	
	
	$scope.cancelled = false;
	$scope.cancelAllItems = function(brkfstObj){
		if(confirm("Are you sure you want to cancel?")){
			$scope.confirmation(brkfstObj);
			
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					value.selected = false;
				}
			});
			
			angular.forEach($scope.suppleItems, function(value,key){
					$scope.favoriteSuppl = "";
			});
			
		}else{
			return;
		}
	}
	
	$scope.confirmation = function(Obj){
		UserService.checkUser($scope.mobileNumber).then(
			 function(response) {
            	if(response.status == 200){
            		$scope.email = response.data.email;
            		var subject = "";
            		if(angular.isDefined(Obj)){
            			subject = "MealTime - Dinner Cancellation - One Time Password(OTP)";
            		}else{
            			subject = "MealTime - Dinner Payment - One Time Password(OTP)";
            		}
            		UserService.sendOTP($scope.mobileNumber, $scope.email, subject).then(
    		                function(response) {
    		                	if(response.status == 200){
    		                		$("#otp").val('');
    		                		$('#otpModal').modal('show');
    		                	}else{
    		                		console.log("Bad Request");
    		                	}
    		               },
    		                function(errResponse){
    		                    console.error('Something went wrong!!');
    		                });
	        				
            		$scope.verifyOTP = function(){
    					UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
							 function(response) {
								 	if(response.data.statusCode == 200){
				                		$('#otpModal').modal('hide');
				                		console.log(response.data);
				                		
				                		if(angular.isDefined(Obj)){
				                			
				                			$scope.dinnerObjList = [];
    				                		delete $scope.brkfstObj1;
    				        				//angular.element(brkfstObj).attr("imgSrc","");
    				        				$scope.cancelled = true;
    				        				$scope.show = false;
    				        				$scope.paymentbtn = false;
    				        				
    				        				$scope.totalAmt = 1000;
    				        				$scope.addbrkbtn = true;
    				        				$scope.showbtn = false;
    				        				
    				        				if(angular.isDefined($scope.otp)){
				                				$scope.otp = "";
				                			}else{
				                				$("#otp").val('');
				                			}	
    				        				
    				        				$scope.cancelledMsg = true;
    				        				$scope.alertCancelledMsg = "Meal has Cacelled!!!";
				                		}else{
				                			window.scrollTo(0,0);
				                			$scope.paymentbtn = false;
				                			$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">×</button><strong>Payment Done Successfully!!!...</strong></div>');
				                			$('#scesavemsg').delay(5000).fadeOut('slow');
				                		}
				                	}else{
				                		if(confirm("Invalid OTP Entered!!!")){
				                			
				                		}else{
				                			$('#otpModal').modal('hide');
				                			if(angular.isDefined($scope.otp)){
				                				$scope.otp = "";
				                			}else{
				                				$("#otp").val('');
				                			}
				                		}
				                		console.log("Bad Request");
				                	}
				               },
				                function(errResponse){
				                    console.error('Something went wrong!!');
				                }
				       )}}else{
	                		console.log("Bad Request");
	                	}
	               },
	                function(errResponse){
	                    console.error('Something went wrong!!');
	                }	
		)};

	
	$scope.payment = function(){
		if(!angular.isDefined($scope.finalData) || $scope.finalData.length == 0){
			if(confirm("Your default breakfast order to be placed")){
				
				angular.forEach($scope.brkfstObj1, function(value,key){
					if(value.imageName == $scope.favoriteDinner){
						$scope.totalAmt = $scope.totalAmt - value.cost;
					}
				});
				
				$scope.confirmation();
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
						$scope.confirmation();
					}else{
						$scope.totalAmt = 1000;
						
						var index = $scope.finalData.indexOf(value);
						$scope.finalData.splice(index, 1);
						
						angular.forEach($scope.brkfstObj1, function(value,key){
							if(value.imageName == $scope.favoriteDinner){
								$scope.totalAmt = $scope.totalAmt - value.cost;
							}
						});
						$scope.show = false;
						return false;
					}
				}
			});
		
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					if(confirm("Your want to add complemenatary Items with default breakfast?")){
						$scope.totalAmt = $scope.totalAmt - value.cost;
						$scope.confirmation();
					}else{
						$scope.totalAmt = 1000;
						
						var index = $scope.finalData.indexOf(value);
						$scope.finalData.splice(index, 1);
						
						angular.forEach($scope.brkfstObj1, function(value,key){
							if(value.imageName == $scope.favoriteDinner){
								$scope.totalAmt = $scope.totalAmt - value.cost;
							}
						});
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
			$scope.paymentbtn = true;
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
			$scope.paymentbtn = false;
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
controller('ContactCtrl', function ($scope, $http) {
	
	alert("In Contact Us Controller");
	
}).
controller('PaymentCtrl', function ($scope, $rootScope, UserService) {
	
	if($rootScope.user == undefined || $rootScope.user == "" || $rootScope.user == null){
		location.href = "#/addprofile";
	}else{
		$scope.proceed = function(){
			$scope.subscribeUser($rootScope.user);
		}
		
		$scope.subscribeUser = function(user){
			UserService.subscribeUser(user).then(
					function(response) {
						if(response.data.statusCode == 200){
							console.log("Response :: "+response.data);
							location.href = "#/paymentsuccess";
						}else{
							console.log("Bad Request");
						}
					},
					function(errResponse){
						console.error('Something went wrong!!');
					}
			);
		}
	}
	
	
}).
controller('ProfileCtrl', function ($scope,$rootScope,$http,UserService) {
	
	$scope.editUser = angular.copy($rootScope.user);
	//console.log(user);
	$scope.isEditForm=false;
	$scope.wrongOTPMsg="";
	
	if($rootScope.user == undefined || $rootScope.user == "" || $rootScope.user == null){
		location.href = "#/addprofile";
	}else{
		//var mobileNumber = $scope.editUser.mobileNumber;
		var userId = $rootScope.user.userId;
		$rootScope.userProfilePic = "images/"+userId+".jpg";
		
		$rootScope.foodType = [{'label':'Veg','value':'veg'},{'label':'Non-Veg','value':'non-veg'}];
		$rootScope.foodStyle = [{'label':'North','value':'north'},{'label':'South','value':'south'}];
		
		
		$scope.editProfile = function(){
			console.log($scope.editUser);
			var subject = "MealTime - Edit Profile - One Time Password(OTP)";
			$rootScope.sendOTP($scope.editUser.mobileNumber, $scope.editUser.email, subject);
			$scope.otp = "";
			$scope.wrongOTPMsg ="";
		}
		$scope.verifyOTP = function(){
			UserService.verifyOTP($scope.editUser.mobileNumber, $scope.otp).then(
					function(response) {
						if(response.data.statusCode == 200){
							$('#otpModal').modal('hide');
							$scope.updateProfile();
						}else{
							$scope.wrongOTPMsg="Invalid OTP. Please enter Correct OTP";
							console.log("Bad Request");
						}
					},
					function(errResponse){
						console.error('Something went wrong!!');
					}
			);
		}
		
		$scope.cancelEdit = function(modalId){
			$scope.editUser = angular.copy($rootScope.user);
			$rootScope.closeModal(modalId);
		}
		
		$scope.updateProfile = function(){
			$scope.otp = "";
			var user = $scope.editUser;
			var file = $('#profilePic')[0].files[0];
			console.log(file);
			if(file){
				$rootScope.userProfilePic = "";
			}
			UserService.updateUser(user, file).then(
					 function(response) {
						 if(response.data.statusCode == 200){
							 	$('#editSuccessModal').modal('show');
						 		console.log(response.data.data);
						 		$rootScope.user = response.data.data;
						 		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
						 		//var mobileNumber = $rootScope.user.mobileNumber;
						 		var userId = $rootScope.user.userId;
						 		$rootScope.userProfilePic = "images/"+userId+".jpg";
						 		$scope.isEditForm=false;
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
	}
	
	
}).
controller('AddProfileCtrl', function ($scope,$rootScope,UserService) {
	
	$scope.addProfile = function(){
		var subject = "MealTime - Create Profile - One Time Password(OTP)";
		$rootScope.sendOTP($scope.mobileNumber, $scope.email, subject);
		$scope.otp = "";
	}
	
	/*$scope.verifyOTP = function(){
		var isOTPValidated = $rootScope.validateOTP();
		if(isOTPValidated){
			$('#otpModal').modal('hide');
			console.log(response.data);
			$scope.submitProfile();
		}else{
			console.log("Wrong OTP");
		}
	}*/
	$scope.verifyOTP = function(){
		UserService.verifyOTP($scope.mobileNumber, $scope.otp).then(
				function(response) {
					if(response.status == 200){
						$('#otpModal').modal('hide');
						$scope.submitProfile();
					}else{
						$('#otpModal').modal('hide');
						console.log("Bad Request");
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
				}
		);
	}
	
	$scope.submitProfile = function(){
		$scope.otp = "";
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