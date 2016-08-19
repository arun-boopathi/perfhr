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
        perfUtils.getInstance().init();
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

/*
 * Reset the form whenever its closed.
 */
$(document).on('hidden.bs.modal', 'div[role="dialog"]', function () {
	var formId=$(this).attr('id');
	$('#'+formId+' .help-block').empty();
	$('#'+formId+' p.text-danger').remove();
	$('#'+formId+' .has-error').removeClass('has-error');
	$("#"+formId).find('input:text, input:password, input:file, select, textarea').val('');
    $("#"+formId).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
});

function perfUtils(){};

perfUtils.getInstance = function(){
    var obj = PerfWidgetCache['perfIns'];
    if(!obj)
        obj = PerfWidgetCache['perfIns'] = new perfUtils();
    return obj;
};

perfUtils.prototype = {
    init: function(){
        if((new Date().getTime()-lastRequestTime)/(1000*60) > 30){
            window.location.href = "logout";
        } else {
            timeoutHandle = window.setTimeout('perfUtils.getInstance().init()', 10000);
        }
    },
    resetForm: function(formId){
    	$('#'+formId+' .help-block').empty();
        $('#'+formId+' div').removeClass('has-error');
    }
};