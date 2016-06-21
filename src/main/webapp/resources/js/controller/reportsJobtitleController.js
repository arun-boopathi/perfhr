var repJb, columnBuilder, brIndex, data, scope;
/*Reports by Job title Controller*/
mainApp.controller('reportsJobtitleController', function($scope, reportJobtitleAPIservice, employeeAPIservice) {
	scope = $scope;
	$scope.data = {};
	$scope.brIndex = 0;
	
	$scope.stDate = {
	    opened: false
	};
    
    $scope.endDate = {
	    opened: false
	};

    $scope.reportStartDate = function() {
	    $scope.stDate.opened = true;
	};
    
	$scope.reportEndDate = function() {
	    $scope.endDate.opened = true;
	};
	scope.startsAt = new Date('01-01-'+new Date().getFullYear());
	scope.endsAt = new Date('12-31-'+new Date().getFullYear());
	
	$('table').on('click', 'tbody tr', function () {
		if($scope.brIndex <= 2){
			console.log('first td ', $(this).find('td:first').html());
			if($(this).find('td:first').html() == 'CHENNAI CONSULTING' 
				|| $(this).find('td:first').html() == 'CHENNAI ADMIN'){
				console.log('in click ', $(this).find('td:first').html());
				if($scope.brIndex == 0){
					$('#jobTitleBreadCrumb').append('<li>'+$(this).find('td:first').html()+'</li>');
					$scope.data.sbu = $(this).find('td:first').html();
					$scope.brIndex++;
					configTable(repJb);
				}			
			} else {
				console.log('in else ', brIndex);
				$('#jobTitleBreadCrumb').append('<li>'+$(this).find('td:first').html()+'</li>');
				if($scope.brIndex == 1){
					$scope.data.designation = $(this).find('td:first').html();
					$scope.brIndex++;
					configTable(repJb);
				}			
			}
		}
	});
	
	$('#jobTitleBreadCrumb').on('click', 'li', function(){
		$scope.brIndex = $('#jobTitleBreadCrumb li').index(this);
		$('#jobTitleBreadCrumb li:gt('+$scope.brIndex+')').remove();
		configTable(repJb);
	});
	
	function configTable(repJb){
		if($scope.brIndex == 0){
			$scope.data.sbu = null;
			$scope.data.designation = null;
			if(repJb){
				repJb.dtInstance.dataTable.fnSetColumnVis(0, true);
				repJb.dtInstance.dataTable.fnSetColumnVis(1, false);	
			}
		} else if($scope.brIndex == 1){
			$scope.data.designation = null;
			if(repJb){
				repJb.dtInstance.dataTable.fnSetColumnVis(0, false);
				repJb.dtInstance.dataTable.fnSetColumnVis(1, true);	
			}
		} else if($scope.brIndex == 2){
			//$scope.data.sbu = null;
			employeeAPIservice.loadEmployeeByDesHistory(scope.startsAt.getTime(), scope.endsAt.getTime(),
					$scope.data.designation).success(function (response) {
				$scope.employees = response;
				vm.dtInstance.DataTable.clear().draw();
		    	vm.dtInstance.DataTable.rows.add($scope.employees).draw();
			});
		}
		if($scope.brIndex <= 1)
			$scope.loadBySbu();		
	}
	
	$scope.loadBySbu = function(){
		reportJobtitleAPIservice.reportsLoadBySbu(scope.startsAt.getTime(), scope.endsAt.getTime(),
				$scope.data.sbu, $scope.data.designation).success(function (response) {
			repJb.dtInstance.DataTable.clear().draw();
	    	repJb.dtInstance.DataTable.rows.add(response).draw();
		}).error(function(){
			$scope.msg="An error occurred!";
		});
	};
	
	configTable();
	
	$scope.searchLeave = function(){
		$scope.loadBySbu();
	};
	
});

mainApp.controller('ReportJobTitleControllerTable', ReportJobTitleControllerTable);

function ReportJobTitleControllerTable($scope, $compile, DTOptionsBuilder, DTColumnBuilder, reportJobtitleAPIservice) {
    repJb = this;
    columnBuilder = DTColumnBuilder;
    repJb.dtColumns = [
        DTColumnBuilder.newColumn('sbu').withTitle('SBU'),
        DTColumnBuilder.newColumn('designation').withTitle('Job Title').notVisible(),
        DTColumnBuilder.newColumn('employeeCount').withTitle('Count')
    ];
    var paramObj = {
		"vm" : repJb,
		"scope" : $scope,
		"compile" : $compile,
		"DtOptionsBuilder" : DTOptionsBuilder,
		"DTColumnBuilder" : DTColumnBuilder,
		"service" : reportJobtitleAPIservice,
		"actions" : false
    };
    perfDatatable.loadTable.init(paramObj);
}