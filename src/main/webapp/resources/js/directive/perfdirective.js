var shortErr = ' is too short.', longErr=' is too long.', reqEr=' is required.', invalidErr=' is invalid.';
var errorMsg = '<p class="text-danger"></p>';

function validate(form){
    var errBlock = $(form).find('.help-block');
    $(errBlock).empty();
    $(form).find('div').removeClass('has-error');
    $.each($(form).find(':input[name]'), function(i, ele){
    	var returnVal = true;
        var eleName = $(ele).attr('name');
        var eleType = $(ele).attr('validType');
        var eleValue = '', regEx='';
        if(eleType === 'select'){
            eleValue = $(ele).find(':selected').text();
        } else {
            if(eleType === 'text-only'){
                regEx = /^[a-zA-Z]+$/;
            } else{
                regEx = /^[0-9]+$/;
            }
            eleValue = $(ele).val();
        }
        var isReq = $(ele).attr('required');
        var minLen = $(ele).attr('ng-minlength');
        var maxLen = $(ele).attr('ng-maxlength');
        if((typeof isReq !== typeof undefined && isReq !== false)
                &&($.trim(eleValue).length === 0)){
            $(errBlock).append($(errorMsg).html(eleName+reqEr));
            returnVal = false;
        } else if((typeof minLen !== typeof undefined && minLen !== false)
                &&($.trim(eleValue).length < minLen)){
            $(errBlock).append($(errorMsg).html(eleName+shortErr));
            returnVal = false;
        } else if((typeof isReq !== typeof undefined && isReq !== false)
            &&($.trim(eleValue).length > maxLen)){
            $(errBlock).append($(errorMsg).html(eleName+longErr));
            returnVal = false;
        } else if((typeof regEx !== typeof undefined && regEx !== '')
                && (!regEx.test(eleValue))){
            $(errBlock).append($(errorMsg).html(eleName+invalidErr));
            returnVal = false;
        }
        if(returnVal === false){
            $(ele).parent().addClass('has-error');
        }
    });
    return $(errBlock).is(':empty');
}

mainApp.directive('ajaxLoading', function () {
    return {
        restrict: 'A',
        link: function (scope) {
            scope.$on("loading:progress", function () {
                $('#overlay').show();
            });
            return scope.$on("loading:finish", function () {
                $('#overlay').hide();
            });
        }
    };
}).directive('button', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attr) {
            // On submit emits form:submit
            if(attr.type === 'submit'){
                elem.on('click', function() {
                    if(validate($(elem).parents('form'))){
                        scope[attr.action]();
                    }
                });
            }
        }
    };
});