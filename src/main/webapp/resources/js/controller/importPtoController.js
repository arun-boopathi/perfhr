mainApp.controller('ptoController', ['$scope','$filter','filterNames','ptoService', function($scope,$filter,filterNames,ptoService){
	$scope.uploadFile = function(){
		 var file = $scope.newFileVal;
		 ptoService.uploadFileToUrl(file, perfUrl['importPto']).then(function(data){
			 console.log('Success');
		 });
	};
}]);

angular.module('pto.controller').factory('ptoService', ['$http','$q', function ($http,$q) { 
	return({
		uploadFileToUrl:uploadFileToUrl,
		fetchPTODet:fetchPTODet
	});

	function uploadFileToUrl(file, uploadUrl) {
         var fd = new FormData();
         fd.append('uploadFiles',file);
         return ($http.post(uploadUrl, fd, {
             transformRequest: angular.identity,
             headers: {'Content-Type': undefined}
         }).then(handleSuccess,handleError));
    }
	
	function fetchPTODet(ptoDetUrl) {
        return ($http.get(ptoDetUrl).then(handleSuccess,handleError));
    }
	
	function handleSuccess(response){
		return response.data;
	}
	function handleError(response){
		return $q.reject(response.data.message);
	}
}]);