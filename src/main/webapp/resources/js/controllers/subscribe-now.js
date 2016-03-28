angular.module('miniMealApp.subscribeNowCtrl', ['ngStorage']).

controller('SubscribeNowCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter) {
	
	$scope.subconfmtn = function(){
		$('#area').val();
		var _date = $filter('date')(new Date($scope.date), 'yyyy-MM-dd');
		if($('#area').val() == 'select'){
			alert("Please Select the Area");
		}else{
			UserService.subscribeNow($scope.firstName,$scope.lastName,$scope.mobile,_date,$('#area').val()).then( 
					function(response){
						debugger;
						$state.go('profile');
			});
		}
	}
	
});