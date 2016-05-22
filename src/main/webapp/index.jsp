

<!DOCTYPE html>
<html lang="en" ng-app="miniMealApp">
  <head>
   <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap1-datetimepicker.css" rel="stylesheet">
    <link href="resources/css/styles.css" rel="stylesheet">
    <link href="resources/css/responsive.css" rel="stylesheet">
   <!--  <link href="resources/css/styles1.css" rel="stylesheet">
    <link href="resources/css/responsive1.css" rel="stylesheet"> -->
 <!--     <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>
    <link href="//fonts.googleapis.com/css?family=Cuprum" type="text/css" rel="stylesheet"> -->
       <link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>
    
   <!--  <link rel="icon" type="image/png" href="resources/images/favicon-16x16.png" sizes="16x16"/>
 -->
    
    
  </head>
  <body>

    <div class="container-fluid bg-pattern">
    <div class="container custom-container mt20 mb20">
	   <div ng-include="'views/users/header.html'"></div>
	   <div ng-include="'views/users/left-menu.html'"></div>
	    
	   <div ui-view></div>
	    	<!-- Home Modal -->
			     <!-- <div class="modal fade bg-transition-s1" id="myModal" data-backdrop="static" data-keyboard="false">
				  <div class="modal-dialog max-w480">
				    <div class="modal-content alpha-bg">
				      <div class="modal-header alpha-bg">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <div class="logo-2 center-block">Meal Time</div>
				        <a href="#" class="logo-2 center-block">Meal Time</a>
				      </div>
				      <div class="modal-body">
				      <span style="color:red;">{{loginError}}</span>
				        <form name="myForm">
				          <div class="form-group">
				            <label for="recipient-name" class="control-label center-block new-title-s4">Just let us know your 10 digit mobile number</label>
				            <input type="number" class="form-control center-block" id="mobileNo" placeholder="+91" style="width: 50%;" name="mobileNo" data-ng-minlength=10 data-ng-maxlength=10 data-ng-pattern=" /^[789]\d{9}$/" required data-ng-model="mobileNumber" data-ng-change="adminchk()">
					          <div class="form-group" data-ng-show="adminuser">
					            <label for="recipient-name" class="control-label">Password:</label>
					            <input type="password" class="form-control" id="password" ng-model="password">
					          </div>
				      		  
				      		  <div class="mt10">
				      		  	<div class="center-block" style="max-width: 128px;">
							        <button type="submit" class="btn btn-success" ng-disabled="myForm.$invalid" data-ng-click="checkUser()">Enter</button>
							        <button type="submit" class="btn btn-success" data-ng-click="notNow()">Later</button>
							        </div>
				      		  	</div>
				      		  </div>
				      		  <div class="text-align-center">
				      		  	<span data-ng-show="myForm.mobileNo.$error.pattern" style="color:red">Not a valid number!</span>
    							<span data-ng-show="myForm.mobileNo.$error.minlength" style="color:red">Enter 10 digit number </span>
				      		  </div>
				      		   
				          
				          
   
				        </form>
	
				      </div>
				    </div>
				  </div>
				</div> --><!-- modal --> 
				 <div class="modal fade custom-modal-bg" id="myModal" tabindex="-1" role="dialog">
				  <div class="modal-dialog max-w400">
				    <div class="modal-content custom-modal-content">
				      <div class="modal-header border-none">
				        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
				        <img alt="Meal Time" src="resources/images/logo.png" width="120" height="100%" class="center-block">
				      </div>
				      <div class="modal-body">
				        <span style="color:red;">{{loginError}}</span>
				        <form name="form">
				          <div class="form-group">
				            <label for="recipient-name" class="control-label center-block log-title-s1">Let us know your mobile number</label>
				            <input type="text" class="form-control center-block w300 mt20" name="mobileno" id="mobileNo" placeholder="+91" data-ng-minlength=10 data-ng-maxlength=10 data-ng-pattern=" /^[789]\d{9}$/" required data-ng-model="mobileNumber" data-ng-change="adminchk()" ng-keypress="$event.keyCode == 13 && checkUser()" autofocus="focusInput">
				           <div class="form-group" data-ng-show="adminuser">
					            <label for="recipient-name" class="control-label">Password:</label>
					            <input type="password" class="form-control" id="password" ng-model="password">
					          </div>
				          </div>
				          <div class="text-align-center" ng-show="userchecklogin===false">
				      		  	 <span class="help-block" id="validationMessage" ng-show="form.mobileno.$error.required">MobileNo is required.</span>
						 <span class="help-block" id="validationMessage" ng-show="form.mobileno.$error.minlength || form.mobileno.$error.maxlength">Enter vaild mobile number.</span>
				   		  </div>
				      		   <div class="modal-footer border-none p0">
					     <div class="center-block w215">
						 	<button type="button" class="btn btn-custom-log-s1 log-btns-s1"   data-ng-click="checkUser(form.$valid)">ENTER</button>
						 	<button type="button" class="btn btn-custom-log-s1 log-btns-s1" data-ng-click="notNow()" data-dismiss="modal" aria-label="Close">LATER</button>
						 </div>
				        <div class="clearfix mt20">
					  </div>
					   
				      </div>
				        </form>
				      </div>
				     
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			
	   <!-- <div ng-include="'views/users/footer.html'"></div> -->
	    
    </div>
	
	</div>
	<script src="resources/js/lib/jquery.min.1.11.3.js"></script>
    <script src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib//moment.js"></script>
   
	<!-- <script type="text/javascript" src="resources/js/lib/bootstrap-datetimepicker.js"></script>
	 --><script type="text/javascript" src="resources/js/lib/bootstrap1-datetimepicker.js"></script>
	 <script type="text/javascript" src="resources/js/lib/bootstrap-datetimepicker.fr.js"></script>
    <script src="resources/js/lib/angular.js"></script>
    <script src="resources/js/lib/angular-ui-router.js"></script>
    <script src="resources/js/lib/ngStorage.min.js"></script>
    <script src="resources/js/routes.js"></script>
    <!--  <script src="resources/js/controller.js"></script> -->
    <script src="resources/js/controllers/about-us.js"></script>
    <script src="resources/js/controllers/add-profile.js"></script>
    <script src="resources/js/controllers/admin-profile.js"></script>
    <script src="resources/js/controllers/am-meal.js"></script>
    <script src="resources/js/controllers/change-password.js"></script>
    <script src="resources/js/controllers/contact-us.js"></script>
    <script src="resources/js/controllers/home.js"></script>
    <script src="resources/js/controllers/payment.js"></script>
    <script src="resources/js/controllers/pm-meal.js"></script>
    <script src="resources/js/controllers/profile.js"></script>
    <script src="resources/js/controllers/admin-payment.js"></script>
    <script src="resources/js/controllers/payment-form.js"></script>
    <script src="resources/js/controllers/our-story.js"></script>
    <script src="resources/js/controllers/sample-meal.js"></script>
    <script src="resources/js/controllers/schedule-enquiry.js"></script>
    <script src="resources/js/controllers/subscribe-now.js"></script>
    <script src="resources/js/controllers/leavemessage.js"></script>
<!--     <script src="resources/js/controllers/controller.js"></script> -->
    <script src="resources/js/services/services.js"></script>
    <script src="resources/js/directives/directives.js"></script>
  
<script type="text/javascript">
$( document ).ready(function() {
	$('.modal').on('shown.bs.modal', function() {
		  $(this).find('[autofocus]').focus();
		});
	
});</script>
  </body>
</html>