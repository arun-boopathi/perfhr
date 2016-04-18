<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="no-touch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perficient HR</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/common.css" >
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/angular-datatables.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/ngDialog-theme-default.min.css">
<link rel="stylesheet" href="css/ngDialog.min.css">
<link rel="stylesheet" href="css/ui-jquery.css">
<link rel="stylesheet" href="css/jquery.mmenu.all.css">
<link rel="stylesheet" href="css/layout.css">

</head>

<body ng-app="perficientHr" class="fixedsubmenu">
	<nav id="menu" class="mm-menu">
        <ul>
            <li><a href="#/dashboard">Dashboard</a></li>
            <li>
            	<a href="#">Administration</a>
            	<ul>
            		<li><a href="#">Projects</a></li>
            		<li><a href="#">Roles</a></li>
            		<li><a href="#">Add Employee</a></li>
            		<li><a href="#/designations">Designations</a></li>
            		<li>
            			<a href="#">Reports</a>
            			<ul>
            				<li><a href="#">WFH Reports</a></li>
            				<li><a href="#">PTO Reports</a></li>
            			</ul>	
            		</li>
            	</ul>
            </li>
            <li><a href="#/employees">Employees</a></li>
            <li><a href="#/profile">Profile</a></li>
            <li>
            	<a href="#">Referral</a>
            	<ul>
            		<li><a href="#/candidate">Candidate</a></li>
            	</ul>
            </li>
            <li><a href="#/pto">PTO</a></li>
            <li><a href="#">WFH</a></li>
            <li><a href="#/notifications">Notifications</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>        
	</nav>
	
	
	<a id="sidePanel" class="mm-slideout" href="#"><span></span></a>
	
	<div class="submenu fixed mm-slideout">
		<div class="divContainer">
			<ng-view></ng-view>
		</div>
	</div>
</body>
<script src="js/lib/jquery.js"></script>
<script src="js/lib/jquery-ui.min.js"></script>
<script src="js/lib/angular.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/angular-route.min.js"></script>
<script src="js/lib/jquery.dataTables.min.js"></script>
<script src="js/lib/angular-datatables.js"></script>
<script src="js/lib/angular-datatables.util.js"></script>
<script src="js/lib/angular-datatables.options.js"></script>
<script src="js/lib/angular-datatables.instances.js"></script>
<script src="js/lib/angular-datatables.factory.js"></script>
<script src="js/lib/angular-datatables.renderer.js"></script>
<script src="js/lib/angular-datatables.directive.js"></script>
<script src="js/lib/jquery.mmenu.all.min.js"></script>
<script src="js/app.js"></script>
<script src="js/common.js"></script>
<script src="js/constants/constants.js"></script>
<script src="js/controller/datatableController.js"></script>
<script src="js/controller/profileController.js"></script>
<script src="js/service/profileServices.js"></script>
<script src="js/controller/employeeController.js"></script>
<script src="js/service/employeeServices.js"></script>
<script src="js/controller/dashboardController.js"></script>
<script src="js/service/dashboardServices.js"></script>
<script src="js/controller/designationController.js"></script>
<script src="js/service/designationServices.js"></script>
<script src="js/controller/ptoController.js"></script>
<script src="js/directives/directives.js"></script>
<script src="js/filters/filters.js"></script>

<script type="text/javascript">
// variables
var $menu = $('#menu');
var $btnMenu = $('.btn-menu');
var $img = $('img'); 

// mmenu customization
$menu.mmenu({
  navbars: [{
	position: "top",
	content: [ "searchfield", "breadcrumbs" ],
	height: 2
  }],
  extensions: ['widescreen', 'theme-dark', 'effect-menu-slide'],
  offCanvas: {
    position  : "left",
    zposition : "back"
  },
  searchfield: true
}).on('click', 'a[href^="#/"]', function() {
	console.log('link: ', $(this));
	window.location.href=$(this).attr('href');
	return false;
});

// toggle menu
var api = $menu.data("mmenu");

$('#sidePanel').on('click', function(e) {
	e.preventDefault();
	if ( $(this).hasClass('mm-opened' )) {
		api.close();
	} else {
		api.open();
	}
});

// change toggle behavior for subpanels
$menu.find( ".mm-next" ).addClass("mm-fullsubopen");

</script>

</html>