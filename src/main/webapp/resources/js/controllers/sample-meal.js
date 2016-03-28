angular.module('miniMealApp.sampleMealCtrl', ['ngStorage']).

controller('SampleMealCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter) {
	
	$scope.requestSampleMeal = function(){
		var area = $('#area').val();
		var _date = $filter('date')(new Date($scope.sampleMeal.date), 'yyyy-MM-dd');
		var sampleMealObj = {};
		sampleMealObj.mobileNumber = $scope.sampleMeal.mobileNumber;
		sampleMealObj.sampleMealDate = _date;
		sampleMealObj.name = $scope.sampleMeal.name;
		sampleMealObj.address = area;
		console.log(sampleMealObj);
		/*UserService.requestSampleMeal(sampleMealObj).then( 
			function(response){
				sdebugger;
		});*/
	}
	
});