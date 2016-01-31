'use strict';

var miniMealApp = angular.module('miniMealApp',['ngRoute']);

miniMealApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			//templateUrl: 'view/index.',
			controller : "HomeCtrl"
		})	
		.when('/aboutus', {
			templateUrl: 'view/aboutus.html',
			controller : "AboutUsCtrl"
		})
		.when('/profile', {
			templateUrl: 'view/profile.html',
			controller : "ProfileCtrl"
		})	
		.when('/ammeal', {
			templateUrl: 'view/ammeal.html',
			controller : "AmMealCtrl"
		})	
		.when('/pmmeal', {
			templateUrl: 'view/pmmeal.html',
			controller : "PmMealCtrl"
		})	
		.when('/payment', {
			templateUrl: 'view/payment.html',
			controller : "PaymentCtrl"
		})
		.when('/contact', {
			templateUrl: 'view/contact.html',
			controller : "ContactCtrl"
		})
}]);