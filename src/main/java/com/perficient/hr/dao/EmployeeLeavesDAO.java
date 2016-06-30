package com.perficient.hr.dao;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public boolean parseDocument(String fileName);
	
	public Object loadAllLeaves(String leaveType, String calYear);
	
	public EmployeeLeaves loadLeaveById(String leaveId);
	
	public Object loadMyLeaves(String leaveType, String calYear, String employeeId);
	
	public EmployeeLeaves applyLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean updateLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean deleteLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public Long getLeaveBalance(String leaveType, String calYear, String calMonth, String employeeId, int totalLeaves);
	
	public Object loadLeaveReport(EmployeeLeaves employeeLeaves);
}
