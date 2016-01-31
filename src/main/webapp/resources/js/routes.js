'use strict';

var module = angular.module('miniMealApp', ['ui.router']);

module.config(function($stateProvider, $urlRouterProvider) {
    
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
            templateUrl: 'views/users/profile.html'
        })
        .state('aboutus', {
            url: '/aboutus',
            templateUrl: 'views/users/aboutus.html'
        })
        .state('viewprofile', {
            url: '/viewprofile',
            templateUrl: 'views/users/view-profile.html'
        })
         .state('breakfast', {
            url: '/breakfast',
            templateUrl: 'views/users/ammeal.html'
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
});