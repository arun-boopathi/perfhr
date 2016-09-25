angular.module('common.services', []).
  factory('commonAPIservice', function($http) {
    var commonAPI = {};
    commonAPI.loadRecords = function(url) {
        return $http({
          method: 'get',
          url: url
        });
    };    
    commonAPI.add = function(url,data) {
        return $http({
          method: 'post',
          data : data,
          url: url
        });
    };
    commonAPI.updateDel = function(url, data) {
        return $http({
          method: 'put',
          data : data,
          url: url
        });
    };
    return commonAPI;
});