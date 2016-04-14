(function(root){
	var perfDatatable = root.perfDatatable || {};
	
	perfDatatable.loadTable = {		
		init: function(vm, $scope, $compile, DTOptionsBuilder, DTColumnBuilder, apiService, url){
			vm.message = '';
		    vm.edit = edit;
		    vm.deleteRow = deleteRow;
		    vm.dtInstance = {};
		    vm.datalist = {};
		    vm.dtOptions = DTOptionsBuilder.fromSource(url)
		        .withDisplayLength(7)
		        .withDOM('pitrfl')
		        .withPaginationType('full_numbers')
		        .withOption('createdRow', createdRow).withOption('aaSorting', [1, 'asc']);
		    
		    vm.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith(actionsHtml));
		    
		    function edit(data, index) {
		    	rowIndex = index-1;
		    	console.log('in edit');
		    	$("#updateEmployeeMsg").addClass('hidden');
		        apiService.loadById(data.pk).success(function (response) {
		        	scope.data = response;
		        });
		    }
		    
		    function deleteRow(data) {
		        vm.message = 'You are trying to remove Employee:  ' + data.lastName+', '+data.firstName ;
		        vm.dtInstance.reloadData();
		    }
		    
		    function createdRow(row, data, dataIndex) {
		        // Recompiling so we can bind Angular directive to the DT
		        $compile(angular.element(row).contents())($scope);
		    }
		    
		    function actionsHtml(data, type, full, meta, iDisplayIndex) {
		        vm.datalist[data.pk] = data;
		        return '<button class="btn btn-warning" data-toggle="modal" data-target="#updateForm" ng-click="showCase.edit(showCase.datalist[' + data.pk + '], '+data.pk+')">' +
		            '   <i class="fa fa-edit"></i>' +
		            '</button>&nbsp;' +
		            '<button class="btn btn-danger" ng-click="showCase.deleteRow(showCase.datalist[' + data.pk + '])" )"="">' +
		            '   <i class="fa fa-trash-o"></i>' +
		            '</button>';
		    };
		}
	};
	
	root.perfDatatable = perfDatatable;
})(this);