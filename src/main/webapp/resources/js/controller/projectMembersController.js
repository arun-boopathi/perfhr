var pm, data, scope;
/* Project controller */
mainApp.controller('projectMembersController', function($scope, projectMemberAPIservice, projectAPIservice, employeeAPIservice) {
    scope = $scope;

    $scope.pmStDate = {
        opened: false
    };
    $scope.pmEndDate = {
        opened: false
    };
    $scope.pmStartDate = function() {
        $scope.pmStDate.opened = true;
    };
    $scope.pmEndDate = function() {
        $scope.pmEndDate.opened = true;
    };

    $scope.addProjectMembers = function(){
        $scope.data = '';
        $('#projectMembersForm').modal();
    };

    $scope.save = function(){
        projectMemberAPIservice.saveProjectMember($scope.data).success(function() {
            pm.dtInstance.reloadData();
            $scope.msg="Project Saved Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during save!";
        });
    };

    $scope.update = function(){
        projectMemberAPIservice.updateProjectMember($scope.data).success(function() {
            pm.dtInstance.dataTable.fnUpdate($scope.data, pm.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.msg="Project Updated Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during update!";
        });
    };

    $scope.deleteProjectMember = function(){
        projectMemberAPIservice.deleteProjectMember($scope.data).success(function() {
            pm.dtInstance.reloadData();
            $('#deleteProjectMember').modal('hide');
            $scope.msg="Project Deleted Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during delete!";
        });
    };

    projectAPIservice.loadProjects().success(function(response) {
        $scope.projects = response.entity;
    });

    employeeAPIservice.loadAllEmployees().success(function(response) {
        $scope.employees = response.entity;
    });
});

mainApp.controller('ProjectsUserController', function($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectMemberAPIservice){
    pm = this;
    pm.dtColumns = [
        DTColumnBuilder.newColumn('projectId.projectName').withTitle('Project Name'),
        DTColumnBuilder.newColumn('employeeId').withTitle('Employee Name').renderWith(function(full) {
            return full.employeeId.firstName+', '+full.employeeId.lastName;
        }),
        DTColumnBuilder.newColumn('employeeId.designations.designation').withTitle('Designation'),
        DTColumnBuilder.newColumn('dtStarted').withTitle('Start Date').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY");
        }),
        DTColumnBuilder.newColumn('dtEnded').withTitle('End Date').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY");
        })
    ];
    var paramObj = {
            "vm" : pm,
            "scope" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : projectMemberAPIservice,
            'loadListUrl' : perfUrl['loadAllProjectMembers'],
            'editFormId' : 'projectMembersForm',
            'deleteFormId' : 'deleteProjectMember'
    };
    perfDatatable.loadTable.init(paramObj);
});