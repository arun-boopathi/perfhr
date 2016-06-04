angular.module('leave.services', []).
  factory('leaveAPIservice', function($http) {
	var leaveAPI = {};
	leaveAPI.loadAllLeaves = function(leaveType) {
        return $http({
          method: 'get', 
          url: perfUrl['loadAllLeaves']+leaveType
        });
    };
    leaveAPI.applyLeave = function(data) {
    	return $http({
          method: 'post', 
          data : data,
          url: perfUrl['applyLeave']
        });
    };
    leaveAPI.updateLeave = function(data) {
        return $http({
          method: 'put', 
          data : data,
          url: perfUrl['updateLeave']
        });
    };
    leaveAPI.deleteLeave = function(data) {
        return $http({
          method: 'put', 
          data : data,
          url: perfUrl['deleteLeave']
        });
    };
    leaveAPI.loadById = function(id){
      return $http({
        method: 'get', 
        url: perfUrl['loadLeaveById']+id
      });
    };
    leaveAPI.loadMyLeaves = function(leaveType){
	  return $http({
	    method: 'get', 
	    url: perfUrl['loadMyLeaves']+leaveType
	  });
    };
    
    return leaveAPI; 
});