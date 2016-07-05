mainApp.directive('ajaxLoading', function () {
    return {
        restrict: 'A',
        link: function (scope, elem, attrs) {
            scope.$on("loading:progress", function () {                 
                $('#overlay').show();
            });
            return scope.$on("loading:finish", function () {
                $('#overlay').hide();
            });
        }
    }
});