angular.module('miniMealApp.subscribeNowCtrl', ['ngStorage']).

controller('SubscribeNowCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter) {
	
	$scope.subconfmtn = function(){
		$('#area').val();
		$scope.subscribeNowErrorMsg = "";
		var _date = $filter('date')(new Date($scope.date), 'yyyy-MM-dd');
		if($('#area').val() == 'select'){
			alert("Please Select the Area");
		}else{
			UserService.subscribeNow($scope.firstName,$scope.lastName,$scope.mobile,_date,$('#area').val()).then( 
					function(response){
						if(response.data.data != "" && data.statusCode == 200){
							$rootScope.user = response.data.data;
							$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">x</button><strong>Subscription Done Successfully!!!...</strong></div>');
							$('#scesavemsg').delay(5000).fadeOut('slow');	
							$timeout(function() {
							      $state.go('profile');
							 }, 7000);						
							}else if(response.data.statusCode == 500){
								$scope.subscribeNowErrorMsg = "Something Went Wrong please check all your input fields!!!!";
							}
			});
		}
	}
	
});