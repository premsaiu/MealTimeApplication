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
	var howwrkvalue=['Flavors','Quality Cooking','Packaging','Delivery','Payments'];
	var count=0;
	$scope.count=0;
	$scope.howrkvalue=howwrkvalue[0];
	$('.media-objectzoom').click(function(){
    	$('.media-objectzoom').removeClass('howitwork-bites-pic-s2');
         $(this).addClass("howitwork-bites-pic-s2");
        $scope.imagevalue(this.id);
    });
	
	
	$scope.next=function(){
		if(count<4){
			count++;
			$scope.count=count;
			$scope.howrkvalue=howwrkvalue[count];
			}
	}
	$scope.previous=function(){
		if(count>0){
			count--;
			$scope.howrkvalue=howwrkvalue[count];
			}
	}
	$scope.imagevalue=function(id){
		
			 $scope.count=id;
	         $scope.howrkvalue=howwrkvalue[id];
		
	}
	
});