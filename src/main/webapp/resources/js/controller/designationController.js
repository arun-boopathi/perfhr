var dc, scope, data;
/* Designation controller */
mainApp.controller('designationController', function($scope, designationAPIservice) {
    $scope.msg='';
    scope = $scope;

    $scope.save = function(){
    	console.log('in save');
        /*designationAPIservice.addDesignation($scope.data).success(function () {
            $scope.closeModal();
            dc.dtInstance.reloadData();
            $scope.msg="Designation Saved Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during save!";
        });*/
    };
    $scope.addDesignation = function(){
        $scope.msg='';
        $scope.data = {};
        $('#designationForm').modal();
    };
    $scope.closeModal = function(){
        $('#designationForm').modal('hide');
    };

    $scope.update = function(){
        designationAPIservice.updateDesignation($scope.data).success(function () {
            dc.dtInstance.dataTable.fnUpdate($scope.data, dc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $scope.closeModal();
            $scope.msg="Designation Updated Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during update!";
        });
    };

    $scope.deleteDesignation = function(){
        designationAPIservice.deleteDesignation($scope.data).success(function () {
            dc.dtInstance.DataTable.row('.selected').remove().draw(false);
            $('#deleteDesignation').modal('hide');
            $scope.msg="Designation Deleted Successfully!";
        }).error(function(){
            $scope.msg="An error occurred during delete!";
        });
    };
});

mainApp.controller('designationTableController', designationTableController);

function designationTableController($scope, $compile, DTOptionsBuilder, DTColumnBuilder, designationAPIservice) {
    dc = this;
    dc.dtColumns = [
        DTColumnBuilder.newColumn('designation').withTitle('Job Title'),
        DTColumnBuilder.newColumn('sbu').withTitle('SBU')
    ];
    var paramObj = {
            "vm" : dc,
            "scope" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : designationAPIservice,
            'loadListUrl' : perfUrl['loadDesignations'],
            'editFormId' : 'designationForm',
            'deleteFormId' : 'deleteDesignation'
    };
    perfDatatable.loadTable.init(paramObj);
}