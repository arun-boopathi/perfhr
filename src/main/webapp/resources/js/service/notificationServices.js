angular.module('notification.services', []).
  factory('notificationAPIservice', function($http) {
	var notificationAPI = {};
	notificationAPI.loadNotificationCount = function() {
        return $http({
          method: 'get', 
          url: perfUrl['loadNotificationCount']
        });
    };
    
    return notificationAPI; 
});