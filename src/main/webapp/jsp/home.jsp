<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" class="no-touch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Perficient HR</title>
<!-- Vendors CSS -->
<link rel="stylesheet" href="css/vendors.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/perf.css">
<link rel="stylesheet" href="css/theme4.css">
</head>
<body ng-app="perficientHr" class="fixedsubmenu">
	<nav id="menu" class="mm-menu" style="display: none;" ng-controller="menuController">
        <ul>
            <li><a href="#/dashboard">Dashboard</a></li>
            <li>
            	<a href="#">Administration</a>
            	<ul>
            		<li><a href="#/designations">Job Title</a></li>
            		<li><a href="#/importpto">Import PTO</a></li>
            		<li><a href="#/projects">Projects</a></li>
            		<li><a href="#/projectmembers">Project Members</a></li>
            		<li>
            			<a href="#">Reports</a>
            			<ul>
            				<li><a href="#/report/jobtitle">Job Title</a></li>
            				<li><a href="#/report/reports_wfh">WFH Reports</a></li>
            				<li><a href="#/report/reports_pto">PTO Reports</a></li>
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
            <li>
            	<a href="#">Interview</a>
            	<ul>
            		<li><a href="#/interview">L1</a></li>
            	</ul>
             </li>
            <li><a href="#/pto">PTO</a></li>
            <li><a href="#/wfh">WFH</a></li>
            <li><a href="#/notifications">Notifications - <span class="badge">{{notificationCount}}</span></a></li>
            <li><a href="logout">Logout</a></li>
        </ul>        
	</nav>
	
	<a id="sidePanel" class="mm-slideout" href="#"><span></span></a>
	
	<div class="submenu fixed mm-slideout">
		<div class="header" ng-controller="headerController"> 
		    <h1><a href="#/dashboard"><img class="" src="images/logo2.png"></a></h1>
		    <ul class="nav-right pull-right list-inline">
               <li class="dropdown nav-profile">
                   <a data-toggle="dropdown" class="dropdown-toggle rightheader" href="" aria-expanded="true">
  				   	   <figure class="user-image"><img src="images/{{user.gender}}-user.png"/></figure>
				   	   <label>{{user.firstName}}, {{user.lastName}}<i class="fa fa-angle-down"></i></label>
                   </a>
                   <ul role="menu" class="dropdown-menu animated littleFadeInRight">
                       <li>
                           <a href="#/profile"> 
                               <i class="fa fa-user"></i>Profile
                           </a>
                       </li>
                       <li>
                           <a href="logout">
                               <i class="fa fa-sign-out"></i>Logout
                           </a>
                       </li>
                       <li class="divider"></li>
                         <li>
                           <a href="#">
                               <i class="fa fa-cog"></i>Theme Settings
                           </a>
                       </li>
                       <ul class="colortheme">
	                        <li class="lime themecolor"></li>
	                        <li class="black themecolor"></li>
	                        <li class="bright-red themecolor"></li>
	                          
	                        <li class="dark-blue themecolor"></li>
	                        <li class="cyan themecolor"></li>
	                        <li class="scorpion themecolor"></li>
                       </ul>
                   </ul>
               </li>
           </ul>
		</div>
		<div class="divContainer" id="divContainer">
			<div id="overlay" ajax-loading><img id="loading" src="images/gears.gif"></div>
			<ng-view></ng-view>
		</div>
	</div>
</body>
<script src="js/lib/jquery.js"></script>
<script src="js/lib/jquery-ui.js"></script>
<script src="js/lib/angular.js"></script>
<script src="js/lib/bootstrap.js"></script>
<script src="js/lib/angular-route.js"></script>
<script src="js/lib/angular-resource.js"></script>
<script src="js/lib/jquery.dataTables.js"></script>
<script src="js/lib/angular-datatables.js"></script>
<script src="js/lib/angular-datatables.util.js"></script>
<script src="js/lib/angular-datatables.options.js"></script>
<script src="js/lib/angular-datatables.instances.js"></script>
<script src="js/lib/angular-datatables.factory.js"></script>
<script src="js/lib/angular-datatables.renderer.js"></script>
<script src="js/lib/angular-datatables.directive.js"></script>
<script src="js/lib/jquery.mmenu.all.min.js"></script>
<script src="js/lib/moment.min.js"></script>
<script src="js/lib/angular-bootstrap.js"></script>
<script src="js/lib/angular-animate.js"></script>
<script src="js/lib/angular-messages.js"></script>
<script src="js/lib/angular-datatables.bootstrap.options.js"></script>
<script src="js/lib/angular-datatables.bootstrap.tabletools.js"></script>
<script src="js/lib/angular-datatables.bootstrap.colvis.js"></script>
<script src="js/lib/datatables.columnfilter.js"></script>
<script src="js/lib/angular-datatables.columnfilter.js"></script>
<script src="js/lib/angular-datatables.responsive.js"></script>
<script src="js/lib/angular-datatables.buttons.js"></script>
<script src="js/lib/datatables.buttons.js"></script>
<script src="js/lib/dataTables.tableTools.js"></script>
<script src="js/lib/angular-datatables.buttons.html5.js"></script>
<script src="js/lib/angular-datatables.buttons.print.js"></script>
<script src="js/lib/angular-datatables.buttons.colVis.js"></script>
<script src="js/lib/angular-datatables.buttons.flash.js"></script>
<script src="js/lib/bootstrap-calendar.min.js"></script>
<script src="js/lib/angular-bootstrap-calendar-tpls.js"></script>
<script src="js/lib/angular-sanitize.js"></script>
<script src="js/lib/ui-bootstrap-tpls-1.3.2.js"></script>
<script src="js/lib/select.js"></script>
<script src="js/app/app.js"></script>
<script src="js/app/common.js"></script>
<script src="js/constants/constants.js"></script>
<script src="js/constants/url.js"></script>
<script src="js/directive/perfdirective.js"></script>
<script src="js/controller/headerController.js"></script>
<script src="js/controller/menuController.js"></script>
<script src="js/controller/datatableController.js"></script>
<script src="js/controller/profileController.js"></script>
<script src="js/service/profileServices.js"></script>
<script src="js/controller/employeeController.js"></script>
<script src="js/service/employeeServices.js"></script>
<script src="js/controller/dashboardController.js"></script>
<script src="js/service/dashboardServices.js"></script>
<script src="js/controller/designationController.js"></script>
<script src="js/service/designationServices.js"></script>
<script src="js/controller/projectController.js"></script>
<script src="js/service/projectServices.js"></script>
<script src="js/controller/projectMembersController.js"></script>
<script src="js/service/projectMembersServices.js"></script>
<script src="js/controller/importPtoController.js"></script>
<script src="js/service/importPtoServices.js"></script>
<script src="js/controller/leaveController.js"></script>
<script src="js/service/leaveServices.js"></script>
<script src="js/controller/reportsJobtitleController.js"></script>
<script src="js/service/reportsJobtitleServices.js"></script>
<script src="js/controller/notificationController.js"></script>
<script src="js/service/notificationServices.js"></script>
<script src="js/controller/leaveReportsController.js"></script>
<!-- <script src="dist/js/dev/lib.js"></script> -->
<!-- <script src="dist/js/dev/perf.js"></script> -->
</html>