angular.module('designation.services', []).
  factory('designationAPIservice', function($http) {
	var designationAPI = {};
	designationAPI.getDesignationDetails = function() {
        return $http({
          method: 'get', 
          url: perfUrl['loadRoles']
        });
    };
    return designationAPI; 
});