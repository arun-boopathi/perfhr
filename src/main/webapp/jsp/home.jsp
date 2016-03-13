<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perficient HR</title>

</head>

<body ng-app="perficientHr">
	<div ng-include="'html/header.html'"/></div>
	<ng-view></ng-view>	
</body>
<script src="js/jquery.js"></script>
<script src="js/angular.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/home.js"></script>
<script src="js/controller/profileController.js"></script>
<script src="js/service/profileServices.js"></script>
</html>