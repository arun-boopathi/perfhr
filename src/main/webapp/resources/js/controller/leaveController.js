var lc, data, scope;
mainApp.controller('wfhController', function($scope, $controller) {
    var scope = this;
    scope = $scope;
    scope.events = [];
    $scope.title="WFH";
    $scope.leaveType="WFH";
    angular.extend(this, $controller('leaveController', {
        $scope: $scope
    }));
});

mainApp.controller('ptoController', function($scope, $controller) {
    var scope = this;
    scope.events = [];
    $scope.title="PTO";
    $scope.leaveType="PTO";
    $scope.isPto = true;
    angular.extend(this, $controller('leaveController', {
        $scope: $scope
    }));
});

/**
 * AngularJS default filter with the following expression:
 * "person in people | filter: {name: $select.search, age: $select.search}"
 * performs an AND between 'name: $select.search' and 'age: $select.search'.
 * We want to perform an OR.
 */
mainApp.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];
    if (angular.isArray(items)) {
      var keys = Object.keys(props);
      items.forEach(function(item) {
        var itemMatches = false;
        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }
        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      // Let the output be the input untouched
      out = items;
    }
    return out;
  };
});

mainApp.controller('leaveController', function($scope, moment, user, leaveAPIservice, employeeAPIservice, calendarConfig) {
    var obj = this;
    //These variables MUST be set as a minimum for the calendar to work
    obj.calendarView = 'month';
    obj.viewDate = new Date();
    obj.viewChangeEnabled = true;
    obj.checkLeaves = 'all';
    obj.calYear = new Date(obj.viewDate).getFullYear();
    obj.calMonth = new Date(obj.viewDate).getMonth();
    scope = $scope;
    $scope.data = {};
    $scope.employees = [];
    $scope.employeesList;
    $scope.leaveBalance = 0;
    $scope.data.requestType = $scope.leaveType;
    var eventArr = [];
    calendarConfig.displayEventEndTimes = true;
    calendarConfig.templates.calendarSlideBox = 'html/templates/calendarSlideBoxTemplate.html';
    calendarConfig.templates.calendarMonthCell = 'html/templates/calendarMonthCell.html';
    calendarConfig.templates.calendarMonthCellEvents = 'html/templates/calendarMonthCellEvents.html';

    this.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };

    this.timeSpanClicked = function(calendarCell, $event){
        if(calendarCell.events.length > 0){
            lc.dtInstance.DataTable.clear().draw();
            lc.dtInstance.DataTable.rows.add(calendarCell.events).draw();
            $('#leaveList').modal();
        }
    };

    $scope.toggleYear = function(calendarDate){
        if(obj.calYear !== new Date(calendarDate).getFullYear()){
            obj.calYear = new Date(calendarDate).getFullYear();
            $scope.toggleLeave(obj.checkLeaves);
        }
        obj.calMonth = new Date(calendarDate).getMonth();
        $scope.getLeaveBalance();
    };

    $scope.openModal = function(){
        $('#leaveModal').modal();
    };

    $scope.applyLeave = function(){
        $scope.data = {};
        $scope.data.employeeId = user.loggedUser.pk;
        $scope.data.notificationToList = [];
        $scope.data.notificationToList.push($scope.employees[user.loggedUser.pk], $scope.employees[user.loggedUser.supervisor]);
        $scope.data.requestType = $scope.leaveType;
        $scope.data.dtEndHalf = "SECOND";
        $scope.data.dtFromHalf = "FIRST";
        $scope.openModal();
    };

    employeeAPIservice.loadEmployees().success(function(response) {
        $scope.employeesList = response.entity;
        $.each(response.entity, function(i, val){
            $scope.employees[val.pk] = val;
        });
        $scope.toggleLeave(obj.checkLeaves);
        $scope.getLeaveBalance();
    });

    $scope.toggleLeave = function(val){
        obj.checkLeaves = val;
        if(val === 'all'){
            leaveAPIservice.loadAllLeaves($scope.leaveType, obj.calYear).success(function (response) {
                $scope.displayLeave(response.entity);
            });
        } else {
            leaveAPIservice.loadMyLeaves($scope.leaveType, obj.calYear).success(function (response) {
                $scope.displayLeave(response.entity);
            });
        }
    };

    function round(value) {
        return Math.round(value * 2) / 2;
    }

    $scope.getLeaveBalance = function(){
        var month = (obj.calMonth+1);
        leaveAPIservice.getLeaveBalance($scope.leaveType, obj.calYear, month).success(function (response) {
            $scope.leaveBalance = round(((response.entity)/8).toFixed(1))+"/"+round((month*1.67).toFixed(1));
        });
    };

    $scope.displayLeave = function(data){
        $scope.scope.events.splice(0, $scope.scope.events.length);
        $.each(data, function(i, val){
             val.startsAt = new Date(val.startsAt);
             val.endsAt = new Date(val.endsAt);
             val.type = $scope.displayType;
             var notifyListPk = [];
             if(val.notificationToList){
                 $.each(val.notificationToList, function(i, item){
                     notifyListPk.push(item.pk);
                 });
                 val.notificationToList.splice(0, val.notificationToList.length);
                 $.each(notifyListPk, function(i, item){
                     val.notificationToList.push($scope.employees[item]);
                 });
             }
             $scope.scope.events[val.pk] = val;
             eventArr[val.pk] = i;
         });
    };

    $scope.saveLeave = function(){
        $scope.data.requestType = $scope.leaveType;
        $.each($scope.data.notificationToList, function(i, val){
            if(val['_uiSelectChoiceDisabled'] !== undefined){
                delete val['_uiSelectChoiceDisabled'];
            }
        });
        leaveAPIservice.applyLeave($scope.data).success(function (response) {
            response.startsAt = new Date(response.entity.startsAt);
            response.endsAt = new Date(response.entity.endsAt);
            $scope.scope.events[response.pk] = response.entity;
            $scope.msg = $scope.title+" Saved Successfully!";
            $scope.getLeaveBalance();
        }).error(function(){
            $scope.msg="An error occurred during Save!";
        });
    };

    $scope.updateLeave = function(){
        $.each($scope.data.notificationToList, function(i, val){
            if(val['_uiSelectChoiceDisabled'] !== undefined){
                delete val['_uiSelectChoiceDisabled'];
            }
        });
        leaveAPIservice.updateLeave($scope.data).success(function () {
            $.grep($scope.scope.events, function (element, index) {
                if($scope.data.pk === element.pk){
                    $scope.scope.events[index] = $scope.data;
                }
                return element.pk === $scope.data.pk;
            });
            $scope.msg=$scope.title+" Updated Successfully!";
            $scope.getLeaveBalance();
        }).error(function(){
            $scope.msg="An error occurred during Update!";
        });
    };

    $scope.deleteLeave = function(){
        leaveAPIservice.deleteLeave($scope.data).success(function () {
            $.grep($scope.scope.events, function (element, index) {
                if($scope.data.pk === element.pk){
                    $scope.scope.events.splice(index, 1);
                }
                return element.pk === $scope.data.pk;
            });
            $scope.msg=$scope.title+" Deleted Successfully!";
            $scope.getLeaveBalance();
        }).error(function(){
            $scope.msg="An error occurred during Delete!";
        });
    };

    $scope.onDtChange = function(){
        var time9 = 60*1000*60*9;
        var time1_30 = 60*1000*60*13.5;
        var time5_30 = 60*1000*60*17.5;
        if($scope.data.startsAt && $scope.data.dtFromHalf) {
            var stDt = new Date($scope.data.startsAt).toDateString();
            if($scope.data.dtFromHalf === 'FIRST'){
                stDt = new Date(stDt).getTime() + (time9);
            } else {
                stDt = new Date(stDt).getTime() + (time1_30);
            }
            $scope.data.startsAt = new Date(stDt);
        }
        if($scope.data.endsAt && $scope.data.dtEndHalf){
            var endDt = new Date($scope.data.endsAt).toDateString();
            if($scope.data.dtEndHalf === 'FIRST'){
                endDt = new Date(endDt).getTime() + (time1_30);
            } else {
                endDt = new Date(endDt).getTime() + (time5_30);
            }
            $scope.data.endsAt = new Date(endDt);
        }
    };
});

mainApp.controller('leaveControllerTable', leaveControllerTable);

function leaveControllerTable($scope, $compile, DTOptionsBuilder, DTColumnBuilder, leaveAPIservice) {
    lc = this;
    lc.dtColumns = [
        DTColumnBuilder.newColumn('title').withTitle('Title'),
        DTColumnBuilder.newColumn('employeeView').withTitle('Employee Name').renderWith(function(data, type, full) {
            return full.employeeView.firstName+' '+full.employeeView.lastName;
        }),
        DTColumnBuilder.newColumn('startsAt').withTitle('Starts').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        }),
        DTColumnBuilder.newColumn('endsAt').withTitle('Ends').renderWith(function(data) {
            return moment(data).format("DD-MM-YYYY hh:mm A");
        }),
        DTColumnBuilder.newColumn('hours').withTitle('Days').renderWith(function(data) {
            return (data/8);
        })
     ];
     var paramObj = {
         "vm" : lc,
         "scope" : $scope,
         "compile" : $compile,
         "DtOptionsBuilder" : DTOptionsBuilder,
         "DTColumnBuilder" : DTColumnBuilder,
         "service" : leaveAPIservice,
         'editFormId' : 'leaveModal',
         "deleteRow" : false
     };
     perfDatatable.loadTable.init(paramObj);
}