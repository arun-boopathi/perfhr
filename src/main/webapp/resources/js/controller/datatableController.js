(function(root){
	var perfDatatable = root.perfDatatable || {};
	
	perfDatatable.loadTable = {		
		init: function(vm, $scope, $compile, DTOptionsBuilder, DTColumnBuilder, apiService, url){
			vm.message = '';
		    vm.deleteRow = deleteRow;
		    vm.dtInstance = {};
		    vm.datalist = {};
		    vm.dtOptions = DTOptionsBuilder.fromSource(url)
		        .withDisplayLength(7)
		        .withDOM('pitrfl')
		        .withPaginationType('full_numbers')
		        .withOption('createdRow', createdRow)
		        .withOption('aaSorting', [1, 'asc'])
		        .withOption('rowCallback', rowCallback);
		    vm.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith(actionsHtml));
		    		    
		    function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
		        $('td', nRow).unbind('click');
		        $('td:last', nRow).bind('click', function() {
		        	rowIndex = aData.pk-1;
		        	console.log('in rowCallback ', rowIndex, 'scope ', scope);
		        	$(".alert-info").addClass('hidden');
		        	apiService.loadById(aData.pk).success(function (response) {
			        	scope.data = response;
			        });
		        });
		        return nRow;
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
		        return '<button class="btn btn-warning" data-toggle="modal" data-target="#updateForm">' +
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