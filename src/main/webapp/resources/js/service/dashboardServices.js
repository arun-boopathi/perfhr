angular.module('dashboard.services', []).factory('dashboardAPIservice', function($http) {
    var dashboardAPI = {};
    dashboardAPI.getVersion = function() {
        return $http({
          ContentType: 'application/text',
          method: 'get',
          url: perfUrl['getVersion']
        });
    };
    return dashboardAPI;
});