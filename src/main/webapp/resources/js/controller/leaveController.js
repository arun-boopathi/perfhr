var vm;
var data;
var scope;
mainApp.controller('wfhController', function($scope, $controller, leaveAPIservice) {
	var scope = this;
	scope = $scope;
	scope.events = [];
	$scope.title="WFH";
	$scope.leaveType="WFH";
	
    angular.extend(this, $controller('leaveController', {
        $scope: $scope
    }));
});

mainApp.controller('ptoController', function($scope, $controller, leaveAPIservice) {
	var scope = this;
	//scope = $scope;
	scope.events = [];
	$scope.title="PTO";
	$scope.leaveType="PTO";
	$scope.isPto = true;
    angular.extend(this, $controller('leaveController', {
        $scope: $scope
    }));
});

mainApp.controller('leaveController', function($scope, moment, leaveAPIservice, calendarConfig) {
	var obj = this;
	//These variables MUST be set as a minimum for the calendar to work
	obj.calendarView = 'month';
	obj.viewDate = new Date();
	scope = $scope;
	$scope.data = {};
	$scope.data.requestType = $scope.leaveType;
	var eventArr = [];
	calendarConfig.displayEventEndTimes = true;
	calendarConfig.templates.calendarSlideBox = 'html/templates/calendarSlideBoxTemplate.html';
	calendarConfig.templates.calendarMonthCell = 'html/templates/calendarMonthCell.html';
	calendarConfig.templates.calendarMonthCellEvents = 'html/templates/calendarMonthCellEvents.html';
	
	this.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };
	
    $scope.openModal = function(){
    	$('#leaveModal').modal();
    };
    
    $scope.applyLeave = function(){
    	$scope.data = {};
    	$scope.data.requestType = $scope.leaveType;
        $scope.openModal();
    };
    
    $scope.editLeave = function(data){
    	$scope.data = data;
    	$scope.openModal();
    };
    
    leaveAPIservice.loadAllLeaves($scope.leaveType).success(function (response) {
     	$.each(response, function(i, val){
     		val.startsAt = new Date(val.startsAt);
     		val.endsAt = new Date(val.endsAt);
     		val.type = $scope.displayType;
     		$scope.scope.events[val.pk] = val;
     		eventArr[val.pk] = i;
     	});
     	console.log('$scope.scope.events ', $scope.scope.events, 'eventArr ',eventArr);
  	});
    
    this.timeSpanClicked = function(calendarCell, $event){
    	if(calendarCell.events.length > 0){
    		vm.dtInstance.DataTable.clear().draw();
        	vm.dtInstance.DataTable.rows.add(calendarCell.events).draw();
        	$('#leaveList').modal();	
    	}
    };
    
    $scope.saveLeave = function(){
    	$scope.data.requestType = $scope.leaveType;
    	leaveAPIservice.applyLeave($scope.data).success(function (response) {
    		response.startsAt = new Date(response.startsAt);
    		response.endsAt = new Date(response.endsAt);
    		$scope.scope.events[response.pk] = response;
			$scope.msg = $scope.title+" Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Save!";
		});
    };
    
    $scope.updateLeave = function(){
    	leaveAPIservice.updateLeave($scope.data).success(function (response) {
    		$.grep($scope.scope.events, function (element, index) {
        		if($scope.data.pk == element.pk){
        			$scope.scope.events[index] = $scope.data;
        		}
        	    return element.pk == $scope.data.pk;
        	});
			$scope.msg=$scope.title+" Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Update!";
		});
    };
    
    $scope.deleteLeave = function(){
    	leaveAPIservice.deleteLeave($scope.data).success(function (response) {
    		$.grep($scope.scope.events, function (element, index) {
        		if($scope.data.pk == element.pk){
        			$scope.scope.events.splice(index, 1);
        		}
        	    return element.pk == $scope.data.pk;
        	});
			$scope.msg=$scope.title+" Deleted Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Delete!";
		});
    };
    
    $scope.onDtChange = function(){
    	var time9 = 60*1000*60*9;
    	var time1_30 = 60*1000*60*13.5;
    	var time5_30 = 60*1000*60*17.5;
    	if($scope.data.startsAt && $scope.data.dtFromHalf) {
    		var stDt = new Date($scope.data.startsAt).toDateString();
    		if($scope.data.dtFromHalf == 'FIRST'){
    			stDt = new Date(stDt).getTime() + (time9);
    		} else {
    			stDt = new Date(stDt).getTime() + (time1_30);
    		}
    		$scope.data.startsAt = new Date(stDt);
    	} 
    	if($scope.data.endsAt && $scope.data.dtEndHalf){
    		var endDt = new Date($scope.data.endsAt).toDateString();
    		if($scope.data.dtEndHalf == 'FIRST'){
    			endDt = new Date(endDt).getTime() + (time1_30);
    		} else {
    			endDt = new Date(endDt).getTime() + (time5_30);
    		}
    		$scope.data.endsAt = new Date(endDt);
    	}
    };
});

mainApp.controller('leaveControllerTable', leaveControllerTable);

function leaveControllerTable($scope, $compile, DTOptionsBuilder, DTColumnBuilder, leaveAPIservice) {
	vm = this;
	vm.dtColumns = [
        DTColumnBuilder.newColumn('title').withTitle('Title'),
        DTColumnBuilder.newColumn('startsAt').withTitle('startsAt').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        }),
        DTColumnBuilder.newColumn('endsAt').withTitle('endsAt').renderWith(function(data, type, full) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        })
 	];
     var paramObj = {
 		"vm" : vm,
 		"scope" : $scope,
 		"compile" : $compile,
 		"DtOptionsBuilder" : DTOptionsBuilder,
 		"DTColumnBuilder" : DTColumnBuilder,
 		"service" : leaveAPIservice,
 		'editFormId' : 'leaveModal',
 		'sortCol': '0'
     };
 	perfDatatable.loadTable.init(paramObj);
}