var mainApp = angular.module("perficientHr", 
		['profile.controller','profile.services','employee.controller',		 
		 'employee.services','ngRoute', 'datatables', 'ngDialog']);
 
mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'html/dashboard.html'
        })
        .when('/employees', {
            templateUrl: 'html/employees.html',
            controller: 'employeeController'
        })
        .otherwise({
            redirectTo: '/home'
        });
});