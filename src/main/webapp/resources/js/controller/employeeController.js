var employee;
var scope;
var vm
/* Employee controller */
angular.module('employee.controller', ['showcase.bindAngularDirective'])
.controller('employeeController', function($scope, employeeAPIservice) {
	
}).controller('updateEmployeeCtrl', function($scope, employeeAPIservice) {
	scope = $scope;
    $scope.submit = function() {
      if ($scope.employee) {
         employeeAPIservice.updateEmployee($scope.employee).success(function (response) {
         	vm.dtInstance.reloadData();
         	$scope.msg = 'Employee updated successfully.';
         }).error(function(error){
         	$scope.msg = 'An Error Occured. Unable to update Employee.';
         });
         $("#updateEmployeeMsg").toggleClass("hidden");
      }
    };
});

angular.module('showcase.bindAngularDirective', ['datatables', 'ngDialog']).controller('BindAngularDirectiveCtrl', 
		function($scope, $compile, DTOptionsBuilder, DTColumnBuilder, ngDialog, employeeAPIservice){
	vm = this;
    vm.message = '';
    vm.edit = edit;
    vm.delete = deleteRow;
    vm.dtInstance = {};
    vm.employees = {};
    vm.dtOptions = DTOptionsBuilder.fromSource(perfUrl['loadAllEmployee'])
        .withDisplayLength(7)
        .withDOM('pitrfl')
        .withPaginationType('full_numbers')
        .withOption('createdRow', createdRow).withOption('aaSorting', [1, 'asc']);
    
    vm.dtColumns = [
        DTColumnBuilder.newColumn('employee_id').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First name'),
        DTColumnBuilder.newColumn('lastName').withTitle('Last name'),
        DTColumnBuilder.newColumn('email').withTitle('Email'),
//      DTColumnBuilder.newColumn('address').withTitle('Address'),
        DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable()
        	.renderWith(actionsHtml)
    ];
    console.log('in module employeeAPIservice: ',employeeAPIservice);
    DTOptionsBuilder.newOptions().withOption('aaSorting', [2, 'asc']);
    function edit(employee) {
        //vm.message = 'You are trying to edit the row: ' + JSON.stringify(employee);
        // Edit some data and call server to make changes...
        // Then reload the data so that DT is refreshed
        //vm.dtInstance.reloadData();
        var employeeId = employee.pk;
        
        employeeAPIservice.getEmployeesDetails(employeeId).success(function (response) {
    		scope.employee = response;
    		if(!$("#updateEmployeeMsg").hasClass('hidden'))
    			$("#updateEmployeeMsg").toggleClass('hidden');
        });
    }
    
    function deleteRow(employee) {
    	console.log('Delete employee ', employee.firstName);
        vm.message = 'You are trying to remove Employee:  ' + employee.lastName+', '+employee.firstName ;
        // Delete some data and call server to make changes...
        // Then reload the data so that DT is refreshed
        vm.dtInstance.reloadData();
    }
    function createdRow(row, data, dataIndex) {
        // Recompiling so we can bind Angular directive to the DT
        $compile(angular.element(row).contents())($scope);
    }
    function actionsHtml(data, type, full, meta) {
        vm.employees[data.pk] = data;
        return '<button class="btn btn-warning" data-toggle="modal" data-target="#updateEmployee" ng-click="showCase.edit(showCase.employees[' + data.pk + '])">' +
            '   <i class="fa fa-edit"></i>' +
            '</button>&nbsp;' +
            '<button class="btn btn-danger" ng-click="showCase.delete(showCase.employees[' + data.pk + '])" )"="">' +
            '   <i class="fa fa-trash-o"></i>' +
            '</button>';
    };
});
