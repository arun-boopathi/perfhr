var perfHrApp = angular.module('perficientHr');

var urlPrefix = 'v-';
var PerfWidgetCache = [];
var lastRequestTime = new Date().getTime();
var timeoutHandle;

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