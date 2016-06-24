(function(root){
    var perfDatatable = root.perfDatatable || {};
    perfDatatable.loadTable = {
        init: function(params){
            var editRow = true, deleteRow = true, responsive = true, actions = true, sortCol = 0;
            params.vm.message = '';
            params.vm.dtInstance = {};
            params.vm.datalist = {};
            var url = (params.loadListUrl)?params.loadListUrl:'';
            editRow = (params.editRow === undefined)?editRow:params.editRow;
            deleteRow = (params.deleteRow === undefined)?deleteRow:params.deleteRow;
            actions = (params.actions === undefined)?actions:params.actions;
            responsive = params.responsive?params.responsive: false;
            params.vm.dtOptions = params.DtOptionsBuilder.fromSource(url)
            .withDisplayLength(7)
            .withDOM('pitrfl')
            .withBootstrap()
            .withOption('responsive', responsive)
            .withOption('createdRow', createdRow)
            .withOption('aaSorting', [params.sortCol === undefined? sortCol: params.sortCol, 'asc'])
            .withOption('rowCallback', rowCallback)
            .withPaginationType('full_numbers')
            .withOption("oLanguage", {"sEmptyTable": params.vm.sEmptyTable === undefined?"No Records Found.": params.vm.sEmptyTable})
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
            if(actions)
                params.vm.dtColumns.push(params.DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith(actionsHtml));

            function rowCallback(nRow, aData) {
                $('td', nRow).unbind('click');
                $('td:last button', nRow).bind('click', function() {
                    params.vm.dtInstance.DataTable.$('tr.selected').removeClass('selected');
                    $(nRow).addClass('selected');
                    scope.data = aData;
                    scope.$apply();
                });
                return nRow;
            }
            function createdRow(row) {
                // Recompiling so we can bind Angular directive to the DT
                params.compile(angular.element(row).contents())(params.scope);
            }
            function actionsHtml(data) {
                params.vm.datalist[data.pk] = data;
                var editRecord='', deleteRecord ='';
                if(editRow){
                    editRecord = '<button class="btn btn-edit" data-toggle="modal" data-target="#'+params.editFormId+'">' +
                    '   <i class="fa fa-pencil"></i>' +
                    '</button>&nbsp;';
                }
                if(deleteRow){
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