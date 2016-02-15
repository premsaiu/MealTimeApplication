'use strict';

angular.module('miniMealApp', ['miniMealApp.services','miniMealApp.controllers','ui.router'])
.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'views/users/home.html',
            controller: 'HomeCtrl'
        })
        .state('profile', {
            url: '/profile',
            templateUrl: 'views/users/profile.html',
            controller: 'ProfileCtrl'
         /*   resolve: {
                factory: checkRouting
            }*/
        })
        .state('aboutus', {
            url: '/aboutus',
            templateUrl: 'views/users/aboutus.html'
        })
         .state('addprofile', {
            url: '/addprofile',
            templateUrl: 'views/users/add-profile.html',
            controller: 'AddProfileCtrl'
        })
         .state('breakfast', {
            url: '/breakfast',
            templateUrl: 'views/users/ammeal.html',
            controller: 'AmMealCtrl'
        })
          .state('dinner', {
            url: '/dinner',
            templateUrl: 'views/users/pmmeal.html'
        })
          .state('payment', {
            url: '/payment',
            templateUrl: 'views/users/payment.html'
        })
          .state('contactus', {
            url: '/contact',
            templateUrl: 'views/users/contact.html'
        })
})
.run( function($rootScope, $location) {
    // register listener to watch route changes
	$rootScope.$on('$stateChangeStart', 
			function(event, toState, toParams, fromState, fromParams){
				if (($rootScope.loggedUser == undefined || $rootScope.loggedUser == false) &&  toState.url != "/home") {
					console.log("User does not logged in.. Redirecting to home.. ");
			    	$location.path("/");
			    }
			});
 });
//fucntion checks whether user logged in before routing
/*var checkRouting= function ($q, $rootScope, $location) {
    if ($rootScope.loginUser) {
        return true;
    } else {
    	$location.path("/")
    }
};*/
