/*Dashboard Controller*/
mainApp.controller('dashboardController', function($scope, dashboardAPIservice) {
	dashboardAPIservice.getVersion().success(function (response) {
        $('#version').html('Version: '+response.entity);
    });
}).controller('approvalReqCtrl', function() {
});