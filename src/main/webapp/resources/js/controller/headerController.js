/*Header Controller*/
mainApp.controller('headerController', function($scope, user, profileAPIservice) {
	profileAPIservice.getProfileDetails().success(function (response) {
		$scope.user = response;
		user.loggedUser = response;
    });
});