'use strict';

angular.module('miniMealApp.scheduleEnquiryCtrl', [])
.controller('ScheduleEnquiryCtrl',function ($scope,$rootScope,$state,UserService,$localStorage) {
	
	$rootScope.notNow = function(){
	/*	$('#myModal').modal('hide');
		$(".adminsection").hide();
		$rootScope.newmenu = true;
		$rootScope.status=false;
		$state.go('ourstory');*/
	}
	$scope.submitsched=function(){
		debugger;
		/*UserService.subscribe($scope.sched).then(function(response) {
			$scope.subscdata = response.data;
		},function(errResponse){
			console.error('Error while retrieving getSubListItems');
		});
		*/
	}
	
});