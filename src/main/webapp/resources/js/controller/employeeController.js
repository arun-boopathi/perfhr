var vm, rowIndex, data, scope;
/* Employee controller */
mainApp.controller('employeeController', function($scope, $controller, employeeAPIservice, designationAPIservice, user) {
	scope = $scope;
	console.log('logged user', user.loggedUser);
	employeeAPIservice.loadAllEmployees().success(function (response) {
		$scope.employees = response;
		vm.dtInstance.DataTable.clear().draw();
    	vm.dtInstance.DataTable.rows.add($scope.employees).draw();
	});
	
	$scope.addEmployee = function(){
		$scope.data = '';
		$('#employeeForm').modal();
	};
	
	$('#employeeForm').on('hidden.bs.modal', function (e) {
		console.log('scope ', $scope);
		$scope.$parent.data = '';
	});
	
	$scope.submit = function() {
		console.log('data ', $scope.data);
        if ($scope.data.pk) {
//           $scope.data.designations = $scope.data.designations.pk;
           employeeAPIservice.updateEmployee($scope.data).success(function (response) {
          	   vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
          	   $scope.msg = 'Employee updated successfully.';         	
           }).error(function(error){
        	   $scope.msg = 'An Error Occured. Unable to update Employee.';
           });
        } else {
           employeeAPIservice.addEmployee($scope.data).success(function (response) {
        	   $scope.msg = 'Employee saved successfully.';
  			   vm.dtInstance.reloadData();
  	       }).error(function(error){
  	    	   $scope.msg = 'An Error Occured. Unable to save Employee.';
  	       });
        }
    };
    
    angular.extend(this, $controller('empProfileController', {
        $scope: $scope
    }));
});

mainApp.controller('EmployeeTableCtrl', EmployeeTableCtrl);

function EmployeeTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('employeeId').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First Name').renderWith(function(data, type, full) {
            return full.firstName+' '+full.lastName;
        }),
        DTColumnBuilder.newColumn('superviserFirstName').withTitle('Supervisor').notVisible().renderWith(function(data, type, full) {
            return full.superviserFirstName+' '+full.superviserLastName;
        }),
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
    		'editFormId' : 'employeeForm',
    		'sortCol': 1,
    		'sEmptyTable' : 'Loading..'
    };
    perfDatatable.loadTable.init(paramObj);
}