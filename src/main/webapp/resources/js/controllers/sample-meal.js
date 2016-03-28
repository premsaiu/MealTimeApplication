angular.module('miniMealApp.sampleMealCtrl', ['ngStorage']).

controller('SampleMealCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter) {
	
	$scope.requestSampleMeal = function(){
		var area = $('#area').val();
		var _date = $filter('date')(new Date($scope.date), 'yyyy-MM-dd');
		var sampleMealObj = {};
		sampleMealObj.mobileNumber = $scope.mobile;
		sampleMealObj.sampleMealDate = _date;
		sampleMealObj.name = $scope.name;
		sampleMealObj.address = area;
		UserService.requestSampleMeal(sampleMealObj).then( 
			function(response){
				sdebugger;
		});
	}
	
});