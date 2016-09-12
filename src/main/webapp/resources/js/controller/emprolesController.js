var eR, columnBuilder, brIndex, data, scope, paramObj;
/*Roles Management by employees Controller*/
mainApp.controller('empRolesController', function($scope, $timeout, rolesAPIservice, employeeAPIservice, emprolesAPIservice) {
	rolesAPIservice.getRolesDetails().success(function (response) {
        $scope.roles = response.entity;
    });
	$scope.data = {};
	$scope.data.employee = [];
	$scope.employeeList = [];
	$scope.empList = [];
	employeeAPIservice.loadAllEmployees().success(function (response) {
		$scope.empList = response.entity;
        $scope.employeeList = $scope.empList;
//        $scope.data.employee = $scope.empList;
    });
	
    $scope.manageRoles = function(){
    	console.log('data ', $scope.data);
        emprolesAPIservice.saveEmpRoles($scope.data).success(function () {
            $scope.closeModal();
            $scope.msg="Designation Saved Successfully!";
        });
    };
    
    $scope.onRoleChange = function(){
    	$timeout(function() {
    		angular.element(document.getElementsByClassName('btn-link')[1]).triggerHandler('click');
    	});
    	$scope.data.employee = [];
    	emprolesAPIservice.loadEmpByRoles($scope.data.role.pk).success(function (response) {
    		$.each(response.entity, function(i, val){
    			$scope.data.employee.push(val.employee);
    		});
    		eR.dtInstance.DataTable.clear().draw();
    		eR.dtInstance.DataTable.rows.add(response.entity).draw();
        });
    };
});

mainApp.controller('employeeRoleTableCtrl', employeeRoleTableCtrl);

function employeeRoleTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, emprolesAPIservice) {
	eR = this;
	eR.dtColumns = [
        DTColumnBuilder.newColumn('roleId').withTitle('Role Name').renderWith(function(data, type, full) {
            return data.roleName;
        }),
        DTColumnBuilder.newColumn('employee').withTitle('Employee').renderWith(function(data, type, full) {
            return data.firstName+' '+data.lastName;
        })
    ];
    var paramObj = {
        "vm" : eR,
        "scope" : $scope,
        "compile" : $compile,
        "DtOptionsBuilder" : DTOptionsBuilder,
        "DTColumnBuilder" : DTColumnBuilder,
        "service" : emprolesAPIservice,
        'sortCol': 1,
        'sEmptyTable' : 'Loading..'
    };
    perfDatatable.loadTable.init(paramObj);
}