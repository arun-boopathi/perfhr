angular.module('dashboard.services', []).
  factory('dashboardAPIservice', function($http) {
	var dashboardAPI = {};
	/*dashboardAPI.getDashboardDetails = function() {
        return $http({
          method: 'get', 
          url: 'dashboard/'
        });
    };*/
    return dashboardAPI; 
});