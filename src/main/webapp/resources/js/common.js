var perfHrApp = angular.module('perficientHr');

//register the interceptor as a service
perfHrApp.factory('perfInterceptor', ['$q', function($q, $window) {
  return {
    'request': function(config) {
    	$('#overlay').show();
    	if(timeoutHandle){
    		window.clearTimeout(timeoutHandle);
    	}
    	PerfConcurrentActivity.getInstance().init();
    	lastRequestTime = new Date().getTime();

    	config.headers = config.headers || {};
        return config;
    },
   'requestError': function(rejection) {
        return $q.reject(rejection);
    },
    'response': function(response) {
    	$('#overlay').hide();
        return response;
    },
   'responseError': function(rejection) {
	    if (rejection.status == 401) {
		   window.location.href = "logout";
	    }
        return $q.reject(rejection);
    }
  };
}]);

perfHrApp.config(['$httpProvider', function($httpProvider) { 
	$httpProvider.interceptors.push('perfInterceptor');
}]);

function PerfConcurrentActivity(){
	
};

PerfConcurrentActivity.getInstance = function(){
	var obj = PerfWidgetCache['perfIns'];
	if(!obj)
		obj = PerfWidgetCache['perfIns'] = new PerfConcurrentActivity();
	return obj;
};

PerfConcurrentActivity.prototype = {
	init: function() { 
		if((new Date().getTime()-lastRequestTime)/(1000*60) > 30){
			window.location.href = "logout";
		} else {
			timeoutHandle = window.setTimeout('PerfConcurrentActivity.getInstance().init()', 10000);	
		}
	}
};