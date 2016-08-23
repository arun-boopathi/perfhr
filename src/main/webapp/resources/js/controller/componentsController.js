var dc, scope, data;
/* Components controller */
mainApp.controller('componentsController', function($scope, componentsAPIservice) {
    $scope.msg='';
    scope = $scope;
    
    $scope.save = function(){
        componentsAPIservice.addComponents($scope.data).success(function () {
        	$scope.data = {};
            $scope.closeModal();
            dc.dtInstance.reloadData();
            $scope.msg="Components Saved Successfully!";
        });
    };
    $scope.addComponents = function(){
        $scope.msg = '';
        $scope.data = {};
        perfUtils.getInstance().resetForm('componentsForm');
        $('#componentsForm').modal();
    };
    
    $scope.closeModal = function(){
        $('#componentsForm').modal('hide');
    };

    $scope.update = function(){
        componentsAPIservice.updateComponents($scope.data).success(function () {
            dc.dtInstance.dataTable.fnUpdate($scope.data, dc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.closeModal();
            $scope.msg="Components Updated Successfully!";
        });
    };

    $scope.deleteComponents = function(){
        componentsAPIservice.deleteComponents($scope.data).success(function () {
            dc.dtInstance.DataTable.row('.selected').remove().draw(false);
            $('#deleteComponents').modal('hide');
            $scope.msg="Components Deleted Successfully!";
        });
    };
});

mainApp.controller('componentsTableController', componentsTableController);

function componentsTableController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, componentsAPIservice) {
    dc = this;
    dc.dtColumns = [
        DTColumnBuilder.newColumn('componentName').withTitle('Components')
    ];
    var paramObj = {
            "vm" : dc,
            "scope" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : componentsAPIservice,
            'loadListUrl' : perfUrl['loadComponents'],
            'editFormId' : 'componentsForm',
            'deleteFormId' : 'deleteComponents'
    };
    perfDatatable.loadTable.init(paramObj);
}