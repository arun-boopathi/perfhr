angular.module('designation.controller', []).
	/* Designation controller */
    controller('designationController', function($scope, designationAPIservice) {
    }).controller('AddDesignationCtrl', function($scope, designationAPIservice) 
    		{
    	       $scope.submit = function() {
        	   designationAPIservice.addDesignation($scope.data).success(function (response) {});
    	    };
    }).controller('UpdateDesignationCtrl', function($scope, designationAPIservice)
    		{
    	       $scope.update = function() 
    		   {
               designationAPIservice.updateDesignation($scope.data).success(function (response) {});
               };
    }).controller('loadDesignationCtrl', function($scope, designationAPIservice) 
    		{
    	    	  $scope.designations = null;   	    	  
                  designationAPIservice.getDesignationDetails().success(function (response) 
                   {
                      $scope.designations = response; 
                   });
    });
    	           	       
    	
      