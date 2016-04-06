angular.module('employee.services', []).
  factory('employeeAPIservice', function($http) {
	var employeeAPI = {};
	employeeAPI.getEmployeesDetails = function(empId) {
        return $http({
          method: 'get', 
          url: perfUrl['loadEmployeeById']+empId
        });
    };
    return employeeAPI; 
});