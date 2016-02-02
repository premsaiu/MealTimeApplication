'use strict';

angular.module('miniMealApp.services',[])
.factory('UserService', ['$http', '$q', function($http, $q){

	/*return {
			checkUser: function(mobileNo) {
					return $http.get('http://localhost:8080/MealTimeApplication/checkUser.spring?mobileNo='+mobileNo)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while user check');
										return $q.reject(errResponse);
									}
							);
			}
		    
	};*/
	
	this.checkUser = function (mobileNo) {
        var request = $http({
            method:"get",
            url:  'http://localhost:8080/MealTimeApplication/checkUser.spring?mobileNo='+mobileNo,
            dataType: "application/json"
        });
        return request;
    };
}]);
