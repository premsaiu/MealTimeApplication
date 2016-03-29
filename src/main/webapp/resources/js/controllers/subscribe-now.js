angular.module('miniMealApp.subscribeNowCtrl', ['ngStorage']).

controller('SubscribeNowCtrl',  function ($scope,$rootScope,$state,UserService,$localStorage,$filter) {
	
	$scope.subconfmtn = function(){
		$('#area').val();
		var _date = $filter('date')(new Date($scope.date), 'yyyy-MM-dd');
		if($('#area').val() == 'select'){
			alert("Please Select the Area");
		}else{
			UserService.subscribeNow($scope.firstName,$scope.lastName,$scope.mobile,_date,$('#area').val()).then( 
					function(response){
						if(response.data.data != ""){
							debugger;
							$rootScope.user = response.data.data;
							$rootScope.newmenu = false;
							$rootScope.status=true;
							$(".adminsection").hide();
							$state.go('profile');
							$('#succussSaveDiv').html('<div id="scesavemsg" class="success"><button type="button" class="close" aria-label="Close">x</button><strong>Subscription Done Successfully!!!...</strong></div>');
							$('#scesavemsg').delay(5000).fadeOut('slow');	
							
						}
			});
		}
	}
	
});