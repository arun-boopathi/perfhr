angular.module('wfh.services', []).
  factory('wfhAPIservice', function($http) {
	var wfhAPI = {};
	wfhAPI.loadAllWfh = function() {
        return $http({
          method: 'get', 
          url: perfUrl['loadAllWfh']
        });
    };
    wfhAPI.applyWfh = function(wfh) {
    	return $http({
          method: 'post', 
          data : wfh,
          url: perfUrl['applyWfh']
        });
    };
    wfhAPI.updateWfh = function(wfh) {
        return $http({
          method: 'put', 
          data : wfh,
          url: perfUrl['updateWfh']
        });
    };
    wfhAPI.deleteWfh = function(wfh) {
        return $http({
          method: 'put', 
          data : wfh,
          url: perfUrl['deleteWfh']
        });
    };
    return wfhAPI; 
});