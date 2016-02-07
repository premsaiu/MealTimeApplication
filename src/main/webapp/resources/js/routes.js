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
        })
        .state('aboutus', {
            url: '/aboutus',
            templateUrl: 'views/users/aboutus.html'
        })
        .state('viewprofile', {
            url: '/viewprofile',
            templateUrl: 'views/users/profile.html',
            controller: 'ProfileCtrl'
        })
         .state('addprofile', {
            url: '/addprofile',
            templateUrl: 'views/users/view-profile.html',
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
});
