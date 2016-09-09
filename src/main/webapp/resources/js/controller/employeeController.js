var em, data, scope;
/* Employee controller */
mainApp.controller('employeeController', function($scope, $controller, employeeAPIservice) {
    scope = $scope;
    employeeAPIservice.loadEmployees().success(function (response) {
        $scope.employees = response.entity;
        em.dtInstance.DataTable.clear().draw();
        em.dtInstance.DataTable.rows.add($scope.employees).draw();
    });

    $scope.addEmployee = function(){
        $scope.data = '';
        $('#employeeForm').modal();
    };

    $('#employeeForm').on('hidden.bs.modal', function () {
        $scope.$parent.data = '';
    });

    $scope.save = function(){
        employeeAPIservice.addEmployee($scope.data).success(function (){
            $scope.msg = 'Employee saved successfully.';
            em.dtInstance.reloadData();
        }).error(function(){
            $scope.msg = 'An Error Occured. Unable to save Employee.';
        });
    };
    $scope.update = function(){
        employeeAPIservice.updateEmployee($scope.data).success(function (){
            em.dtInstance.dataTable.fnUpdate($scope.data, em.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.msg = 'Employee updated successfully.';
        }).error(function(){
            $scope.msg = 'An Error Occured. Unable to update Employee.';
        });
    };

    angular.extend(this, $controller('empProfileController', {
        $scope: $scope
    }));
});

mainApp.controller('employeeTableCtrl', employeeTableCtrl);

function employeeTableCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder, employeeAPIservice) {
    em = this;
    em.dtColumns = [
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
        "vm" : em,
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