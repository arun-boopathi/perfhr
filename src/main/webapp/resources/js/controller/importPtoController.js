mainApp.controller('importPtoController', function($scope, ptoAPIService){
	$scope.uploadFile = function(){
	    ptoAPIService.uploadFileToUrl($scope.newFileVal).success(function(data){
	    	$('input[type="file"]').val('');
	    	if(data == true)
	    		$scope.msg = 'PTO document processed successfully!';
	    	else
	    		$scope.msg="An error occurred during document processing!";
		}).error(function(){
			$scope.msg="An error occurred during document processing!";
		});
	};
});