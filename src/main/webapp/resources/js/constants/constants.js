var perfHrApp = angular.module('perficientHr');

var urlPrefix = 'v-';
var PerfWidgetCache = [];
var lastRequestTime = new Date().getTime();
var timeoutHandle;

var perfUrl = { 
	'validateSession'  : 'user/validateSession',
	'loadAllEmployee'  : urlPrefix+'employee/loadAllEmployee',
	'loadEmployee'     : urlPrefix+'employee/loadEmployee',
	'loadEmployeeById' : urlPrefix+'employee/loadEmployeeById?employeeId=',
	'updateEmployee'   : urlPrefix+'employee/updateEmployee',
	'addEmployee'      : urlPrefix+'employee/addEmployee',
	'loadDesignations' : urlPrefix+'designation/loadDesignations',
    'addDesignation'   : urlPrefix+'designation/addDesignation',
    'updateDesignation': urlPrefix+'designation/updateDesignation',
    'loadProjects' : urlPrefix+'projects/loadProjects',
    'addProject'   : urlPrefix+'projects/addProject',
    'updateProject': urlPrefix+'projects/updateProject',
    'loadProjectById' : urlPrefix+'projects/loadProjectById?projectPk=',
    'loadProjectMembersByProjectId' : urlPrefix+'projectmembers/loadProjectMembersByProjectId?projectId=',
    'importPto' : urlPrefix+'pto/fetchExcel',
    'applyWfh' : urlPrefix+'pto/applyWfh',
    'loadAllWfh' : urlPrefix+'pto/loadAllWfh',
    'updateWfh' : urlPrefix+'pto/updateWfh',
    'deleteWfh' : urlPrefix+'pto/deleteWfh'
};

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