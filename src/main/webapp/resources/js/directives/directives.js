(function(){
	var perficientHr = angular.module("perficientHr");
	
	
	/**
	 * Date Picker Directive - Employee PTO Section.
	 * 
	 */
	angular.module('perficientHr').directive('ptoCalendar', function () {
	    return {
	        restrict: 'A',
	        require: 'ngModel',
	         link: function (scope, element, attrs) {
	        	 scope.$watch("ptosTaken", function (newValue) {
	      		   if(undefined!=newValue){
	      			 element.datepicker({
			                beforeShowDay: function(date){
				                var Highlight = $.grep(scope.ptosTaken, function(n, i){
				                		if(date.toString() == n.toString()){
				                			return n;
				                		}
				                	});
				                if (Highlight.length >0)
				                return [true, "ptosTaken", Highlight];
				                else
				                 return [true, '', ''];
			                },
			                dateFormat: 'DD, d  MM, yy',
			                firstDay: 1, 
			                numberOfMonths: 12,
			                minDate: new Date(2013, 0, 1),
			                maxDate: new Date(),
			                numberOfMonths: [2,6],
			                onSelect: function (date) {
			                    scope.date = date;
			                    scope.$apply();
			                }
			            });
	      		   }
	      		 
	      	      }, true);
	        }
	    };
	});
	
}());