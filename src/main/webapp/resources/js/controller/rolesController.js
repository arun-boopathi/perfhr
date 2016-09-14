var dc, scope, data;
/* Roles controller */
mainApp.controller('rolesController', function($scope, rolesAPIservice) {
    $scope.msg='';
    scope = $scope;
    
    $scope.save = function(){
        rolesAPIservice.addRoles($scope.data).success(function () {
        	$('.help-block').html("Roles Saved Successfully!");
            dc.dtInstance.reloadData();
        });
    };
    
    $scope.addRoles = function(){
        $scope.msg = '';
        $('#rolesForm').modal();
    };
    
    $scope.closeModal = function(){
        $('#rolesForm').modal('hide');
    };

    $scope.update = function(){
        rolesAPIservice.updateRoles($scope.data).success(function () {
            dc.dtInstance.dataTable.fnUpdate($scope.data, dc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $('.help-block').html("Roles Updated Successfully!");
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