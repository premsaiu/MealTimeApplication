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
	
	var brkfstObj = {
			status: "Today's Breakfast Special",
			imgSrc: "resources/images/ammeal/meal_01.jpg",
			imageName: "Masala Dosa",
			brkfstInfo: "Masala Dosa with Chutney"
	}
	
	$scope.brkfstObj = brkfstObj;
		/*$scope.status = "Today's Breakfast Special";
		$scope.imageSrc = "resources/images/ammeal/meal_01.jpg";
		$scope.imageName = "Masala Dosa";
		$scope.brkfstInfo = "Masala Dosa with Chutney";*/
		
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
		
		$scope.chooseItems = function(){
			angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					value.selected = false;
				}
			});
			
			$scope.favoriteSuppl = "";
			$scope.showModal = true;
			
			$("#myAddonModal").modal('show');
		};
		$scope.cancelled = false;
		$scope.cancelAllItems = function(brkfstObj){
			delete $scope.brkfstObj;
			angular.element(brkfstObj).attr("imgSrc","");
			$scope.cancelled = true;
			//$scope.brkfstObj.imgSrc = "";
			$scope.show = false;
		}
		
		
		$scope.updateSelection = function(event){
			$scope.supplflag = false;
			$scope.alertShow = false;
			
			/*angular.forEach($scope.complItems, function(value,key){
				if(value.selected){
					$scope.supplflag = true;
				}
			});*/
			
			angular.forEach($scope.suppleItems, function(value,key){
				if(value.Item==$scope.favoriteSuppl){
					$scope.supplflag = true;
				}
			});
			
			if ($scope.supplflag) {
				if(confirm("Do you want to replace the selected item with existing?")){
					angular.forEach($scope.suppleItems, function(value,key){
						if(value.Item==$scope.favoriteSuppl){
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
		
		$scope.favoriteSuppl = "";

		$scope.updateSelectionSuppl = function(event){
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
		k8			//$scope.complflag = true;
					$scope.favoriteSuppl = "";
				}
			}
		}
		
		$(document).ready(function() {
		    
		    /*
		    * I like wrapping inputs with labels for various reasons
		    * What gets targeted may have to change depending on your markup
		    * I'm using mouseup for the event as it seems more reliable and "click"
		    * triggers two events
		    */
		    $("#new label").on("mouseup", function(e) {
		        
		        // cache the selected radio item
		        var thisItem = $(this);
		        // save the current state of the chosen item
		        var previous = thisItem.children("input").prop("checked");
		        // if item was previously selected then...
		        if (previous) {
		            // I introduce a slight delay
		            // Without the delay the input stays checked
		            setTimeout(function() {
		                // after the delay uncheck the item
		                // call blur() at end in case of focus indicator on input
		                thisItem.children("input").prop("checked", false).blur();
		            }, 10);
		        }

		    });       
		    
		});
		
		
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