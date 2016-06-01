/*Dashboard Controller*/
mainApp.controller('dashboardController', function($scope, $interval, dashboardAPIservice, profileAPIservice) {
	profileAPIservice.getProfileDetails().success(function (response) {
  	  user = response;
  	  $('#welcomeMsg').html('Welcome '+response.lastName+', '+response.firstName);
        $scope.profile = response; 
    });	
}).controller('approvalReqCtrl', function($scope, dashboardAPIservice) {
	console.log('in approvalReqCtrl controller ', dashboardAPIservice);
});