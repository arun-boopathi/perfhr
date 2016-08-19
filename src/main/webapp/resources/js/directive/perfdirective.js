var shortErr = ' is too short.', longErr=' is too long.', reqEr=' is required.', invalidErr=' is invalid.';
var errorMsg = '<p class="text-danger"></p>';

function validateField(ele, eleValue, regEx, errBlock){
    var eleName = $(ele).attr('name'), isReq = $(ele).attr('required'), minLen = $(ele).attr('ng-minlength'), maxLen = $(ele).attr('ng-maxlength');
    var error = '';
    if((typeof isReq !== typeof undefined)
            &&($.trim(eleValue).length === 0)){
    	error = reqEr;
    } else if(eleValue.length > 0){
        if((typeof minLen !== typeof undefined) && $.trim(eleValue).length < minLen){
            error = shortErr;
        } else if((typeof maxLen !== typeof undefined) && $.trim(eleValue).length > maxLen){
        	error = longErr;
        } else if(regEx !== '' && !regEx.test(eleValue)){
        	error = invalidErr;
        }
    }
    if($.trim(error).length !== 0){
    	$(ele).parent().addClass('has-error');
    	$(errorMsg).html(eleName+error).insertAfter($(ele));
    }
}

function validateForm(form){
    var errBlock = $(form).find('.help-block');
    $(errBlock).empty();
    $(form).find('div').removeClass('has-error');
    $(form).find('p.text-danger').remove();
    $.each($(form).find(':input[name]'), function(i, ele){
        var eleType = $(ele).attr('validType');
        var eleValue = '', regEx='';
        if(eleType === 'select'){
            eleValue = $(ele).find(':selected').text();
        } else {
        	eleValue = $(ele).val();
            if(eleType === 'text-only'){
                regEx = /^[a-zA-Z ]*$/;
            } else if(eleType === 'number-only'){
                regEx = /^[0-9]+$/;
            } else if(eleType === 'email'){
                regEx = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
            } else if(eleType === 'date'){
            	eleValue = eleValue.replace(/-/g, "/");
                regEx = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
            } else if(eleType === 'alpha-numeric'){
                regEx = /^[a-zA-Z0-9 ]*$/;
            }
        }
        validateField(ele, eleValue, regEx, errBlock);
    });
    return $(form).find('p.text-danger').length === 0?true:false;
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
            if(attr.type === 'submit'){
                elem.on('click', function() {
                    if(validateForm($(elem).parents('form'))){
                        scope[attr.action]();
                    }
                });
            }
        }
    };
});