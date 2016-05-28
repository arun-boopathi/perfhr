var vm, pm;
var rowIndex;
var data;
var scope;
/* Project controller */
mainApp.controller('projectController', function($scope, projectAPIservice, employeeAPIservice) {
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
		projectAPIservice.addProject($scope.data).success(function(response) {
			vm.dtInstance.reloadData();
			$scope.msg="Project Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during save!";
		});
	};
	
	$scope.addProject = function(){
		$scope.data = '';
		$('#projectForm').modal();
	};
	
	$scope.update = function(){
		projectAPIservice.updateProject($scope.data).success(function () {
			vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
			$scope.msg="Project Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during update!";
		});
	};
	
	$scope.deleteProject = function(){
		projectAPIservice.deleteProject($scope.data).success(function () {
			vm.dtInstance.reloadData();
			$('#deleteProject').modal('hide');
			$scope.msg="Project Deleted Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during delete!";
		});
	};
});

mainApp.controller('ProjectsController', ProjectsController);

function ProjectsController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('projectName').withTitle('Projects'),
        DTColumnBuilder.newColumn('startDate').withTitle('Start Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        }),
        DTColumnBuilder.newColumn('endDate').withTitle('End Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        })
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
    		'deleteFormId' : 'deleteProject',
    		'sortCol': '0'
    };
    perfDatatable.loadTable.init(paramObj);
}