var scope, data, rowIndex;
/* Designation controller */
mainApp.controller('designationController', function($scope, designationAPIservice) {
	$scope.msg='';
	scope = $scope;

	$scope.save = function(){
		designationAPIservice.addDesignation($scope.data).success(function (response) {
			$scope.closeModal();
			vm.dtInstance.reloadData();
			$scope.msg="Designation Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during save!";
		});
	};
	
	$scope.addDesignation = function(){
		$scope.msg='';
		$scope.data = {};
		$('#designationForm').modal();
	};
	
	$scope.closeModal = function(){
		$('#designationForm').modal('hide');
	};
	
	$scope.update = function(){
		designationAPIservice.updateDesignation($scope.data).success(function () {
			vm.dtInstance.dataTable.fnUpdate($scope.data, rowIndex, undefined, false);
			$scope.closeModal();
			$scope.msg="Designation Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during update!";
		});
	};
	
	$scope.deleteDesignation = function(){
		designationAPIservice.deleteDesignation($scope.data).success(function () {
			vm.dtInstance.DataTable.row('.selected').remove().draw(false);
			$('#deleteDesignation').modal('hide');
			$scope.msg="Designation Deleted Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during delete!";
		});
	};
});

mainApp.controller('DesignationController', DesignationController);

function DesignationController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, designationAPIservice) {
    vm = this;
    vm.dtColumns = [
        DTColumnBuilder.newColumn('designation').withTitle('Designations')
    ];
    var paramObj = {
    		"vm" : vm,
    		"scope" : $scope,
    		"compile" : $compile,
    		"DtOptionsBuilder" : DTOptionsBuilder,
    		"DTColumnBuilder" : DTColumnBuilder,
    		"service" : designationAPIservice,
    		'loadListUrl' : perfUrl['loadDesignations'],
    		'editFormId' : 'designationForm',
    		'deleteFormId' : 'deleteDesignation'
    };
    perfDatatable.loadTable.init(paramObj);
}