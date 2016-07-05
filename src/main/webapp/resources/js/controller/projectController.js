var pc, data, scope;
/* Project controller */
mainApp.controller('projectController', function($scope, projectAPIservice) {
    scope = $scope;
    $scope.stDate = {
        opened: false
    };
    $scope.endDate = {
        opened: false
    };
    $scope.prStartDate = function() {
        $scope.stDate.opened = true;
    };
    $scope.prEndDate = function() {
        $scope.endDate.opened = true;
    };

    $scope.save = function(){
        projectAPIservice.addProject($scope.data).success(function() {
            pc.dtInstance.reloadData();
            $scope.msg="Project Saved Successfully!";
        });
    };

    $scope.addProject = function(){
        $scope.data = '';
        $('#projectForm').modal();
    };

    $scope.update = function(){
        projectAPIservice.updateProject($scope.data).success(function() {
            pc.dtInstance.dataTable.fnUpdate($scope.data, pc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.msg="Project Updated Successfully!";
        });
    };

    $scope.deleteProject = function(){
        projectAPIservice.deleteProject($scope.data).success(function() {
            pc.dtInstance.reloadData();
            $('#deleteProject').modal('hide');
            $scope.msg="Project Deleted Successfully!";
        });
    };
});

mainApp.controller('projectsTableController', projectsTableController);

function projectsTableController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectAPIservice) {
    pc = this;
    pc.dtColumns = [
        DTColumnBuilder.newColumn('projectName').withTitle('Projects'),
        DTColumnBuilder.newColumn('startDate').withTitle('Start Date').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY");
        }),
        DTColumnBuilder.newColumn('endDate').withTitle('End Date').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY");
        })
    ];
    var paramObj = {
            "vm" : pc,
            "scope" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : projectAPIservice,
            'loadListUrl' : perfUrl['loadProjects'],
            'editFormId' : 'projectForm',
            'deleteFormId' : 'deleteProject'
    };
    perfDatatable.loadTable.init(paramObj);
}