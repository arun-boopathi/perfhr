angular.module('leave.services', []).
  factory('leaveAPIservice', function($http) {
	var leaveAPI = {};
	leaveAPI.loadAllLeaves = function(leaveType, calYear) {
        return $http({
          method: 'get', 
          url: perfUrl['loadAllLeaves'].replace('{leaveType}', leaveType).replace('{calYear}', calYear)
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
    leaveAPI.loadMyLeaves = function(leaveType, calYear){
	  return $http({
	    method: 'get', 
	    url: perfUrl['loadMyLeaves'].replace('{leaveType}', leaveType).replace('{calYear}', calYear)
	  });
    };
    
    return leaveAPI; 
});