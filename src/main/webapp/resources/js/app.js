var mainApp = angular.module("perficientHr", 
		['profile.controller','profile.services',
		 'employee.controller','employee.services',
		 'dashboard.controller','dashboard.services',
		 'pto.controller',
		 'designation.controller','designation.services',
		 'role.controller','role.services',
		 'project.controller','project.services',
		 'ngRoute', 'datatables']);

var rand = Math.floor(Math.random()*(3-1+1)+1);

mainApp.config(function($routeProvider) {
	$routeProvider
	    .when('/home', {
	        templateUrl: 'html/dashboard'+rand+'.html',
	        controller: 'dashboardController'
	    })
	    .when('/employees', {
	        templateUrl: 'html/employees.html',
	        controller: 'employeeController'
	    })
	    .when('/profile', {
	        templateUrl: 'html/profile.html',
	        controller: 'profileController'
	    })
	    .when('/pto', {
	        templateUrl: 'html/pto.html',
	        controller: 'ptoController'
	    })
	    .when('/candidate', {
	        templateUrl: 'html/candidate.html'
	    })
	    .when('/designations', {
	        templateUrl: 'html/designations.html',
	        controller: 'designationController'
	    })
	    .when('/projects', {
	        templateUrl: 'html/projects.html',
	        controller: 'projectController'
	    })
	    .otherwise({
	        redirectTo: '/home'
	    });
});