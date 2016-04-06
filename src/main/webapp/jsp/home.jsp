<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perficient HR</title>
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/angular-datatables.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/ngDialog-theme-default.min.css">
<link rel="stylesheet" href="css/ngDialog.min.css">
<link rel="stylesheet" href="css/ui-jquery.css">
</head>

<body ng-app="perficientHr">
	<div ng-include="'html/header.html'"/></div>
	<div class="divContainer">
		<ng-view></ng-view>
	</div>
</body>
<script src="js/lib/jquery.js"></script>
<script src="js/lib/jquery-ui.min.js"></script>
<script src="js/lib/angular.js"></script>
<script src="js/lib/angular-route.min.js"></script>
<script src="js/lib/jquery.dataTables.min.js"></script>
<script src="js/lib/angular-datatables.js"></script>
<script src="js/lib/angular-datatables.util.js"></script>
<script src="js/lib/angular-datatables.options.js"></script>
<script src="js/lib/angular-datatables.instances.js"></script>
<script src="js/lib/angular-datatables.factory.js"></script>
<script src="js/lib/angular-datatables.renderer.js"></script>
<script src="js/lib/angular-datatables.directive.js"></script>
<script src="js/lib/ngDialog.min.js"></script>
<script src="js/app.js"></script>
<script src="js/constants/constants.js"></script>
<script src="js/controller/profileController.js"></script>
<script src="js/service/profileServices.js"></script>
<script src="js/controller/employeeController.js"></script>
<script src="js/service/employeeServices.js"></script>
<script src="js/controller/dashboardController.js"></script>
<script src="js/service/dashboardServices.js"></script>
<script src="js/controller/ptoController.js"></script>
<script src="js/directives/directives.js"></script>
<script src="js/filters/filters.js"></script>
<footer>
   <div class="container">
       <div class="row">
           <div class="col-md-12">
               &copy; 2016  | By : <a href="http://www.perficient.com/" target="_blank">Perficient</a>
           </div>
       </div>
   </div>
</footer>
</html>