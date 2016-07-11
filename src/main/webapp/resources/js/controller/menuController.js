/*Menu Controller*/
mainApp.controller('menuController', function($scope, user, notificationAPIservice) {
    notificationAPIservice.loadNotificationCount().success(function (response) {
        if(response.entity > 0)
            $scope.notificationCount = response.entity;
    });
});