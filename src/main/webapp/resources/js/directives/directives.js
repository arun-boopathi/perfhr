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
	
	angular.module('perficientHr').directive('datepicker', function () {
		return {
	        restrict: "A",
	        link: function (scope, el, attr) {
	            el.datepicker({
	                 dateFormat: 'yy-mm-dd'
	            });
	        }
	    };
	});
	
	angular.module("perficientHr").directive('addRows', function($templateRequest,$compile){
		return(
			function(scope,element,attrs){
				element.bind("click", function(){
					 scope.index++;
					 $templateRequest("js/templates/addRows.html").then(function(html){											
					      var template = angular.element(html);
						  $(template).find("input").attr("ng-model", $(template).find("input").attr("ng-model")+scope.index)
					      angular.element($("#"+element.attr('id')).parents().eq(5)).append(template);
					      $compile(template)(scope);
					   });
				});
		} )
	});
	
	angular.module('perficientHr').directive('ptoCalendar', function () {
		
	});
	
	angular.module('perficientHr').directive('fileModel', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attrs) {
	            element.bind('change', function(){
	                    scope.newFileVal=element[0].files[0];
	            });
	        }
	    };
	});
	
}());