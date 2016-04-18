
angular.module('designation.controller', []).
	/* Designation controller */
    controller('designationController', function($scope, designationAPIservice) {
      $scope.designation = null;
      designationAPIservice.getDesignationDetails().success(function (response) {
          $scope.designation = response; 
    });
});