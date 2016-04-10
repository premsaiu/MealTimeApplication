//define(function () {
	'use strict';
angular.module('miniMealApp', ['miniMealApp.services',
                               'miniMealApp.directives',
                               'miniMealApp.homeCtrl',
                               'miniMealApp.aboutusCtrl',
                               'miniMealApp.addprofileCtrl',
                               'miniMealApp.ammealCtrl',
                               'miniMealApp.pmmealCtrl',
                               'miniMealApp.profileCtrl',
                               'miniMealApp.paymentCtrl',
                               'miniMealApp.contactusCtrl',
                               'miniMealApp.adminprofileCtrl',
                               'miniMealApp.changepwdCtrl',
                               'miniMealApp.adminpaymentCtrl',
                               'miniMealApp.paymentformCtrl',
                               'miniMealApp.ourStoryCtrl',
                               'miniMealApp.sampleMealCtrl',
                               'miniMealApp.scheduleEnquiryCtrl',
                               'miniMealApp.subscribeNowCtrl',
                               'ui.router'])
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
            templateUrl: 'views/users/pmmeal.html',
            controller: 'PmMealCtrl'
        })
          .state('payment', {
            url: '/payment',
            templateUrl: 'views/users/payment.html',
            controller: 'PaymentCtrl'
        })
          .state('contactus', {
            url: '/contact',
            templateUrl: 'views/users/contact.html'
        })
        .state('paymentsuccess', {
            url: '/paymentsuccess',
            templateUrl: 'views/users/payment-success.html'
        })
        .state('adminhome', {
            url: '/adminhome',
            templateUrl: 'views/admin/access.html'
        })
        .state('changepassword', {
            url: '/changepwd',
            templateUrl: 'views/admin/change_password.html',
            controller: 'ChangePwdCtrl'
        })
        .state('adminprofile', {
            url: '/admprofile',
            templateUrl: 'views/admin/profile_admin.html',
            controller: 'AdminProfileCtrl'
        })
        .state('adminbreakfast', {
            url: '/admbreakfast',
            templateUrl: 'views/admin/breakfast.html'
        })
        .state('admindinner', {
            url: '/admdinner',
            templateUrl: 'views/admin/dinner.html'
        })
        .state('adminpayment', {
            url: '/admpayment',
            templateUrl: 'views/admin/payment_admin.html',
            controller: 'AdminPaymentCtrl'
        })
        .state('paymentForm', {
            url: '/paymentForm',
            templateUrl: 'views/admin/payment_form.html',
            controller: 'PaymentFormCtrl'
        })
        .state('ourstory', {
            url: '/ourstory',
            templateUrl: 'views/users/our_story.html',
            controller: 'OurStoryCtrl'
        })
        .state('loginsample', {
            url: '/loginsample',
            templateUrl: 'views/users/our_story.html',
            controller: 'OurStoryCtrl'
        })
        .state('scheduleenquiry', {
            url: '/scheduleenquiry',
            templateUrl: 'views/users/schedule_enquiry.html',
            controller: 'ScheduleEnquiryCtrl'
        })
        .state('samplemeal', {
            url: '/samplemeal',
            templateUrl: 'views/users/sample_meal.html',
            controller: 'SampleMealCtrl'
        })
        .state('subscribenow', {
            url: '/subscribenow',
            templateUrl: 'views/users/subscribe_now.html',
            controller: 'SubscribeNowCtrl'
        })
})
.run( function($rootScope, $location) {
	/*var routesPermission = ['/home'];
	
	$rootScope.$on('$routeChangeStart', function(){
		if(routesPermission.indexOf($location.path()) != -1 && ($rootScope.loggedUser == undefined || $rootScope.loggedUser == false)){
			$location.path('/')
		}
	});*/
    // register listener to watch route changes
	/*$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
			//if (toState.url != "/home") {
			if (($rootScope.loggedUser == undefined || $rootScope.loggedUser == false) &&  toState.url != "/home") {
				console.log("User does not logged in.. Redirecting to home.. ");
		    	$location.path("/");
		    }	
	});*/
 });
//});
//fucntion checks whether user logged in before routing
/*var checkRouting= function ($q, $rootScope, $location) {
    if ($rootScope.loginUser) {
        return true;
    } else {
    	$location.path("/")
    }
};*/
