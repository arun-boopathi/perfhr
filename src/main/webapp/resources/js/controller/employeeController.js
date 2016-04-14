var vm;
var rowIndex;
var data;
var scope;
/* Employee controller */
angular.module('employee.controller', ['showcase.bindAngularDirective'])
.controller('employeeController', function($scope, employeeAPIservice) {
	
}).controller('updateEmployeeCtrl', function($scope, employeeAPIservice) {
	scope = $scope;
	$scope.submit = function() {
        if ($scope.data) {
           employeeAPIservice.updateEmployee($scope.data).success(function (response) {
          	 vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
           	 $scope.msg = 'Employee updated successfully.';         	
           }).error(function(error){
           	 $scope.msg = 'An Error Occured. Unable to update Employee.';
           });
           $("#updateEmployeeMsg").toggleClass("hidden");
        };
    };
});

angular.module('showcase.bindAngularDirective', ['datatables']).controller('EmployeeTableCtrl', EmployeeTableCtrl);
function EmployeeTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice) {
    vm = this;
    vm.dtColumns = [
    		        DTColumnBuilder.newColumn('employee_id').withTitle('ID'),
    		        DTColumnBuilder.newColumn('firstName').withTitle('First name'),
    		        DTColumnBuilder.newColumn('lastName').withTitle('Last name'),
    		        DTColumnBuilder.newColumn('email').withTitle('Email')
    		    ];
    perfDatatable.loadTable.init(vm, $scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice, perfUrl['loadAllEmployee']);
}

/* hidden.bs.modal event example */
$('#updateForm').on('hidden', function () {
    window.alert('hidden event fired!');
});