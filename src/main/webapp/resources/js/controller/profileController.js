var user;

angular.module('profile.controller', []).
	/* Profile controller */
    controller('profileController', function($scope, profileAPIservice) {
      $scope.profile = null;
      profileAPIservice.getProfileDetails().success(function (response) {
          $scope.profile = response; 
    });
});