(function(angular) {
	/* ProjectMembersController*/
	var ProjectMembersController = function($scope, $controller, DTColumnBuilder, projectAPIservice, employeeAPIservice){
		var _this = this;
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
	    projectAPIservice.loadProjects().success(function(response) {
	        $scope.projects = response.entity;
	    });

	    employeeAPIservice.loadEmployees().success(function(response) {
	        $scope.employees = response.entity;
	    });
	    
	    $scope.validate = function(){
    		perfUtils.getInstance().compareDate();
    	};
	    
		$scope.dtColumns = [
            DTColumnBuilder.newColumn('projectId.projectName').withTitle('Project Name'),
	        DTColumnBuilder.newColumn('employeeId').withTitle('Employee Name').renderWith(function(full) {
	            return full.firstName+', '+full.lastName;
	        }),
	        DTColumnBuilder.newColumn('employeeId.designations.designation').withTitle('Designation'),
	        DTColumnBuilder.newColumn('dtStarted').withTitle('Start Date').renderWith(function(data) {
	            return moment(data).format("DD-MM-YYYY");
	        }),
	        DTColumnBuilder.newColumn('dtEnded').withTitle('End Date').renderWith(function(data) {
	            return moment(data).format("DD-MM-YYYY");
	        })
        ];
		
		var vm = {
			'title' : 'Project Members',
		    'formId' : 'projectMembersForm',
		    'scope' : $scope,
		    'addUrl' : perfUrl['saveProjectMember'],
		    'updateUrl' : perfUrl['updateProjectMember'],
		    'deleteUrl' : perfUrl['deleteProjectMember'],
		    'loadListUrl': perfUrl['loadAllProjectMembers']
		};
		angular.extend(this, $controller('AbstractController', {_this: _this, vm:vm}));
	};
	ProjectMembersController.$inject = ['$scope','$controller', 'DTColumnBuilder', 'projectAPIservice', 'employeeAPIservice'];
	mainApp.controller('projectMembersController', ProjectMembersController);
})(angular);