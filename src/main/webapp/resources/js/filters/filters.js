(function(){
	angular.module("perficientHr").filter('calculateDayDiff', function(){
		return(function(fromDate, toDate){
			var oneDay = 24*60*60*1000;
			var fromDte = new Date(fromDate);
			var toDte = new Date(toDate);
			var valueSign = (fromDte.getTime()> toDte.getTime())?-1:1;
			return Math.round(Math.abs((fromDte.getTime() - toDte.getTime())/(oneDay))*valueSign);
		});
	});
}());