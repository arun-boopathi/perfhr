var user;

angular.module('profile.controller', []).
	/* Profile controller */
    controller('profileController', function($scope, profileAPIservice) {
      $scope.profile = null;
      profileAPIservice.getProfileDetails().success(function (response) {
    	  console.log('response ', response);
    	  user = response;
    	  $('#welcomeMsg').html('Welcome '+response.lastName+', '+response.firstName);
          $scope.profile = response; 
    });
});