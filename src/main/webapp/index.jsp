<!DOCTYPE html>
<html lang="en" ng-app="miniMealApp">
  <head>
   
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/styles.css" rel="stylesheet">
    <link href="resources/css/responsive.css" rel="stylesheet">
    
    
   <!--  <link rel="icon" type="image/png" href="resources/images/favicon-16x16.png" sizes="16x16"/>
 -->
    
    
  </head>
  <body>

    <div class="container-fluid bg-pattern">
    <div class="container custom-container mt20 mb20">
	   <div ng-include="'views/users/header.html'"></div>
	   <div ng-include="'views/users/left-menu.html'"></div>
	    
	   <div ui-view></div>
	   <div ng-include="'views/users/footer.html'"></div>
	    
    </div>
	
	</div>
	<script src="resources/js/lib/jquery.min.1.11.3.js"></script>
    <script src="resources/js/lib/bootstrap.min.js"></script>
	<script src="resources/js/lib/angular.js"></script>
    <script src="resources/js/lib/angular-ui-router.js"></script>
    <script src="resources/js/routes.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/controller.js"></script>
  </body>
</html>