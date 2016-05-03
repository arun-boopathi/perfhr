angular.module('designation.services', []).
  factory('designationAPIservice', function($http) {
	var designationAPI = {};
	designationAPI.getDesignationDetails = function() {
        return $http({
          method: 'get', 
          url: perfUrl['loadRoles']
        });
    };
    designationAPI.addDesignation = function(designation) {
    	        return $http({
          method: 'post', 
          data : designation,
          url: perfUrl['addDesignation']
        });
    };
    designationAPI.updateDesignation = function(designation) {
        return $http({
          method: 'put', 
          data : designation,
          url: perfUrl['updateDesignation']
        });
    };
    return designationAPI; 
});