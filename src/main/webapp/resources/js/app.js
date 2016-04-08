var mainApp = angular.module("perficientHr", 
		['profile.controller','profile.services',
		 'employee.controller','employee.services',
		 'dashboard.controller','dashboard.services',
		 'pto.controller',
		 'ngRoute', 'datatables', 'ngDialog']);
 
mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'html/dashboard1.html',
            controller: 'approvalReqCtrl'
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
            templateUrl: 'html/candidate.html',
        })
        .otherwise({
            redirectTo: '/home'
        });
});