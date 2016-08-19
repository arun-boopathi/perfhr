var dc, scope, data;
/* Roles controller */
mainApp.controller('rolesController', function($scope, rolesAPIservice) {
    $scope.msg='';
    scope = $scope;
    
    $scope.save = function(){
        rolesAPIservice.addRoles($scope.data).success(function () {
            $scope.closeModal();
            dc.dtInstance.reloadData();
            $scope.msg="Roles Saved Successfully!";
        });
    };
    $scope.addRoles = function(){
        $scope.msg = '';
        $scope.data = {};
        perfUtils.getInstance().resetForm('rolesForm');
        $('#rolesForm').modal();
    };
    
    $scope.closeModal = function(){
        $('#rolesForm').modal('hide');
    };

    $scope.update = function(){
        rolesAPIservice.updateRoles($scope.data).success(function () {
            dc.dtInstance.dataTable.fnUpdate($scope.data, dc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.closeModal();
            $scope.msg="Roles Updated Successfully!";
        });
    };

    $scope.deleteRoles = function(){
        rolesAPIservice.deleteRoles($scope.data).success(function () {
            dc.dtInstance.DataTable.row('.selected').remove().draw(false);
            $('#deleteRoles').modal('hide');
            $scope.msg="Roles Deleted Successfully!";
        });
    };
});

mainApp.controller('rolesTableController', rolesTableController);

function rolesTableController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, rolesAPIservice) {
    dc = this;
    dc.dtColumns = [
        DTColumnBuilder.newColumn('roleName').withTitle('Roles')
    ];
    var paramObj = {
            "vm" : dc,
            "scope" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : rolesAPIservice,
            'loadListUrl' : perfUrl['loadRoles'],
            'editFormId' : 'rolesForm',
            'deleteFormId' : 'deleteRoles'
    };
    perfDatatable.loadTable.init(paramObj);
}