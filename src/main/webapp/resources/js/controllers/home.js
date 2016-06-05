'use strict';

angular.module('miniMealApp.homeCtrl', ['ngStorage']).
controller('HomeCtrl',  function ($scope, $rootScope, $state, $localStorage, UserService, commonCode) {
	$rootScope.commonCode = commonCode;
    $('.menuSelect').click(function(){
    	$('.menuSelect').removeClass('active');
         $(this).addClass("active");
    });
    
    $('.page1menu').click(function(){
    	$('.page1menu').removeClass('active');
         $(this).addClass("active");
    });
    
    $localStorage.status = true;
    $localStorage.regUser = false;
    $rootScope.newmenu = $localStorage.newmenu;
	$rootScope.status = $localStorage.status;
    
    $rootScope.modalShow = function(){
    	$rootScope.mobileNumber="";
		$('#myModal').modal('show');
	}
    
    $scope.addClassCSS = function(){
	    	$('.nav-list-headnav').removeClass('active')
	    	$('.nav-list-sub1').removeClass('active');
	         $('.nav-list-sub1:nth-child(5)').addClass("active");
	}
    
	if(($rootScope.loggedUser == undefined || $rootScope.loggedUser == false) && $rootScope.modalfirstshow == undefined){
		$scope.modalShow();
		$localStorage.$reset();
	}
	$rootScope.notNow = function(){
		$('#myModal').modal('hide');
		$localStorage.$reset();
		$(".adminsection").hide();
		$localStorage.newmenu = true;
		$localStorage.status = true;
		$localStorage.userName = "Visitor";
		$rootScope.status = true;
		$rootScope.newmenu = true;
		$rootScope.userName = "Visitor";
		$rootScope.modalfirstshow=false;
		/*$state.go('ourstory');*/
	}
	$rootScope.adminchk=function(){
		UserService.adminchk($scope.mobileNumber).then(
	            function(response) {
	            	$rootScope.loggedUser = true;
	            	$localStorage.loggedUser = true;
	            	if(response.data == "" || response.data == null){
	            		$rootScope.adminuser = "";
	            		$localStorage.adminuser = "";
	            	}else{
	            		$rootScope.adminuser = response.data;
	            		$localStorage.adminuser = response.data;
	            		console.log($localStorage.adminuser);
	            	}
	           },
	            function(errResponse){
	                console.error('Error while checking user');
	            }
	   );
	}
	$rootScope.checkUser = function(valid){
		$rootScope.userchecklogin=valid;
		if(valid){
		$rootScope.loginError = "";
		console.log("Mobile Number::"+$scope.mobileNumber);
		
		if(angular.isDefined($scope.mobileNumber)){
			$localStorage.mobileNumber = $scope.mobileNumber;
			$rootScope.mobileNumber = $scope.mobileNumber;
		if($scope.password){
			UserService.checkAdmin($scope.mobileNumber, $scope.password).then(
	            function(response) {
	            	$localStorage.loggedUser = true;
	            	$rootScope.loggedUser = true;
	            	if(response.data.data == "" || response.data.data == null){
	            		$rootScope.loginError = "Invalid Credentials. Please try again later";
	            	}else{
	            		$localStorage.user = response.data.data;
	            		$rootScope.user = response.data.data;
	            		console.log($localStorage.user);
	            		$rootScope.status=false;
	            		$rootScope.regUser = true;
	            		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
	            		$localStorage.status = false;
	            		$localStorage.regUser = true;
	            		$localStorage.userName = $localStorage.user.firstName+" "+$localStorage.user.lastName;
	            		$('#myModal').modal('hide');
	            		$state.go('adminhome')
	            	}
	           },
	            function(errResponse){
	        	    $scope.loginError = "Invalid Credentials. Please try again later";
	                console.error('Error while checking user');
	            });
				}
		else{
					UserService.checkUser($scope.mobileNumber).then(
	                function(response) {
	                	if(response.data != ""){
	                		UserService.checkSubscription(response.data.userId).then(function(response1) {
								if(response1.data.data.userSubscription != null 
									&& response1.data.data.userSubscription.confirmation == false){
								    $(".adminsection").hide();
									$rootScope.newmenu = true;
									$rootScope.status = true;
									$rootScope.profileShow = true;
									$localStorage.newmenu = true;
									$localStorage.status = true;
									$localStorage.profileShow = true;
									/*$state.go('ourstory');*/
								}else if(response1.data.data.userSubscription == null){
									$(".adminsection").hide();
									$rootScope.newmenu = true;
									$rootScope.status  =true;
									$rootScope.profileShow = true;
									$localStorage.newmenu = true;
									$localStorage.status = true;
									$localStorage.profileShow = true;
									/*$state.go('ourstory');*/
								}
								else{
									$(".adminsection").hide();
									$rootScope.newmenu = true;
									$rootScope.status  =true;
									$rootScope.profileShow = true;
									$localStorage.newmenu = true;
									$localStorage.status = true;
									$localStorage.profileShow = true;
								}
							});
	                	}
	                	$rootScope.loggedUser = true;
	                	$localStorage.loggedUser = true;
	                	$localStorage.user = response.data;
	                	$rootScope.user = response.data;
	                	console.log($localStorage.user);
                		if($localStorage.user.roleId == 3){
                			$rootScope.userName = "Visitor";
                			$localStorage.userName = "Visitor";
                		}else{
	                		$rootScope.status=true;
	                		$rootScope.regUser = true;
	                		$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
                			$localStorage.status = true;
                			$localStorage.regUser = true;
                			$localStorage.userName = $localStorage.user.firstName+" "+$localStorage.user.lastName;
                		}
	               },
	                function(errResponse){
	                    console.error('Error while checking user');
	                }
	       );
			$('#myModal').modal('hide');
		}
	  }
		
	  else{
		  	$('#myModal').modal('hide');
			$(".adminsection").hide();
			$rootScope.newmenu = true;
			$rootScope.status = true;
			$localStorage.newmenu = true;
			$localStorage.status = true;
			$state.go('home');
	  }
	
	}
	}

	
	$rootScope.closeModal =function(modalId){
		$('#'+modalId).modal('hide');
	}
	
	
	
	$rootScope.checkUserByMobile = function(mobileNumber){
		UserService.checkUser(mobileNumber).then(
            function(response) {
            	if(response.data != ""){
            		$rootScope.loggedUser = true;
            		$rootScope.user = response.data;
            		$localStorage.loggedUser = true;
            		$localStorage.user = response.data;
            		console.log($localStorage.user);
            		if($localStorage.user.roleId == 3){
            			$rootScope.userName = "Visitor";
            			$localStorage.userName = "Visitor";
            		}else{
            			$rootScope.status = true;
            			$rootScope.regUser = true;
            			$rootScope.userName = $rootScope.user.firstName+" "+$rootScope.user.lastName;
            			$localStorage.status = true;
            			$localStorage.regUser = true;
            			$localStorage.userName = $localStorage.user.firstName+" "+$localStorage.user.lastName;
            		}
            	}
           },
            function(errResponse){
                console.error('Error while checking user');
            }
       );
	}
	
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
	console.log("new menu-->"+$rootScope.newmenu);
	console.log("status-->"+$rootScope.profileShow);
	
})