/*Dashboard Controller*/
mainApp.controller('dashboardController', function($scope, $interval, dashboardAPIservice, profileAPIservice, user) {
	/*profileAPIservice.getProfileDetails().success(function (response) {
		user.loggedUser = response;
    });*/	
}).controller('approvalReqCtrl', function($scope, dashboardAPIservice) {
	console.log('in approvalReqCtrl controller ', dashboardAPIservice);
});