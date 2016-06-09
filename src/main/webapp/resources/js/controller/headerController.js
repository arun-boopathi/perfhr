/*Header Controller*/
mainApp.controller('headerController', function($scope, user, profileAPIservice) {
	console.log('in header', user.loggedUser);
	//$scope.user = user;
	profileAPIservice.getProfileDetails().success(function (response) {
		$scope.user = response;
    });
	//console.log('in header', $scope.user);
});