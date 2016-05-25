angular.module('leave.services', []).
  factory('leaveAPIservice', function($http) {
	var leaveAPI = {};
	leaveAPI.loadAllLeaves = function(leaveType) {
        return $http({
          method: 'get', 
          url: perfUrl['loadAllLeaves']+leaveType
        });
    };
    leaveAPI.applyLeave = function(wfh) {
    	return $http({
          method: 'post', 
          data : wfh,
          url: perfUrl['applyLeave']
        });
    };
    leaveAPI.updateLeave = function(wfh) {
        return $http({
          method: 'put', 
          data : wfh,
          url: perfUrl['updateLeave']
        });
    };
    leaveAPI.deleteLeave = function(wfh) {
        return $http({
          method: 'put', 
          data : wfh,
          url: perfUrl['deleteLeave']
        });
    };
    leaveAPI.loadById = function(id){
      return $http({
        method: 'get', 
        url: perfUrl['loadLeaveById']+id
      });
    };
    leaveAPI.loadMyLeaves = function(empId){
	  return $http({
	    method: 'get', 
	    url: perfUrl['loadMyLeaves']+empId
	  });
    };
    
    return leaveAPI; 
});