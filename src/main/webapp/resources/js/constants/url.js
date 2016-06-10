var perfUrl = { 
	'validateSession'  : 'user/validateSession',
	'loadAllEmployee'  : urlPrefix+'employee/loadAllEmployee',
	'loadEmployee'     : urlPrefix+'employee/loadEmployee',
	'loadEmployeeById' : urlPrefix+'employee/loadEmployeeById?employeeId=',
	'updateEmployee'   : urlPrefix+'employee/updateEmployee',
	'addEmployee'      : urlPrefix+'employee/addEmployee',
	'loadDesignations' : urlPrefix+'designation/loadDesignations',
	'loadDesignationById' : urlPrefix+'designation/loadDesignationById?id=',
    'addDesignation'   : urlPrefix+'designation/addDesignation',
    'updateDesignation': urlPrefix+'designation/updateDesignation',
    'deleteDesignation' : urlPrefix+'designation/deleteDesignation',
    'loadProjects' : urlPrefix+'projects/loadProjects',
    'addProject'   : urlPrefix+'projects/addProject',
    'updateProject': urlPrefix+'projects/updateProject',
    'deleteProject': urlPrefix+'projects/deleteProject',
    'loadProjectById' : urlPrefix+'projects/loadProjectById?projectPk=',
    'loadProjectMembersByProjectId' : urlPrefix+'projectmembers/loadProjectMembersByProjectId?projectId=',
    'loadAllProjectMembers' : urlPrefix+'projectmembers/loadAllProjectMembers',
    'loadProjectMemberById' : urlPrefix+'projectmembers/loadProjectMemberById?projectMemberId=',
    'saveProjectMember' : urlPrefix+'projectmembers/saveProjectMember',
    'updateProjectMember' : urlPrefix+'projectmembers/updateProjectMember',
    'deleteProjectMember' : urlPrefix+'projectmembers/deleteProjectMember',
    'importPto' : urlPrefix+'leave/fetchExcel',
    'applyLeave' : urlPrefix+'leave/applyLeave',
    'loadAllLeaves' : urlPrefix+'leave/loadAllLeaves/{leaveType}/{calYear}',
    'updateLeave' : urlPrefix+'leave/updateLeave',
    'deleteLeave' : urlPrefix+'leave/deleteLeave',
    'loadLeaveById' : urlPrefix+'leave/loadLeaveById?leaveId=',
    'loadMyLeaves' : urlPrefix+'leave/loadMyLeaves/{leaveType}/{calYear}',
    'getLeaveBalance' : urlPrefix+'leave/getLeaveBalance/{leaveType}/{calYear}',
    'loadLeaveReport': urlPrefix+'leave/loadLeaveReport',
    'loadNotificationCount' : urlPrefix+'notification/loadNotificationCount'
};