angular.module('perficientHr', [
  'perficientHr.controllers',
  'perficientHr.services',
  'ngRoute'
]).config(['$routeProvider', function($routeProvider) {
	  $routeProvider.
		when("/", {templateUrl: "html/employee.html", controller: "employeeController"}).
		otherwise({redirectTo: '/employee'});
}]);