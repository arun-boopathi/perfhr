angular.module('pto.controller', []).controller('ptoController', ['$scope','$filter','filterNames', function($scope,$filter,filterNames){
	$scope.ptoTimeFrame = "March, 2016";
	$scope.ptoLeavetaken = "5";
	$scope.ptoLeavebalance = "7";
	$scope.ptosTaken =[new Date("03-30-2015"),new Date("05-02-2016"),new Date("05-01-2015"),new Date("02-16-2016"),new Date("12-07-2015")];
	$scope.isSplLeavesPanel = false;
	
	$scope.toggleSplLeaves = function(){
		$scope.isSplLeavesPanel = !$scope.isSplLeavesPanel;
	};
	
	$scope.$watch("ptoToDt", function (newValue) {
		if(undefined != $scope.ptoFromDt){
			$scope.daysAllotted = $filter(filterNames.calculateDayDiff)(new Date($scope.ptoFromDt),new Date($scope.ptoToDt));
		}
	});
	
	$scope.$watch("ptoFromDt", function (newValue) {
		if(undefined != $scope.ptoToDt){
			$scope.daysAllotted = $filter(filterNames.calculateDayDiff)(new Date($scope.ptoFromDt),new Date($scope.ptoToDt));
		}
	});
}]);
