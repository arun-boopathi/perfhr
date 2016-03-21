angular.module('employee.services', []).
  factory('employeeAPIservice', function($http) {
	var employeeAPI = {};
	employeeAPI.getEmployeesDetails = function(empId) {
        return $http({
          method: 'get', 
          url: 'employee/loadEmployeeById?employeeId='+empId
        });
    };
    
    return employeeAPI; 
});