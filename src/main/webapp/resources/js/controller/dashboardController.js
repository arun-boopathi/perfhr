var dashboard;

/*Dashboard Controller*/
angular.module('dashboard.controller', []).
controller('dashboardController', function($scope, $interval, dashboardAPIservice, profileAPIservice) {
	profileAPIservice.getProfileDetails().success(function (response) {
  	  console.log('dashboard response ', response);
  	  user = response;
  	  $('#welcomeMsg').html('Welcome '+response.lastName+', '+response.firstName);
        $scope.profile = response; 
    });	
}).controller('approvalReqCtrl', function($scope, dashboardAPIservice) {
	console.log('in approvalReqCtrl controller ', dashboardAPIservice);
});