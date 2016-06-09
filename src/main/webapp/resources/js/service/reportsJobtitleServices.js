angular.module('reportJobtitle.services', []).
  factory('reportJobtitleAPIservice', function($http) {
	var reportJobtitleAPI = {};
	reportJobtitleAPI.getDesignationDetails = function() {
        return $http({
          method: 'get', 
          url: perfUrl['loadDesignations']
        });
    };
    return reportJobtitleAPI; 
});