angular.module('miniMealApp.ourStoryCtrl', ['ngStorage']).
controller('OurStoryCtrl',  function ($scope, $rootScope, $state, $localStorage, UserService, commonCode) {
	$rootScope.commonCode = commonCode;
	$rootScope.loggedUser = $localStorage.loggedUser;
	$rootScope.adminuser = $localStorage.adminuser;
	$rootScope.newmenu = $localStorage.newmenu;
	$rootScope.status = $localStorage.status;
	$rootScope.adminuser = $localStorage.adminuser;
	$rootScope.mobileNumber = $localStorage.mobileNumber;
	$rootScope.user = $localStorage.user;
	$rootScope.regUser = $localStorage.regUser;
	$rootScope.userName = $localStorage.userName;
	$rootScope.profileShow = $localStorage.profileShow;
	
	$rootScope.notNow = function(){
		$('#myModal').modal('hide');
		$(".adminsection").hide();
		$localStorage.newmenu = true;
		$localStorage.status = true;
		$localStorage.userName = "Visitor";
		//$rootScope.newmenu = $localStorage.newmenu;
		//$rootScope.status = $localStorage.status;
		$state.go('ourstory');
	}
	
});