var urlPrefix = 'v-';

var perfUrl = {
	'loadAllEmployee' : urlPrefix+'employee/loadAllEmployee',
	'loadEmployee': urlPrefix+'employee/loadEmployee',
	'loadEmployeeById': urlPrefix+'employee/loadEmployeeById?employeeId='
};

var perfHrApp = angular.module('perficientHr');

//register the interceptor as a service
perfHrApp.factory('perfInterceptor', ['$q', function($q, $window) {
  return {
    // optional method
    'request': function(config) {
      // do something on success
    	console.log('config ', config);
      return config;
    },
    // optional method
   'requestError': function(rejection) {
      // do something on error
	   console.log('requestError');
      return $q.reject(rejection);
    },
    // optional method
    'response': function(response) {
    	console.log('response ', response);
      // do something on success
      return response;
    },
    // optional method
   'responseError': function(rejection) {
	   console.log('responseError');
	   if (rejection.status == 401) {
		   window.location.href = "login";
	   }
      // do something on error
      return $q.reject(rejection);
    }
  };
}]);

perfHrApp.config(['$httpProvider', function($httpProvider) { 
	$httpProvider.interceptors.push('perfInterceptor');
}]);

/*// alternatively, register the interceptor via an anonymous factory
$httpProvider.interceptors.push(function($q, dependency1, dependency2) {
  return {
   'request': function(config) {
       // same as above
    },

    'response': function(response) {
       // same as above
    }
  };
});*/



(function(){
	
	var goalApp = angular.module('perficientHr');
	
	goalApp.constant('factoryData',{
		
	});
	
	goalApp.constant('filterNames',{
		revertNewLine: 'revertNewLine',
		splitColon: 'splitColon',
		calculateDayDiff: 'calculateDayDiff'
	});
	
}());