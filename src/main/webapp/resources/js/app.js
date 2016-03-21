var mainApp = angular.module("perficientHr", 
		['profile.controller','profile.services',
		 'employee.controller','employee.services',
		 'dashboard.controller','dashboard.services',
		 'ngRoute', 'datatables', 'ngDialog']);
 
mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'html/dashboard.html',
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
        .otherwise({
            redirectTo: '/home'
        });
});