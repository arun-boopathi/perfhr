var lr, data, scope;
mainApp.controller('wfhReportController', function($scope, $controller) {
    $scope.title="WFH";
    $scope.requestType="WFH";
    angular.extend(this, $controller('leaveReportController', {
        $scope: $scope
    }));
});

mainApp.controller('ptoReportController', function($scope, $controller) {
    $scope.title="PTO";
    $scope.requestType="PTO";
    $scope.isPto = true;
    angular.extend(this, $controller('leaveReportController', {
        $scope: $scope
    }));
});

mainApp.controller('leaveReportController', function($scope, moment, leaveAPIservice, employeeAPIservice) {
    scope = $scope;
    $scope.data = {};
    $scope.stDate = {
        opened: false
    };
    $scope.endDate = {
        opened: false
    };
    $scope.reportStartDate = function() {
        $scope.stDate.opened = true;
    };
    $scope.reportEndDate = function() {
        $scope.endDate.opened = true;
    };
    $scope.data.requestType = $scope.requestType;
    $scope.searchLeave = function(){
        leaveAPIservice.loadLeaveReport($scope.data).success(function(response) {
            lr.dtInstance.DataTable.clear().draw();
            lr.dtInstance.DataTable.rows.add(response).draw();
        });
    };

    employeeAPIservice.loadAllEmployees().success(function(response) {
        $scope.employees = response;
    });
});

mainApp.controller('leaveReportControllerTable', leaveReportControllerTable);

function leaveReportControllerTable($scope, $compile, DTOptionsBuilder, DTColumnBuilder, leaveAPIservice) {
    lr = this;
    lr.dtColumns = [
        DTColumnBuilder.newColumn('title').withTitle('Title'),
        DTColumnBuilder.newColumn('startsAt').withTitle('Starts').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        }),
        DTColumnBuilder.newColumn('endsAt').withTitle('Ends').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        }),
        DTColumnBuilder.newColumn('hours').withTitle('Days').renderWith(function(data) {
            return (data/8);
        })
     ];
     var paramObj = {
         "vm" : lr,
         "scope" : $scope,
         "compile" : $compile,
         "DtOptionsBuilder" : DTOptionsBuilder,
         "DTColumnBuilder" : DTColumnBuilder,
         "service" : leaveAPIservice,
         'editFormId' : 'leaveModal',
         'actions': false
     };
     perfDatatable.loadTable.init(paramObj);
}