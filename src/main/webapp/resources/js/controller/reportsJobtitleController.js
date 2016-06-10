/*Reports by Job title Controller*/
mainApp.controller('reportsJobtitleController', function($scope) {
	$scope.data = {};
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
	
	$scope.data.startsAt = new Date('01-01-'+new Date().getFullYear()).getTime();
	$scope.data.endsAt = new Date('12-31-'+new Date().getFullYear()).getTime();
	
	var startsAt = new Date($scope.data.startsAt);
	var endsAt = new Date($scope.data.endsAt);
	
	console.log('startsAt ',startsAt);
	console.log('endsAt ',endsAt);
	
	
	
});