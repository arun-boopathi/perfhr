angular.module('profile.controller', []).
	/* Profile controller */
    controller('profileController', function($scope, profileAPIservice) {
      $scope.profile = null;
      profileAPIservice.getProfileDetails().success(function (response) {
    	  console.log('response ', response);
          $scope.profile = response; 
    });
});