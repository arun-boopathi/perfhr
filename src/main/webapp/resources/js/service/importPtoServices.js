angular.module('pto.services', []).factory('ptoAPIService', function ($http) {
	var importPtoAPI = {};
	importPtoAPI.uploadFileToUrl = function(file) {
         var fd = new FormData();
         fd.append('uploadFiles',file);
         return $http({
             method: 'post', 
             data : fd,
             url: perfUrl['importPto'],
             headers: {'Content-Type': undefined}
         });
    };
    return importPtoAPI;
});