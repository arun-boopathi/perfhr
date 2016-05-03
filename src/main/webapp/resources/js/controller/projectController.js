var vm, pm;
var rowIndex;
var data;
var scope;
angular.module('project.controller', ['projectTable.bindAngularDirective']).
	/* Project controller */
controller('projectController', function($scope, projectAPIservice, employeeAPIservice) {
	scope = $scope;
	
	$scope.save = function(){
		console.log('in projectController');
		projectAPIservice.addProject($scope.data).success(function(response) {
			vm.dtInstance.reloadData();
			$scope.data.msg="Project Saved Successfully!";
		}).error(function(){
			$scope.data.msg="An error occurred during save!";
		});
	};
	
	$scope.addProject = function(){
		$scope.data = '';
		$('#projectForm').modal();
	};
	
	$scope.update = function(){
		projectAPIservice.updateProject($scope.data).success(function () {
			vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
			$scope.data.msg="Project Updated Successfully!";
		}).error(function(){
			$scope.data.msg="An error occurred during update!";
		});
	};
	
	$scope.addProjectMembers = function(){
		projectAPIservice.loadProjects().success(function(response) {
			$scope.projects = response;
		});
		employeeAPIservice.loadAllEmployees().success(function(response) {
			$scope.employees = response;
		});
		pm.dtInstance.DataTable.clear().draw();
		$('#projectMembersForm').modal();
	};
	/*$scope.onDelete = function(data){
		$scope.index = $scope.projects.indexOf(data);
		$scope.tempData = {
			projectName : data.projectName
		};
		$('#deleteProject').modal();
	};
	
	$scope.deleteProject = function(){
		$scope.projects.splice($scope.index , 1);
		$scope.msg="Project Deleted Successfully!";
	};
	
	$scope.selectProject = function(data){
		if($('#project_'+data.pk).prop('checked')) {
			$('input:checkbox:not("#project_'+data.pk+'")').prop('disabled', true);
		} else {
			$('input:checkbox:not("#project_'+data.pk+'")').prop('disabled', false);
		}			
	};
	
	$scope.addMembers = function(){
		
	};*/
}).controller('ProjectMembersCtrl', function($scope, projectAPIservice){
	$scope.save = function(){
		console.log('in ProjectMembersCtrl ', $scope.selectedProject);
		console.log('in ProjectMembersCtrl ', $scope.selectedEmployee);
	};
	
	$scope.changedProject=function(item){
		pm.dtInstance.DataTable.ajax.url(perfUrl['loadProjectMembersByProjectId']+item.pk).load();
	};
});

angular.module('projectTable.bindAngularDirective', ['datatables']).controller('ProjectsController', ProjectsController).controller('ProjectsUserController', ProjectsUserController);

function ProjectsController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('projectName').withTitle('Projects')
    ];
    var paramObj = {
    		"vm" : vm,
    		"scope" : $scope,
    		"compile" : $compile,
    		"DtOptionsBuilder" : DTOptionsBuilder,
    		"DTColumnBuilder" : DTColumnBuilder,
    		"service" : projectAPIservice,
    		'loadListUrl' : perfUrl['loadProjects'],
    		'editFormId' : 'projectForm',
    		'sortCol': '0'
    };
    perfDatatable.loadTable.init(paramObj);
}

function ProjectsUserController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectAPIservice){
	pm = this;
	pm.dtColumns = [
        DTColumnBuilder.newColumn('employeeId').withTitle('Members'),
        DTColumnBuilder.newColumn('dtStarted').withTitle('Start Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        }),
        DTColumnBuilder.newColumn('dtEnded').withTitle('End Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        })
    ];
    var paramObj = {
    		"vm" : pm,
    		"scope" : $scope,
    		"compile" : $compile,
    		"DtOptionsBuilder" : DTOptionsBuilder,
    		"DTColumnBuilder" : DTColumnBuilder,
    		"service" : projectAPIservice,
    		'editFormId' : 'projectForm',
    		'sortCol': '0',
    		'editRow' : false
    };
    perfDatatable.loadTable.init(paramObj);
}