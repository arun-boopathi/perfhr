var vm, rowIndex, data, scope;
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
		console.log('save ', $scope.data);
		projectMemberAPIservice.saveProjectMember($scope.data).success(function(response) {
			vm.dtInstance.reloadData();
			$scope.msg="Project Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during save!";
		});
	};
	
	$scope.update = function(){
		projectMemberAPIservice.updateProjectMember($scope.data).success(function(response) {
			vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
			$scope.msg="Project Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during update!";
		});
	};
	
	$scope.deleteProjectMember = function(){
		console.log('save ', $scope.data);
		projectMemberAPIservice.deleteProjectMember($scope.data).success(function(response) {
			vm.dtInstance.reloadData();
			$('#deleteProjectMember').modal('hide');
			$scope.msg="Project Deleted Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during delete!";
		});
	};
	
	projectAPIservice.loadProjects().success(function(response) {
		$scope.projects = response;
	});
	
	employeeAPIservice.loadAllEmployees().success(function(response) {
		$scope.employees = response;
	});
});

mainApp.controller('ProjectsUserController', function($scope, $compile, DTOptionsBuilder, DTColumnBuilder, projectMemberAPIservice){
	vm = this;
	vm.dtColumns = [
	    DTColumnBuilder.newColumn('projectId.projectName').withTitle('Project Name'),
	    DTColumnBuilder.newColumn('employeeId').withTitle('Employee Name').renderWith(function(data, type, full) {
            return full.employeeId.firstName+', '+full.employeeId.lastName;
        }),
        DTColumnBuilder.newColumn('employeeId.designations.designation').withTitle('Designation'),
        DTColumnBuilder.newColumn('dtStarted').withTitle('Start Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        }),
        DTColumnBuilder.newColumn('dtEnded').withTitle('End Date').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY");
        })
    ];
    var paramObj = {
    		"vm" : vm,
    		"scope" : $scope,
    		"compile" : $compile,
    		"DtOptionsBuilder" : DTOptionsBuilder,
    		"DTColumnBuilder" : DTColumnBuilder,
    		"service" : projectMemberAPIservice,
    		'loadListUrl' : perfUrl['loadAllProjectMembers'],
    		'editFormId' : 'projectMembersForm',
    		'deleteFormId' : 'deleteProjectMember',
    		'sortCol': '0'
    };
    perfDatatable.loadTable.init(paramObj);
});