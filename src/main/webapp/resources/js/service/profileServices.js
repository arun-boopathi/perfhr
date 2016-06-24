angular.module('profile.services', []).
  factory('profileAPIservice', function($http) {
    var profileAPI = {};
    profileAPI.getProfileDetails = function() {
        return $http({
          method: 'get',
          url: perfUrl['loadEmployee']
        });
    };
    return profileAPI;
});