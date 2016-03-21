var employee;
/* Employee controller */
angular.module('employee.controller', ['showcase.bindAngularDirective']).
    controller('employeeController', function($scope, employeeAPIservice) {
	console.log('in employeeController employeeAPIservice: ',employeeAPIservice);
}).controller('formCtrl', ['$scope', function($scope) {
	$scope.list = [];
    $scope.submit = function() {
      if ($scope.firstName) {
        console.log('val: ', $scope.firstName);
      }
    };
}]);

angular.module('showcase.bindAngularDirective', ['datatables', 'ngDialog']).controller('BindAngularDirectiveCtrl', 
		function($scope, $compile, DTOptionsBuilder, DTColumnBuilder, ngDialog, employeeAPIservice){
	var vm = this;
    vm.message = '';
    vm.edit = edit;
    vm.delete = deleteRow;
    vm.dtInstance = {};
    vm.employees = {};
    vm.dtOptions = DTOptionsBuilder.fromSource('employee/loadAllEmployee')
        .withDisplayLength(7)
        .withDOM('pitrfl')
        .withPaginationType('full_numbers')
        .withOption('createdRow', createdRow);
    vm.dtColumns = [
        DTColumnBuilder.newColumn('employee_id').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First name'),
        DTColumnBuilder.newColumn('lastName').withTitle('Last name'),
        DTColumnBuilder.newColumn('email').withTitle('Email'),
//                    DTColumnBuilder.newColumn('address').withTitle('Address'),
        DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable()
        	.renderWith(actionsHtml)
    ];
    console.log('in module employeeAPIservice: ',employeeAPIservice);
    function edit(employee) {
        //vm.message = 'You are trying to edit the row: ' + JSON.stringify(employee);
        // Edit some data and call server to make changes...
        // Then reload the data so that DT is refreshed
        //vm.dtInstance.reloadData();
        var employeeId = employee.pk;
        console.log('employeeId: ',employeeId);
        ngDialog.open({
            template: 'html/employee.html',
            showClose : true,
            closeByDocument : false,
            className: 'ngdialog-theme-default custom-width-800',
            controller: ['$scope','employeeAPIservice', function($scope, employeeAPIservice) {
                // controller logic
            	$scope.employee = null;
            	console.log('employeeAPIservice: ',employeeAPIservice);
            	employeeAPIservice.getEmployeesDetails(employeeId).success(function (response) {
            		$scope.employee = response;
      	        });
            }]
        });
               
    }
    function deleteRow(employee) {
        vm.message = 'You are trying to remove the row: ' + JSON.stringify(employee);
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
        return '<button class="btn btn-info" ng-click="showCase.edit(showCase.employees[' + data.pk + '])">' +
            '   <i class="fa fa-edit"></i>' +
            '</button>&nbsp;' +
            '<button class="btn btn-danger" ng-click="showCase.delete(showCase.employees[' + data.pk + '])" )"="">' +
            '   <i class="fa fa-trash-o"></i>' +
            '</button>';
    };
});
