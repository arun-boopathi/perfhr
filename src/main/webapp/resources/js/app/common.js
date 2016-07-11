var perfHrApp = angular.module('perficientHr');
var lastRequestTime, timeoutHandle;
//register the interceptor as a service
perfHrApp.factory('perfInterceptor', ['$q', '$rootScope', function($q, $rootScope) {
  var loadingCount = 0;
  return {
    'request': function(config) {
        if(++loadingCount === 1)
            $rootScope.$broadcast('loading:progress');
        if(timeoutHandle){
            window.clearTimeout(timeoutHandle);
        }
        perfConcurrentActivity.getInstance().init();
        lastRequestTime = new Date().getTime();
        config.headers = config.headers || {};
        return config;
    },
    'requestError': function(rejection) {
        return $q.reject(rejection);
    },
    'response': function(response) {
        if(--loadingCount === 0)
            $rootScope.$broadcast('loading:finish');
        return response;
    },
    'responseError': function(rejection) {
        if(--loadingCount === 0)
           $rootScope.$broadcast('loading:finish');
        if (rejection.status === 401) {
           window.location.href = "logout";
        }
        return $q.reject(rejection);
    }
  };
}]);

perfHrApp.config(['$httpProvider', function($httpProvider){
    $httpProvider.interceptors.push('perfInterceptor');
}]);

function perfConcurrentActivity(){};

perfConcurrentActivity.getInstance = function(){
    var obj = PerfWidgetCache['perfIns'];
    if(!obj)
        obj = PerfWidgetCache['perfIns'] = new perfConcurrentActivity();
    return obj;
};

perfConcurrentActivity.prototype = {
    init: function(){
        if((new Date().getTime()-lastRequestTime)/(1000*60) > 30){
            window.location.href = "logout";
        } else {
            timeoutHandle = window.setTimeout('perfConcurrentActivity.getInstance().init()', 10000);
        }
    }
};