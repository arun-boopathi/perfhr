package com.perficient.hr.service;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesService {

	public Object parseDocument(String fileName);
	
	public Object loadAllLeaves(String leaveType, String calYear);
	
	public Object loadLeaveById(String leaveId);
	
	public Object loadMyLeaves(String leaveType, String calYear, String employeeId);
	
	public Object applyLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public Object updateLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public Object deleteLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public Object getLeaveBalance(String leaveType, String calYear, String calMonth, String employeeId, int totalLeaves);
	
	public Object loadLeaveReport(EmployeeLeaves employeeLeaves);
}
