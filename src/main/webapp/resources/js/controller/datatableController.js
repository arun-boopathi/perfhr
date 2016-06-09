(function(root){
	var perfDatatable = root.perfDatatable || {};
	
	perfDatatable.loadTable = {		
		init: function(params){
			params.vm.message = '';
			params.vm.dtInstance = {};
			params.vm.datalist = {};
			var url, responsive = '';
			responsive = params.responsive?params.responsive: false; 
			if(params.loadListUrl)
				url = params.loadListUrl;
			params.vm.dtOptions = params.DtOptionsBuilder.fromSource(url)
	        .withDisplayLength(7)
	        .withDOM('pitrfl')
	        .withBootstrap()
	        .withOption('responsive', responsive)
	        /*.withOption('responsive', {
	            details: true
	        })*/
	        .withOption('createdRow', createdRow)
	        .withOption('aaSorting', [params.sortCol != undefined? params.sortCol: 0 , 'asc'])
	        .withOption('rowCallback', rowCallback)
	        .withOption('fnDrawCallback', drawCallback)
	        .withPaginationType('full_numbers')
	        .withOption("oLanguage", {"sEmptyTable": params.vm.sEmptyTable != null ? params.vm.sEmptyTable: "No Records Found."})
	        .withColumnFilter()
	        .withButtons([
	            { 
	            	extend : 'excel',
	            	exportOptions: {
	            		columns: ':not(:last-child)'
	                }
	            },
	            {
	            	extend : 'print',
	            	exportOptions: {
	            		columns: ':not(:last-child)'
	                }
	            }
        	]);
			params.vm.dtColumns.push(params.DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith(actionsHtml));

			function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
		        $('td', nRow).unbind('click');
		        $('td:last button', nRow).bind('click', function() {
		        	vm.dtInstance.DataTable.$('tr.selected').removeClass('selected');
		        	$(nRow).addClass('selected');
		        	rowIndex = aData.pk-1;
		        	scope.data = aData;
		        	scope.$apply();
		        });
		        return nRow;
		    }
		    
		    function drawCallback(settings){
		    	if(settings.aoData.length > 0) {
		    		/*var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		            api.column(4, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                	console.log('group ', group, 'i', i);
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="5">'+group+'</td></tr>'
		                    );
		                    last = group;
		                }
		            });*/
		            
		         // Order by the grouping
			        /*$('#DataTables_Table_0_wrapper tbody').on( 'click', 'tr.group', function () {
			        	console.log('row click');
			            var currentOrder = vm.dtInstance.DataTable.order()[0];
			            if ( currentOrder[0] === 4 && currentOrder[1] === 'asc' ) {
			            	vm.dtInstance.DataTable.order( [ 4, 'desc' ] ).draw();
			            }
			            else {
			            	vm.dtInstance.DataTable.order( [ 4, 'asc' ] ).draw();
			            }
			        });*/
		        }		    	
		    }
		    
		    function createdRow(row, data, dataIndex) {
		        // Recompiling so we can bind Angular directive to the DT
		    	params.compile(angular.element(row).contents())(params.scope);
		    }
		    
		    function actionsHtml(data, type, full, meta, iDisplayIndex) {
		        vm.datalist[data.pk] = data;
		        var editRecord='', deleteRecord ='';
		        if(params.editRow == undefined){
		        	editRecord = '<button class="btn btn-edit" data-toggle="modal" data-target="#'+params.editFormId+'">' +
		            '   <i class="fa fa-edit"></i>' +
		            '</button>&nbsp;';	
		        }
		        if(params.deleteRow == undefined){
		        	deleteRecord = '<button class="btn btn-danger" data-toggle="modal" data-target="#'+params.deleteFormId+'">' +
		            '   <i class="fa fa-trash-o"></i>' +
		            '</button>';
		        }
		        return  editRecord+deleteRecord;
		    };
		}
	};
	
	root.perfDatatable = perfDatatable;
})(this);