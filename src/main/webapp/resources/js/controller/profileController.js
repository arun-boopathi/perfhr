var user;

angular.module('profile.controller', []).
	/* Profile controller */
    controller('profileController', function($scope, profileAPIservice) {
      $scope.data = null;
      profileAPIservice.getProfileDetails().success(function (response) {
          $scope.data = response; 
    });
});