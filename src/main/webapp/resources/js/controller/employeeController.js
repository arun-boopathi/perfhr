var vm;
var rowIndex;
var data;
var scope;
/* Employee controller */
angular.module('employee.controller', ['showcase.bindAngularDirective'])
.controller('employeeController', function($scope, employeeAPIservice) {
	
}).controller('AddEmployeeCtrl', function($scope, employeeAPIservice) {
	$scope.finishLoading = function() {
		$('#employee_id').removeAttr('disabled');
		$(".alert-info").addClass('hidden');
	};
	$scope.submit = function() {
		if ($scope.data) {
			$('.alert-info').removeClass('hidden');
		    employeeAPIservice.addEmployee($scope.data).success(function (response) {
		      $scope.msg = 'Employee saved successfully.';
			  vm.dtInstance.reloadData();
	        }).error(function(error){
	       	  $scope.msg = 'An Error Occured. Unable to save Employee.';
	        });
		}
    };
}).controller('UpdateEmployeeCtrl', function($scope, employeeAPIservice) {
	scope = $scope;
	$scope.submit = function() {
        if ($scope.data) {
           $('.alert-info').removeClass('hidden');
           employeeAPIservice.updateEmployee($scope.data).success(function (response) {
          	   vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
           	   $scope.msg = 'Employee updated successfully.';         	
           }).error(function(error){
           	   $scope.msg = 'An Error Occured. Unable to update Employee.';
           });
        }
    };
});

angular.module('showcase.bindAngularDirective', ['datatables']).controller('EmployeeTableCtrl', EmployeeTableCtrl);

function EmployeeTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('employeeId').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First name'),
        DTColumnBuilder.newColumn('lastName').withTitle('Last name'),
        DTColumnBuilder.newColumn('email').withTitle('Email'),
        DTColumnBuilder.newColumn('designation').withTitle('Designation')
    ];
    perfDatatable.loadTable.init(vm, $scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice, perfUrl['loadAllEmployee']);
}