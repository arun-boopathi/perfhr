var mainApp = angular.module("perficientHr", ['profile.controller','profile.services','ngRoute']);
 
mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'html/profile.html',
            controller: 'profileController'
        })
        .when('/employee', {
            templateUrl: 'html/employee.html',
            controller: 'employeeController'
        })
        .otherwise({
            redirectTo: '/home'
        });
});
 
