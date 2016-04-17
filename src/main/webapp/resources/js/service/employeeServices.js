angular.module('employee.services', []).
  factory('employeeAPIservice', function($http) {
	var employeeAPI = {};
	employeeAPI.loadById = function(empId) {
        return $http({
          method: 'get', 
          url: perfUrl['loadEmployeeById']+empId
        });
    };
    employeeAPI.addEmployee = function(employee) {
        return $http({
          method: 'post', 
          data : employee,
          url: perfUrl['addEmployee']
        });
    };
    employeeAPI.updateEmployee = function(employee) {
        return $http({
          method: 'put', 
          data : employee,
          url: perfUrl['updateEmployee']
        });
    };
    return employeeAPI; 
});

function doHttp($http){
	console.log('in http');
	return $http;
}