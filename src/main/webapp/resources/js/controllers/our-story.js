angular.module('miniMealApp.ourStoryCtrl', ['ngStorage']).

controller('OurStoryCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage) {
	
	$rootScope.notNow = function(){
		$('#myModal').modal('hide');
		$(".adminsection").hide();
		$rootScope.newmenu = true;
		$rootScope.status=false;
		$state.go('ourstory');
	}
	
});