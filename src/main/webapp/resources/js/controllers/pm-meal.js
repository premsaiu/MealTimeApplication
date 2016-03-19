'use strict';

angular.module('miniMealApp.pmmealCtrl', []).

controller('PmMealCtrl', function ($rootScope, $scope, $http, $state, UserService) {
	
	if ($rootScope.loggedUser == undefined || $rootScope.loggedUser == false || $rootScope.userName == "Visitor") {
		if(!angular.isDefined($rootScope.user)) $state.go("profile");
    	return false;
    }
	
	if(angular.isDefined($rootScope.user.userId)){
		UserService.checkSubscription($rootScope.user.userId).then(
				function(response) {
					if(response.data.data != "" && response.data.data != null){
						console.log("Response :: "+response.data.data);
						$rootScope.subscribeUserDetails = response.data.data;
						$rootScope.isUserSubscribed = true;
						if($rootScope.subscribeUserDetails.isActive){
							$rootScope.isActive = true;
							UserService.getDinnerItem($rootScope.user.userId,0).then(function(response) {
								if(response.status == 200 && response.data.data != null){
									$scope.dinnerObjList = response.data.data;
									
									$scope.favoriteDinner = "";
									
									angular.forEach($scope.dinnerObjList, function(value,key){
										if(value.isActive.toLowerCase() == "yes") $scope.favoriteDinner = value.itemName;
									});
									
									var brkfstObj1 = {
											status: "Today's Dinner Special",
											imgSrc: "resources/images/ammeal/meal_01.jpg",
											imageName: "Chapathi",
											brkfstInfo: "Chapathi with Curry",
											cost: 20.00
									}
									
									UserService.walletCheck($rootScope.user.userId).then(function(response){
										if(response.status == 200 && response.data.data != null){
											$scope.totalAmt = response.data.data.cash;
										}
									});
								}else{
									$scope.cancelled = true;
									$scope.showbtn = false;
									$scope.addbrkbtn = true;
								}
							});
							
							$scope.PMFinalSubItems = {};
							
							$scope.PMFinalSubItems.userId = $rootScope.user.userId;
							
							$scope.PMFinalSubItems.addId = 0;
							
							$scope.PMFinalSubItems.pmSubItemsList = [];

							$scope.finalData = [];
							
							UserService.checkDinnerSubItem($scope.PMFinalSubItems).then(function(response){
								if(response.status == 200 && response.data.data != null){
									angular.forEach(response.data.data.pmSubItemsList, function(value,key){
										$scope.show = true;
										value.selected = true;
										angular.forEach($scope.complItems, function(value1,key1){
											if(value1.itemId == value.itemId){
												value1.selected = true;
												$scope.paymentbtn = true;
											}
										});
										
										angular.forEach($scope.suppleItems, function(value2,key2){
											$scope.favoriteSuppl = "";
											if(value2.itemId == value.itemId){
												$scope.favoriteSuppl = value2.itemName;
												$scope.paymentbtn = true;
											}
										});
										
										$scope.finalData.push(value);
									});
								}
							});
						}else{
							$rootScope.isActive = false;
							$tate.go('home');
							alert("Your Subsciption is Still Pending, Kindly contact Admin");
							return false;
						}
					}else{
						$rootScope.isUserSubscribed = false;
						$state.go("payment");
						return false;
					}
				},
				function(errResponse){
					console.error('Something went wrong!!');
				}
		);
	}
	
	$scope.updateSelectionDinner = function(event,obj){
		UserService.updateDinnerItem(obj.itemId,function(response){
			if(response.data == "success"){
				$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">x</button><strong>Updated Dinner Item Successfully!!!...</strong></div>');
					$('#scesavemsg').delay(5000).fadeOut('slow');	
			}
		});
	}
	
	$scope.paymentbtn = false;
	$scope.addbrkbtn = false;
	$scope.showbtn = true;
	$scope.cancelledMsg = false;
	
	UserService.getSubListItems1().then(function(response) {
		$scope.complItems = response.data.data[1];
		$scope.suppleItems = response.data.data[2];
	},function(errResponse){
		console.error('Error while retrieving getSubListItems');
	});
	
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
			UserService.getDinnerItem($rootScope.user.userId,1).then(function(response) {
				$scope.dinnerObjList = response.data.data;
				//$scope.brkfstObj = brkfstObj;
				$scope.cancelled = false;
				$scope.addbrkbtn = false;
				$scope.showbtn = true;
				$scope.cancelledMsg = false;
				$scope.paymentbtn = false;
			});
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
                		if(angular.isDefined(Obj) && typeof Obj != "number"){
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
    								 				if(angular.isDefined(Obj) && typeof Obj != "number"){
    								 					UserService.cancelDinnerItems(Obj,$rootScope.user.userId).then(function(response){
    								 						if(response.status == 200){
	    								 						delete $scope.brkfstObj;
		    								 					$scope.cancelled = true;
		    								 					$scope.show = false;
		    								 					$scope.paymentbtn = false;
		    								 					$scope.dinnerObjList = [];
		    								 					UserService.walletCheck($rootScope.user.userId).then(function(response){
		    								 						if(response.data.data != "" && response.data.data != null){
		    								 							$scope.totalAmt = response.data.cash;
		    								 						}
		    								 					});
		    								 					//$scope.totalAmt = 1000;
		    								 					$scope.addbrkbtn = true;
		    								 					$scope.showbtn = false;
		    								 					
		    								 					if(angular.isDefined($scope.otp)){
		    								 						$scope.otp = "";
		    								 					}else{
		    								 						$("#otp").val('');
		    								 					}	
		    								 					$scope.cancelledMsg = true;
		    								 					$scope.alertCancelledMsg = "Your Dinner is Cacelled!!!";
    								 						}
    								 					});
    								 				}else{
    								 					UserService.payment($rootScope.user.userId,Obj).then(function(response){
    								 						if(response.status == 200){
    								 							window.scrollTo(0,0);
    								 							$scope.paymentbtn = false;
    								 							$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">×</button><strong>Payment Done Successfully!!!...</strong></div>');
    								 							$('#scesavemsg').delay(5000).fadeOut('slow');
    								 						}
    								 					});
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
					$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.itemPrice;
					$scope.confirmation();
				}else{
					//$scope.totalAmt = $scope.totalAmt; // Final Amount from the Admin Table
					return;
				}
			}
			
			if(angular.isDefined($scope.finalData) && $scope.finalData.length !== 0){
				angular.forEach($scope.suppleItems, function(value,key){
					if(value.itemName == $scope.favoriteSuppl){
						if(confirm("Would you really want to replace default dinner with supplementary Item?")){
											if($rootScope.isUserSubscribed && $rootScope.isActive && $rootScope.subscribeUserDetails.status.toLowerCase() == "success"){
												UserService.walletCheck($rootScope.user.userId).then(function(response){
													if(response.data.data != "" && response.data.data != null){
														$scope.totalAmt = $scope.totalAmt - value.cost;
														$scope.confirmation($scope.totalAmt);
													}else{
														alert("Dont have minimum balance for payment");
													}
												});
											}else{
												$state.go("payment");
												alert("Your Subscription is still pending with Admin");
											}
						}else{
							UserService.walletCheck($rootScope.user.userId).then(function(response){
								if(response.data.data != "" && response.data.data != null){
									$scope.totalAmt = response.data.cash;
								}
							});
							
							var index = $scope.finalData.indexOf(value);
							$scope.finalData.splice(index, 1);
							
							$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.itemPrice;
							$scope.show = false;
							return false;
						}
					}
				});
			
				angular.forEach($scope.complItems, function(value,key){
					if(value.selected){
						if(confirm("Your want to add complemenatary Items with default Dinner?")){
												if($rootScope.isUserSubscribed && $rootScope.isActive && $rootScope.subscribeUserDetails.status.toLowerCase() == "success"){
													UserService.walletCheck($rootScope.user.userId).then(function(response){
														if(response.data.data != "" && response.data.data != null){
															$scope.totalAmt = $scope.totalAmt - value.cost;
															$scope.confirmation($scope.totalAmt);
														}else{
															alert("Dont have minimum balance for payment");
														}
													});
												}else{
													$state.go("payment");
													alert("Your Subscription is still pending with Admin")
												}
						}else{
							//$scope.totalAmt = 1000;
							UserService.walletCheck($rootScope.user.userId).then(function(response){
								if(response.data.data != "" && response.data.data != null){
									$scope.totalAmt = response.data.cash;
								}
							});
							
							var index = $scope.finalData.indexOf(value);
							$scope.finalData.splice(index, 1);
							
							$scope.totalAmt = $scope.totalAmt - $scope.brkfstObj.itemPrice;
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
			
			$scope.PMFinalSubItems = {};
			
			$scope.PMFinalSubItems.userId = $rootScope.user.userId;
			
			$scope.PMFinalSubItems.addId = 1;
			
			$scope.PMFinalSubItems.pmSubItemsList = [];
			
			//$scope.totalAmt = 1000;
			
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					$scope.show = true;
					//$scope.finalData.push(value);
					$scope.PMFinalSubItems.pmSubItemsList.push(value);
					//finalAmt = $scope.totalAmt - value.cost;
					//$scope.totalAmt = finalAmt;
				}
			});
			
			angular.forEach($scope.suppleItems, function(value1,key1){
				if(value1.itemName == $scope.favoriteSuppl && $scope.favoriteSuppl != ''){
					$scope.show = true;
					$scope.PMFinalSubItems.pmSubItemsList.push(value1);
//					$scope.finalData.push(value1);
					//$scope.finalData = $scope.PMFinalSubItems.pmSubItemsList.push(value1);
					/*UserService.checkSubItem($scope.PMFinalSubItems).then(function(response){
						if(response.status == 200 && response.data.data != null){
							$.angular.forEach(response.data.data, function(value,key){
							});
						}
					});*/
					//finalAmt = $scope.totalAmt - value1.cost;
					//$scope.totalAmt = finalAmt;
				}
			});

			UserService.checkDinnerSubItem($scope.PMFinalSubItems).then(function(response){
				if(response.status == 200 && response.data.data != null){
					angular.forEach(response.data.data.pmSubItemsList, function(value1,key1){
						if(value1.selected && value1.itemType == "complementary"){
							$scope.finalData.push(value1);	
						}else if(value1.itemType != "complementary"){
							$scope.favoriteSuppl = value1.itemName;
							$scope.finalData.push(value1);
						}
					});
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
			//finalAmt = finalAmt + data.cost;
			//$scope.totalAmt = finalAmt;
			
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
			
			UserService.deleteDinnerSubItemAddon(data.itemId,$rootScope.user.userId).then(function(response){
				if(response.status == 200){
					
				}
			});
		}
});