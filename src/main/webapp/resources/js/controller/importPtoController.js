mainApp.controller('importPtoController', function($scope, ptoAPIService){
    $scope.uploadFile = function(){
    	var file = $scope.uploadPto;
        ptoAPIService.uploadFileToUrl(file).success(function(data){
            $('input[type="file"]').val('');
            if(data)
                $scope.msg = 'PTO document processed successfully!';
            else
                $scope.msg="An error occurred during document processing!";
        }).error(function(){
            $scope.msg="An error occurred during document processing!";
        });
    };
});