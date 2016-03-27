angular.module('miniMealApp.scheduleEnquiryCtrl', ['ngStorage']).

controller('ScheduleEnquiryCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage) {
	
	$rootScope.notNow = function(){
		$('#myModal').modal('hide');
		$(".adminsection").hide();
		$rootScope.newmenu = true;
		$rootScope.status=false;
		$state.go('ourstory');
	}
	
});