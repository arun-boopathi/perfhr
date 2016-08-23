(function(root){
    var perfDatatable = root.perfDatatable || {};
    this.params = null;
    perfDatatable.loadTable = {
        init: function(params){
            this.params = params;
            var editRow = true, deleteRow = true, actions = true;
            params.vm.message = '';
            params.vm.dtInstance = {};
            params.vm.datalist = {};
            params.url = (params.loadListUrl)?params.loadListUrl:'';
            params.editRow = (params.editRow === undefined)?editRow:params.editRow;
            params.deleteRow = (params.deleteRow === undefined)?deleteRow:params.deleteRow;
            params.actions = (params.actions === undefined)?actions:params.actions;
            params.responsive = params.responsive?params.responsive: false;
            perfDatatable.loadTable.loadDataTable(params);
        },
        loadDataTable: function(params){
            params.vm.dtOptions = params.DtOptionsBuilder.fromSource(params.url)
            .withDataProp('entity')
            .withDisplayLength(7)
            .withDOM('pitrfl')
            .withBootstrap()
            .withOption('responsive', params.responsive)
            .withOption('createdRow', createdRow)
            .withOption('aaSorting', [params.sortCol === undefined? 0: params.sortCol, 'asc'])
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
            if(params.actions)
                params.vm.dtColumns.push(params.DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith(actionsHtml));

            function createdRow(row) {
                // Recompiling so we can bind Angular directive to the DT
                params.compile(angular.element(row).contents())(params.scope);
            }
            function actionsHtml(data) {
                params.vm.datalist[data.pk] = data;
                var editRecord='', deleteRecord ='';
                if(params.editRow){
                    editRecord = '<button class="btn btn-edit" data-toggle="modal" onclick="perfDatatable.loadTable.popRecord(this, '+data.pk+', '+params.editFormId+')">' +
                    '   <i class="fa fa-pencil"></i>' +
                    '</button>&nbsp;';
                }
                if(params.deleteRow){
                    deleteRecord = '<button class="btn btn-danger" data-toggle="modal" onclick="perfDatatable.loadTable.popRecord(this, '+data.pk+', '+params.deleteFormId+')">' +
                    '   <i class="fa fa-trash-o"></i>' +
                    '</button>';
                }
                return  editRecord+deleteRecord;
            };
        },
        popRecord: function(ele, id, formId){
            this.params.vm.dtInstance.DataTable.$('tr.selected').removeClass('selected');
            $(ele).parents('tr').addClass('selected');
            scope.data = this.params.vm.datalist[id];
            this.params.scope.$apply();
            $(formId).modal('show');
        }
    };
    root.perfDatatable = perfDatatable;
})(this);