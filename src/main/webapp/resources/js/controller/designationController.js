var dc,  data;
/* Designation controller */
mainApp.controller('designationController', function($scope, designationAPIservice, $compile, DTOptionsBuilder, DTColumnBuilder) {
    dc = this;
    $scope.dtColumns = [
        DTColumnBuilder.newColumn('designation').withTitle('Job Title'),
        DTColumnBuilder.newColumn('sbu').withTitle('SBU')
    ];
    
    var paramObj = {
            "vm" : $scope,
            "compile" : $compile,
            "DtOptionsBuilder" : DTOptionsBuilder,
            "DTColumnBuilder" : DTColumnBuilder,
            "service" : designationAPIservice,
            'loadListUrl' : perfUrl['loadDesignations'],
            'editFormId' : 'designationForm',
            'deleteFormId' : 'deleteDesignation'
    };
    perfDatatable.loadTable.init(paramObj);
    
    $scope.save = function(){
        designationAPIservice.addDesignation($scope.data).success(function () {
        	$scope.msg="Designation Saved Successfully!";
            dc.dtInstance.reloadData();
            $('#designationForm').modal('hide');
        });
    };
    
    $scope.addDesignation = function(){
    	perfUtils.getInstance().resetForm();
        $('#designationForm').modal();
    };
    
    $scope.update = function(){
        designationAPIservice.updateDesignation($scope.data).success(function () {
        	console.log('dc in upd ', dc);
            dc.dtInstance.dataTable.fnUpdate($scope.data, dc.dtInstance.DataTable.$('tr.selected'), undefined, false);
            $('#designationForm .help-block').html("Designation Updated Successfully!");            
        });
    };

    $scope.deleteDesignation = function(){
        designationAPIservice.deleteDesignation($scope.data).success(function () {
            dc.dtInstance.DataTable.row('.selected').remove().draw(false);
            $('#deleteDesignation').modal('hide');
            $scope.msg="Designation Deleted Successfully!";
        });
    };
});