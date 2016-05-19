var vm;
var rowIndex;
var data;
var scope;
/* Employee controller */
mainApp.controller('employeeController', function($scope, employeeAPIservice, designationAPIservice) {
	scope = $scope;
	
	designationAPIservice.getDesignationDetails().success(function (response) {
	   $scope.designations = response;
	});
	
	$scope.addEmployee = function(){
		$scope.data = '';
		$('#employee_id').removeAttr('disabled');
		$('#employeeForm').modal();
	};
	
	$scope.submit = function() {
        if ($scope.data.pk) {
           employeeAPIservice.updateEmployee($scope.data).success(function (response) {
          	   vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
          	   $scope.data.msg = 'Employee updated successfully.';         	
           }).error(function(error){
        	   $scope.data.msg = 'An Error Occured. Unable to update Employee.';
           });
        } else {
           employeeAPIservice.addEmployee($scope.data).success(function (response) {
        	   $scope.data.msg = 'Employee saved successfully.';
  			   vm.dtInstance.reloadData();
  	       }).error(function(error){
  	    	   $scope.data.msg = 'An Error Occured. Unable to save Employee.';
  	       });
        }
    };
});

mainApp.controller('EmployeeTableCtrl', EmployeeTableCtrl);

function EmployeeTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('employeeId').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First Name'),
        DTColumnBuilder.newColumn('lastName').withTitle('Last Name').withClass('none'),
        DTColumnBuilder.newColumn('email').withTitle('Email'),
        DTColumnBuilder.newColumn('designations.designation').withTitle('Designation')
    ];
    var paramObj = {
    		"vm" : vm,
    		"scope" : $scope,
    		"compile" : $compile,
    		"DtOptionsBuilder" : DTOptionsBuilder,
    		"DTColumnBuilder" : DTColumnBuilder,
    		"service" : employeeAPIservice,
    		'loadListUrl' : perfUrl['loadAllEmployee'],
    		'editFormId' : 'employeeForm',
    		'sortCol': '1'
    };
    perfDatatable.loadTable.init(paramObj);
}