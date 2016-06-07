/*Dashboard Controller*/
mainApp.controller('dashboardController', function($scope, $interval, dashboardAPIservice, profileAPIservice) {
	profileAPIservice.getProfileDetails().success(function (response) {
		$scope.currentUser = response;
    });	
}).controller('approvalReqCtrl', function($scope, dashboardAPIservice) {
	console.log('in approvalReqCtrl controller ', dashboardAPIservice);
});