/* Designation controller */
mainApp.controller('designationController', function($scope, designationAPIservice) {
	$scope.designations = [];
	$scope.tempData = {};
	$scope.index = '';
	$scope.msg='';
	
	$('#designationForm').on('hidden.bs.modal', function (e) {
		$scope.reset();
	});
	
	designationAPIservice.getDesignationDetails().success(function (response) {
	   $scope.designations = response;
	});
	
	$scope.save = function(){
		designationAPIservice.addDesignation($scope.tempData).success(function (response) {
			$scope.designations.push(response);
			$scope.msg="Designation Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during save!";
		});
	};
	
	$scope.addDesignation = function(){
		$('#designationForm').modal();
	};
	
	$scope.edit = function(data){
		$scope.index = $scope.designations.indexOf(data);
		$scope.tempData = {
			pk : data.pk,
			designation : data.designation
		};
	};
	
	$scope.update = function(){
		designationAPIservice.updateDesignation($scope.tempData).success(function () {
			$scope.designations[$scope.index] = $scope.tempData;
			$scope.msg="Designation Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during update!";
		});
	};
	
	$scope.onDelete = function(data){
		$scope.index = $scope.designations.indexOf(data);
		$scope.tempData = {
			designation : data.designation
		};
		$('#deleteDesignation').modal();
	};
	
	$scope.deleteDesignation = function(){
		$scope.designations.splice($scope.index , 1);
		$scope.msg="Designation Deleted Successfully!";
	};
	
	$scope.reset = function(){
		$scope.tempData = {};
		$scope.index = '';
		$scope.msg='';
	};
});