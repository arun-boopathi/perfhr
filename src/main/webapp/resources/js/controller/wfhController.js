mainApp.controller('wfhController', function($scope, moment, leaveAPIservice) {
	$scope.tempData = {};
    var scope = this;
    //These variables MUST be set as a minimum for the calendar to work
    scope.calendarView = 'month';
    scope.viewDate = new Date();
    $scope.leaveType="WFH";
    scope.events = [];

    leaveAPIservice.loadAllLeaves($scope.leaveType).success(function (response) {
     	$.each(response, function(i, val){
     		val.startsAt = new Date(val.startsAt);
     		val.endsAt = new Date(val.endsAt);
     		val.type = 'success';
     		scope.events[val.pk] = val;
     	});
  	});
    
    scope.isCellOpen = false;
    
    scope.eventClicked = function(event) {
      $scope.data = event;
      $('#wfhModal').modal();
    };

    $scope.addWfh = function(){
      $scope.data = '';
      $scope.openModal();
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
    
    $scope.saveWfh = function(){
    	console.log('data ', $scope.data);
    	/*leaveAPIservice.applyWfh($scope.data).success(function (response) {
    		console.log("success", response);
    		scope.events[response.pk] = response;
			$scope.msg="WFH Saved Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Save!";
		});*/
    };
    
    /*$scope.updateWfh = function(){
    	wfhAPIservice.updateWfh($scope.data).success(function (response) {
    		scope.events[response.pk] = $scope.data;
			$scope.msg="WFH Updated Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Update!";
		});
    };
    
    $scope.deleteWfh = function(){
    	wfhAPIservice.deleteWfh($scope.data).success(function (response) {
    		var index = scope.events.indexOf($scope.data.pk);
    		scope.events.splice(index, 1);
//    		scope.events[response.pk] = $scope.data;
			$scope.msg="WFH Deleted Successfully!";
		}).error(function(){
			$scope.msg="An error occurred during Delete!";
		});
    };*/
    
    $scope.openModal = function(){
    	$('#wfhModal').modal();
    };
   
    scope.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };
});