<!DOCTYPE html>
<html lang="en" ng-app="miniMealApp">
  <head>
   
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="resources/css/styles.css" rel="stylesheet">
    <link href="resources/css/responsive.css" rel="stylesheet">
    <link href="resources/css/styles1.css" rel="stylesheet">
    <link href="resources/css/responsive1.css" rel="stylesheet">
     <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>
   
    
   <!--  <link rel="icon" type="image/png" href="resources/images/favicon-16x16.png" sizes="16x16"/>
 -->
    
    
  </head>
  <body>

    <div class="container-fluid bg-pattern">
    <div class="container custom-container mt20 mb20">
	   <div ng-include="'views/users/header.html'"></div>
	   <div ng-include="'views/users/left-menu.html'"></div>
	   <div ng-include="'views/users/left-menu-new.html'"></div>
	    
	   <div ui-view></div>
	    	<!-- Home Modal -->
			    <div class="modal fade" id="myModal" data-backdrop="static" data-keyboard="false">
				  <div class="modal-dialog max-w400">
				    <div class="modal-content">
				      <div class="modal-header">
				        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
				        <h4 class="modal-title">Log Account</h4>
				      </div>
				      <div class="modal-body">
				      <span style="color:red;">{{loginError}}</span>
				        <form name="myForm">
				          <div class="form-group">
				            <label for="recipient-name" class="control-label">Registered Mobile No:</label>
				            <input type="number" class="form-control" id="mobileNo" name="mobileNo" data-ng-minlength=10 data-ng-maxlength=10 data-ng-pattern=" /^[789]\d{9}$/" required data-ng-model="mobileNumber" data-ng-change="adminchk()">
				      		  </div>
				      		   <span data-ng-show="myForm.mobileNo.$error.pattern">Not a valid number!</span>
    							<span data-ng-show="myForm.mobileNo.$error.minlength">Enter 10 digit number </span>
				          
				          <div class="form-group" data-ng-show="adminuser">
				            <label for="recipient-name" class="control-label">Password:</label>
				            <input type="password" class="form-control" id="password" ng-model="password">
				          </div>
				          
   
				        </form>
	
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success" data-ng-click="checkUser()">Submit</button>
				        <button type="submit" class="btn btn-success" data-ng-click="notNow()">Later</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			
	   <div ng-include="'views/users/footer.html'"></div>
	    
    </div>
	
	</div>
	<script src="resources/js/lib/jquery.min.1.11.3.js"></script>
    <script src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib//moment.js"></script>
   
	<script type="text/javascript" src="resources/js/lib//bootstrap-datetimepicker.js"></script>
    
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
<!--     <script src="resources/js/controllers/controller.js"></script> -->
    <script src="resources/js/services/services.js"></script>
    <script src="resources/js/directives/directives.js"></script>
   
  </body>
</html>