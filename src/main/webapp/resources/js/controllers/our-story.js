angular.module('miniMealApp.ourStoryCtrl', ['ngStorage']).
controller('OurStoryCtrl',  function ($scope, $rootScope, $state,$stateParams, $localStorage, UserService, commonCode) {
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
	var suvalue=$stateParams.scrollTo;
	var homwcountvalue=$stateParams.count;
		$('body').animate({scrollTop: suvalue}, 800);
		/*if(suvalue==732){
			$('.media-objectzoom').addClass("howitwork-bites-pic-s2");
		}*/
	
	$rootScope.notNow = function(){
		$('#myModal').modal('hide');
		$(".adminsection").hide();
		$localStorage.newmenu = true;
		$localStorage.status = true;
		$localStorage.userName = "Visitor";
		//$rootScope.newmenu = $localStorage.newmenu;
		//$rootScope.status = $localStorage.status;
		$state.go('home');
	}
	var howwrkvalue=['Flavors','Quality Cooking','Packaging','Delivery','Payments'];
	var count=0;

	var asd=0;
	if(homwcountvalue==='001'){
		count=1;
	}
	$('.media-objectzoom').click(function(){
    	$('.media-objectzoom').removeClass('howitwork-bites-pic-s2');
         $(this).addClass("howitwork-bites-pic-s2");
        asd=this.id;
        $scope.$apply(function () {
       	 $scope.count = asd;
            $scope.howrkvalue=howwrkvalue[asd];
       });

    });
	        $scope.count = asd;
	        $scope.howrkvalue=howwrkvalue[asd];
	       
	
	$scope.next=function(){
		if(count<4){
			count++;
			$('#'+$('.howitwork-bites-pic-s2').attr("id")+'').addClass("howitwork-bites-pic-s2");
		    $('#'+$('.howitwork-bites-pic-s2').attr("id")+'').removeClass('howitwork-bites-pic-s2');
			$scope.count=count;
			$scope.howrkvalue=howwrkvalue[$scope.count];
			$('#'+$scope.count+'').removeClass('howitwork-bites-pic-s2');
			$('#'+$scope.count+'').addClass("howitwork-bites-pic-s2");
			}
	}
	$scope.previous=function(){
		if(count>0){
			count--;
			$('#'+$('.howitwork-bites-pic-s2').attr("id")+'').removeClass('howitwork-bites-pic-s2');
		    $('#'+$('.howitwork-bites-pic-s2').attr("id")+'').addClass("howitwork-bites-pic-s2");
			$scope.count=count;
			$scope.howrkvalue=howwrkvalue[$scope.count];
			$('#'+$scope.count+'').removeClass('howitwork-bites-pic-s2');
			$('#'+$scope.count+'').addClass("howitwork-bites-pic-s2");
				
			}
	}
	$scope.getBackgroundColour=function(){
		alert("hi");
			 $scope.count=1;
	         $scope.howrkvalue=howwrkvalue[1];
		
	}
	
});