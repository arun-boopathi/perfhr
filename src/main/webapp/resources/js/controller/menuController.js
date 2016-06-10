/*Menu Controller*/
mainApp.controller('menuController', function($scope, user, notificationAPIservice) {
	notificationAPIservice.loadNotificationCount().success(function (response) {
		if(response > 0)
			$scope.notificationCount = response;
    });
});