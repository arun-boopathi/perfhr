angular.module('perficientHr', [
  'perficientHr.controllers',
  'perficientHr.services',
  'ngRoute'
]).config(['$routeProvider', function($routeProvider) {
	  $routeProvider.
		when("/drivers", {templateUrl: "app/partials/drivers.html", controller: "driversController"}).
		when("/drivers/:id", {templateUrl: "app/partials/driver.html", controller: "driverController"}).
		otherwise({redirectTo: '/drivers'});
}]);